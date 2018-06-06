package com.pfe.gestioncarburant.services.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.BonCarburantDao;
import com.pfe.gestioncarburant.dao.BonMissionDao;
import com.pfe.gestioncarburant.services.ChartService;

@Service
public class ChartServiceImp implements ChartService {
	@Autowired
	private BonCarburantDao bonCarburantDao;
	@Autowired
	private BonMissionDao bonMissionDao;

	@Override
	public List<String> findValeurByCritere(int critere, Calendar date) {
		List<String> listValeur = new ArrayList<>();
		if (critere == 1) {
			listValeur.add("Janvier");
			listValeur.add("Février");
			listValeur.add("Mars");
			listValeur.add("Avril");
			listValeur.add("Mai");
			listValeur.add("Juin");
			listValeur.add("Juillet");
			listValeur.add("Aout");
			listValeur.add("Septembre");
			listValeur.add("Octobre");
			listValeur.add("Nouvembre");
			listValeur.add("Décembre");
		}
		if (critere == 2) {
			listValeur.add("Trimestre 1");
			listValeur.add("Trimestre 2");
			listValeur.add("Trimestre 3");
			listValeur.add("Trimestre 4");
		}
		if (critere == 3) {
			for (int index = 2017; index <= date.get(Calendar.YEAR); index++) {
				listValeur.add(String.valueOf(index));
			}
		}
		return listValeur;
	}

	// public int[] getQteMoisbyGazoil(TypeCarburant typeCarburant) throws Exception
	// {
	// TypeCarburant type = typeCarburant;
	// List<BonMission> listBonMission = new ArrayList<>();
	// List<BonCarburant> listBonCarburant = new ArrayList<>();
	// int[] qteMois = new int[12];
	// listBonMission = findBonMissionByTypeCarburant(type);
	// List<BonMission> listAnuelle = new ArrayList<>();
	// for (BonMission bon : listBonMission) {
	// if (bon.getId().getDateAffectation().getYear() ==
	// LocalDateTime.now().getYear()) {
	// switch (bon.getId().getDateAffectation().getMonth()) {
	// case 0:
	// qteMois[0] += bon.getQte();
	// break;
	// case 1:
	// qteMois[1] += bon.getQte();
	// break;
	// case 2:
	// qteMois[2] += bon.getQte();
	// break;
	// case 3:
	// qteMois[3] += bon.getQte();
	// break;
	// case 4:
	// qteMois[4] += bon.getQte();
	// break;
	// case 5:
	// qteMois[5] += bon.getQte();
	// break;
	// case 6:
	// qteMois[6] += bon.getQte();
	// break;
	// case 7:
	// qteMois[7] += bon.getQte();
	// break;
	// case 8:
	// qteMois[8] += bon.getQte();
	// break;
	// case 9:
	// qteMois[9] += bon.getQte();
	// break;
	// case 10:
	// qteMois[10] += bon.getQte();
	// break;
	// case 11:
	// qteMois[11] += bon.getQte();
	// break;
	// }
	// }
	// }
	// return qteMois;
	// }
	//
	// public List<BonMission> findBonMissionByTypeCarburant(TypeCarburant
	// typeCarburant) throws Exception {
	// List<BonCarburant> listBonCarburant = new ArrayList<>();
	// List<BonMission> listBonMission = new ArrayList<>();
	// Criterion crit = Restrictions.eq("typeCarburant", typeCarburant);
	// listBonCarburant = bonCarburantDao.findByCriteria(BonCarburant.class, crit);
	// for (BonCarburant bon : listBonCarburant) {
	// Criterion crit2 = Restrictions.eq("bonCarburant", bon);
	// listBonMission.addAll(bonMissionDao.findByCriteria(BonMission.class, crit2));
	// }
	// return listBonMission;
	//
	// }
}
