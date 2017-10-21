package com.hallocasa.services.hcfilters.listers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hallocasa.services.generalities.location.NeighborhoodService;
import com.hallocasa.systemproperties.SystemConstants;
import com.hallocasa.systemproperties.SystemProperty;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.Neighborhood;
import com.hallocasa.vo.options.DropdownOption;

/**
 * Lister used in filters in order to search a child neighborhood elements
 * according to its parent city
 * 
 * @author avilbol
 */
public class NeighborhoodLister extends HcLister<Neighborhood> {

	@Override
	public DropdownOption toDropdownOption(Neighborhood neighborhood) {
		DropdownOption opt = new DropdownOption();
		opt.setOptionId(neighborhood.getId());
		opt.setData1(neighborhood.getLang());
		opt.setData2(neighborhood.getDependsOnLang().toString());
		return opt;
	}

	@Override
	public NeighborhoodService loadService() {
		String neighborhoodServiceJndi = String.format("java:global/hallocasamig-endpoint-%1$s-%2$s/NeighborhoodServiceImp",
				SystemProperty.get(SystemConstants.APP_ENVIRONMENT), 
				SystemProperty.get(SystemConstants.APP_VERSION));
		try {
			return (NeighborhoodService) InitialContext.doLookup(neighborhoodServiceJndi);
		} catch (NamingException e) {
			throw new FatalException("Lookup for state service failed", e);
		}
	}
}
