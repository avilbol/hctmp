package com.hallocasa.services.hcfilters.listers;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import com.hallocasa.services.generalities.location.CityService;
import com.hallocasa.vo.City;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

public class CityLister implements HcLister {

	@EJB
	CityService cityService;
	
	@Override
	public List<DropdownOption> loadFilterOptions(List<PropertyFilterSubmission> filterList) {
		PropertyFilterSubmission filterSubm = (PropertyFilterSubmission) filterList.get(0);
		List<DropdownOption> optionList = filterSubm.getSelectedFilterOptions();
		List<Integer> intList = new LinkedList<>();
		for(DropdownOption dropOpt : optionList){
			intList.add(dropOpt.getOptionId());
		}
		return toResultList(cityService.findByStatesId(intList));
	}

	@Override
	public List<DropdownOption> loadFieldOptions(List<PropertyField> fieldList) {
		PropertyField field = (PropertyField)fieldList.get(0);
		Integer stateId =  field.getFieldValueList().get(0).getIdentifier();
		return toResultList(cityService.findByStateId(stateId));
	}
	
	private List<DropdownOption> toResultList(List<City> cityList){
		List<DropdownOption> resultList = new LinkedList<>();
		for(City city : cityList){
			DropdownOption opt = new DropdownOption();
			opt.setOptionId(city.getId());
			opt.setData1(city.getName());
			resultList.add(opt);
		}
		return resultList;
	}
}
