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
 * Ville generated by hbm2java
 */
@Entity
@Table(name = "ville", catalog = "gestion_carburant_db")
public class Ville implements java.io.Serializable {

	private Integer id;
	private Coordonnee coordonnee;
	private String libelle;
	private Set<Departement> departements = new HashSet<Departement>(0);

	public Ville() {
	}

	public Ville(Coordonnee coordonnee, String libelle) {
		this.coordonnee = coordonnee;
		this.libelle = libelle;
	}

	public Ville(Coordonnee coordonnee, String libelle, Set<Departement> departements) {
		this.coordonnee = coordonnee;
		this.libelle = libelle;
		this.departements = departements;
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
	@JoinColumn(name = "id_coordonnee", nullable = false)
	public Coordonnee getCoordonnee() {
		return this.coordonnee;
	}

	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}

	@Column(name = "libelle", nullable = false)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ville")
	public Set<Departement> getDepartements() {
		return this.departements;
	}

	public void setDepartements(Set<Departement> departements) {
		this.departements = departements;
	}

}
