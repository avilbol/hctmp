package com.hallocasa.services.properties.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.dataentities.app.properties.PropertyFieldValue;
import com.hallocasa.services.interfaces.PropertyFilteringServices;

/**
 * Service for property filtering
 * @author Alexander Villamil
 *
 */
@Stateless
public class PropertyFilteringServicesImpl implements PropertyFilteringServices, Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -9116655571979021754L;

	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;
	
	@Override
	public List<String> loadIdsForFiltering(PropertyFilter filter) {
		// Build query base
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Property> c = q.from(Property.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(filter.getPropertyProposalList() != null){
			List<Integer> intValues = new ArrayList<>();
			for(PropertyProposalVO propertyProposal : filter.getPropertyProposalList())
				intValues.add(propertyProposal.getId());
			predicates.add(c.get("propertyProposal").get("id").in(intValues));
		}
		if(filter.getPropertyLocationList() != null){
			List<Integer> intValues = new ArrayList<>();
			for(PropertyLocationVO propertyLocation : filter.getPropertyLocationList())
				intValues.add(propertyLocation.getId());
			predicates.add(c.get("propertyLocation").get("id").in(intValues));
		}
		if(filter.getPropertyTypeList() != null){
			List<Integer> intValues = new ArrayList<>();
			for(PropertyTypeVO propertyType : filter.getPropertyTypeList())
				intValues.add(propertyType.getId());
			predicates.add(c.get("propertyType").get("id").in(intValues));
		}
		q.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		q.multiselect(c.get("id"));
		return em.createQuery(q).getResultList();
	}

	
	public List<PropertyFieldValue> loadPropertyFieldValues(List<Integer> propertyIdList){
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<PropertyFieldValue> q = cb.createQuery(PropertyFieldValue.class);
		Root<PropertyFieldValue> c = q.from(PropertyFieldValue.class);
		q.select(c);
		return em.createQuery(q).getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
