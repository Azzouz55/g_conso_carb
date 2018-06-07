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
}
