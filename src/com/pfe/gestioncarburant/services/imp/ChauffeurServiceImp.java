package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.apache.commons.validator.EmailValidator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.ChauffeurDao;
import com.pfe.gestioncarburant.dao.imp.ChauffeurDaoImp;
import com.pfe.gestioncarburant.entities.Chauffeur;
import com.pfe.gestioncarburant.entities.Employee;
import com.pfe.gestioncarburant.services.ChauffeurService;
@Service
public class ChauffeurServiceImp implements ChauffeurService {

	@Autowired
	private ChauffeurDao chauffeurDao;

	@Override
	public String[] save(Chauffeur chauffeur) throws Exception {
		
		
		String [] res = new String[2];
//		Si longeur num CIN invalide
		
		if (chauffeur.getEmployee().getCin().length() != 8) {
			res[0] = "0";
			res[1] = "CIN Invalide";
			return res;
		}
//		Si format email invalide
		if (chauffeur.getEmployee().getEmail() == null || !EmailValidator.getInstance().isValid(chauffeur.getEmployee().getEmail())){
			res[0] = "0";
			res[1] = "E-Mail Invalide";
			return res;
		}
//		Si numéro tel invalide
		if (chauffeur.getEmployee().getTel() == null || chauffeur.getEmployee().getTel().length() !=8){
			res[0] = "0";
			res[1] = "Numéro de téléphoe Invalide";
			return res;
		}

		Criterion crit = Restrictions.idEq(chauffeur.getEmployee().getMatricule());
		List<Chauffeur> list = chauffeurDao.findByCriteria(Chauffeur.class, crit);

		if (!list.isEmpty()) {
			res[0] = "0";
			res[1] = "Matricule existant";
			return res;
		}
		crit = Restrictions.eq("cin", chauffeur.getEmployee().getCin());
		list = chauffeurDao.findByCriteria(Employee.class, crit);
		if (!list.isEmpty()) {
			res[0] = "0";
			res[1] = "CIN existant";
			return res;
		}

		crit = Restrictions.eq("email", chauffeur.getEmployee().getEmail());
		list = chauffeurDao.findByCriteria(Employee.class, crit);
		if (!list.isEmpty()) {
			res[0] = "0";
			res[1] = "E-mail existant";
			return res;
		}

		chauffeurDao.saveOrUpdate(chauffeur.getEmployee());
		chauffeurDao.saveOrUpdate(chauffeur);
		res[0] = "1";
		res[1] = "Ajout effectué";
		return res;
	}

	@Override
	public String[] update(Chauffeur chauffeur) throws Exception {
		
		String[] res = new String[2];
//		Si longeur num CIN invalide
		
		if (chauffeur.getEmployee().getCin().length() != 8) {
			res[0] = "0";
			res[1] = "CIN Invalide";
			return res;
		}
//		Si format email invalide
		if (chauffeur.getEmployee().getEmail() == null || !EmailValidator.getInstance().isValid(chauffeur.getEmployee().getEmail())){
			res[0] = "0";
			res[1] = "E-Mail Invalide";
			return res;
		}
//		Si numéro tel invalide
		if (chauffeur.getEmployee().getTel() == null || chauffeur.getEmployee().getTel().length() !=8){
			res[0] = "0";
			res[1] = "Numéro de téléphoe Invalide";
			return res;
		}

		Criterion crit1 = Restrictions.idEq(chauffeur.getEmployee().getMatricule());
		Criterion crit2 = Restrictions.eq("cin", chauffeur.getEmployee().getCin());
		Criterion crit3 = Restrictions.eq("email", chauffeur.getEmployee().getEmail());
		Criterion crit = Restrictions.not(crit1);
		List<Chauffeur> list = chauffeurDao.findByCriteria(Employee.class, crit1, crit2, crit3);

		if (list.isEmpty()) {

			list = chauffeurDao.findByCriteria(Employee.class, crit, crit2);
			if (!list.isEmpty()) {
				res[0] = "1";
				res[1] = "CIN existant";
				return res;
			}

			list = chauffeurDao.findByCriteria(Employee.class, crit, crit3);
			if (!list.isEmpty()) {
				res[0] = "1";
				res[1] = "E-mail existant";
				return res;
			}

		}
		chauffeurDao.saveOrUpdate(chauffeur.getEmployee());
		chauffeurDao.saveOrUpdate(chauffeur);
		res[0] = "1";
		res[1] = "Modification effectué";
		return res;

	}

	@Override
	public void delete(Chauffeur chauffeur) throws Exception {
		// TODO Auto-generated method stub
		chauffeurDao.delete(chauffeur.getEmployee());
		
	}

	@Override
	public List<Chauffeur> findAll() throws Exception {
		// TODO Auto-generated method stub
		return chauffeurDao.findAll(Chauffeur.class);
	}

	public ChauffeurDao getChauffeurDao() {
		return chauffeurDao;
	}

	public void setChauffeurDao(ChauffeurDao chauffeurDao) {
		this.chauffeurDao = chauffeurDao;
	}
	
	

}
