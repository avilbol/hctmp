package com.hallocasa.services.hcfilters.listers;

import java.util.LinkedList;
import java.util.List;

import com.hallocasa.services.generalities.Listable;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

/**
 * This class is able to manage connections between entities whose values are related
 * to the identifier of a parent value. Example: a state has a country - its parent
 * @author avilbol
 * @param <T>
 */
public abstract class HcLister<T> {
	
	/**
	 * List the options that correspond to filter parent list and options specified
	 * @param filterList
	 * 			The parent filter list specified
	 * @return
	 * 			The options that apply
	 */
	public List<DropdownOption> loadFilterOptions(List<PropertyFilterSubmission> filterList) {
		PropertyFilterSubmission filterSubm = (PropertyFilterSubmission) filterList.get(0);
		List<DropdownOption> optionList = filterSubm.getSelectedFilterOptions();
		List<Integer> intList = new LinkedList<>();
		for(DropdownOption dropOpt : optionList){
			intList.add(dropOpt.getOptionId());
		}
		return toResultList(loadService().findByParentIdList(intList));
	}
	
	/**
	 * List the options that correspond to property fields and values specified
	 * @param fieldList
	 * 			The property field list with specified selected values
	 * @return
	 * 			The options that apply
	 */
	public List<DropdownOption> loadFieldOptions(PropertyKey propertyKey, List<PropertyField> fieldList){
		PropertyField field = (PropertyField)fieldList.get(0);
		Integer parentId =  field.getFieldValueList().get(0).getIdentifier();
		return toResultList(loadService().findByParentId(parentId));
	}

	private List<DropdownOption> toResultList(List<T> itemList){
		List<DropdownOption> resultList = new LinkedList<>();
		for(T item : itemList){
			resultList.add(toDropdownOption(item));
		}
		return resultList;
	}

	/**
	 * Extracts an item to an specific dropdown option
	 * @param item
	 * 		The listable item
	 * @return
	 * 		A dropdown option with the information extracted
	 */
	abstract DropdownOption toDropdownOption(T item);

	/**
	 * Specifies the listable service to use
	 */
	abstract Listable<T> loadService();
}
