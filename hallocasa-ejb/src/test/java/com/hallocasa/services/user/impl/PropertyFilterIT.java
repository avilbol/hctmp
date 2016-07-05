package com.hallocasa.services.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Test;

import com.hallocasa.commons.filters.FieldFilterType;
import com.hallocasa.commons.vo.properties.PropertyFieldVO;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.commons.vo.properties.filters.ComparatorType;
import com.hallocasa.commons.vo.properties.filters.PropertyFieldFilter;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
import com.hallocasa.services.properties.impl.PropertyFilteringServicesImpl;
import com.hallocasa.tests.database.DatabaseUtils;

public class PropertyFilterIT {

	private EntityManagerFactory emf;
	private EntityManager em;

	/* constructors */
	@Before
	public void initialize() {

		// Open persistence unit
		try {
			emf = DatabaseUtils.loadTestAppPersistenceUnit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		em = emf.createEntityManager();
	}

	public PropertyFilter basicFilter() {
		PropertyFilter filter = new PropertyFilter();
		PropertyTypeVO ptype = new PropertyTypeVO();
		ptype.setId(2);
		PropertyTypeVO ptype2 = new PropertyTypeVO();
		ptype2.setId(2);
		//filter.setPropertyTypeList(Arrays.asList(new PropertyTypeVO[] { ptype, ptype2 }));
		PropertyLocationVO plocation1 = new PropertyLocationVO();
		plocation1.setId(1);
		//filter.setPropertyLocationList(Arrays.asList(new PropertyLocationVO[] { plocation1 }));
		return filter;
	}

	public PropertyFilter fullFilter() {
		PropertyFilter filter = basicFilter();
		PropertyFieldFilter fieldFilter1 = new PropertyFieldFilter();
		PropertyFieldVO field = new PropertyFieldVO();
		field.setId(1);
		
		// Languages filter
		fieldFilter1.setPropertyField(field);
		fieldFilter1.setComparatorType(ComparatorType.LIST);
		fieldFilter1.setType(FieldFilterType.MULTIPLE_SELECT_OR);
		List<PropertyFieldFilter> filters = new ArrayList<PropertyFieldFilter>();
		filters.add(fieldFilter1);
		
		List<String> values = new ArrayList<String>();
		values.add("de");
		values.add("en");
		fieldFilter1.setStringValues(values);

		// Price filter
		PropertyFieldFilter fieldFilter2 = new PropertyFieldFilter();
		field = new PropertyFieldVO();
		field.setId(5);
		fieldFilter2.setPropertyField(field);
		fieldFilter2.setComparatorType(ComparatorType.OBJECT_PROPERTY);
		fieldFilter2.setObjectProperty("value");
		//fieldFilter2.setValueFrom(00000);
		fieldFilter2.setValueTo(1000000.0);
		fieldFilter2.setType(FieldFilterType.RANGE);
		filters.add(fieldFilter2);
		
		
		// Square meters area filter
		PropertyFieldFilter fieldFilter3 = new PropertyFieldFilter();
		field = new PropertyFieldVO();
		field.setId(6);
		fieldFilter3.setPropertyField(field);
		fieldFilter3.setComparatorType(ComparatorType.VALUE);
		fieldFilter3.setValueFrom(40.0);
		fieldFilter3.setValueTo(60.0);
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
		
		List<Integer> valuesInteger = new ArrayList<>();
		valuesInteger.add(5);
		valuesInteger.add(6);
		valuesInteger.add(7);
		fieldFilter4.setIntValues(valuesInteger);

		// City filter
		PropertyFieldFilter fieldFilter5 = new PropertyFieldFilter();
		field = new PropertyFieldVO();
		field.setId(8);
		fieldFilter5.setPropertyField(field);
		fieldFilter5.setComparatorType(ComparatorType.VALUE);
		fieldFilter5.setIntValue(181);
		fieldFilter5.setType(FieldFilterType.MULTIPLE_SELECT_OR);
		filters.add(fieldFilter5);
		
		valuesInteger = new ArrayList<>();
		valuesInteger.add(181);
		valuesInteger.add(182);
		valuesInteger.add(183);
		fieldFilter5.setIntValues(valuesInteger);
		
		
		filter.setPropertyFieldFilters(filters);
		return filter;
	}

	@Test
	public void testGetIdsFromKeys() {
		PropertyFilteringServicesImpl psimpl = new PropertyFilteringServicesImpl();
		psimpl.setEm(em);
		psimpl.loadIdsForFiltering(basicFilter());
	}

	@Test
	public void testGetPropertyFieldValueFromIds() {
		PropertyFilteringServicesImpl psimpl = new PropertyFilteringServicesImpl();
		psimpl.setEm(em);
		psimpl.loadPropertyFieldEligible(psimpl.loadIdsForFiltering(basicFilter()),
				fullFilter().getPropertyFieldFilters());
	}

	@Test
	public void testApprovedPropertiesFromIds() {
		PropertyFilteringServicesImpl psimpl = new PropertyFilteringServicesImpl();
		psimpl.setEm(em);
		PropertyFilter basicFilter = basicFilter();
		PropertyFilter fullFilter = fullFilter();
		List<PropertyFieldFilter> fieldFilters = fullFilter.getPropertyFieldFilters();
		List<String> idsForFiltering = psimpl.loadIdsForFiltering(basicFilter);
		List<Object[]> propertyFieldObjEligibleList = psimpl.loadPropertyFieldEligible(idsForFiltering, fieldFilters);
		List<String> filteredIds = psimpl.approvedPropertyIds(propertyFieldObjEligibleList, fieldFilters);
		System.out.println(filteredIds.size());
	}
	
	@Test
	public void testGetFilteredProperties() {
		PropertyFilteringServicesImpl psimpl = new PropertyFilteringServicesImpl();
		psimpl.setEm(em);
		PropertyFilter basicFilter = basicFilter();
		PropertyFilter fullFilter = fullFilter();
		List<PropertyFieldFilter> fieldFilters = fullFilter.getPropertyFieldFilters();
		List<String> idsForFiltering = psimpl.loadIdsForFiltering(basicFilter);
		List<Object[]> propertyFieldObjEligibleList = psimpl.loadPropertyFieldEligible(idsForFiltering, fieldFilters);
		List<String> filteredIds = psimpl.approvedPropertyIds(propertyFieldObjEligibleList, fieldFilters);
		List<PropertyVO> propertiesFiltered = psimpl.propertiesInIdList(filteredIds);
	}

}
