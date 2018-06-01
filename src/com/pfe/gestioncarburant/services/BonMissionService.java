package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.BonMission;
import com.pfe.gestioncarburant.entities.Mission;

public interface BonMissionService {

	public String[] save(BonMission bonMission) throws Exception;

	public String[] update(BonMission bonMission) throws Exception;

	public void delete(BonMission bonMission) throws Exception;

	public List<BonMission> findBonMissionByMission(Mission mission) throws Exception;

	public int getTotalLitre(Mission mission) throws Exception;

}
