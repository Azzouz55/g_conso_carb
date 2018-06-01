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

import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Categorie;
import com.pfe.gestioncarburant.entities.Departement;
import com.pfe.gestioncarburant.services.CadreService;
import com.pfe.gestioncarburant.services.CategorieService;
import com.pfe.gestioncarburant.services.DepartementService;

@Component
@ManagedBean
@SessionScoped
public class CadreBean {

	@Autowired
	private CadreService cadreService;
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private DepartementService departementService;

	private Cadre cadre = new Cadre();
	private List<Cadre> list = new ArrayList<Cadre>();
	private List<Categorie> listCategorie = new ArrayList<Categorie>();
	private List<Departement> listDepartement = new ArrayList<Departement>();
	private boolean btnAdd, btnEdit;

	public void clickAdd() {
		cadre = new Cadre();
		btnAdd = true;
		btnEdit = false;
	}

	public void clickEdit() {

		try {
			listCategorie = categorieService.findAll();
			listDepartement = departementService.findAll();
			btnAdd = false;
			btnEdit = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ajouter() {
		try {
			String[] test = cadreService.save(cadre);
			if (test[0] == "1") {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", test[1]));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", test[1]));
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
			String test[] = cadreService.update(cadre);
			if (test[0] == "1") {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", test[1]));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", test[1]));
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
			cadreService.delete(cadre);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	public List<Cadre> getList() {
		try {
			list = cadreService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<Cadre> list) {
		this.list = list;
	}

	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	public List<Categorie> getListCategorie() {
		try {
			listCategorie = categorieService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCategorie;
	}

	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}

	public List<Departement> getListDepartement() {
		try {
			listDepartement = departementService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDepartement;
	}

	public void setListDepartement(List<Departement> listDepartement) {
		this.listDepartement = listDepartement;
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

	public CadreService getCadreService() {
		return cadreService;
	}

	public void setCadreService(CadreService cadreService) {
		this.cadreService = cadreService;
	}

	public CategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public DepartementService getDepartementService() {
		return departementService;
	}

	public void setDepartementService(DepartementService departementService) {
		this.departementService = departementService;
	}

}
