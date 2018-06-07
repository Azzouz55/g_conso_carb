package com.pfe.gestioncarburant.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.EntreeBon;
import com.pfe.gestioncarburant.services.EntreeBonService;

@Component
@ManagedBean
@ViewScoped
public class EntreBonBean {
	@Autowired
	private EntreeBonService entreeBonService;
	private EntreeBon entreeBon = new EntreeBon();
	private List<EntreeBon> list = new ArrayList<EntreeBon>();
	private BonCarburant bonCarburant = new BonCarburant();
	private boolean btnAdd, btnEdit;

	public void clickAdd() {
		entreeBon = new EntreeBon();
		bonCarburant = new BonCarburant();
		btnAdd = true;
		btnEdit = false;
	}

	public void clickEdit() {

		try {
			btnAdd = false;
			btnEdit = true;
			bonCarburant = entreeBon.getBonCarburant();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ajouter() {
		try {

			entreeBon.setBonCarburant(bonCarburant);
			boolean test = entreeBonService.save(entreeBon);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Ajout effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "entreBon existant"));
				RequestContext.getCurrentInstance().addCallbackParam("added", false);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur d'insertion"));
			RequestContext.getCurrentInstance().addCallbackParam("added", false);

			e.printStackTrace();
		}
	}

	public void modifier() {
		try {

			entreeBon.setBonCarburant(bonCarburant);
			boolean test = entreeBonService.update(entreeBon);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Modification effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "entreBon existant"));
				RequestContext.getCurrentInstance().addCallbackParam("added", false);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de modification"));
			RequestContext.getCurrentInstance().addCallbackParam("added", false);

			e.printStackTrace();
		}
	}

	public void supprimer() {
		try {
			entreeBonService.delete(entreeBon);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	public List<EntreeBon> getList() {
		try {
			list = entreeBonService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<EntreeBon> list) {
		this.list = list;
	}

	public EntreeBon getentreBon() {
		return entreeBon;
	}

	public void setentreBon(EntreeBon entreBon) {
		this.entreeBon = entreBon;
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

	public BonCarburant getbonCarburant() {
		return bonCarburant;
	}

	public void setbonCarburant(BonCarburant bonCarburant) {
		this.bonCarburant = bonCarburant;
	}

}
