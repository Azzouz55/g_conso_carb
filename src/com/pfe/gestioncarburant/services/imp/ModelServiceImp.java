package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.ModelDao;
import com.pfe.gestioncarburant.entities.Marque;
import com.pfe.gestioncarburant.entities.Model;
import com.pfe.gestioncarburant.services.ModelService;

@Service
public class ModelServiceImp implements ModelService {
	@Autowired
	private ModelDao modeleDao;

	@Override
	public boolean save(Model model) throws Exception {

		Criterion crit = Restrictions.eq("libelle", model.getLibelle());
		List<Model> list = modeleDao.findByCriteria(Model.class, crit);

		if (list.isEmpty()) {
			modeleDao.saveOrUpdate(model);
			return true;
		}
		list = modeleDao.findByCriteria(Model.class, crit);

		if (list.isEmpty()) {
			modeleDao.saveOrUpdate(model);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Model modele) throws Exception {
		Criterion crit = Restrictions.eq("libelle", modele.getLibelle());
		Criterion crit2 = Restrictions.idEq(modele.getId());
		List<Model> list = modeleDao.findByCriteria(Model.class, crit, crit2);

		if (!list.isEmpty()) {
			modeleDao.saveOrUpdate(modele);
			return true;
		}
		return false;
	}

	@Override
	public void delete(Model modele) throws Exception {
		modeleDao.delete(modele);
	}

	@Override
	public List<Model> findAll() throws Exception {
		return modeleDao.findAll(Model.class);
	}

	@Override
	public List<Model> findByMarque(Marque marque) throws Exception {
		Criterion crit = Restrictions.eq("marque", marque);
		return modeleDao.findByCriteria(Model.class, crit);
	}
}
