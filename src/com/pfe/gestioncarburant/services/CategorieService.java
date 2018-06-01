package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Categorie;

public interface CategorieService {
	public boolean save(Categorie categorie) throws Exception;
	public boolean update(Categorie categorie) throws Exception;
	public void delete(Categorie categorie) throws Exception;
	public List<Categorie> findAll() throws Exception;

}
