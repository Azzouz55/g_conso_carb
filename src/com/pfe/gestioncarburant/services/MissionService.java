package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.Mission;
import com.pfe.gestioncarburant.entities.Ville;

public interface MissionService {
	public String[] save(Mission mission) throws Exception;

	public String[] update(Mission mission) throws Exception;

	public void delete(Mission mission) throws Exception;

	public List<Mission> findAll() throws Exception;

	public double distance(Ville ville1, Ville ville2);

}
