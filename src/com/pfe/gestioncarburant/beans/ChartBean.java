package com.pfe.gestioncarburant.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfe.gestioncarburant.dto.MoisDTO;
import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.services.BonCadreService;
import com.pfe.gestioncarburant.services.BonMissionService;
import com.pfe.gestioncarburant.services.ChartService;
import com.pfe.gestioncarburant.services.TypeCarburantService;

@Component
@ManagedBean
@SessionScoped
public class ChartBean {
	@Autowired
	private BonMissionService bonMissionService;
	@Autowired
	private BonCadreService bonCadreService;
	@Autowired
	private TypeCarburantService typeCarburantService;
	@Autowired
	private ChartService chartService;

	private BarChartModel barModel1;
	private Calendar date = Calendar.getInstance();
	private int critere = 1;
	private String valeur = String.valueOf(date.get(Calendar.MONTH) + 1);
	private List<String> listValeur = new ArrayList<>();

	public BarChartModel init() {
		barModel1 = initBarModel();
		return barModel1;
	}

	public void findValeurByCritere() {
		listValeur = new ArrayList<>();
		setList(chartService.findValeurByCritere(critere, date));

	}

	public void afficher() {
		barModel1 = initBarModel();
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		model.setTitle("Diagramme des sorties des bons");
		model.setLegendPosition("ne");
		model.setAnimate(true);

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setLabel("Mois");
		xAxis.setMin(0);
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setLabel("Quantité");
		yAxis.setMin(0);

		try {
			List<TypeCarburant> list = typeCarburantService.findAll();
			for (TypeCarburant type : list) {
				ChartSeries chartSerie = new ChartSeries();
				// TODO Année dynamique
				chartSerie.setLabel(type.getLibelle());
				if (critere == 3) {
					MoisDTO bonMission = bonMissionService.findByYear(type, Integer.parseInt(valeur));
					MoisDTO bonCadre = bonCadreService.findByYear(type, Integer.parseInt(valeur));
					chartSerie.set("Janvier", bonMission.getJanvier() + bonCadre.getJanvier());
					chartSerie.set("Février", bonMission.getFevrier() + bonCadre.getFevrier());
					chartSerie.set("Mars", bonMission.getMars() + bonCadre.getMars());
					chartSerie.set("Avril", bonMission.getAvril() + bonCadre.getAvril());
					chartSerie.set("Mai", bonMission.getMai() + bonCadre.getMai());
					chartSerie.set("Juin", bonMission.getJuin() + bonCadre.getJuin());
					chartSerie.set("Juillet", bonMission.getJuillet() + bonCadre.getJuillet());
					chartSerie.set("Aout", bonMission.getAout() + bonCadre.getAout());
					chartSerie.set("Septembre", bonMission.getSeptembre() + bonCadre.getSeptembre());
					chartSerie.set("Octobre", bonMission.getOctobre() + bonCadre.getNouvembre());
					chartSerie.set("Nouvembre", bonMission.getNouvembre() + bonCadre.getNouvembre());
					chartSerie.set("Décembre", bonMission.getDecembre() + bonCadre.getDecembre());
				}
				if (critere == 2) {
					MoisDTO bonMission = bonMissionService.findByYear(type, date.get(Calendar.YEAR));
					MoisDTO bonCadre = bonCadreService.findByYear(type, date.get(Calendar.YEAR));
					switch (valeur) {
					case "Trimestre 1":
						chartSerie.set("Janvier", bonMission.getJanvier() + bonCadre.getJanvier());
						chartSerie.set("Février", bonMission.getFevrier() + bonCadre.getFevrier());
						chartSerie.set("Mars", bonMission.getMars() + bonCadre.getMars());
						break;
					case "Trimestre 2":
						chartSerie.set("Avril", bonMission.getAvril() + bonCadre.getAvril());
						chartSerie.set("Mai", bonMission.getMai() + bonCadre.getMai());
						chartSerie.set("Juin", bonMission.getJuin() + bonCadre.getJuin());
						break;
					case "Trimestre 3":
						chartSerie.set("Juillet", bonMission.getJuillet() + bonCadre.getJuillet());
						chartSerie.set("Aout", bonMission.getAout() + bonCadre.getAout());
						chartSerie.set("Septembre", bonMission.getSeptembre() + bonCadre.getSeptembre());
						break;
					case "Trimestre 4":
						chartSerie.set("Octobre", bonMission.getOctobre() + bonCadre.getNouvembre());
						chartSerie.set("Nouvembre", bonMission.getNouvembre() + bonCadre.getNouvembre());
						chartSerie.set("Décembre", bonMission.getDecembre() + bonCadre.getDecembre());
						break;

					}

					// if (valeur.equals("Trimestre 1")) {
					// chartSerie.set("Janvier", bonMission.getJanvier() + bonCadre.getJanvier());
					// chartSerie.set("Février", bonMission.getFevrier() + bonCadre.getFevrier());
					// chartSerie.set("Mars", bonMission.getMars() + bonCadre.getMars());
					// }
					// if (valeur.equals("Trimestre 2")) {
					// chartSerie.set("Avril", bonMission.getAvril() + bonCadre.getAvril());
					// chartSerie.set("Mai", bonMission.getMai() + bonCadre.getMai());
					// chartSerie.set("Juin", bonMission.getJuin() + bonCadre.getJuin());
					// }
					// if (valeur.equals("Trimestre 2")) {
					// chartSerie.set("Juillet", bonMission.getJuillet() + bonCadre.getJuillet());
					// chartSerie.set("Aout", bonMission.getAout() + bonCadre.getAout());
					// chartSerie.set("Septembre", bonMission.getSeptembre() +
					// bonCadre.getSeptembre());
					// }
					// if (valeur.equals("Trimestre 2")) {
					// chartSerie.set("Octobre", bonMission.getOctobre() + bonCadre.getNouvembre());
					// chartSerie.set("Nouvembre", bonMission.getNouvembre() +
					// bonCadre.getNouvembre());
					// chartSerie.set("Décembre", bonMission.getDecembre() +
					// bonCadre.getDecembre());
					// }
				}
				if (critere == 1) {
					MoisDTO bonMission = bonMissionService.findByYear(type, date.get(Calendar.YEAR));
					MoisDTO bonCadre = bonCadreService.findByYear(type, date.get(Calendar.YEAR));
					switch (valeur) {
					case "Janvier":
						chartSerie.set("Janvier", bonMission.getJanvier() + bonCadre.getJanvier());
						break;
					case "Février":
						chartSerie.set("Février", bonMission.getFevrier() + bonCadre.getFevrier());
						break;
					case "Mars":
						chartSerie.set("Mars", bonMission.getMars() + bonCadre.getMars());
						break;
					case "Avril":
						chartSerie.set("Avril", bonMission.getAvril() + bonCadre.getAvril());
						break;
					case "Mai":
						chartSerie.set("Mai", bonMission.getMai() + bonCadre.getMai());
						break;
					case "Juin":
						chartSerie.set("Juin", bonMission.getJuin() + bonCadre.getJuin());
						break;
					case "Juillet":
						chartSerie.set("Juillet", bonMission.getJuillet() + bonCadre.getJuillet());
						break;
					case "Aout":
						chartSerie.set("Aout", bonMission.getAout() + bonCadre.getAout());
						break;
					case "Septembre":
						chartSerie.set("Septembre", bonMission.getSeptembre() + bonCadre.getSeptembre());
						break;
					case "Octobre":
						chartSerie.set("Octobre", bonMission.getOctobre() + bonCadre.getNouvembre());
						break;
					case "Nouvembre":
						chartSerie.set("Nouvembre", bonMission.getNouvembre() + bonCadre.getNouvembre());
						break;
					case "Décembre":
						chartSerie.set("Décembre", bonMission.getDecembre() + bonCadre.getDecembre());
						break;
					case "1":
						chartSerie.set("Janvier", bonMission.getJanvier() + bonCadre.getJanvier());
						break;
					case "2":
						chartSerie.set("Février", bonMission.getFevrier() + bonCadre.getFevrier());
						break;
					case "3":
						chartSerie.set("Mars", bonMission.getMars() + bonCadre.getMars());
						break;
					case "4":
						chartSerie.set("Avril", bonMission.getAvril() + bonCadre.getAvril());
						break;
					case "5":
						chartSerie.set("Mai", bonMission.getMai() + bonCadre.getMai());
						break;
					case "6":
						chartSerie.set("Juin", bonMission.getJuin() + bonCadre.getJuin());
						break;
					case "7":
						chartSerie.set("Juillet", bonMission.getJuillet() + bonCadre.getJuillet());
						break;
					case "8":
						chartSerie.set("Aout", bonMission.getAout() + bonCadre.getAout());
						break;
					case "9":
						chartSerie.set("Septembre", bonMission.getSeptembre() + bonCadre.getSeptembre());
						break;
					case "10":
						chartSerie.set("Octobre", bonMission.getOctobre() + bonCadre.getNouvembre());
						break;
					case "11":
						chartSerie.set("Nouvembre", bonMission.getNouvembre() + bonCadre.getNouvembre());
						break;
					case "12":
						chartSerie.set("Décembre", bonMission.getDecembre() + bonCadre.getDecembre());
						break;
					}
				}
				model.addSeries(chartSerie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	public BarChartModel getBarModel1() {
		return barModel1;
	}

	public void setBarModel1(BarChartModel barModel1) {
		this.barModel1 = barModel1;
	}

	public int getCritere() {
		return critere;
	}

	public void setCritere(int critere) {
		this.critere = critere;
	}

	public List<String> getList() {
		return listValeur;
	}

	public void setList(List<String> list) {
		this.listValeur = list;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

}
