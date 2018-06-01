package com.pfe.gestioncarburant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;


public interface GenericDao {
	public void save(Object entity)throws Exception;

		public void saveOrUpdate(Object entity)throws Exception;
		public  void delete(Object entity)throws Exception;
		public List findAll (Class cls)throws Exception;
		public List findByCriteria(Class cls,Criterion crit)throws Exception;
		public List findByCriteria(Class cls,Criterion crit,Criterion crit2)throws Exception;
		public List findByCriteria(Class cls,Criterion crit,Criterion crit2,Criterion crit3)throws Exception;
		public List findByCriteria(Class cls,Criterion crit,Criterion crit2,Criterion crit3 ,Criterion crit4)throws Exception;


}
