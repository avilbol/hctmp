/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import com.hallocasa.business.services.filter.FilterCondition.FilterOperation;

/**
 *
 * @author David Mantilla
 * @param <T>
 */
public class DefaultContainsMenuItemFilterCondition<T> implements FilterMenuItemCondition<T> {

    private final T firstValue;
    private final FilterMenuItem<T> filterMenuItem;
    private final String label;

    /**
     *
     * @param filterMenuItem
     * @param label
     * @param id
     * @param value Value that reprents the contents parameter in the condition
     */
    public DefaultContainsMenuItemFilterCondition(FilterMenuItem<T> filterMenuItem, String label, T value) {
        this.filterMenuItem = filterMenuItem;
        this.label = label;
        this.firstValue = value;
        if ( !filterMenuItem.getFilterType().equals(FilterMenuItem.FilterType.CONTAINS ) ){
            throw new IllegalArgumentException("Not supported for that kind of menu item");
        }
    }


    @Override
    public T getFirstValue() {
        return firstValue;
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
    public T getSecondValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Return a user-redeable string of this condition
     *
     * @return
     */
    @Override
    public String toString() {
        return getLabel() + ": " + getFirstValue();
    }
    
    @Override
    public FilterOperation getFilterOperation() {
    	return FilterOperation.LIKE;
    }

}
