package com.pfe.gestioncarburant.services;

import com.pfe.gestioncarburant.entities.User;

public interface UserProfileService {

	public User findUserByUsername(String username) throws Exception;

}
