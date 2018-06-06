package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.dto.MoisDTO;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.BonMission;
import com.pfe.gestioncarburant.entities.Mission;
import com.pfe.gestioncarburant.entities.TypeCarburant;

public interface BonMissionService {

	public String[] save(BonMission bonMission) throws Exception;

	public String[] update(BonMission bonMission) throws Exception;

	public void delete(BonMission bonMission) throws Exception;

	public List<BonMission> findBonMissionByMission(Mission mission) throws Exception;

	public int getTotalLitre(Mission mission) throws Exception;

	MoisDTO findByMonth(BonCarburant bonCarburant);

	MoisDTO findByQuarter(BonCarburant bonCarburant);

	MoisDTO findByYear(TypeCarburant typeCarburant, int annee);

}
