package com.pfe.gestioncarburant.dao;

import java.util.List;

import com.pfe.gestioncarburant.entities.Voiture;

public interface VoitureDao extends GenericDao {

	public List<Voiture> findVoitureFonctionNonAffecte();

	public List<Voiture> findVoitureServiceNonAffecte();

}
