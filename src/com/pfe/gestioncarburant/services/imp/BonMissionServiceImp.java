package com.pfe.gestioncarburant.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.BonCarburantDao;
import com.pfe.gestioncarburant.dao.BonMissionDao;
import com.pfe.gestioncarburant.dto.MoisDTO;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.BonMission;
import com.pfe.gestioncarburant.entities.Mission;
import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.services.BonMissionService;

@Service
public class BonMissionServiceImp implements BonMissionService {
	@Autowired
	private BonMissionDao bonMissionDao;
	@Autowired
	private BonCarburantDao bonCarburantDao;

	@Override
	public String[] save(BonMission bonMission) throws Exception {
		String[] result = new String[2];
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonMission.getId().getIdBonCarburant())).get(0);
		// Si la valeur de quantit� est 0
		if (bonMission.getQte() == 0) {
			result[0] = "0";
			result[1] = "Quantit� de bon carburant " + bonCarburant.getTypeCarburant().getLibelle() + " - "
					+ bonCarburant.getLitre() + "L doit �tre sup�rieur � 0!";
			return result;
		}
		// Si stock est vide
		if (bonCarburant.getQte() == 0) {
			result[0] = "0";
			result[1] = "Stock de bon carburant " + bonCarburant.getTypeCarburant().getLibelle() + " - "
					+ bonCarburant.getLitre() + "L est vide!";
			return result;
		}
		// Si la quantit� � ajouter d�passe le quantit� en stock
		if (bonCarburant.getQte() < bonMission.getQte()) {
			result[0] = "0";
			result[1] = "Stock de bon carburant " + bonCarburant.getTypeCarburant().getLibelle() + " - "
					+ bonCarburant.getLitre() + "L  Insuffisant\n Il reste :" + bonCarburant.getQte() + " bon(s)";
			return result;
		}

		bonMissionDao.save(bonMission);
		bonCarburant.setQte(bonCarburant.getQte() - bonMission.getQte());
		bonCarburantDao.saveOrUpdate(bonCarburant);
		result[0] = "1";
		result[1] = "Bons de bon carburant " + bonCarburant.getTypeCarburant().getLibelle() + " - "
				+ bonCarburant.getLitre() + "L  de mission courante affect� avec succ�e";
		return result;
	}

	@Override
	public String[] update(BonMission bonMission) throws Exception {
		String[] result = new String[2];
		BonMission old = (BonMission) bonMissionDao
				.findByCriteria(BonMission.class, Restrictions.idEq(bonMission.getId())).get(0);
		int qte = 0;
		qte = old.getQte() - bonMission.getQte();
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonMission.getId().getIdBonCarburant())).get(0);
		if (bonCarburant.getQte() < qte) {
			result[0] = "0";
			result[1] = "La quantit�e � modifier d�passe la quantit� en stock!";
			return result;
		}
		bonMissionDao.saveOrUpdate(bonMission);
		bonCarburant.setQte(bonCarburant.getQte() + qte);
		bonCarburantDao.saveOrUpdate(bonCarburant);
		result[0] = "1";
		result[1] = "Bon de mission modifi� avec succ�e";
		return result;
	}

	@Override
	public void delete(BonMission bonMission) throws Exception {
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonMission.getId().getIdBonCarburant())).get(0);
		bonMissionDao.delete(bonMission);
		bonCarburant.setQte(bonCarburant.getQte() + bonMission.getQte());
		bonCarburantDao.saveOrUpdate(bonCarburant);

	}

	@Override
	public List<BonMission> findBonMissionByMission(Mission mission) throws Exception {
		Criterion crit = Restrictions.eq("mission", mission);
		return bonMissionDao.findByCriteria(BonMission.class, crit);
	}

	@Override
	public int getTotalLitre(Mission mission) throws Exception {
		int total = 0, nbrLitre = 0;
		List<BonMission> listBonMission = new ArrayList<>();

		listBonMission = findBonMissionByMission(mission);
		for (int index = 0; index < listBonMission.size(); index++) {
			nbrLitre = (listBonMission.get(index).getQte() * listBonMission.get(index).getBonCarburant().getLitre());
			total = total + nbrLitre;
		}
		return total;
	}

	@Override
	public MoisDTO findByMonth(BonCarburant bonCarburant) {
		return bonMissionDao.findByMonth(bonCarburant);
	}

	@Override
	public MoisDTO findByYear(TypeCarburant typeCarburant, int annee) {
		return bonMissionDao.findByYear(typeCarburant, annee);
	}

	@Override
	public MoisDTO findByQuarter(BonCarburant bonCarburant) {
		return bonMissionDao.findByQuarter(bonCarburant);
	}

}
