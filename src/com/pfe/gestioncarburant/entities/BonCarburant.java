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
 * BonCarburant generated by hbm2java
 */
@Entity
@Table(name = "bon_carburant", catalog = "gestion_carburant_db")
public class BonCarburant implements java.io.Serializable {

	private Integer id;
	private TypeCarburant typeCarburant;
	private int litre;
	private int qte;
	private Set<EntreeBon> entreeBons = new HashSet<EntreeBon>(0);
	private Set<BonCadre> bonCadres = new HashSet<BonCadre>(0);
	private Set<BonMission> bonMissions = new HashSet<BonMission>(0);

	public BonCarburant() {
	}

	public BonCarburant(TypeCarburant typeCarburant, int litre, int qte) {
		this.typeCarburant = typeCarburant;
		this.litre = litre;
		this.qte = qte;
	}

	public BonCarburant(TypeCarburant typeCarburant, int litre, int qte, Set<EntreeBon> entreeBons,
			Set<BonCadre> bonCadres, Set<BonMission> bonMissions) {
		this.typeCarburant = typeCarburant;
		this.litre = litre;
		this.qte = qte;
		this.entreeBons = entreeBons;
		this.bonCadres = bonCadres;
		this.bonMissions = bonMissions;
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
	@JoinColumn(name = "id_type_carburant", nullable = false)
	public TypeCarburant getTypeCarburant() {
		return this.typeCarburant;
	}

	public void setTypeCarburant(TypeCarburant typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

	@Column(name = "litre", nullable = false)
	public int getLitre() {
		return this.litre;
	}

	public void setLitre(int litre) {
		this.litre = litre;
	}

	@Column(name = "qte", nullable = false)
	public int getQte() {
		return this.qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bonCarburant")
	public Set<EntreeBon> getEntreeBons() {
		return this.entreeBons;
	}

	public void setEntreeBons(Set<EntreeBon> entreeBons) {
		this.entreeBons = entreeBons;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bonCarburant")
	public Set<BonCadre> getBonCadres() {
		return this.bonCadres;
	}

	public void setBonCadres(Set<BonCadre> bonCadres) {
		this.bonCadres = bonCadres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bonCarburant")
	public Set<BonMission> getBonMissions() {
		return this.bonMissions;
	}

	public void setBonMissions(Set<BonMission> bonMissions) {
		this.bonMissions = bonMissions;
	}

}