package com.pfe.gestioncarburant.services.imp;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.UsersDao;
import com.pfe.gestioncarburant.entities.User;
import com.pfe.gestioncarburant.services.UserProfileService;

@Service
public class UserProfileServiceImp implements UserProfileService {
	@Autowired
	private UsersDao usersDao;
	// @Autowired
	// private EmployeDao employeDao;

	@Override
	public User findUserByUsername(String username) throws Exception {
		Criterion crit = Restrictions.eq("login", username);
		return (User) usersDao.findByCriteria(User.class, crit).get(0);
	}

}
