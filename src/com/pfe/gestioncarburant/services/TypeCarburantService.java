package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.TypeCarburant;

public interface TypeCarburantService {

	public boolean save(TypeCarburant typeCarburant) throws Exception;

	public boolean update(TypeCarburant typeCarburant) throws Exception;

	public void delete(TypeCarburant typeCarburant) throws Exception;

	public List<TypeCarburant> findAll() throws Exception;

	public List<TypeCarburant> findByType(String libelle) throws Exception;

}
