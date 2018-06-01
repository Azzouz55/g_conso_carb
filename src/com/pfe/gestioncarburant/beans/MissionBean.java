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
import com.pfe.gestioncarburant.entities.Chauffeur;
import com.pfe.gestioncarburant.entities.Departement;
import com.pfe.gestioncarburant.entities.Mission;
import com.pfe.gestioncarburant.entities.Ville;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.CadreService;
import com.pfe.gestioncarburant.services.ChauffeurService;
import com.pfe.gestioncarburant.services.DepartementService;
import com.pfe.gestioncarburant.services.MissionService;
import com.pfe.gestioncarburant.services.VilleService;
import com.pfe.gestioncarburant.services.VoitureService;

@Component
@ManagedBean
@SessionScoped
public class MissionBean {
	@Autowired
	private MissionService missionService;
	@Autowired
	private VoitureService voitureService;
	@Autowired
	private CadreService cadreService;
	@Autowired
	private ChauffeurService chauffeurService;
	@Autowired
	private VilleService villeService;
	@Autowired
	private DepartementService departementService;

	private Mission mission = new Mission();
	private Cadre cadre = new Cadre();
	private Chauffeur chauffeur = new Chauffeur();
	private Voiture voiture = new Voiture();
	private Ville ville = new Ville();
	private Ville villeDest = new Ville();
	private Departement departement = new Departement();
	private Departement departementDest = new Departement();

	private List<Mission> list = new ArrayList<>();
	private List<Voiture> voitureList = new ArrayList<>();
	private List<Departement> departementDestList = new ArrayList<>();
	private List<Ville> villeDestList = new ArrayList<>();
	private int id;
	private boolean btnAdd, btnEdit, disabled, btnShow;

	public void clickAdd() {
		mission = new Mission();
		cadre = new Cadre();
		voiture = new Voiture();
		ville = new Ville();
		villeDest = new Ville();
		departement = new Departement();
		departementDest = new Departement();
		departementDestList = new ArrayList<Departement>();

		btnAdd = true;
		btnEdit = false;
		disabled = false;

	}

	public void clickEdit() {

		id = mission.getId();
		cadre = mission.getCadre();
		chauffeur = mission.getChauffeur();
		voiture = mission.getVoiture();

		// try {
		// departement = departementService.findByCadre(cadre);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		departementDest = mission.getDepartementByIdDepartementDest();

		btnAdd = false;
		btnEdit = true;
		disabled = true;

	}

	public void findDepartementByCadre() {
		try {
			this.departement = departementService.findByCadre(cadre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ajouter() {
		String[] result = new String[2];

		try {
			cadre = cadreService.getCadre(cadre);
			mission.setCadre(cadre);
			mission.setDepartementByIdDepartement(cadre.getDepartement());
			mission.setDepartementByIdDepartementDest(departementService.getDepartement(departementDest));

			result = missionService.save(mission);
			if (result[0] == "1") {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", result[1]));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerte", result[1]));
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
		String[] result = new String[2];
		try {
			result = missionService.update(mission);
			if (result[0].equals("1")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", result[1]));
				RequestContext.getCurrentInstance().addCallbackParam("added", true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerte", result[1]));
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
			missionService.delete(mission);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression effectué"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de supprission"));
			e.printStackTrace();
		}
	}

	// Injection Spring
	public MissionService getMissionService() {
		return missionService;
	}

	public void setMissionService(MissionService missionService) {
		this.missionService = missionService;
	}

	public VoitureService getVoitureService() {
		return voitureService;
	}

	public void setVoitureService(VoitureService voitureService) {
		this.voitureService = voitureService;
	}

	public CadreService getCadreService() {
		return cadreService;
	}

	public void setCadreService(CadreService cadreService) {
		this.cadreService = cadreService;
	}

	public ChauffeurService getChauffeurService() {
		return chauffeurService;
	}

	public void setChauffeurService(ChauffeurService chauffeurService) {
		this.chauffeurService = chauffeurService;
	}

	public DepartementService getDepartementService() {
		return departementService;
	}

	public void setDepartementService(DepartementService departementService) {
		this.departementService = departementService;
	}// **********************************

	public Mission getMission() {
		return mission;
	}

	public VilleService getVilleService() {
		return villeService;
	}

	public void setVilleService(VilleService villeService) {
		this.villeService = villeService;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Ville getVilleDest() {
		return villeDest;
	}

	public void setVilleDest(Ville villeDest) {
		this.villeDest = villeDest;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Departement getDepartementDest() {
		return departementDest;
	}

	public void setDepartementDest(Departement departementDest) {
		this.departementDest = departementDest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public boolean isBtnShow() {
		return btnShow;
	}

	public void setBtnShow(boolean btnShow) {
		this.btnShow = btnShow;
	}

	public List<Mission> getList() {
		try {
			list = missionService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<Mission> list) {
		this.list = list;
	}

	public void findDepartementByVille() {
		try {
			departementDestList = departementService.findByVille(villeDest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Departement> getDepartementDestList() {
		return this.departementDestList;
	}

	public void setDepartementDestList(List<Departement> departementDestList) {
		this.departementDestList = departementDestList;
	}

	public List<Ville> getVilleDestList() {
		try {
			villeDestList = villeService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return villeDestList;
	}

	public void setVilleDestList(List<Ville> villeDestList) {
		this.villeDestList = villeDestList;
	}

	public List<Voiture> getVoitureList() {
		try {
			voitureList = voitureService.findVoitureServiceNonAffecte();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voitureList;
	}

	public void setVoitureList(List<Voiture> voitureList) {
		this.voitureList = voitureList;
	}

}
