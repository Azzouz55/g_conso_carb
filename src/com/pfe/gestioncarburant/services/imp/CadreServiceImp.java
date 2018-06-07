package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.apache.commons.validator.EmailValidator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.CadreDao;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Employee;
import com.pfe.gestioncarburant.services.CadreService;

@SuppressWarnings("deprecation")
@Service
public class CadreServiceImp implements CadreService {

	@Autowired
	private CadreDao cadreDao;

	// Retour null si les infos correcte
	public String[] isValid(Cadre cadre) {
		String[] res = new String[2];
		// Si longeur num CIN invalide
		if (cadre.getEmployee().getCin().length() != 8) {
			res[0] = "0";
			res[1] = "CIN Invalide";
			return res;
		}
		// Si format email invalide
		if (cadre.getEmployee().getEmail() == null
				|| !EmailValidator.getInstance().isValid(cadre.getEmployee().getEmail())) {
			res[0] = "0";
			res[1] = "E-Mail Invalide";
			return res;
		}
		// Si numéro tel invalide
		if (cadre.getEmployee().getTel() == null || cadre.getEmployee().getTel().length() != 8) {
			res[0] = "0";
			res[1] = "Numéro de téléphoe Invalide";
			return res;
		}
		return null;

	}

	@Override
	public String[] save(Cadre cadre) throws Exception {
		String[] result = new String[2];
		result = isValid(cadre);
		if (result != null) {
			return result;
		}
		result = new String[2];

		// Si cadre existe
		Criterion crit = Restrictions.idEq(cadre.getEmployee().getMatricule());
		List<Cadre> list = cadreDao.findByCriteria(Cadre.class, crit);
		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "Matricule existant";
			return result;
		}
		// Si matricule existe
		crit = Restrictions.eq("cin", cadre.getEmployee().getCin());
		list = cadreDao.findByCriteria(Employee.class, crit);
		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "CIN existant";
			return result;

		}
		// Si mail existe
		crit = Restrictions.eq("email", cadre.getEmployee().getEmail());
		list = cadreDao.findByCriteria(Employee.class, crit);
		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "E-mail existant";
			return result;

		}
		cadreDao.save(cadre.getEmployee());
		cadreDao.save(cadre);
		result[0] = "1";
		result[1] = "Ajout effectué";
		return result;

	}

	@Override
	public String[] update(Cadre cadre) throws Exception {
		String[] result = new String[2];
		result = isValid(cadre);
		if (result != null) {
			return result;
		}
		result = new String[2];
		Criterion crit1 = Restrictions.idEq(cadre.getEmployee().getMatricule());
		Criterion crit = Restrictions.not(crit1);
		Criterion crit2 = Restrictions.eq("cin", cadre.getEmployee().getCin());
		Criterion crit3 = Restrictions.eq("email", cadre.getEmployee().getEmail());
		List<Cadre> list = cadreDao.findByCriteria(Employee.class, crit1, crit2, crit3);

		if (list.isEmpty()) {
			// Si num cin existe
			list = cadreDao.findByCriteria(Employee.class, crit, crit2);
			if (!list.isEmpty()) {
				result[0] = "1";
				result[1] = "CIN existant";
				return result;
			}
			// Si mail existe
			list = cadreDao.findByCriteria(Employee.class, crit, crit3);
			if (list.size() == 1) {
				result[0] = "1";
				result[1] = "E-mail existant";
				return result;
			}

		}
		cadreDao.saveOrUpdate(cadre.getEmployee());
		cadreDao.saveOrUpdate(cadre);
		result[0] = "1";
		result[1] = "Modification effectué";
		return result;

	}

	@Override
	public void delete(Cadre cadre) throws Exception {
		cadreDao.delete(cadre);
		cadreDao.delete(cadre.getEmployee());
	}

	@Override
	public List<Cadre> findAll() throws Exception {
		return cadreDao.findAll(Cadre.class);
	}

	@Override
	public Cadre getCadre(Cadre cadre) throws Exception {
		Criterion crit = Restrictions.idEq(cadre.getMatricule());
		return (Cadre) cadreDao.findByCriteria(Cadre.class, crit).get(0);

	}
}
