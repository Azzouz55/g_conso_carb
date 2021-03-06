package com.pfe.gestioncarburant.entities;
// Generated May 16, 2018 6:55:08 PM by Hibernate Tools 5.1.7.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TypeCarburant generated by hbm2java
 */
@Entity
@Table(name = "type_carburant", catalog = "gestion_carburant_db")
public class TypeCarburant implements java.io.Serializable {

	private Integer id;
	private String libelle;
	private Set<BonCarburant> bonCarburants = new HashSet<BonCarburant>(0);
	private Set<Voiture> voitures = new HashSet<Voiture>(0);

	public TypeCarburant() {
	}

	public TypeCarburant(String libelle) {
		this.libelle = libelle;
	}

	public TypeCarburant(String libelle, Set<BonCarburant> bonCarburants, Set<Voiture> voitures) {
		this.libelle = libelle;
		this.bonCarburants = bonCarburants;
		this.voitures = voitures;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "libelle", nullable = false, length = 20)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeCarburant")
	public Set<BonCarburant> getBonCarburants() {
		return this.bonCarburants;
	}

	public void setBonCarburants(Set<BonCarburant> bonCarburants) {
		this.bonCarburants = bonCarburants;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeCarburant")
	public Set<Voiture> getVoitures() {
		return this.voitures;
	}

	public void setVoitures(Set<Voiture> voitures) {
		this.voitures = voitures;
	}

}
