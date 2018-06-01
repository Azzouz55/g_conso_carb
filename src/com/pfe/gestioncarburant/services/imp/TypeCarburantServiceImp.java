package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.TypeCarburantDao;
import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.services.TypeCarburantService;

@Service
public class TypeCarburantServiceImp implements TypeCarburantService {
	@Autowired
	private TypeCarburantDao typeCarburantDao;

	@Override
	public boolean save(TypeCarburant typeCarburant) throws Exception {

		Criterion crit = Restrictions.eq("libelle", typeCarburant.getLibelle());
		List<TypeCarburant> list = typeCarburantDao.findByCriteria(TypeCarburant.class, crit);

		if (list.isEmpty()) {
			typeCarburantDao.saveOrUpdate(typeCarburant);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(TypeCarburant typeCarburant) throws Exception {
		Criterion crit = Restrictions.eq("libelle", typeCarburant.getLibelle());
		Criterion crit2 = Restrictions.idEq(typeCarburant.getId());
		List<TypeCarburant> list = typeCarburantDao.findByCriteria(TypeCarburant.class, crit, crit2);

		if (!list.isEmpty()) {
			typeCarburantDao.saveOrUpdate(typeCarburant);
			return true;
		}
		list = typeCarburantDao.findByCriteria(TypeCarburant.class, crit);

		if (list.isEmpty()) {
			typeCarburantDao.saveOrUpdate(typeCarburant);
			return true;
		}
		return false;
	}

	@Override
	public void delete(TypeCarburant typeCarburant) throws Exception {

		typeCarburantDao.delete(typeCarburant);

	}

	@Override
	public List<TypeCarburant> findAll() throws Exception {
		// TODO Auto-generated method stub
		return typeCarburantDao.findAll(TypeCarburant.class);
	}

	@Override
	public List<TypeCarburant> findByType(String libelle) throws Exception {
		Criterion crit = Restrictions.eq("libelle", libelle);
		return typeCarburantDao.findByCriteria(TypeCarburant.class, crit);
	}

	public TypeCarburantDao getTypeCarburantDao() {
		return typeCarburantDao;
	}

	public void setTypeCarburantDao(TypeCarburantDao typeCarburantDao) {
		this.typeCarburantDao = typeCarburantDao;
	}

}
