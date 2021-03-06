package com.pfe.gestioncarburant.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfe.gestioncarburant.entities.BonCadre;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Categorie;
import com.pfe.gestioncarburant.entities.Departement;
import com.pfe.gestioncarburant.services.BonCadreService;
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
	private BonCadreService bonCadreService;
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private DepartementService departementService;

	private Cadre cadre = new Cadre();
	private Categorie categorie = new Categorie();
	private Departement departement = new Departement();
	private List<Cadre> list = new ArrayList<Cadre>();
	private List<Categorie> listCategorie = new ArrayList<Categorie>();
	private List<Departement> listDepartement = new ArrayList<Departement>();
	private boolean btnAdd, btnEdit;

	public void clickAdd() {
		cadre = new Cadre();
		categorie = new Categorie();
		departement = new Departement();
		btnAdd = true;
		btnEdit = false;
	}

	public void clickEdit() {
		departement = cadre.getDepartement();
		categorie = cadre.getCategorie();
		btnAdd = false;
		btnEdit = true;

	}

	public void ajouter() {
		try {
			cadre.setCategorie(categorie);
			cadre.setDepartement(departement);
			String[] test = cadreService.save(cadre);
			if (test[0] == "1") {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succ�s", test[1]));
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
			cadre.setCategorie(categorie);
			cadre.setDepartement(departement);
			String test[] = cadreService.update(cadre);
			if (test[0] == "1") {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succ�s", test[1]));
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
			List<BonCadre> listBonCadre = bonCadreService.findBonCadreByCadre(cadre);
			if (listBonCadre.isEmpty()) {
				cadreService.delete(cadre);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succ�s", "Suppression effectu�"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Attention", "Cadre : " + cadre.getMatricule() + " avoir au moins une voiture"));

			}

		} catch (ConstraintViolationException exceptionViolation) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention",
					"Cadre : " + cadre.getMatricule() + " avoir au moins une voiture"));
			exceptionViolation.printStackTrace();
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

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
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
}
