package com.pfe.gestioncarburant.services.imp;

import java.sql.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.AffectationDao;
import com.pfe.gestioncarburant.entities.Affectation;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.services.AffectationVoitureService;

@Service
public class AffectationVoitureServiceImp implements AffectationVoitureService {

	@Autowired
	private AffectationDao affectationDao;

	@Override
	public void save(Affectation affectation) throws Exception {
		affectationDao.saveOrUpdate(affectation);

	}

	@Override
	public void update(Affectation affectation) throws Exception {
		affectationDao.saveOrUpdate(affectation);
	}

	@Override
	public void delete(Affectation affectation) throws Exception {
		affectationDao.delete(affectation);
	}

	@Override
	public List<Affectation> findAffectationByCadre(Cadre cadre) throws Exception {
		Criterion crit = Restrictions.eq("cadre", cadre);
		return affectationDao.findByCriteria(Affectation.class, crit);
	}

	@Override
	public List<Affectation> findAffectationByCadre(Cadre cadre, Date date) throws Exception {
		Criterion crit = Restrictions.eq("cadre", cadre);
		Criterion crit2 = Restrictions.isNull("dateRetour");
		return affectationDao.findByCriteria(Affectation.class, crit, crit2);
	}

	public AffectationDao getAffectationDao() {
		return affectationDao;
	}

	public void setAffectationDao(AffectationDao affectationDao) {
		this.affectationDao = affectationDao;
	}

}
