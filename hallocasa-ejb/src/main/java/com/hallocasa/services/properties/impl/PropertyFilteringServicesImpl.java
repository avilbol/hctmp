package com.hallocasa.services.properties.impl;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.hallocasa.commons.jsonmanager.JsonManager;
import com.hallocasa.commons.utils.ComparatorUtils;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.filters.PropertyFieldFilter;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.dataentities.app.properties.PropertyFieldValue;
import com.hallocasa.services.interfaces.PropertyFilteringServices;
import static com.hallocasa.commons.utils.ComparatorUtils.*;

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

	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;

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
		for (PropertyFieldFilter pfv : pfFilterList) {
			fieldsToApply.add(pfv.getPropertyField().getId());
		}
		Expression<String> propertyFieldId = c.get("propertyField").get("id");
		Expression<String> propertyId = c.get("property").get("id");
		q.where(cb.and(propertyFieldId.in(fieldsToApply),
				propertyId.in(propertyIdList)));
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

	private boolean match(Object[] pfValue,
			List<PropertyFieldFilter> pfFilterList) {
		Integer propertyFieldId = (Integer) pfValue[PROPERTY_FIELD_ID_POS];
		for (PropertyFieldFilter pffilter : pfFilterList) {
			if (propertyFieldId.equals(pffilter.getPropertyField().getId())) {
				String propertyFieldValue = (String) pfValue[PROPERTY_VALUE_POS];
				Object propertyFieldAttr = JsonManager.loadValue(
						propertyFieldValue, pffilter.getComparatorType(),
						pffilter.getObjectProperty());
				List<?> filterList = coalesce(pffilter.getIntValues(),
						pffilter.getStringValues());
				Object filterUnique = coalesce(pffilter.getIntValue(), pffilter.getStringValue());
				switch (pffilter.getType()) {
				case RANGE:
					return (inRange(propertyFieldAttr,
							new BigDecimal(pffilter.getValueFrom()),
							new BigDecimal(pffilter.getValueTo())));
				case MULTIPLE_SELECT_OR:
					return select(propertyFieldAttr, filterList, false);
				case MULTIPLE_SELECT_AND:
					return select(propertyFieldAttr, filterList, false);
				case UNIQUE_SELECT:
					return select(propertyFieldAttr, filterUnique, false);
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
