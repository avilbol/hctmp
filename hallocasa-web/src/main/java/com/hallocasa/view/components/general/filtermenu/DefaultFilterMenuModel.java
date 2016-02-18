/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David Mantilla
 */
public abstract class DefaultFilterMenuModel implements FilterMenuModel {

	private final List<FilterMenuItem<?>> filterMenuItems;

	/**
	 * Default constructor
	 */
	public DefaultFilterMenuModel() {
		filterMenuItems = new ArrayList<>();
	}

	/**
	 * Finds a filter menu item
	 *
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public FilterMenuItem<?> findFilterMenuItem(String id) {
		for (FilterMenuItem f : filterMenuItems) {
			if (f.getId().equals(id)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Adds a filter menu item
	 *
	 * @param filterMenuItem
	 */
	@SuppressWarnings("rawtypes")
	public void addFilterMenuItem(FilterMenuItem filterMenuItem) {
		filterMenuItems.add(filterMenuItem);
	}

	@Override
	public List<FilterMenuItem<?>> getFilterMenuItems() {
		return filterMenuItems;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FilterMenuItemCondition<?>[] getActiveMenuItemConditions() {
		List<FilterMenuItemCondition> conditions = new ArrayList<>();
		for (FilterMenuItem fi : getFilterMenuItems()) {
			conditions.addAll(Arrays.asList(fi.getFilterMenuItemConditions()));
		}
		return conditions.toArray(new FilterMenuItemCondition[] {});
	}

}
