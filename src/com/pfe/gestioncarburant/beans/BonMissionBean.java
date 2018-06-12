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

import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.BonMission;
import com.pfe.gestioncarburant.entities.BonMissionId;
import com.pfe.gestioncarburant.entities.Mission;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.BonCarburantService;
import com.pfe.gestioncarburant.services.BonMissionService;

@Component
@ManagedBean
@ViewScoped
public class BonMissionBean {
	@Autowired
	private BonCarburantService bonCarburantService;
	@Autowired
	private BonMissionService bonMissionService;

	private BonMission bonMission = new BonMission();
	private BonMissionId id = new BonMissionId();
	private BonCarburant bonCarburant = new BonCarburant();
	private Voiture voiture = new Voiture();
	private Mission mission;
	private List<BonMission> list = new ArrayList<>();
	private List<BonCarburant> listBonCarburant = new ArrayList<>();
	private boolean btnAdd, btnEdit, disabled;

	public void clickAdd() {
		bonMission = new BonMission();
		id = new BonMissionId();
		bonCarburant = new BonCarburant();

		btnAdd = true;
		btnEdit = false;
		disabled = false;
	}

	public void clickEdit() {
		id = bonMission.getId();
		bonCarburant = bonMission.getBonCarburant();

		btnAdd = false;
		btnEdit = true;
		disabled = true;
	}

	public void ajouter() {
		try {
			id.setIdMission(mission.getId());
			bonMission.setId(id);
			String[] test = bonMissionService.save(bonMission);
			if (mission.getDateRetour() == null) {
				if (test[0] == "1") {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", test[1]));
					RequestContext.getCurrentInstance().addCallbackParam("added", true);
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", test[1]));
					RequestContext.getCurrentInstance().addCallbackParam("added", false);
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "Mission finie"));
				RequestContext.getCurrentInstance().addCallbackParam("added", false);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur d'affectation"));
			RequestContext.getCurrentInstance().addCallbackParam("added", false);
			e.printStackTrace();
		}
	}

	public void modifier() {
		try {
			if (mission.getDateRetour() == null) {
				String test[] = bonMissionService.update(bonMission);
				if (test[0] == "1") {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", test[1]));
					RequestContext.getCurrentInstance().addCallbackParam("added", true);
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", test[1]));
					RequestContext.getCurrentInstance().addCallbackParam("added", false);
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte", "Mission finie"));
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
			bonMissionService.delete(bonMission);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	// Getters and setters
	public BonMission getBonMission() {
		return bonMission;
	}

	public void setBonMission(BonMission bonMission) {
		this.bonMission = bonMission;
	}

	public BonMissionId getId() {
		return id;
	}

	public void setId(BonMissionId id) {
		this.id = id;
	}

	public BonCarburant getBonCarburant() {
		return bonCarburant;
	}

	public void setBonCarburant(BonCarburant bonCarburant) {
		this.bonCarburant = bonCarburant;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
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

	public List<BonMission> getList() {
		try {
			list = bonMissionService.findBonMissionByMission(mission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<BonMission> list) {
		this.list = list;
	}

	// List bon carburant par rapport la voiture de mission
	public List<BonCarburant> getListBonCarburant() {
		try {
			listBonCarburant = bonCarburantService.findBonCarburantByVoiture(mission.getVoiture());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listBonCarburant;
	}

	public void setListBonCarburant(List<BonCarburant> listBonCarburant) {
		this.listBonCarburant = listBonCarburant;
	}

	// Calcul de total des litres affectées
	public int getTotalLitre() {
		int total = 9999;
		try {
			total = bonMissionService.getTotalLitre(mission);
			setDisabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	// Prevent direct access to BonMission.xhtml
	public void init() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (mission == null) {
			ExternalContext externalContext = context.getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + "/faces/views/Mission.xhtml");
		}
	}
}
