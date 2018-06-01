package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.BonCadreDao;
import com.pfe.gestioncarburant.dao.BonCarburantDao;
import com.pfe.gestioncarburant.entities.BonCadre;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.services.BonCadreService;

@Service
public class BonCadreServiceImp implements BonCadreService {
	@Autowired
	private BonCadreDao bonCadreDao;
	@Autowired
	private BonCarburantDao bonCarburantDao;

	@Override
	public String[] save(BonCadre bonCadre) throws Exception {
		String[] result = new String[2];
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonCadre.getId().getIdBonCarburant())).get(0);
		// Si la valeur de quantit� est 0
		if (bonCadre.getQte() == 0) {
			result[0] = "0";
			result[1] = "Quantit� doit �tre sup�rieur � 0!";
			return result;
		}
		// Si stock est vide
		if (bonCarburant.getQte() == 0) {
			result[0] = "0";
			result[1] = "Stock vide!";
			return result;
		}
		// Si la quantit� � ajouter d�passe le quantit� en stock
		if (bonCarburant.getQte() < bonCadre.getQte()) {
			result[0] = "0";
			result[1] = "Stock Insuffisant, Il reste :" + bonCarburant.getQte() + " bon(s)";
			return result;
		}

		bonCarburant.setQte(bonCarburant.getQte() - bonCadre.getQte());
		bonCarburantDao.saveOrUpdate(bonCarburant);
		bonCadreDao.saveOrUpdate(bonCadre);

		result[0] = "1";
		result[1] = "Affectation effectu�";
		return result;

	}

	@Override
	public String[] update(BonCadre bonCadre) throws Exception {
		String[] result = new String[2];
		BonCadre old = (BonCadre) bonCadreDao.findByCriteria(BonCadre.class, Restrictions.idEq(bonCadre.getId()))
				.get(0);
		int qte = 0;
		qte = old.getQte() - bonCadre.getQte();
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonCadre.getId().getIdBonCarburant())).get(0);
		if (bonCarburant.getQte() < qte) {
			result[0] = "0";
			result[1] = "La quantit�e � modifier d�passe la quantit� en stock!";
			return result;

		}
		bonCadreDao.saveOrUpdate(bonCadre);
		bonCarburant.setQte(bonCarburant.getQte() + qte);
		bonCarburantDao.saveOrUpdate(bonCarburant);
		result[0] = "1";
		result[1] = "Modification effectu�";
		return result;

	}

	@Override
	public void delete(BonCadre bonCadre) throws Exception {
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonCadre.getId().getIdBonCarburant())).get(0);
		bonCarburant.setQte(bonCarburant.getQte() + bonCadre.getQte());
		bonCadreDao.delete(bonCadre);
		bonCarburantDao.saveOrUpdate(bonCarburant);
	}

	@Override
	public List<BonCadre> findBonCadreByCadre(Cadre cadre) throws Exception {
		Criterion crit = Restrictions.eq("cadre", cadre);
		return bonCadreDao.findByCriteria(BonCadre.class, crit);
	}

	public BonCadreDao getBonCadreDao() {
		return bonCadreDao;
	}

	public void setBonCadreDao(BonCadreDao bonCadreDao) {
		this.bonCadreDao = bonCadreDao;
	}

}