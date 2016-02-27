/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import java.util.HashMap;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.hallocasa.view.components.base.BaseComponent;

/**
 *
 * @author David Mantilla
 */
@FacesComponent("FilterStatusComponent")
public class FilterStatusComponent extends BaseComponent {


	private enum AttributesEnum {

		value;
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
	 * Listener for the condition remove click
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onConditionRemoveClick(ActionEvent event) {
		FilterMenuItemCondition filterMenuItemCondition = (FilterMenuItemCondition) event
				.getComponent().getAttributes().get("condition");
		filterMenuItemCondition.getFilterMenuItem()
				.removeFilterMenuItemCondition(filterMenuItemCondition);
	}


	/**
	 * Getter for value
	 *
	 * @return
	 */
	@SuppressWarnings("unused")
	private FilterMenuModel getValue() {
		return (FilterMenuModel) getAttributes().get(
				AttributesEnum.value.name());
	}
}
