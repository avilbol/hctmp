/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import com.hallocasa.services.filter.FilterCondition.FilterOperation;

/**
 *
 * @author David Mantilla
 * @param <T>
 */
public class DefaultRangeMenuItemFilterCondition<T> implements
		FilterMenuItemCondition<T> {

	private final T secondValue;
	private final T firstValue;
	private final FilterMenuItem<T> filterMenuItem;
	private final String label;

	/**
	 *
	 * @param filterMenuItem
	 * @param label
	 * @param firstValue
	 * @param secondValue
	 */
	public DefaultRangeMenuItemFilterCondition(
			FilterMenuItem<T> filterMenuItem, String label, T firstValue,
			T secondValue) {
		this.filterMenuItem = filterMenuItem;
		this.label = label;
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		if (!filterMenuItem.getFilterType().equals(
				FilterMenuItem.FilterType.RANGE)) {
			throw new IllegalArgumentException(
					"Not supported for that kind of menu item");
		}
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
		return label;
	}

	@Override
	public FilterMenuItem<T> getFilterMenuItem() {
		return filterMenuItem;
	}

	@Override
	public String toString() {
		return getLabel() + ": " + getFirstValue() + " - " + getSecondValue();
	}
	
	@Override
	public FilterOperation getFilterOperation() {
		return FilterOperation.BETWEEN;
	}

}
