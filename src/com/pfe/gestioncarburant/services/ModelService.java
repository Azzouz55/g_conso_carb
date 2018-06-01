package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Marque;
import com.pfe.gestioncarburant.entities.Model;

public interface ModelService {
	public boolean save(Model modele) throws Exception;

	public boolean update(Model modele) throws Exception;

	public void delete(Model modele) throws Exception;

	public List<Model> findAll() throws Exception;

	public List<Model> findByMarque(Marque marque) throws Exception;
}
