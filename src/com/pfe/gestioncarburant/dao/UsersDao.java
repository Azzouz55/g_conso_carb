package com.pfe.gestioncarburant.dao;

import java.util.List;

import com.pfe.gestioncarburant.entities.User;

public interface UsersDao extends GenericDao{

	
	public List<User> findByProperty(User user);
}
