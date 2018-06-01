package com.pfe.gestioncarburant.dao.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfe.gestioncarburant.dao.UsersDao;
import com.pfe.gestioncarburant.entities.User;

@Repository
public class UsersDaoImp extends GenericDaoImp implements UsersDao{

	@Override
	public List<User> findByProperty(User user) {
		
		Criterion crit1 = Restrictions.idEq(user.getMatricule());
		Criterion crit = Restrictions.not(crit1);
		Criterion crit2 = Restrictions.eq("emp.cin", user.getEmployee().getCin());
		Criterion crit3 = Restrictions.eq("emp.email", user.getEmployee().getEmail());
		Criterion crit4 = Restrictions.eq("login", user.getLogin());
		startOperation();
		List list= hibernateSession.createCriteria(User.class).createAlias("employee", "emp").add(crit).add(crit2).add(crit3).add(crit4).list();
			endOperation();
			return list;
	}

}
