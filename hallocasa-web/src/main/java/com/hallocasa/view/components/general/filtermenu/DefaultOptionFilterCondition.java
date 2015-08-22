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
public class DefaultOptionFilterCondition<T> implements
		FilterMenuItemCondition<T> {

	private final FilterMenuItem<T> filterMenuItem;
	private final String label;

	/**
	 * 
	 * @param filterMenuItem
	 * @param label
	 */
	public DefaultOptionFilterCondition(FilterMenuItem<T> filterMenuItem,
			String label) {
		this.filterMenuItem = filterMenuItem;
		this.label = label;
		if (!filterMenuItem.getFilterType().equals(
				FilterMenuItem.FilterType.SELECT_MANY)) {
			throw new IllegalArgumentException(
					"Not supported for that kind of menu item");
		}
	}

	@Override
	public T getFirstValue() {
		return null;
	}

	@Override
	public T getSecondValue() {
		return null;
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
	public FilterOperation getFilterOperation() {
		return FilterOperation.EQUALS;
	}
}
