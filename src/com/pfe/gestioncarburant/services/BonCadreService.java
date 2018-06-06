package com.pfe.gestioncarburant.services;

import java.util.Calendar;
import java.util.List;

import com.pfe.gestioncarburant.dto.MoisDTO;
import com.pfe.gestioncarburant.entities.BonCadre;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.TypeCarburant;

public interface BonCadreService {
	public String[] save(BonCadre bonCadre) throws Exception;

	public String[] update(BonCadre bonCadre) throws Exception;

	public void delete(BonCadre bonCadre) throws Exception;

	public List<BonCadre> findBonCadreByCadre(Cadre cadre) throws Exception;

	MoisDTO findByMonth(BonCarburant bonCarburant);

	MoisDTO findByQuarter(BonCarburant bonCarburant);

	MoisDTO findByYear(TypeCarburant typeCarburant, int annee);

	int getTotalLitre(Cadre cadre) throws Exception;

	int getTotalLitreByDate(Cadre cadre, Calendar date) throws Exception;

}
