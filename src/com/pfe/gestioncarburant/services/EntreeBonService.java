package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.EntreeBon;

public interface EntreeBonService {
	public boolean save(EntreeBon entreBon) throws Exception;

	public boolean update(EntreeBon entreBon) throws Exception;

	public void delete(EntreeBon entreBon) throws Exception;

	public List<EntreeBon> findAll() throws Exception;
}
