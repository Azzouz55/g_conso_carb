package com.pfe.gestioncarburant.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.pfe.gestioncarburant.dao.GenericDao;
import com.pfe.gestioncarburant.utils.HibernateUtil;

public class GenericDaoImp implements GenericDao {

	protected Session hibernateSession;
	private Transaction tx;
	
	protected void startOperation(){
		hibernateSession=HibernateUtil.getInstance().getSession();
		tx=hibernateSession.beginTransaction();
	}
	protected void endOperation(){
		tx.commit();
		hibernateSession.close();
	}
	@Override
	public void save(Object entity) {
		startOperation();
		hibernateSession.save(entity);
		endOperation();
	}
	@Override
	public void saveOrUpdate(Object entity) {
		startOperation();
		hibernateSession.saveOrUpdate(entity);
		endOperation();
	}

	@Override
	public void delete(Object entity) {
		startOperation();
		hibernateSession.delete(entity);
		endOperation();
	}

	@Override
	public List findAll(Class cls) {
		startOperation();
	List list=	hibernateSession.createCriteria(cls).list();
		endOperation();
		return list;
	}

	@Override
	public List findByCriteria(Class cls, Criterion crit) {
		startOperation();
		List list=	hibernateSession.createCriteria(cls).add(crit).list();
			endOperation();
		return list;
	}

	@Override
	public List findByCriteria(Class cls, Criterion crit, Criterion crit2) {
		startOperation();
		List list=	hibernateSession.createCriteria(cls).add(crit).add(crit2).list();
			endOperation();
		return list;
	}
	@Override
	public List findByCriteria(Class cls, Criterion crit, Criterion crit2, Criterion crit3) throws Exception {
		startOperation();
		List list=	hibernateSession.createCriteria(cls).add(crit).add(crit2).add(crit3).list();
			endOperation();
		return list;
	}
	@Override
	public List findByCriteria(Class cls, Criterion crit, Criterion crit2, Criterion crit3, Criterion crit4) throws Exception {
		startOperation();
		List list= hibernateSession.createCriteria(cls).add(crit).add(crit2).add(crit3).add(crit4).list();
			endOperation();
		return list;
	}
	

}
