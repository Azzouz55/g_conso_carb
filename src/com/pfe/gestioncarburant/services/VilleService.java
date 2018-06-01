package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Ville;

public interface VilleService {

	public boolean save(Ville ville) throws Exception;

	public boolean update(Ville ville) throws Exception;

	public void delete(Ville ville) throws Exception;

	public List<Ville> findAll() throws Exception;

	public Ville findVilleByCadre(Cadre cadre) throws Exception;

}
