package com.pfe.gestioncarburant.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import com.pfe.gestioncarburant.dao.BonCadreDao;
import com.pfe.gestioncarburant.dto.MoisDTO;
import com.pfe.gestioncarburant.entities.BonCarburant;
import com.pfe.gestioncarburant.entities.TypeCarburant;

@Repository
public class BonCadreDaoImp extends GenericDaoImp implements BonCadreDao {
	@Override
	public MoisDTO findByYear(TypeCarburant typeCarburant, int annee) {
		MoisDTO result = new MoisDTO();
		List<BonCarburant> list = new ArrayList<>();
		list = this.findAll(BonCarburant.class);
		for (BonCarburant listBon : list) {
			String sql = "   SELECT ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 1 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as janvier, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 2 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as fevrier, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 3 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as mars, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 4 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as avril, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 5 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as mai, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 6 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as juin, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 7 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as juillet, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 8 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as aout, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 9 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as septembre, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 10 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as octobre, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 11 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as nouvembre, "
					+ "  ( select IFNULL(sum(c.litre),0)*IFNULL(m.qte,0) from bon_cadre m INNER JOIN bon_carburant c ON m.id_bon_carburant = c.id INNER JOIN type_carburant t ON c.id_type_carburant = t.id where month(date_affectation)= 12 and year(date_affectation)=:annee and m.id_bon_carburant=:bon and t.libelle=:param) as decembre ";
			startOperation();

			MoisDTO month = (MoisDTO) hibernateSession.createSQLQuery(sql).addScalar("janvier", Hibernate.INTEGER)
					.addScalar("fevrier", Hibernate.INTEGER).addScalar("mars", Hibernate.INTEGER)
					.addScalar("avril", Hibernate.INTEGER).addScalar("mai", Hibernate.INTEGER)
					.addScalar("juin", Hibernate.INTEGER).addScalar("juillet", Hibernate.INTEGER)
					.addScalar("aout", Hibernate.INTEGER).addScalar("septembre", Hibernate.INTEGER)
					.addScalar("octobre", Hibernate.INTEGER).addScalar("nouvembre", Hibernate.INTEGER)
					.addScalar("decembre", Hibernate.INTEGER).setParameter("param", typeCarburant.getLibelle())
					.setParameter("bon", listBon.getId()).setParameter("annee", annee)
					.setResultTransformer(new AliasToBeanResultTransformer(MoisDTO.class)).uniqueResult();
			endOperation();

			result.setJanvier(month.getJanvier() + result.getJanvier());
			result.setFevrier(month.getFevrier() + result.getFevrier());
			result.setMars(month.getMars() + result.getMars());
			result.setAvril(month.getAvril() + result.getAvril());
			result.setMai(month.getMai() + result.getMai());
			result.setJuin(month.getJuin() + result.getJuin());
			result.setJuillet(month.getJuillet() + result.getJuillet());
			result.setAout(month.getAout() + result.getAout());
			result.setSeptembre(month.getSeptembre() + result.getSeptembre());
			result.setOctobre(month.getOctobre() + result.getOctobre());
			result.setNouvembre(month.getNouvembre() + result.getNouvembre());
			result.setDecembre(month.getDecembre() + result.getDecembre());
		}
		return result;
	}

	@Override
	public MoisDTO findByMonth(BonCarburant bonCarburant) {
		return null;
	}

	@Override
	public MoisDTO findByQuarter(BonCarburant bonCarburant) {
		return null;
	}
}
