package com.hallocasa.services.hcfilters.listers;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hallocasa.services.generalities.location.StateService;
import com.hallocasa.systemproperties.SystemConstants;
import com.hallocasa.systemproperties.SystemProperty;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.State;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

public class StateLister implements HcLister {

	@EJB
	StateService stateService;
	
	@Override
	public List<DropdownOption> loadFilterOptions(List<PropertyFilterSubmission> filterList) {
		PropertyFilterSubmission filterSubm = (PropertyFilterSubmission) filterList.get(0);
		List<DropdownOption> optionList = filterSubm.getSelectedFilterOptions();
		List<Integer> intList = new LinkedList<>();
		for(DropdownOption dropOpt : optionList){
			intList.add(dropOpt.getOptionId());
		}
		return toResultList(getStateService().findByCountriesId(intList));
	}

	@Override
	public List<DropdownOption> loadFieldOptions(PropertyKey propertyKey, List<PropertyField> fieldList) {
		Integer countryId =  propertyKey.getCountry().getId();
		return toResultList(getStateService().findByCountryId(countryId));
	}
	
	private List<DropdownOption> toResultList(List<State> stateList){
		List<DropdownOption> resultList = new LinkedList<>();
		for(State state : stateList){
			DropdownOption opt = new DropdownOption();
			opt.setOptionId(state.getId());
			opt.setData1(state.getName());
			resultList.add(opt);
		}
		return resultList;
	}
	
	private StateService getStateService(){
		if(stateService == null){
			String stateServiceJndi = String.format("java:global/hallocasamig-endpoint-%1$s-%2$s/StateServiceImp",
					SystemProperty.get(SystemConstants.APP_ENVIRONMENT),
					SystemProperty.get(SystemConstants.APP_VERSION));
			try {
				stateService = (StateService) InitialContext.doLookup(stateServiceJndi);
			} catch (NamingException e) {
				throw new FatalException("Lookup for state service failed", e);
			}
		}
		return stateService;
	}
}
