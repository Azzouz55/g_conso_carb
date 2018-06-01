package com.pfe.gestioncarburant.entities;
// Generated May 23, 2018 7:02:01 PM by Hibernate Tools 5.1.7.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mission generated by hbm2java
 */
@Entity
@Table(name = "mission", catalog = "gestion_carburant_db")
public class Mission implements java.io.Serializable {

	private Integer id;
	private Cadre cadre;
	private Chauffeur chauffeur;
	private Departement departementByIdDepartement;
	private Departement departementByIdDepartementDest;
	private Voiture voiture;
	private String description;
	private Date dateSortie;
	private Date dateRetour;
	private int odometreSortie;
	private Integer odometreRetour;
	private double distancePrevue;
	private int distanceParcourue;
	private Set<BonMission> bonMissions = new HashSet<BonMission>(0);

	public Mission() {
		cadre = new Cadre();
		chauffeur = new Chauffeur();
		voiture = new Voiture();
		departementByIdDepartement = new Departement();
		departementByIdDepartementDest = new Departement();
	}

	public Mission(Cadre cadre, Chauffeur chauffeur, Departement departementByIdDepartement,
			Departement departementByIdDepartementDest, Voiture voiture, Date dateSortie, int odometreSortie,
			double distancePrevue, int distanceParcourue) {
		this.cadre = cadre;
		this.chauffeur = chauffeur;
		this.departementByIdDepartement = departementByIdDepartement;
		this.departementByIdDepartementDest = departementByIdDepartementDest;
		this.voiture = voiture;
		this.dateSortie = dateSortie;
		this.odometreSortie = odometreSortie;
		this.distancePrevue = distancePrevue;
		this.distanceParcourue = distanceParcourue;
	}

	public Mission(Cadre cadre, Chauffeur chauffeur, Departement departementByIdDepartement,
			Departement departementByIdDepartementDest, Voiture voiture, String description, Date dateSortie,
			Date dateRetour, int odometreSortie, Integer odometreRetour, double distancePrevue, int distanceParcourue,
			Set<BonMission> bonMissions) {
		this.cadre = cadre;
		this.chauffeur = chauffeur;
		this.departementByIdDepartement = departementByIdDepartement;
		this.departementByIdDepartementDest = departementByIdDepartementDest;
		this.voiture = voiture;
		this.description = description;
		this.dateSortie = dateSortie;
		this.dateRetour = dateRetour;
		this.odometreSortie = odometreSortie;
		this.odometreRetour = odometreRetour;
		this.distancePrevue = distancePrevue;
		this.distanceParcourue = distanceParcourue;
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
	@JoinColumn(name = "matricule_cadre", nullable = false)
	public Cadre getCadre() {
		return this.cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "matricule_chauffeur", nullable = false)
	public Chauffeur getChauffeur() {
		return this.chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_departement", nullable = false)
	public Departement getDepartementByIdDepartement() {
		return this.departementByIdDepartement;
	}

	public void setDepartementByIdDepartement(Departement departementByIdDepartement) {
		this.departementByIdDepartement = departementByIdDepartement;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_departement_dest", nullable = false)
	public Departement getDepartementByIdDepartementDest() {
		return this.departementByIdDepartementDest;
	}

	public void setDepartementByIdDepartementDest(Departement departementByIdDepartementDest) {
		this.departementByIdDepartementDest = departementByIdDepartementDest;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "matricule_voiture", nullable = false)
	public Voiture getVoiture() {
		return this.voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_sortie", nullable = false, length = 10)
	public Date getDateSortie() {
		return this.dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_retour", length = 10)
	public Date getDateRetour() {
		return this.dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	@Column(name = "odometre_sortie", nullable = false)
	public int getOdometreSortie() {
		return this.odometreSortie;
	}

	public void setOdometreSortie(int odometreSortie) {
		this.odometreSortie = odometreSortie;
	}

	@Column(name = "odometre_retour")
	public Integer getOdometreRetour() {
		return this.odometreRetour;
	}

	public void setOdometreRetour(Integer odometreRetour) {
		this.odometreRetour = odometreRetour;
	}

	@Column(name = "distance_prevue", nullable = false, precision = 22, scale = 0)
	public double getDistancePrevue() {
		return this.distancePrevue;
	}

	public void setDistancePrevue(double distancePrevue) {
		this.distancePrevue = distancePrevue;
	}

	@Column(name = "distance_parcourue", nullable = false)
	public int getDistanceParcourue() {
		return this.distanceParcourue;
	}

	public void setDistanceParcourue(int distanceParcourue) {
		this.distanceParcourue = distanceParcourue;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mission")
	public Set<BonMission> getBonMissions() {
		return this.bonMissions;
	}

	public void setBonMissions(Set<BonMission> bonMissions) {
		this.bonMissions = bonMissions;
	}

}