package com.pfe.gestioncarburant.services;

import java.util.List;

import com.pfe.gestioncarburant.entities.BonCadre;
import com.pfe.gestioncarburant.entities.Cadre;

public interface BonCadreService {
	public String[] save(BonCadre bonCadre) throws Exception;

	public String[] update(BonCadre bonCadre) throws Exception;

	public void delete(BonCadre bonCadre) throws Exception;

	public List<BonCadre> findBonCadreByCadre(Cadre cadre) throws Exception;

}
