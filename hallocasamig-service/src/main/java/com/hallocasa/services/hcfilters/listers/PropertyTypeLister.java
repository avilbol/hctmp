package com.hallocasa.services.hcfilters.listers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hallocasa.services.properties.PropertyTypeService;
import com.hallocasa.systemproperties.SystemConstants;
import com.hallocasa.systemproperties.SystemProperty;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.hcfilter.properties.PropertyType;
import com.hallocasa.vo.options.DropdownOption;

/**
 * Lister for cities, that have as parents the property type groups
 * @author avilbol
 */
public class PropertyTypeLister extends HcLister<PropertyType> {
	
	@Override
	public DropdownOption toDropdownOption(PropertyType propertyType){
		DropdownOption opt = new DropdownOption();
		opt.setOptionId(propertyType.getId());
		opt.setData1(propertyType.getLang());
		opt.addToParentInfo("groupId", propertyType.getGroup().getId());
		return opt;
	}
	
	@Override
	public PropertyTypeService loadService(){
		String propertyTypeServiceJndi = String.format("java:global/hallocasamig-endpoint-%1$s-%2$s/PropertyTypeServiceImp",
				SystemProperty.get(SystemConstants.APP_ENVIRONMENT),
				SystemProperty.get(SystemConstants.APP_VERSION));
		try {
			return (PropertyTypeService) InitialContext.doLookup(propertyTypeServiceJndi);
		} catch (NamingException e) {
			throw new FatalException("Lookup for property type service failed", e);
		}
	}
}
