package com.pfe.gestioncarburant.services;

import java.util.Calendar;
import java.util.List;

public interface ChartService {

	List<String> findValeurByCritere(int critere, Calendar date);

}
