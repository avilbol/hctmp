package com.hallocasa.services.hcfilters.listers;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import com.hallocasa.services.generalities.location.StateService;
import com.hallocasa.vo.State;
import com.hallocasa.vo.hcfilter.properties.PropertyDropdownFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

public class StateLister implements HcLister {

	@EJB
	StateService stateService;
	
	@Override
	public List<DropdownOption> loadFilterOptions(List<PropertyFilterSubmission> filterList) {
		PropertyDropdownFilterSubmission filterSubm = (PropertyDropdownFilterSubmission) filterList.get(0);
		List<DropdownOption> optionList = filterSubm.getSelectedFilterOptions();
		List<Integer> intList = new LinkedList<>();
		for(DropdownOption dropOpt : optionList){
			intList.add(dropOpt.getOptionId());
		}
		return toResultList(stateService.findByCountriesId(intList));
	}

	@Override
	public List<DropdownOption> loadFieldOptions(List<PropertyField> fieldList) {
		PropertyField field = (PropertyField)fieldList.get(0);
		Integer countryId =  field.getFieldValueList().get(0).getIdentifier();
		return toResultList(stateService.findByCountryId(countryId));
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
}
