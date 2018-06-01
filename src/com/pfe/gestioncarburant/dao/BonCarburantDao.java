package com.pfe.gestioncarburant.dao;

import java.util.List;

import com.pfe.gestioncarburant.entities.BonCarburant;

public interface BonCarburantDao extends GenericDao{
	public List<BonCarburant> findBonCarburantbyVoiture();
}
