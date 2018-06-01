package com.pfe.gestioncarburant.dao.imp;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.pfe.gestioncarburant.dao.BonCarburantDao;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.Voiture;

@Repository
public class BonCarburantDaoImp extends GenericDaoImp implements BonCarburantDao{

	@Override
	public List<BonCarburant> findBonCarburantbyVoiture(){
	return null;
		
	}
}
