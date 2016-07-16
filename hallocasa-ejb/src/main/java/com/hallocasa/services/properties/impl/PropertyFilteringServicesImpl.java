package com.hallocasa.services.properties.impl;

import static com.hallocasa.commons.utils.ComparatorUtils.inRange;
import static com.hallocasa.commons.utils.ComparatorUtils.select;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hallocasa.commons.filters.FieldFilterType;
import com.hallocasa.commons.jsonmanager.JsonManager;
import com.hallocasa.commons.vo.properties.PropertyFieldVO;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.commons.vo.properties.filters.ComparatorType;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.dataentities.app.properties.PropertyFieldValue;
import com.hallocasa.filters.converters.PropertyFieldFilter;
import com.hallocasa.filters.converters.PropertyFilter;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.interfaces.PropertyFilteringServices;

/**
 * Service for property filtering
 * 
 * @author Alexander Villamil
 * 
 */
@Stateless
public class PropertyFilteringServicesImpl implements
		PropertyFilteringServices, Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -9116655571979021754L;

	/**
	 * Property Id POS
	 */
	private static final Integer PROPERTY_ID_POS = 0;

	/**
	 * Property Field Id POS
	 */
	private static final Integer PROPERTY_FIELD_ID_POS = 1;

	/**
	 * Property Value POS
	 */
	private static final Integer PROPERTY_VALUE_POS = 2;

	@PersistenceContext(unitName = "App")
	private EntityManager em;

	@Override
	public PropertyFilter getFilterScheme() {
		PropertyFilter filter = new PropertyFilter();
		PropertyFieldFilter fieldFilter1 = new PropertyFieldFilter();
		PropertyFieldVO field = new PropertyFieldVO();
		field.setId(1);
		
		// Languages filter
		fieldFilter1.setPropertyField(field);
		fieldFilter1.setComparatorType(ComparatorType.LIST);
		fieldFilter1.setType(FieldFilterType.MULTIPLE_SELECT_OR);
		List<PropertyFieldFilter> filters = new ArrayList<PropertyFieldFilter>();
		filters.add(fieldFilter1);
	

		// Price filter
		PropertyFieldFilter fieldFilter2 = new PropertyFieldFilter();
		field = new PropertyFieldVO();
		field.setId(5);
		fieldFilter2.setPropertyField(field);
		fieldFilter2.setComparatorType(ComparatorType.OBJECT_PROPERTY);
		fieldFilter2.setObjectProperty("value");
		fieldFilter2.setType(FieldFilterType.RANGE);
		filters.add(fieldFilter2);
		
		
		// Square meters area filter
		PropertyFieldFilter fieldFilter3 = new PropertyFieldFilter();
		field = new PropertyFieldVO();
		field.setId(6);
		fieldFilter3.setPropertyField(field);
		fieldFilter3.setComparatorType(ComparatorType.VALUE);
		fieldFilter3.setType(FieldFilterType.RANGE);
		filters.add(fieldFilter3);

		
		
		// State filter
		PropertyFieldFilter fieldFilter4 = new PropertyFieldFilter();
		field = new PropertyFieldVO();
		field.setId(7);
		fieldFilter4.setPropertyField(field);
		fieldFilter4.setComparatorType(ComparatorType.VALUE);
		fieldFilter4.setType(FieldFilterType.MULTIPLE_SELECT_OR);
		filters.add(fieldFilter4);
		

		// City filter
		PropertyFieldFilter fieldFilter5 = new PropertyFieldFilter();
		field = new PropertyFieldVO();
		field.setId(8);
		fieldFilter5.setPropertyField(field);
		fieldFilter5.setComparatorType(ComparatorType.VALUE);
		fieldFilter5.setType(FieldFilterType.MULTIPLE_SELECT_OR);
		filters.add(fieldFilter5);
		
		
		filter.setPropertyFieldFilters(filters);
		return filter;
	}
	
	@Override
	public List<String> loadIdsForFiltering(PropertyFilter filter) {
		// Build query base
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Property> c = q.from(Property.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (filter.getPropertyProposalList() != null) {
			List<Integer> intValues = new ArrayList<>();
			for (PropertyProposalVO propertyProposal : filter
					.getPropertyProposalList())
				intValues.add(propertyProposal.getId());
			predicates.add(c.get("propertyProposal").get("id").in(intValues));
		}
		if (filter.getPropertyLocationList() != null) {
			List<Integer> intValues = new ArrayList<>();
			for (PropertyLocationVO propertyLocation : filter
					.getPropertyLocationList())
				intValues.add(propertyLocation.getId());
			predicates.add(c.get("propertyLocation").get("id").in(intValues));
		}
		if (filter.getPropertyTypeList() != null) {
			List<Integer> intValues = new ArrayList<>();
			for (PropertyTypeVO propertyType : filter.getPropertyTypeList())
				intValues.add(propertyType.getId());
			predicates.add(c.get("propertyType").get("id").in(intValues));
		}
		q.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		q.multiselect(c.get("id"));
		return em.createQuery(q).getResultList();
	}

	public List<Object[]> loadPropertyFieldEligible(
			List<String> propertyIdList, List<PropertyFieldFilter> pfFilterList) {
		if (propertyIdList.isEmpty()) {
			return new ArrayList<Object[]>();
		}
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		Root<PropertyFieldValue> c = q.from(PropertyFieldValue.class);
		List<Integer> fieldsToApply = new ArrayList<Integer>();
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (PropertyFieldFilter pfv : pfFilterList) {
			fieldsToApply.add(pfv.getPropertyField().getId());
		}
		Expression<String> propertyFieldId = c.get("propertyField").get("id");
		Expression<String> propertyId = c.get("property").get("id");
		
		predicates.add(propertyId.in(propertyIdList));
		if(!fieldsToApply.isEmpty())
			predicates.add(propertyFieldId.in(fieldsToApply));
		q.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		q.multiselect(propertyId, propertyFieldId, c.get("value"));
		List<Object[]> resultList = em.createQuery(q).getResultList();
		return resultList;
	}

	public List<String> approvedPropertyIds(List<Object[]> pfValues,
			List<PropertyFieldFilter> pfFilterList) {
		Set<String> overallPropertyIdSet = new HashSet<String>();
		Set<String> rejectedPropertyIdSet = new HashSet<String>();
		for (Object[] pfValue : pfValues) {
			String propertyId = (String) pfValue[PROPERTY_ID_POS];
			overallPropertyIdSet.add(propertyId);
			if (!match(pfValue, pfFilterList))
				rejectedPropertyIdSet.add(propertyId);
		}
		overallPropertyIdSet.removeAll(rejectedPropertyIdSet);
		return new ArrayList<String>(overallPropertyIdSet);
	}
	

	@Override
	public List<PropertyVO> loadProperties(PropertyFilter propertyFilter) {
		List<PropertyFieldFilter> fieldFilters = propertyFilter.getPropertyFieldFilters();
		// Apply key property filters
		List<String> idsForFiltering = loadIdsForFiltering(propertyFilter);
		// Load the property field values that are eligible to validate in filtering
		List<Object[]> propertyFieldObjEligibleList = loadPropertyFieldEligible(idsForFiltering, fieldFilters);
		// Apply property field value filters
		List<String> filteredIds = approvedPropertyIds(propertyFieldObjEligibleList, fieldFilters);
		// Return properties filtered
		return propertiesInIdList(filteredIds);
	}
	
	public List<PropertyVO> propertiesInIdList(List<String> propertyIdList){
		if(propertyIdList == null || propertyIdList.isEmpty()){
			return new ArrayList<PropertyVO>();
		}
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Property> q = cb.createQuery(Property.class);
		Root<Property> c = q.from(Property.class);
		Expression<String> propertyId = c.get("id");
		q.where(propertyId.in(propertyIdList));
		q.select(c);
		List<Property> propertyList = em.createQuery(q).getResultList();
		return ParsersContext.PROPERTY_VO_PARSER.toValueObject(propertyList);
	}

	private boolean match(Object[] pfValue,
			List<PropertyFieldFilter> pfFilterList) {
		Integer propertyFieldId = (Integer) pfValue[PROPERTY_FIELD_ID_POS];
		for (PropertyFieldFilter pffilter : pfFilterList) {
			if (propertyFieldId.equals(pffilter.getPropertyField().getId())) {
				String propertyFieldValue = (String) pfValue[PROPERTY_VALUE_POS];
				Object propertyFieldAttr = JsonManager.loadValue(
						propertyFieldValue, pffilter.getComparatorType(),
						pffilter.getObjectProperty());
				if(pffilter.getFilterConverter() != null){
					String helper = (String) JsonManager.loadValue(
							propertyFieldValue, ComparatorType.OBJECT_PROPERTY,
							pffilter.getFilterConverter().getObjectProperty());
					propertyFieldAttr = pffilter.getFilterConverter().transform(
							propertyFieldAttr, new String[]{helper});
				}
				switch (pffilter.getType()) {
				case RANGE:
					return (inRange(propertyFieldAttr,
							pffilter.getValueFrom(), pffilter.getValueTo()));
				case MULTIPLE_SELECT_OR:
					return select(propertyFieldAttr, pffilter.getStringValues(), false);
				case MULTIPLE_SELECT_AND:
					return select(propertyFieldAttr, pffilter.getStringValues(), false);
				case UNIQUE_SELECT:
					return select(propertyFieldAttr, pffilter.getStringValue(), false);
				case BOOLEAN:
					//TODO: Write the implementation for this
					return false;
				case CUSTOM:
					//TODO: Write the implementation for this
					return false;
				}
			}
		}
		return true;

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
