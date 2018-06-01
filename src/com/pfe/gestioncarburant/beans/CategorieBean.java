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

import com.pfe.gestioncarburant.entities.Categorie;
import com.pfe.gestioncarburant.services.CategorieService;

@Component
@ManagedBean
@ViewScoped
public class CategorieBean {
	@Autowired
	private CategorieService categorieService;
	private Categorie categorie = new Categorie();
	private List<Categorie> list = new ArrayList<Categorie>();
	private boolean btnAdd, btnEdit;

	public void clickAdd() {
		categorie = new Categorie();
		btnAdd = true;
		btnEdit = false;
	}

	public void clickEdit() {
		btnAdd = false;
		btnEdit = true;
	}

	public void ajouter() {
		try {
			boolean test = categorieService.save(categorie);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Ajout effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "Categorie existant"));
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
			boolean test = categorieService.update(categorie);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Modification effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "Categorie existant"));
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
			categorieService.delete(categorie);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	public List<Categorie> getList() {
		try {
			list = categorieService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<Categorie> list) {
		this.list = list;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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

	public CategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

}
