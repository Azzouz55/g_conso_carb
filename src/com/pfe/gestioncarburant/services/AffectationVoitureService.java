package com.pfe.gestioncarburant.services;

import java.sql.Date;
import java.util.List;

import com.pfe.gestioncarburant.entities.Affectation;
import com.pfe.gestioncarburant.entities.Cadre;

public interface AffectationVoitureService {
	
	
	public void save(Affectation affectation)  throws Exception ;
	public void update(Affectation affectation)  throws Exception ;
	public void delete(Affectation affectation)  throws Exception ;
	public List<Affectation> findAffectationByCadre(Cadre cadre)  throws Exception ;
	public List<Affectation> findAffectationByCadre(Cadre cadre, Date date) throws Exception;
	

}
