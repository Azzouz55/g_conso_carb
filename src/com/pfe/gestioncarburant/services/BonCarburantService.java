package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.Voiture;

public interface BonCarburantService {

	public boolean save(BonCarburant bonCarburant) throws Exception;

	public boolean update(BonCarburant bonCarburant) throws Exception;

	public void delete(BonCarburant bonCarburant) throws Exception;

	public List<BonCarburant> findAll() throws Exception;

	public List<BonCarburant> findBonCarburantByVoiture(Voiture voiture) throws Exception;

	public BonCarburant findBonCarburantByBonCarburant(int bonCarburant) throws Exception;

}
