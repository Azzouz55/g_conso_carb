package com.pfe.gestioncarburant.services.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.BonCadreDao;
import com.pfe.gestioncarburant.dao.BonCarburantDao;
import com.pfe.gestioncarburant.dto.MoisDTO;
import com.pfe.gestioncarburant.entities.BonCadre;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.services.BonCadreService;

@Service
public class BonCadreServiceImp implements BonCadreService {
	@Autowired
	private BonCadreDao bonCadreDao;
	@Autowired
	private BonCarburantDao bonCarburantDao;

	@Override
	public String[] save(BonCadre bonCadre) throws Exception {
		String[] result = new String[2];
		int qteTotal = 0, qteParMois = 0, qteAutotrise = 0;

		qteParMois = getTotalLitreByDate(bonCadre.getCadre(), Calendar.getInstance());
		qteTotal = qteParMois + bonCadre.getQte();
		qteAutotrise = bonCadre.getCadre().getCategorie().getLittreParMois();
		if (qteAutotrise < qteTotal) {
			int reste = qteAutotrise - qteParMois;
			result[0] = "0";
			result[1] = "Il reste " + reste + " L pour ce mois";
			return result;
		}

		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonCadre.getId().getIdBonCarburant())).get(0);
		// Si la valeur de quantité est 0
		if (bonCadre.getQte() == 0) {
			result[0] = "0";
			result[1] = "Quantité doit étre supérieur à 0!";
			return result;
		}
		// Si stock est vide
		if (bonCarburant.getQte() == 0) {
			result[0] = "0";
			result[1] = "Stock vide!";
			return result;
		}
		// Si la quantité à ajouter dépasse le quantité en stock
		if (bonCarburant.getQte() < bonCadre.getQte()) {
			result[0] = "0";
			result[1] = "Stock Insuffisant, Il reste :" + bonCarburant.getQte() + " bon(s)";
			return result;
		}

		bonCarburant.setQte(bonCarburant.getQte() - bonCadre.getQte());
		bonCarburantDao.saveOrUpdate(bonCarburant);
		bonCadreDao.saveOrUpdate(bonCadre);

		result[0] = "1";
		result[1] = "Affectation effectué";
		return result;

	}

	@Override
	public String[] update(BonCadre bonCadre) throws Exception {

		String[] result = new String[2];
		int qteTotal = 0, qteParMois = 0, qteAutotrise = 0;

		qteParMois = getTotalLitreByDate(bonCadre.getCadre(), Calendar.getInstance());
		qteTotal = qteParMois + bonCadre.getQte();
		qteAutotrise = bonCadre.getCadre().getCategorie().getLittreParMois();
		if (qteAutotrise < qteTotal) {
			int reste = qteAutotrise - qteParMois;
			result[0] = "0";
			result[1] = "Il reste " + reste + " L pour ce mois";
			return result;
		}

		BonCadre old = (BonCadre) bonCadreDao.findByCriteria(BonCadre.class, Restrictions.idEq(bonCadre.getId()))
				.get(0);
		int qte = 0;
		qte = old.getQte() - bonCadre.getQte();
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonCadre.getId().getIdBonCarburant())).get(0);
		if (bonCarburant.getQte() < qte) {
			result[0] = "0";
			result[1] = "La quantitée à modifier dépasse la quantité en stock!";
			return result;
		}
		bonCadreDao.saveOrUpdate(bonCadre);
		bonCarburant.setQte(bonCarburant.getQte() + qte);
		bonCarburantDao.saveOrUpdate(bonCarburant);
		result[0] = "1";
		result[1] = "Modification effectué";
		return result;

	}

	@Override
	public void delete(BonCadre bonCadre) throws Exception {
		BonCarburant bonCarburant = (BonCarburant) bonCarburantDao
				.findByCriteria(BonCarburant.class, Restrictions.idEq(bonCadre.getId().getIdBonCarburant())).get(0);
		bonCarburant.setQte(bonCarburant.getQte() + bonCadre.getQte());
		bonCadreDao.delete(bonCadre);
		bonCarburantDao.saveOrUpdate(bonCarburant);
	}

	@Override
	public List<BonCadre> findBonCadreByCadre(Cadre cadre) throws Exception {
		Criterion crit = Restrictions.eq("cadre", cadre);

		return bonCadreDao.findByCriteria(BonCadre.class, crit);
	}

	@Override
	public MoisDTO findByMonth(BonCarburant bonCarburant) {
		// TODO Auto-generated method stub
		return bonCadreDao.findByMonth(bonCarburant);
	}

	@Override
	public MoisDTO findByYear(TypeCarburant typeCarburant, int annee) {
		// TODO Auto-generated method stub
		return bonCadreDao.findByYear(typeCarburant, annee);
	}

	@Override
	public MoisDTO findByQuarter(BonCarburant bonCarburant) {
		// TODO Auto-generated method stub
		return bonCadreDao.findByQuarter(bonCarburant);
	}

	@Override
	public int getTotalLitre(Cadre cadre) throws Exception {
		int total = 0, nbrLitre = 0;
		List<BonCadre> listBonCadre = new ArrayList<>();

		listBonCadre = findBonCadreByCadre(cadre);
		for (int index = 0; index < listBonCadre.size(); index++) {
			nbrLitre = (listBonCadre.get(index).getQte() * listBonCadre.get(index).getBonCarburant().getLitre());
			total = total + nbrLitre;
		}
		return total;
	}

	@Override
	public int getTotalLitreByDate(Cadre cadre, Calendar date) throws Exception {
		Calendar dateBon = Calendar.getInstance();

		int total = 0, nbrLitre = 0;
		List<BonCadre> listBonCadre = new ArrayList<>();
		listBonCadre = findBonCadreByCadre(cadre);
		for (int index = 0; index < listBonCadre.size(); index++) {
			dateBon.setTime(listBonCadre.get(index).getId().getDateAffectation());
			if (dateBon.get(Calendar.MONTH) == date.get(Calendar.MONTH)) {
				nbrLitre = (listBonCadre.get(index).getQte() * listBonCadre.get(index).getBonCarburant().getLitre());
				total = total + nbrLitre;
			}

		}
		return total;
	}

}
