package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.CoordonneeDao;
import com.pfe.gestioncarburant.entities.Coordonnee;
import com.pfe.gestioncarburant.services.CoordonneeService;

@Service
public class CoordonneeServiceImp implements CoordonneeService {
	@Autowired
	private CoordonneeDao coordonneeDao;

	@Override
	public String[] save(Coordonnee coordonnee) throws Exception {
		String[] result = new String[2];
		coordonneeDao.saveOrUpdate(coordonnee);
		result[0] = "1";
		result[1] = "Ajout effectué";
		return result;
	}

	@Override
	public String[] update(Coordonnee coordonnee) throws Exception {
		String[] result = new String[2];
		coordonneeDao.saveOrUpdate(coordonnee);
		result[0] = "1";
		result[1] = "Ajout effectué";
		return result;
	}

	@Override
	public void delete(Coordonnee coordonnee) throws Exception {
		String[] result = new String[2];
		coordonneeDao.delete(coordonnee);
		result[0] = "1";
		result[1] = "Ajout effectué";

	}

	@Override
	public List<Coordonnee> findAll() throws Exception {
		return coordonneeDao.findAll(Coordonnee.class);
	}

}
