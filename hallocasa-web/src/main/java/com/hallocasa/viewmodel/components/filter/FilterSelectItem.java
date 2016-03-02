/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.components.filter;

import javax.faces.model.SelectItem;

import com.hallocasa.services.filter.FilterCondition;

/**
 * SelectItem for filter items
 *
 * @author David Mantilla
 */
public class FilterSelectItem extends SelectItem {

    private static final long serialVersionUID = 2453323615432549825L;
    private final FilterCondition.FilterOperation filterOperation;
    private final Object rangeFrom;
    private final Object rangeTo;

    /**
     * Constructor
     *
     * @param value
     * @param label
     * @param filterOperation
     */
    public FilterSelectItem(Object value, String label,
            FilterCondition.FilterOperation filterOperation) {
        super(value, label);
        this.filterOperation = filterOperation;
        this.rangeFrom = null;
        this.rangeTo = null;
        if (filterOperation.equals(FilterCondition.FilterOperation.BETWEEN)) {
            throw new UnsupportedOperationException(
                    "For between operation use Object id, ( rangeFrom, rangeTo,  label ) constructor ");
        }
    }

    /**
     * Constructor for creating a between operation using the rangeFrom and RangeTo. id object is
     * used to identify the select menu item
     *
     * @param id
     * @param label
     * @param rangeFrom
     * @param rangeTo
     */
    public FilterSelectItem(Object id, Object rangeFrom, Object rangeTo,
            String label) {
        super(id, label);
        this.filterOperation = FilterCondition.FilterOperation.BETWEEN;
        this.rangeTo = rangeTo;
        this.rangeFrom = rangeFrom;
    }

    /**
     * @param value
     * @param label
     */
    public FilterSelectItem(Object value, String label) {
        super(value, label);
        this.filterOperation = FilterCondition.FilterOperation.EQUALS;
        this.rangeFrom = null;
        this.rangeTo = null;
    }

    /**
     * @return
     */
    public FilterCondition.FilterOperation getFilterOperation() {
        return filterOperation;
    }

    /**
     * @return the rangeFrom
     */
    public Object getRangeFrom() {
        return rangeFrom;
    }

    /**
     * @return the rangeTo
     */
    public Object getRangeTo() {
        return rangeTo;
    }

}
