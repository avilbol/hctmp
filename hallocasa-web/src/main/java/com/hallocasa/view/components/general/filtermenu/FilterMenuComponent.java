/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import java.util.HashMap;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.hallocasa.view.components.base.BaseComponent;

/**
 *
 * @author David Mantilla
 */
@FacesComponent("FilterMenuComponent")
public class FilterMenuComponent extends BaseComponent {

	private enum AttributesEnum {

		value, filterChangeListener;
	}

	@Override
	protected void initialize() {
		//
	}

	@Override
	protected void saveComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		//
	}

	@Override
	protected void restoreComponent(FacesContext facesContext,
			HashMap<String, Object> map) {
		//
	}

	/**
	 * Getter for the value attribute
	 *
	 * @return
	 */
	@SuppressWarnings("unused")
	private FilterMenuModel getValue() {
		return (FilterMenuModel) getAttributes().get(
				AttributesEnum.value.name());
	}

	/**
	 * Listener for the filter selection change
	 *
	 * @param event
	 */
	@SuppressWarnings("rawtypes")
	public void onFilterChange(AjaxBehaviorEvent event) {
		FilterMenuItem filterMenuItem = (FilterMenuItem) event.getComponent()
				.getAttributes().get("filterItem");
		filterMenuItem.processFilterChange();

		if (getFilterChangeListener() != null) {
			MethodExpression me = getFilterChangeListener();
			me.invoke(FacesContext.getCurrentInstance().getELContext(),
					new Object[] { filterMenuItem });
		}
	}

	/**
	 * @return the filter change listener
	 */
	private MethodExpression getFilterChangeListener() {
		return (MethodExpression) getAttributes().get(
				AttributesEnum.filterChangeListener.name());
	}
}
