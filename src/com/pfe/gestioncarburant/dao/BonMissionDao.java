package com.pfe.gestioncarburant.dao;

import com.pfe.gestioncarburant.dto.MoisDTO;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.TypeCarburant;

public interface BonMissionDao extends GenericDao {

	MoisDTO findByMonth(BonCarburant bonCarburant);

	MoisDTO findByQuarter(BonCarburant bonCarburant);

	MoisDTO findByYear(TypeCarburant typeCarburant, int annee);

}
