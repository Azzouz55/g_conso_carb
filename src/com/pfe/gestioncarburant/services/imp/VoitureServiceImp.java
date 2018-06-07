package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.VoitureDao;
import com.pfe.gestioncarburant.entities.Affectation;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.VoitureService;

@Service
public class VoitureServiceImp implements VoitureService {

	@Autowired
	private VoitureDao voitureDao;

	public String[] isValid(Voiture car) {
		String[] res = new String[2];
		if (car.getMatricule().indexOf("TUN") <= 1 || car.getMatricule().length() < 5
				|| car.getMatricule().length() > 10) {
			// Si longeur num matricule invalide
			res[0] = "0";
			res[1] = "Fomat matricule invalide\n Vérifiez qu'il respecte cette format : '200TUN2000'";
			return res;
		}
		return null;
	}

	@Override
	public String[] save(Voiture voiture) throws Exception {
		// Si longueur num matricule invalide
		if (isValid(voiture) != null) {
			return isValid(voiture);
		}
		String[] result = new String[2];
		Criterion crit = Restrictions.idEq(voiture.getMatricule());
		List<Voiture> list = voitureDao.findByCriteria(Voiture.class, crit);
		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "Matricule existant";
			return result;
		}
		voitureDao.saveOrUpdate(voiture);
		result[0] = "1";
		result[1] = "Ajout effectué";
		return result;
	}

	@Override
	public void update(Voiture voiture) throws Exception {
		voitureDao.saveOrUpdate(voiture);

	}

	@Override
	public void delete(Voiture voiture) throws Exception {
		voitureDao.delete(voiture);
	}

	@Override
	public List<Voiture> findAll() throws Exception {
		return voitureDao.findAll(Voiture.class);
	}

	@Override
	public List<Voiture> findVoitureFonctionNonAffecte() throws Exception {
		return voitureDao.findVoitureFonctionNonAffecte();
	}

	@Override
	public List<Voiture> findVoitureServiceNonAffecte() throws Exception {
		return voitureDao.findVoitureServiceNonAffecte();
	}

	@Override
	public List<Voiture> findVoitureByCadre(Affectation affectation) throws Exception {
		Criterion crit = Restrictions.eq("matricule", affectation.getId().getMatriculeVoiture());
		return voitureDao.findByCriteria(Voiture.class, crit);
	}

	@Override
	public Voiture findVoitureByMatricule(String matricule) throws Exception {
		Criterion crit = Restrictions.eq("matricule", matricule);
		return (Voiture) voitureDao.findByCriteria(Voiture.class, crit).get(0);

	}
}
