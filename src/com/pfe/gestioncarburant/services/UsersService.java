package com.pfe.gestioncarburant.services;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pfe.gestioncarburant.entities.User;

public interface UsersService {
	public String[] save(User user) throws Exception;

	public String[] update(User user) throws Exception;

	public void delete(User user) throws Exception;

	public List<User> findAll() throws Exception;

	public User findByLogin(String arg0) throws UsernameNotFoundException;
}
