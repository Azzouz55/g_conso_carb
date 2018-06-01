package com.pfe.gestioncarburant.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.entities.Marque;

public interface MarqueService {
	public boolean save(Marque marque) throws Exception;
	public boolean update(Marque marque) throws Exception;
	public void delete(Marque marque) throws Exception;
	public List<Marque> findAll() throws Exception;

}
