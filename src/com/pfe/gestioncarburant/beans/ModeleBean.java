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

import com.pfe.gestioncarburant.entities.Marque;
import com.pfe.gestioncarburant.entities.Model;
import com.pfe.gestioncarburant.services.ModelService;

@Component
@ManagedBean
@ViewScoped
public class ModeleBean {
	@Autowired
	private ModelService modelService;
	private Model model = new Model();
	private List<Model> list = new ArrayList<Model>();
	private Marque marque;
	private boolean btnAdd, btnEdit;

	public void clickAdd() {
		model = new Model();
		btnAdd = true;
		btnEdit = false;
	}

	public void clickEdit() {
		btnAdd = false;
		btnEdit = true;
	}

	public void ajouter() {
		try {

			model.setMarque(marque);
			boolean test = modelService.save(model);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Ajout effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "Modele existant"));
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
			boolean test = modelService.update(model);
			if (test) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Modification effectué"));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "Modele existant"));
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
			modelService.delete(model);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	public List<Model> getList() {
		try {
			list = modelService.findByMarque(marque);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<Model> list) {
		this.list = list;
	}

	public Model getModele() {
		return model;
	}

	public void setModele(Model modele) {
		this.model = modele;
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

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	// Prevent direct access to Model.xhtml

	public void init() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (marque == null) {
			ExternalContext externalContext = context.getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/faces/admin/Marque.xhtml");
		}
	}

}
