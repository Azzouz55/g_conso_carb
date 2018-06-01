package com.pfe.gestioncarburant.entities;
// Generated May 16, 2018 6:55:08 PM by Hibernate Tools 5.1.7.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AffectationId generated by hbm2java
 */
@Embeddable
public class AffectationId implements java.io.Serializable {

	private String matriculeCadre;
	private String matriculeVoiture;
	private Date dateAffectation;

	public AffectationId() {
	}

	public AffectationId(String matriculeCadre, String matriculeVoiture, Date dateAffectation) {
		this.matriculeCadre = matriculeCadre;
		this.matriculeVoiture = matriculeVoiture;
		this.dateAffectation = dateAffectation;
	}

	@Column(name = "matricule_cadre", nullable = false, length = 8)
	public String getMatriculeCadre() {
		return this.matriculeCadre;
	}

	public void setMatriculeCadre(String matriculeCadre) {
		this.matriculeCadre = matriculeCadre;
	}

	@Column(name = "matricule_voiture", nullable = false, length = 10)
	public String getMatriculeVoiture() {
		return this.matriculeVoiture;
	}

	public void setMatriculeVoiture(String matriculeVoiture) {
		this.matriculeVoiture = matriculeVoiture;
	}

	@Column(name = "date_affectation", nullable = false, length = 10)
	public Date getDateAffectation() {
		return this.dateAffectation;
	}

	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AffectationId))
			return false;
		AffectationId castOther = (AffectationId) other;

		return ((this.getMatriculeCadre() == castOther.getMatriculeCadre())
				|| (this.getMatriculeCadre() != null && castOther.getMatriculeCadre() != null
						&& this.getMatriculeCadre().equals(castOther.getMatriculeCadre())))
				&& ((this.getMatriculeVoiture() == castOther.getMatriculeVoiture())
						|| (this.getMatriculeVoiture() != null && castOther.getMatriculeVoiture() != null
								&& this.getMatriculeVoiture().equals(castOther.getMatriculeVoiture())))
				&& ((this.getDateAffectation() == castOther.getDateAffectation())
						|| (this.getDateAffectation() != null && castOther.getDateAffectation() != null
								&& this.getDateAffectation().equals(castOther.getDateAffectation())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMatriculeCadre() == null ? 0 : this.getMatriculeCadre().hashCode());
		result = 37 * result + (getMatriculeVoiture() == null ? 0 : this.getMatriculeVoiture().hashCode());
		result = 37 * result + (getDateAffectation() == null ? 0 : this.getDateAffectation().hashCode());
		return result;
	}

}
