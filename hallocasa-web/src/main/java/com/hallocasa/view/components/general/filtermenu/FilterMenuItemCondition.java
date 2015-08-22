/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import com.hallocasa.services.filter.FilterCondition.FilterOperation;

/**
 * Interface for filter menu item condition
 * 
 * @author David Mantilla
 * @param <T>
 *            Filter condition value type
 */
public interface FilterMenuItemCondition<T> {

	/**
	 * Getter for first value
	 *
	 * @return
	 */
	public T getFirstValue();

	/**
	 * Getter for second value
	 *
	 * @return
	 */
	public T getSecondValue();

	/**
	 * Return label
	 *
	 * @return
	 */
	public String getLabel();

	/**
	 * Filter menu item which this conditions belongs to
	 * 
	 * @return
	 */
	public FilterMenuItem<T> getFilterMenuItem();
	
	/**
	 * @return
	 */
	public FilterOperation getFilterOperation();
	

}
