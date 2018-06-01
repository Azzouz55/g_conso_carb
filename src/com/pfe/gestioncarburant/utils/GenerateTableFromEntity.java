package com.pfe.gestioncarburant.utils;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.pfe.gestioncarburant.entities.Affectation;
import com.pfe.gestioncarburant.entities.BonCadre;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.BonMission;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Categorie;
import com.pfe.gestioncarburant.entities.Chauffeur;
import com.pfe.gestioncarburant.entities.Departement;
import com.pfe.gestioncarburant.entities.Employee;
import com.pfe.gestioncarburant.entities.EntreeBon;
import com.pfe.gestioncarburant.entities.Marque;
import com.pfe.gestioncarburant.entities.Mission;
import com.pfe.gestioncarburant.entities.Model;
import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.entities.User;
import com.pfe.gestioncarburant.entities.Ville;
import com.pfe.gestioncarburant.entities.Voiture;

public class GenerateTableFromEntity {
	public static void main(String[] args) {

		AnnotationConfiguration config = new AnnotationConfiguration();

		config.addAnnotatedClass(Affectation.class);
		config.addAnnotatedClass(BonCadre.class);
		config.addAnnotatedClass(BonCarburant.class);
		config.addAnnotatedClass(Cadre.class);
		config.addAnnotatedClass(Categorie.class);
		config.addAnnotatedClass(Chauffeur.class);
		config.addAnnotatedClass(Departement.class);
		config.addAnnotatedClass(Employee.class);
		config.addAnnotatedClass(EntreeBon.class);
		config.addAnnotatedClass(Marque.class);
		config.addAnnotatedClass(Mission.class);
		config.addAnnotatedClass(BonMission.class);
		config.addAnnotatedClass(Model.class);
		config.addAnnotatedClass(TypeCarburant.class);
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Ville.class);
		config.addAnnotatedClass(Voiture.class);
		config.configure();
		new SchemaExport(config).create(true, true);

	}
}
