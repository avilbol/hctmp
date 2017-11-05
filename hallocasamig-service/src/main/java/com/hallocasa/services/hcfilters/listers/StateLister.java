package com.hallocasa.services.hcfilters.listers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hallocasa.services.generalities.location.StateService;
import com.hallocasa.systemproperties.SystemConstants;
import com.hallocasa.systemproperties.SystemProperty;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.State;
import com.hallocasa.vo.options.DropdownOption;

/**
 * Lister for states, that have as parents the countries
 * @author avilbol
 */
public class StateLister extends HcLister<State> {

	@Override
	public DropdownOption toDropdownOption(State state){
		DropdownOption opt = new DropdownOption();
		opt.setOptionId(state.getId());
		opt.setData1(state.getName());
		opt.addToParentInfo("countryId", state.getCountryId());
		return opt;
	}
	
	@Override
	public StateService loadService(){
		String stateServiceJndi = String.format("java:global/hallocasamig-endpoint-%1$s-%2$s/StateServiceImp",
				SystemProperty.get(SystemConstants.APP_ENVIRONMENT),
				SystemProperty.get(SystemConstants.APP_VERSION));
		try {
			return (StateService) InitialContext.doLookup(stateServiceJndi);
		} catch (NamingException e) {
			throw new FatalException("Lookup for state service failed", e);
		}
	}
}
