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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model generated by hbm2java
 */
@Entity
@Table(name = "model", catalog = "gestion_carburant_db")
public class Model implements java.io.Serializable {

	private Integer id;
	private Marque marque;
	private String libelle;
	private Set<Voiture> voitures = new HashSet<Voiture>(0);

	public Model() {
	}

	public Model(Marque marque, String libelle) {
		this.marque = marque;
		this.libelle = libelle;
	}

	public Model(Marque marque, String libelle, Set<Voiture> voitures) {
		this.marque = marque;
		this.libelle = libelle;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_marque", nullable = false)
	public Marque getMarque() {
		return this.marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	@Column(name = "libelle", nullable = false, length = 20)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
	public Set<Voiture> getVoitures() {
		return this.voitures;
	}

	public void setVoitures(Set<Voiture> voitures) {
		this.voitures = voitures;
	}

}
