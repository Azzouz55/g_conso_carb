package com.pfe.gestioncarburant.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.services.BonCarburantService;

@Component
@ManagedBean
@SessionScoped
public class BonCarburantBean {
	@Autowired
	private BonCarburantService bonCarburantService;
	private BonCarburant bonCarburant = new BonCarburant();
	private List<BonCarburant> list = new ArrayList<BonCarburant>();
	private TypeCarburant typeCarburant = new TypeCarburant();
	private boolean btnAdd, btnEdit;

	public void clickAdd() {
		bonCarburant = new BonCarburant();
		typeCarburant = new TypeCarburant();
		btnAdd = true;
		btnEdit = false;
	}

	public void clickEdit() {

		try {
			btnAdd = false;
			btnEdit = true;
			typeCarburant = bonCarburant.getTypeCarburant();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ajouter() {
		try {

			bonCarburant.setTypeCarburant(typeCarburant);
			boolean test = bonCarburantService.save(bonCarburant);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Ajout effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "BonCarburant existant"));
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

			bonCarburant.setTypeCarburant(typeCarburant);
			boolean test = bonCarburantService.update(bonCarburant);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Modification effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "BonCarburant existant"));
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
			bonCarburantService.delete(bonCarburant);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	public List<BonCarburant> getList() {
		try {
			list = bonCarburantService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<BonCarburant> list) {
		this.list = list;
	}

	public BonCarburant getBonCarburant() {
		return bonCarburant;
	}

	public void setBonCarburant(BonCarburant bonCarburant) {
		this.bonCarburant = bonCarburant;
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

	public TypeCarburant getTypeCarburant() {
		return typeCarburant;
	}

	public void setTypeCarburant(TypeCarburant typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

	public BonCarburantService getBonCarburantService() {
		return bonCarburantService;
	}

	public void setBonCarburantService(BonCarburantService bonCarburantService) {
		this.bonCarburantService = bonCarburantService;
	}

}
