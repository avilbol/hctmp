package com.hallocasa.services.hcfilters.listers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hallocasa.services.generalities.location.CityService;
import com.hallocasa.systemproperties.SystemConstants;
import com.hallocasa.systemproperties.SystemProperty;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.City;
import com.hallocasa.vo.options.DropdownOption;

/**
 * Lister for cities, that have as parents the states
 * @author avilbol
 */
public class CityLister extends HcLister<City> {
	
	@Override
	public DropdownOption toDropdownOption(City city){
		DropdownOption opt = new DropdownOption();
		opt.setOptionId(city.getId());
		opt.setData1(city.getName());
		return opt;
	}
	
	@Override
	public CityService loadService(){
		String cityServiceJndi = String.format("java:global/hallocasamig-endpoint-%1$s-%2$s/CityServiceImp",
				SystemProperty.get(SystemConstants.APP_ENVIRONMENT),
				SystemProperty.get(SystemConstants.APP_VERSION));
		try {
			return (CityService) InitialContext.doLookup(cityServiceJndi);
		} catch (NamingException e) {
			throw new FatalException("Lookup for city service failed", e);
		}
	}
}
