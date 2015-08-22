/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.components.filter;

import com.hallocasa.services.filter.FilterCondition;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItem;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItemCondition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Mantilla
 * @param <T>
 */
public class FilterConditionAdapter<T> extends FilterCondition<T> implements
		FilterMenuItemCondition<T> {

	private final T firstValue;
	private final T secondValue;
	private final FilterMenuItem<T> filterMenuItem;
	private final String label;

	/**
	 * Constructor
	 *
	 * @param filterMenuItem
	 * @param label
	 * @param conditionId
	 * @param fieldName
	 * @param filterOperation
	 * @param firstValue
	 * @param secondValue
	 */
	public FilterConditionAdapter(FilterMenuItem<T> filterMenuItem,
			String label, String conditionId, String fieldName,
			FilterOperation filterOperation, T firstValue, T secondValue) {
		super(conditionId, fieldName, filterOperation);
		this.filterMenuItem = filterMenuItem;
		this.firstValue = firstValue;
		this.secondValue = secondValue;

		List<String> params = new ArrayList<>();
		params.addAll(getValuesKeySet());
		this.setValue(params.get(0), firstValue);
		if (params.size() > 1) {
			this.setValue(params.get(1), secondValue);
		}

		this.label = label;
	}

	@Override
	public String toString() {
		return super.toString(); // To change body of generated methods, choose
									// Tools | Templates.
	}

	@Override
	public T getFirstValue() {
		return firstValue;
	}

	@Override
	public T getSecondValue() {
		return secondValue;
	}

	@Override
	public String getLabel() {
		switch (getFilterOperation()) {
		case EQUALS:
			return firstValue + "";
		case BETWEEN:
			return firstValue + " - " + secondValue;
		case LIKE:
			return firstValue + "";
		case GREATER_THAN:
			return "> " + firstValue;
		case LESS_THAN:
			return "<" + firstValue;
		default:
			return label;
		}

	}

	@Override
	public FilterMenuItem<T> getFilterMenuItem() {
		return filterMenuItem;
	}


}
