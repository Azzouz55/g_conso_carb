package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.MarqueDao;
import com.pfe.gestioncarburant.entities.Marque;
import com.pfe.gestioncarburant.services.MarqueService;

@Service
public class MarqueServiceImp implements MarqueService {
	@Autowired
	private MarqueDao marqueDao;

	@Override
	public boolean save(Marque marque) throws Exception {

		Criterion crit = Restrictions.eq("libelle", marque.getLibelle());
		List<Marque> list = marqueDao.findByCriteria(Marque.class, crit);

		if (list.isEmpty()) {
			marqueDao.saveOrUpdate(marque);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(Marque marque) throws Exception {
		Criterion crit = Restrictions.eq("libelle", marque.getLibelle());
		Criterion crit2 = Restrictions.idEq(marque.getId());
		List<Marque> list = marqueDao.findByCriteria(Marque.class, crit, crit2);

		if (!list.isEmpty()) {
			marqueDao.saveOrUpdate(marque);
			return true;
		}
		list = marqueDao.findByCriteria(Marque.class, crit);
		if (list.isEmpty()) {
			marqueDao.saveOrUpdate(marque);
			return true;
		}
		return false;
	}

	@Override
	public void delete(Marque marque) throws Exception {
		marqueDao.delete(marque);
	}

	@Override
	public List<Marque> findAll() throws Exception {
		// TODO Auto-generated method stub
		return marqueDao.findAll(Marque.class);
	}
}
