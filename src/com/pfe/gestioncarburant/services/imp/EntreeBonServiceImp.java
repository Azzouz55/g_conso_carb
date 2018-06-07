package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.BonCarburantDao;
import com.pfe.gestioncarburant.dao.EntreeBonDao;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.EntreeBon;
import com.pfe.gestioncarburant.services.EntreeBonService;

@Service
public class EntreeBonServiceImp implements EntreeBonService {
	@Autowired
	private EntreeBonDao entreeBonDao;

	@Autowired
	private BonCarburantDao bonCarburantDao;

	@Override
	public boolean save(EntreeBon entreeBon) throws Exception {
		entreeBonDao.saveOrUpdate(entreeBon);
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(entreeBon.getBonCarburant().getId())).get(0);
		bonCarburant.setQte(bonCarburant.getQte() + entreeBon.getQte());
		bonCarburantDao.saveOrUpdate(bonCarburant);
		return true;

	}

	@Override
	public boolean update(EntreeBon entreeBon) throws Exception {

		EntreeBon old = (EntreeBon) entreeBonDao.findByCriteria(EntreeBon.class, Restrictions.idEq(entreeBon.getId()))
				.get(0);
		int qte = 0;
		qte = old.getQte() - entreeBon.getQte();

		entreeBonDao.saveOrUpdate(entreeBon);
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(entreeBon.getBonCarburant().getId())).get(0);
		bonCarburant.setQte(bonCarburant.getQte() - qte);
		bonCarburantDao.saveOrUpdate(bonCarburant);
		return true;

	}

	@Override
	public void delete(EntreeBon entreBon) throws Exception {
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(entreBon.getBonCarburant().getId())).get(0);
		bonCarburant.setQte(bonCarburant.getQte() - entreBon.getQte());
		bonCarburantDao.saveOrUpdate(bonCarburant);
		entreeBonDao.delete(entreBon);
	}

	@Override
	public List<EntreeBon> findAll() throws Exception {
		return entreeBonDao.findAll(EntreeBon.class);
	}
}
