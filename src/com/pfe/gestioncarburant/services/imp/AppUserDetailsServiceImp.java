package com.pfe.gestioncarburant.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.entities.User;
import com.pfe.gestioncarburant.services.AppUserDetailsService;
import com.pfe.gestioncarburant.services.UsersService;

@Service
public class AppUserDetailsServiceImp implements AppUserDetailsService {

	@Autowired
	private UsersService usersService;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		User user = usersService.findByLogin(arg0);
		return user;
	}

}
