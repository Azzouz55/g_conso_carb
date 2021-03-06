package com.pfe.gestioncarburant.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.pfe.gestioncarburant.entities.BonCadre;
import com.pfe.gestioncarburant.entities.BonCadreId;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.Cadre;
import com.pfe.gestioncarburant.entities.Voiture;
import com.pfe.gestioncarburant.services.AffectationVoitureService;
import com.pfe.gestioncarburant.services.BonCadreService;
import com.pfe.gestioncarburant.services.BonCarburantService;
import com.pfe.gestioncarburant.services.CadreService;
import com.pfe.gestioncarburant.services.VoitureService;

@Component
@ManagedBean
@ViewScoped
public class BonCadreBean {
	@Autowired
	private BonCarburantService bonCarburantService;
	@Autowired
	private BonCadreService bonCadreService;
	@Autowired
	private CadreService cadreService;
	@Autowired
	private VoitureService voitureService;
	@Autowired
	private AffectationVoitureService affectationVoitureService;

	private BonCadre bonCadre = new BonCadre();
	private BonCadreId id = new BonCadreId();
	private BonCarburant bonCarburant = new BonCarburant();
	private Cadre cadre;
	private Voiture voiture = new Voiture();
	private Affectation affectation;
	private List<BonCadre> list = new ArrayList<>();
	private List<BonCarburant> listBonCarburant = new ArrayList<>();
	private Calendar date = Calendar.getInstance();
	private boolean btnAdd, btnEdit, disabled;

	// Rederiction � la page Cadrexhtml
	// si le cadre s�l�ctionn� n'obtient encore de voiture
	public void redirect() {
		try {
			affectation = (Affectation) affectationVoitureService.findAffectationByCadre(cadre, null).get(0);
			voiture = (Voiture) voitureService.findVoitureByCadre(affectation).get(0);
			FacesContext context = FacesContext.getCurrentInstance();
			if (voiture != null) {
				ExternalContext externalContext = context.getExternalContext();
				externalContext.redirect(externalContext.getRequestContextPath() + "/faces/views/BonCadre.xhtml");
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerte",
							"S'il vous plait, affectez une voiture pour le cadre n�:'" + cadre.getMatricule()
									+ "' avant l'affectation des bons"));
			e.printStackTrace();
		}
	}

	public void clickAdd() {
		bonCadre = new BonCadre();
		id = new BonCadreId();
		bonCarburant = new BonCarburant();

		btnAdd = true;
		btnEdit = false;
		disabled = false;
	}

	public void clickEdit() {
		id = bonCadre.getId();
		bonCarburant = bonCadre.getBonCarburant();

		btnAdd = false;
		btnEdit = true;
		disabled = true;
	}

	public void ajouter() {
		try {
			Cadre cadre2 = cadreService.getCadre(cadre);
			BonCarburant bonCarburant2 = bonCarburantService.findBonCarburantByBonCarburant(id.getIdBonCarburant());
			bonCadre.setCadre(cadre2);
			id.setMatriculeCadre(cadre.getMatricule());
			bonCadre.setId(id);
			bonCadre.setBonCarburant(bonCarburant2);

			String[] test = bonCadreService.save(bonCadre);
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur d'affectation"));
			RequestContext.getCurrentInstance().addCallbackParam("added", false);
			e.printStackTrace();
		}
	}

	public void modifier() {
		try {
			String test[] = bonCadreService.update(bonCadre);
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
			bonCadreService.delete(bonCadre);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succ�s", "Suppression effectu�"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Erreur de suppression"));
			e.printStackTrace();
		}
	}

	public BonCadre getBonCadre() {
		return bonCadre;
	}

	public void setBonCadre(BonCadre bonCadre) {
		this.bonCadre = bonCadre;
	}

	public BonCadreId getId() {
		return id;
	}

	public void setId(BonCadreId id) {
		this.id = id;
	}

	public BonCarburant getBonCarburant() {
		return bonCarburant;
	}

	public void setBonCarburant(BonCarburant bonCarburant) {
		this.bonCarburant = bonCarburant;
	}

	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public List<BonCadre> getList() {
		try {
			list = bonCadreService.findBonCadreByCadre(cadre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void setList(List<BonCadre> list) {
		this.list = list;
	}

	public List<BonCarburant> getListBonCarburant() {
		try {
			affectation = (Affectation) affectationVoitureService.findAffectationByCadre(cadre, null).get(0);
			voiture = (Voiture) voitureService.findVoitureByCadre(affectation).get(0);
			listBonCarburant = bonCarburantService.findBonCarburantByVoiture(voiture);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBonCarburant;
	}

	public void setListBonCarburant(List<BonCarburant> listBonCarburant) {
		this.listBonCarburant = listBonCarburant;
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getTotalLitre() {
		int total = 9999;
		setDisabled(false);
		try {
			total = bonCadreService.getTotalLitre(cadre);
			setDisabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	public int getTotalLitreByMonth() {
		int total = 9999;
		try {
			total = bonCadreService.getTotalLitreByDate(cadre, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
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
