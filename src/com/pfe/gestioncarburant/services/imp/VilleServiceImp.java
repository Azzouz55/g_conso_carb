package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.CoordonneeDao;
import com.pfe.gestioncarburant.dao.VilleDao;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Ville;
import com.pfe.gestioncarburant.services.VilleService;

@Service
public class VilleServiceImp implements VilleService {

	@Autowired
	private VilleDao villeDao;
	@Autowired
	private CoordonneeDao coordonneeDao;

	@Override
	public boolean save(Ville ville) throws Exception {

		Criterion crit = Restrictions.eq("libelle", ville.getLibelle());
		List<Ville> list = villeDao.findByCriteria(Ville.class, crit);

		if (list.isEmpty()) {
			coordonneeDao.save(ville.getCoordonnee());
			villeDao.saveOrUpdate(ville);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(Ville ville) throws Exception {
		Criterion crit = Restrictions.eq("libelle", ville.getLibelle());
		Criterion crit2 = Restrictions.idEq(ville.getId());
		List<Ville> list = villeDao.findByCriteria(Ville.class, crit, crit2);

		if (!list.isEmpty()) {
			coordonneeDao.save(ville.getCoordonnee());
			villeDao.saveOrUpdate(ville);
			return true;

		}
		list = villeDao.findByCriteria(Ville.class, crit);

		if (list.isEmpty()) {
			villeDao.saveOrUpdate(ville);
			return true;
		}
		return false;
	}

	@Override
	public void delete(Ville ville) throws Exception {

		villeDao.delete(ville);

	}

	@Override
	public List<Ville> findAll() throws Exception {
		// TODO Auto-generated method stub
		return villeDao.findAll(Ville.class);
	}

	public VilleDao getVilleDao() {
		return villeDao;
	}

	public void setVilleDao(VilleDao villeDao) {
		this.villeDao = villeDao;
	}

	@Override
	public Ville findVilleByCadre(Cadre cadre) throws Exception {
		Criterion crit = Restrictions.eq("cadre", cadre);
		return (Ville) villeDao.findByCriteria(Ville.class, crit).get(0);
	}

}
