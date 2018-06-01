package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Departement;
import com.pfe.gestioncarburant.entities.Ville;

public interface DepartementService {

	public boolean save(Departement departement) throws Exception;

	public boolean update(Departement departement) throws Exception;

	public void delete(Departement departement) throws Exception;

	public List<Departement> findAll() throws Exception;

	public List<Departement> findByVille(Ville ville) throws Exception;

	public Departement findByCadre(Cadre cadre) throws Exception;

	public Departement getDepartement(Departement departement) throws Exception;

}
