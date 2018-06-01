package com.pfe.gestioncarburant.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfe.gestioncarburant.entities.TypeCarburant;
import com.pfe.gestioncarburant.services.ChartService;
import com.pfe.gestioncarburant.services.TypeCarburantService;

@Component
@ManagedBean
@ViewScoped
public class ChartBean implements Serializable {
	@Autowired
	private ChartService chartService;
	@Autowired
	private TypeCarburantService typeCarburantService;

	private BarChartModel animatedModel;
	private List<TypeCarburant> listTypeCarburant = new ArrayList<>();

	public void init() {
		createAnimatedModel();
	}

	private void createAnimatedModel() {
		animatedModel = initBarModel();
		animatedModel.setTitle("Bar Charts");
		animatedModel.setAnimate(true);
		animatedModel.setLegendPosition("ne");
		Axis yAxis = animatedModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		ChartSeries carburantSeries = new ChartSeries();
		// try {
		// listTypeCarburant = typeCarburantService.findAll();
		//
		// int[] qteMois;
		// for (TypeCarburant typeCarb : listTypeCarburant) {
		// qteMois = chartService.getQteMoisbyGazoil(typeCarb);
		// carburantSeries.setLabel(typeCarb.getLibelle());
		// for (int i = 0; i < qteMois.length; i++) {
		// carburantSeries.set(i, qteMois[i]);
		// }
		// model.addSeries(carburantSeries);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		carburantSeries.setLabel("hahahaha");
		carburantSeries.set("5", 5);
		carburantSeries.set("5", 8);
		model.addSeries(carburantSeries);

		return model;
	}

	public BarChartModel getAnimatedModel() {
		return animatedModel;
	}

	public void setAnimatedModel(BarChartModel animatedModel) {
		this.animatedModel = animatedModel;
	}

	public ChartService getChartService() {
		return chartService;
	}

	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}

	public TypeCarburantService getTypeCarburantService() {
		return typeCarburantService;
	}

	public void setTypeCarburantService(TypeCarburantService typeCarburantService) {
		this.typeCarburantService = typeCarburantService;
	}

	public List<TypeCarburant> getListTypeCarburant() {
		return listTypeCarburant;
	}

	public void setListTypeCarburant(List<TypeCarburant> listTypeCarburant) {
		this.listTypeCarburant = listTypeCarburant;
	}

}
