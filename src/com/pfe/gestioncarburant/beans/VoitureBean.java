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

import com.pfe.gestioncarburant.entities.Marque;
import com.pfe.gestioncarburant.entities.Model;
import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.ModelService;
import com.pfe.gestioncarburant.services.VoitureService;

@Component
@ManagedBean
@ViewScoped
public class VoitureBean {

	@Autowired
	private VoitureService voitureService;
	@Autowired
	private ModelService modelService;
	private Marque marque = new Marque();
	private List<Model> listModel = new ArrayList<Model>();
	private Model model = new Model();
	private TypeCarburant typeCarburant = new TypeCarburant();

	private Voiture voiture = new Voiture();
	private List<Voiture> list = new ArrayList<Voiture>();
	private boolean btnAdd, btnEdit, read;

	public void clickAdd() {
		voiture = new Voiture();
		listModel = new ArrayList<Model>();
		marque = new Marque();
		model = new Model();
		btnAdd = true;
		btnEdit = false;
		read = false;
	}

	public void clickEdit() {

		try {
			read = true;
			btnAdd = false;
			btnEdit = true;
			typeCarburant = voiture.getTypeCarburant();
			marque = voiture.getModel().getMarque();
			listModel = modelService.findByMarque(marque);
			model = voiture.getModel();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findModelByMarque() {

		try {
			listModel = new ArrayList<Model>();
			listModel = modelService.findByMarque(marque);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ajouter() {
		try {
			voiture.setTypeCarburant(typeCarburant);
			voiture.setModel(model);
			String[] test = voitureService.save(voiture);

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
			e.printStackTrace();
			RequestContext.getCurrentInstance().addCallbackParam("added", false);

		}
	}

	public void modifier() {
		try {

			voiture.setTypeCarburant(typeCarburant);
			voiture.setModel(model);
			voitureService.update(voiture);

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
			voitureService.delete(voiture);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	public List<Voiture> getList() {
		try {
			list = voitureService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<Voiture> list) {
		this.list = list;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
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

	public List<Model> getListModel() {
		return listModel;
	}

	public void setListModel(List<Model> listModel) {
		this.listModel = listModel;
	}

	public Model getModele() {
		return model;
	}

	public void setModele(Model modele) {
		this.model = modele;
	}

	public TypeCarburant getTypeCarburant() {
		return typeCarburant;
	}

	public void setTypeCarburant(TypeCarburant typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
}
