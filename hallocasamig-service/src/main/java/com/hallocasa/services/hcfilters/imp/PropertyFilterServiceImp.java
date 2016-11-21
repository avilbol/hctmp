package com.hallocasa.services.hcfilters.imp;

import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.hcfilter.IDAOHcFilter;
import com.hallocasa.dao.i.properties.IDAOPropertyField;
import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.services.hcfilters.PropertyFilterService;
import com.hallocasa.services.security.imp.AuthenticationServiceImp;
import com.hallocasa.vo.hcfilter.HcFilter;
import com.hallocasa.vo.hcfilter.properties.PropertyDropdownFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyFilter;
import com.hallocasa.vo.properties.PropertyField;

/**
 * Service for filters used in application
 * 
 * @author avillamil
 */
@Stateless
public class PropertyFilterServiceImp implements PropertyFilterService {

	/* static */
	private Logger LOG = Logger.getLogger(AuthenticationServiceImp.class.getName());

	/* dependencies */
	@EJB
	IDAOHcFilter daoHcFilter;

	@EJB
	IDAOPropertyField daoPropertyField;

	/**
	 * Default constructor
	 */
	public PropertyFilterServiceImp() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyFilter> loadPropertyFilterList(boolean filterByNature, List<Integer> filterNatureIdList) {
		List<EntityHcFilter> entityHcFilterList;
		entityHcFilterList = filterByNature ? daoHcFilter.find(filterNatureIdList) : daoHcFilter.find();
		List<PropertyFilter> filterList = new LinkedList<PropertyFilter>();
		for (EntityHcFilter entityHcFilter : entityHcFilterList) {
			HcFilter hcFilter = (HcFilter) toValueObject(entityHcFilter);
			filterList.add(transformToPropertyFilter(hcFilter));
		}
		return filterList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyFilter> loadPropertyFilterList(List<PropertyDropdownFilterSubmission> filterList) {
		List<EntityHcFilter> entityHcFilterList = daoHcFilter.findByPropertyKeys(filterList);
		List<PropertyFilter> resultList = new LinkedList<PropertyFilter>();
		for (EntityHcFilter entityHcFilter : entityHcFilterList) {
			HcFilter hcFilter = (HcFilter) toValueObject(entityHcFilter);
			resultList.add(transformToPropertyFilter(hcFilter));
		}
		return resultList;
	}

	private PropertyFilter transformToPropertyFilter(HcFilter hcFilter) {
		Optional<EntityPropertyField> entPropertyField = daoPropertyField.findByFilterId(hcFilter.getId());
		PropertyField propertyField = entPropertyField.isPresent() ? 
				(PropertyField) toValueObject(entPropertyField.get()) : null;
		return new PropertyFilter(hcFilter, propertyField);
	}
}
