package com.pfe.gestioncarburant.entities;
// Generated May 16, 2018 6:55:08 PM by Hibernate Tools 5.1.7.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BonCadre generated by hbm2java
 */
@Entity
@Table(name = "bon_cadre", catalog = "gestion_carburant_db")
public class BonCadre implements java.io.Serializable {

	private BonCadreId id;
	private BonCarburant bonCarburant;
	private Cadre cadre;
	private int qte;
	private Boolean confirmation;

	public BonCadre() {
	}

	public BonCadre(BonCadreId id, BonCarburant bonCarburant, Cadre cadre, int qte) {
		this.id = id;
		this.bonCarburant = bonCarburant;
		this.cadre = cadre;
		this.qte = qte;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "matriculeCadre", column = @Column(name = "matricule_cadre", nullable = false, length = 8)),
			@AttributeOverride(name = "idBonCarburant", column = @Column(name = "id_bon_carburant", nullable = false)),
			@AttributeOverride(name = "dateAffectation", column = @Column(name = "date_affectation", nullable = false, length = 10)) })
	public BonCadreId getId() {
		return this.id;
	}

	public void setId(BonCadreId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bon_carburant", nullable = false, insertable = false, updatable = false)
	public BonCarburant getBonCarburant() {
		return this.bonCarburant;
	}

	public void setBonCarburant(BonCarburant bonCarburant) {
		this.bonCarburant = bonCarburant;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "matricule_cadre", nullable = false, insertable = false, updatable = false)
	public Cadre getCadre() {
		return this.cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	@Column(name = "qte", nullable = false)
	public int getQte() {
		return this.qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	@Column(name = "confirmation")
	public Boolean getConfirmation() {
		return this.confirmation;
	}

	public void setConfirmation(Boolean confirmation) {
		this.confirmation = confirmation;
	}

}
