package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Coordonnee;

public interface CoordonneeService {
	public String[] save(Coordonnee coordonnee) throws Exception;

	public String[] update(Coordonnee coordonnee) throws Exception;

	public void delete(Coordonnee coordonnee) throws Exception;

	public List<Coordonnee> findAll() throws Exception;

}
