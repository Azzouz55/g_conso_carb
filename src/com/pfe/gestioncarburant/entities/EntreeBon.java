package com.pfe.gestioncarburant.entities;
// Generated May 16, 2018 6:55:08 PM by Hibernate Tools 5.1.7.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EntreeBon generated by hbm2java
 */
@Entity
@Table(name = "entree_bon", catalog = "gestion_carburant_db")
public class EntreeBon implements java.io.Serializable {

	private Integer id;
	private BonCarburant bonCarburant;
	private Date date;
	private int qte;

	public EntreeBon() {
	}

	public EntreeBon(BonCarburant bonCarburant, Date date, int qte) {
		this.bonCarburant = bonCarburant;
		this.date = date;
		this.qte = qte;
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
	@JoinColumn(name = "id_bon_carburant", nullable = false)
	public BonCarburant getBonCarburant() {
		return this.bonCarburant;
	}

	public void setBonCarburant(BonCarburant bonCarburant) {
		this.bonCarburant = bonCarburant;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "qte", nullable = false)
	public int getQte() {
		return this.qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

}