package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.CategorieDao;
import com.pfe.gestioncarburant.entities.Categorie;
import com.pfe.gestioncarburant.services.CategorieService;

@Service
public class CategorieServiceImp implements CategorieService {
	@Autowired
	private CategorieDao categorieDao;

	@Override
	public boolean save(Categorie categorie) throws Exception {

		Criterion crit = Restrictions.eq("libelle", categorie.getLibelle());
		List<Categorie> list = categorieDao.findByCriteria(Categorie.class, crit);

		if (list.isEmpty()) {
			categorieDao.saveOrUpdate(categorie);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(Categorie categorie) throws Exception {
		Criterion crit = Restrictions.eq("libelle", categorie.getLibelle());
		Criterion crit2 = Restrictions.idEq(categorie.getId());
		List<Categorie> list = categorieDao.findByCriteria(Categorie.class, crit, crit2);

		if (!list.isEmpty()) {
			categorieDao.saveOrUpdate(categorie);
			return true;
		}
		list = categorieDao.findByCriteria(Categorie.class, crit);

		if (list.isEmpty()) {
			categorieDao.saveOrUpdate(categorie);
			return true;
		}
		return false;
	}

	@Override
	public void delete(Categorie categorie) throws Exception {

		categorieDao.delete(categorie);

	}

	@Override
	public List<Categorie> findAll() throws Exception {
		return categorieDao.findAll(Categorie.class);
	}

}
