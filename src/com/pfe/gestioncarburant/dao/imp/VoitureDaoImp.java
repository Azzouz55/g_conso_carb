package com.pfe.gestioncarburant.dao.imp;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.pfe.gestioncarburant.dao.VoitureDao;
import com.pfe.gestioncarburant.entities.Voiture;

@Repository
public class VoitureDaoImp extends GenericDaoImp implements VoitureDao {

	@Override
	public List<Voiture> findVoitureFonctionNonAffecte() {
		startOperation();

		Criterion crit = Restrictions.eq("v.typeAttribution", "Fonction");
		Criterion crit2 = Restrictions.isNull("aff.voiture");
		Criterion crit3 = Restrictions.isNull("aff.dateRetour");
		Criterion crit4 = Restrictions.not(crit2);
		Criterion crit5 = Restrictions.or(crit3, crit4);
		// Criterion crit5 = Restrictions.isNotNull("aff.dateRetour");
		// Criterion crit6 = Restrictions.or(crit4, crit5);
		List<Voiture> list = hibernateSession.createCriteria(Voiture.class, "v")
				.createAlias("v.affectations", "aff", CriteriaSpecification.LEFT_JOIN).add(crit).add(crit5)
				.setProjection(Projections.projectionList().add(Projections.property("matricule"), "matricule")
						.add(Projections.property("model"), "model")
						// .add(Projections.property("modele.marque"), "modele.marque")
						.add(Projections.groupProperty("v.matricule")))
				.setResultTransformer(Transformers.aliasToBean(Voiture.class)).list();

		endOperation();

		return list;
	}

	@Override
	public List<Voiture> findVoitureServiceNonAffecte() {
		startOperation();

		Criterion crit = Restrictions.eq("v.typeAttribution", "Service");
		Criterion crit2 = Restrictions.isNull("mis.voiture");
		Criterion crit3 = Restrictions.isNull("mis.dateRetour");
		Criterion crit4 = Restrictions.not(crit2);
		Criterion crit5 = Restrictions.or(crit3, crit4);
		// Criterion crit5 = Restrictions.isNotNull("aff.dateRetour");
		// Criterion crit6 = Restrictions.or(crit4, crit5);
		List<Voiture> list = hibernateSession.createCriteria(Voiture.class, "v")
				.createAlias("v.missions", "mis", CriteriaSpecification.LEFT_JOIN).add(crit).add(crit5)
				.setProjection(Projections.projectionList().add(Projections.property("matricule"), "matricule")
						.add(Projections.property("model"), "model")
						// .add(Projections.property("modele.marque"), "modele.marque")
						.add(Projections.groupProperty("v.matricule")))
				.setResultTransformer(Transformers.aliasToBean(Voiture.class)).list();

		endOperation();

		return list;
	}

}
