package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.DepartementDao;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Departement;
import com.pfe.gestioncarburant.entities.Ville;
import com.pfe.gestioncarburant.services.DepartementService;

@Service
public class DepartementServiceImp implements DepartementService {

	@Autowired
	private DepartementDao departementDao;

	@Override
	public boolean save(Departement departement) throws Exception {

		Criterion crit = Restrictions.eq("libelle", departement.getLibelle());
		List<Departement> list = departementDao.findByCriteria(Departement.class, crit);

		if (list.isEmpty()) {
			departementDao.saveOrUpdate(departement);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(Departement departement) throws Exception {
		Criterion crit = Restrictions.eq("libelle", departement.getLibelle());
		Criterion crit2 = Restrictions.idEq(departement.getId());
		List<Departement> list = departementDao.findByCriteria(Departement.class, crit, crit2);

		if (!list.isEmpty()) {
			departementDao.saveOrUpdate(departement);
			return true;
		}

		list = departementDao.findByCriteria(Departement.class, crit);

		if (list.isEmpty()) {
			departementDao.saveOrUpdate(departement);
			return true;
		}
		return false;
	}

	@Override
	public void delete(Departement departement) throws Exception {

		departementDao.delete(departement);

	}

	@Override
	public List<Departement> findAll() throws Exception {
		// TODO Auto-generated method stub
		return departementDao.findAll(Departement.class);
	}

	@Override
	public List<Departement> findByVille(Ville ville) throws Exception {

		Criterion crit = Restrictions.eq("ville", ville);
		return departementDao.findByCriteria(Departement.class, crit);
	}

	@Override
	public Departement findByCadre(Cadre cadre) throws Exception {
		Criterion crit = Restrictions.eq("cadre", cadre);
		return (Departement) departementDao.findByCriteria(Departement.class, crit).get(0);
	}

	@Override
	public Departement getDepartement(Departement departement) throws Exception {
		Criterion crit = Restrictions.idEq(departement.getId());
		return (Departement) departementDao.findByCriteria(Departement.class, crit).get(0);
	}

}
