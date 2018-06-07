package com.pfe.gestioncarburant.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfe.gestioncarburant.entities.Affectation;
import com.pfe.gestioncarburant.entities.AffectationId;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.AffectationVoitureService;
import com.pfe.gestioncarburant.services.VoitureService;

@Component
@ManagedBean
@ViewScoped
public class AffectationVoitureCadreBean {
	@Autowired
	private VoitureService voitureService;
	@Autowired
	private AffectationVoitureService affectationVoitureService;
	private Affectation affectation = new Affectation();
	private AffectationId id = new AffectationId();
	private Voiture voiture = new Voiture();
	private Cadre cadre;
	private List<Affectation> list = new ArrayList<>();
	private List<Voiture> listVoiture = new ArrayList<>();
	private boolean btnAdd, btnEdit, disabled;

	public void clickAdd() {
		affectation = new Affectation();
		id = new AffectationId();
		voiture = new Voiture();

		btnAdd = true;
		btnEdit = false;
		disabled = false;

	}

	public void clickEdit() {

		id = affectation.getId();
		voiture = affectation.getVoiture();

		btnAdd = false;
		btnEdit = true;
		disabled = true;

	}

	public void ajouter() {
		id.setMatriculeCadre(cadre.getMatricule());
		id.setMatriculeVoiture(voiture.getMatricule());
		affectation.setId(id);
		try {
			affectationVoitureService.save(affectation);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Ajout effectué"));
			RequestContext.getCurrentInstance().addCallbackParam("added", true);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur d'insertion"));
			RequestContext.getCurrentInstance().addCallbackParam("added", false);
			e.printStackTrace();
		}
	}

	public void modifier() {
		id.setMatriculeCadre(cadre.getMatricule());
		id.setMatriculeVoiture(voiture.getMatricule());
		affectation.setId(id);
		try {
			affectationVoitureService.update(affectation);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Modification effectué"));
			RequestContext.getCurrentInstance().addCallbackParam("added", true);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de modification"));
			RequestContext.getCurrentInstance().addCallbackParam("added", false);
			e.printStackTrace();
		}
	}

	public void supprimer() {

		try {
			affectationVoitureService.delete(affectation);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de supprission"));
			e.printStackTrace();
		}
	}

	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	public List<Affectation> getList() {

		try {
			list = affectationVoitureService.findAffectationByCadre(cadre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<Affectation> list) {
		this.list = list;
	}

	public Affectation getAffectation() {
		return affectation;
	}

	public void setAffectation(Affectation affectation) {
		this.affectation = affectation;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public List<Voiture> getListVoiture() {
		try {
			listVoiture = voitureService.findVoitureFonctionNonAffecte();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listVoiture;
	}

	public void setListVoiture(List<Voiture> listVoiture) {
		this.listVoiture = listVoiture;
	}

	public AffectationId getId() {
		return id;
	}

	public void setId(AffectationId id) {
		this.id = id;
	}

	public boolean isBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(boolean btnAdd) {
		this.btnAdd = btnAdd;
	}

	public boolean isBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(boolean btnEdit) {
		this.btnEdit = btnEdit;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	// Prevent direct access to AffectationVoitureCadre.xhtml
	public void init() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (cadre == null) {
			ExternalContext externalContext = context.getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/faces/views/Cadre.xhtml");
		}
	}

}
