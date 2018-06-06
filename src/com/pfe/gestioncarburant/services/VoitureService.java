package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Affectation;
import com.pfe.gestioncarburant.entities.Voiture;

public interface VoitureService {

	public String[] save(Voiture voiture) throws Exception;

	public void update(Voiture voiture) throws Exception;

	public void delete(Voiture voiture) throws Exception;

	public List<Voiture> findAll() throws Exception;

	public List<Voiture> findVoitureFonctionNonAffecte() throws Exception;

	public List<Voiture> findVoitureByCadre(Affectation affectation) throws Exception;

	public List<Voiture> findVoitureServiceNonAffecte() throws Exception;

	public Voiture findVoitureByMatricule(String matricule) throws Exception;

}
