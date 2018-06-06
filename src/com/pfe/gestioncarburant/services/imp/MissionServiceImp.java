package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.MissionDao;
import com.pfe.gestioncarburant.dao.VoitureDao;
import com.pfe.gestioncarburant.entities.Coordonnee;
import com.pfe.gestioncarburant.entities.Mission;
import com.pfe.gestioncarburant.entities.Ville;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.MissionService;

@Service
public class MissionServiceImp implements MissionService {
	@Autowired
	private MissionDao missionDao;
	@Autowired
	private VoitureDao voitureDao;

	@Override
	public double distance(Ville ville1, Ville ville2) {
		if (ville1 == ville2) {
			return 0.0;
		} else {
			Coordonnee cor1, cor2;
			cor1 = ville1.getCoordonnee();
			cor2 = ville2.getCoordonnee();
			return Coordonnee.distance(cor1, cor2);
		}
	}

	@Override
	public String[] save(Mission mission) throws Exception {
		String[] result = new String[2];
		double distance = 2 * distance(mission.getDepartementByIdDepartement().getVille(),
				mission.getDepartementByIdDepartementDest().getVille());
		distance += (distance * 5) / 100;
		// TODO Validation des données entrées

		mission.setDistancePrevue(distance);
		missionDao.save(mission);
		result[0] = "1";
		result[1] = "Mission affecté avec succée";
		return result;
	}

	@Override
	public String[] update(Mission mission) throws Exception {
		String[] result = new String[2];
		Voiture voiture = new Voiture();
		// TODO Validation des données entrées
		if (mission.getOdometreRetour() != null) {
			int distanceParcourue = mission.getOdometreRetour() - mission.getOdometreSortie();
			if (distanceParcourue <= 0) {
				result[0] = "0";
				result[1] = "Odométre de retour doit étre supérieur à l'odométre de sortie";
				return result;
			} else {
				mission.setDistanceParcourue(distanceParcourue);
			}
		}
		result[0] = "1";
		result[1] = "Mission modifier avec succée";
		missionDao.saveOrUpdate(mission);
		voiture = mission.getVoiture();
		voiture.setOdometre(mission.getOdometreRetour());
		voitureDao.saveOrUpdate(voiture);
		return result;
	}

	@Override
	public void delete(Mission mission) throws Exception {
		missionDao.delete(mission);
	}

	@Override
	public List<Mission> findAll() throws Exception {
		return missionDao.findAll(Mission.class);
	}

	public MissionDao getMissionDao() {
		return missionDao;
	}

	public void setMissionDao(MissionDao missionDao) {
		this.missionDao = missionDao;
	}

}
