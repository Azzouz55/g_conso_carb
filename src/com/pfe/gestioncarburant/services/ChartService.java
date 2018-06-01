package com.pfe.gestioncarburant.services;

import com.pfe.gestioncarburant.entities.TypeCarburant;

public interface ChartService {

	public int[] getQteMoisbyGazoil(TypeCarburant typeCarburant) throws Exception;

}
