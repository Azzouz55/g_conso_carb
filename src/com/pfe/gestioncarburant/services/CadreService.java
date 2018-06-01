package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Cadre;

public interface CadreService {

	public String[] save(Cadre cadre) throws Exception;

	public String[] update(Cadre cadre) throws Exception;

	public void delete(Cadre cadre) throws Exception;

	public List<Cadre> findAll() throws Exception;

	public Cadre getCadre(Cadre cadre) throws Exception;

}
