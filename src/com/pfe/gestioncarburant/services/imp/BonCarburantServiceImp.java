package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.BonCarburantDao;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.BonCarburantService;

@Service
public class BonCarburantServiceImp implements BonCarburantService {
	@Autowired
	private BonCarburantDao bonCarburantDao;

	@Override
	public boolean save(BonCarburant bonCarburant) throws Exception {

		Criterion crit = Restrictions.eq("litre", bonCarburant.getLitre());
		Criterion crit2 = Restrictions.eq("typeCarburant", bonCarburant.getTypeCarburant());
		List<BonCarburant> list = bonCarburantDao.findByCriteria(BonCarburant.class, crit, crit2);

		if (list.isEmpty()) {
			bonCarburantDao.saveOrUpdate(bonCarburant);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(BonCarburant bonCarburant) throws Exception {

		Criterion crit = Restrictions.eq("litre", bonCarburant.getLitre());
		Criterion crit2 = Restrictions.eq("typeCarburant", bonCarburant.getTypeCarburant());
		Criterion crit3 = Restrictions.idEq(bonCarburant.getId());
		List<BonCarburant> list = bonCarburantDao.findByCriteria(BonCarburant.class, crit, crit2, crit3);

		if (!list.isEmpty()) {
			bonCarburantDao.saveOrUpdate(bonCarburant);
			return true;

		} else {
			list = bonCarburantDao.findByCriteria(BonCarburant.class, crit, crit2);

			if (list.isEmpty()) {
				bonCarburantDao.saveOrUpdate(bonCarburant);
				return true;

			}
		}

		return false;

	}

	@Override
	public void delete(BonCarburant bonCarburant) throws Exception {
		bonCarburantDao.delete(bonCarburant);
	}

	@Override
	public List<BonCarburant> findAll() throws Exception {
		return bonCarburantDao.findAll(BonCarburant.class);
	}

	public BonCarburantDao getBonCarburantDao() {
		return bonCarburantDao;
	}

	public void setBonCarburantDao(BonCarburantDao bonCarburantDao) {
		this.bonCarburantDao = bonCarburantDao;
	}

	@Override
	public List<BonCarburant> findBonCarburantByVoiture(Voiture voiture) throws Exception {
		Criterion crit = Restrictions.eq("typeCarburant", voiture.getTypeCarburant());
		// TODO Auto-generated method stub
		return bonCarburantDao.findByCriteria(BonCarburant.class, crit);
	}

}
