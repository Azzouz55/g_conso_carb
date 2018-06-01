package com.pfe.gestioncarburant.entities;
// Generated May 16, 2018 6:55:08 PM by Hibernate Tools 5.1.7.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Voiture generated by hbm2java
 */
@Entity
@Table(name = "voiture", catalog = "gestion_carburant_db")
public class Voiture implements java.io.Serializable {

	private String matricule;
	private Model model;
	private TypeCarburant typeCarburant;
	private int reservoir;
	private int puissance;
	private String transmission;
	private String typeAttribution;
	private Set<Mission> missions = new HashSet<Mission>(0);
	private Set<Affectation> affectations = new HashSet<Affectation>(0);

	public Voiture() {

	}

	public Voiture(String matricule, Model model, TypeCarburant typeCarburant, int reservoir, int puissance,
			String transmission, String typeAttribution) {
		this.matricule = matricule;
		this.model = model;
		this.typeCarburant = typeCarburant;
		this.reservoir = reservoir;
		this.puissance = puissance;
		this.transmission = transmission;
		this.typeAttribution = typeAttribution;
	}

	public Voiture(String matricule, Model model, TypeCarburant typeCarburant, int reservoir, int puissance,
			String transmission, String typeAttribution, Set<Mission> missions, Set<Affectation> affectations) {
		this.matricule = matricule;
		this.model = model;
		this.typeCarburant = typeCarburant;
		this.reservoir = reservoir;
		this.puissance = puissance;
		this.transmission = transmission;
		this.typeAttribution = typeAttribution;
		this.missions = missions;
		this.affectations = affectations;
	}

	@Id

	@Column(name = "matricule", unique = true, nullable = false, length = 10)
	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_model", nullable = false)
	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_carburant", nullable = false)
	public TypeCarburant getTypeCarburant() {
		return this.typeCarburant;
	}

	public void setTypeCarburant(TypeCarburant typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

	@Column(name = "reservoir", nullable = false)
	public int getReservoir() {
		return this.reservoir;
	}

	public void setReservoir(int reservoir) {
		this.reservoir = reservoir;
	}

	@Column(name = "puissance", nullable = false)
	public int getPuissance() {
		return this.puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	@Column(name = "transmission", nullable = false, length = 15)
	public String getTransmission() {
		return this.transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	@Column(name = "type_attribution", nullable = false, length = 15)
	public String getTypeAttribution() {
		return this.typeAttribution;
	}

	public void setTypeAttribution(String typeAttribution) {
		this.typeAttribution = typeAttribution;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "voiture")
	public Set<Mission> getMissions() {
		return this.missions;
	}

	public void setMissions(Set<Mission> missions) {
		this.missions = missions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "voiture")
	public Set<Affectation> getAffectations() {
		return this.affectations;
	}

	public void setAffectations(Set<Affectation> affectations) {
		this.affectations = affectations;
	}

}