package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Chauffeur;

public interface ChauffeurService {

	
	public String[] save(Chauffeur chauffeur) throws Exception;
	public String[] update(Chauffeur chauffeur) throws Exception;
	public void delete(Chauffeur chauffeur) throws Exception;
	public List<Chauffeur> findAll() throws Exception;
	
	
}
