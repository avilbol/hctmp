/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author David Mantilla
 * @param <T>
 *            Class for the Filter value
 */
public interface FilterMenuItem<T> {
	// TODO: The component filterMenu is using some properties like firstValue,
	// secondValue
	// which are not defined in this interface, however it says that it expects
	// this kind
	// of menu items. This is a extreme problem

	public enum FilterType {

		SELECT_MANY, RANGE, CONTAINS;
	}

	/**
	 * Validator method for the first value
	 *
	 * @param context
	 *            faces context
	 * @param component
	 *            component which is triggering the validation
	 * @param value
	 *            value to validate
	 * @throws ValidatorException
	 */
	public void validateFirstValue(FacesContext context, UIComponent component,
			Object value) throws ValidatorException;

	/**
	 * Validator method for the second value
	 *
	 * @param context
	 *            faces context
	 * @param component
	 *            component which is triggering the validation
	 * @param value
	 *            value to validate
	 * @throws ValidatorException
	 */
	public void validateSecondValue(FacesContext context,
			UIComponent component, Object value) throws ValidatorException;

	/**
	 * Process that the filter has change, The implmentation of this method must
	 * check the condition changed and add to the active condition list or
	 * remove from it, depends on the value changed
	 */
	public void processFilterChange();

	/**
	 * Removes a filter condition from the list of active conditions
	 *
	 * @param filterMenuItemConditionToRemove
	 */
	public void removeFilterMenuItemCondition(
			FilterMenuItemCondition<?> filterMenuItemConditionToRemove);

	/**
	 * Returns the filter type
	 *
	 * @return
	 */
	public FilterType getFilterType();

	/**
	 * If filter is SELECT... type, then must return the list of the filter
	 * options that will appear in the list
	 *
	 * @return
	 */
	public SelectItem[] getFilterMenuItemOptions();

	/**
	 * List of filter conditions. When the filter is RANGE type, then the value
	 * is in the first item of the filter conditions list and the list should
	 * not be greater than 1 item
	 *
	 * @return
	 */
	public FilterMenuItemCondition<T>[] getFilterMenuItemConditions();

	/**
	 * Return the converter
	 *
	 * @return
	 */
	public Converter getConverter();

	/**
	 * Return the title
	 *
	 * @return
	 */
	public String getTitle();

	/**
	 * Returns an id
	 *
	 * @return
	 */
	public String getId();

	/**
	 * 
	 * @return
	 */
	public T getFirstValue();

	/**
	 * 
	 * @return
	 */
	public T getSecondValue();

	/**
	 * 
	 * @return
	 */
	public List<T> getListValue();

	/**
	 * 
	 * @param value
	 */
	public void setFirstValue(T value);

	/**
	 * 
	 * @param value
	 */
	public void setSecondValue(T value);

	/**
	 * 
	 * @param values
	 */
	public void setListValue(List<T> values);

}
