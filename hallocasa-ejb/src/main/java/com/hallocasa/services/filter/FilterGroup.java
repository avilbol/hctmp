/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * This class represents a filter group, that means a list of conditions over
 * the same entity field. All conditions will be concatenated with an "or"
 * between each other
 *
 * @author David Mantilla
 * @param <T>
 *            FilterCondition class
 */
public class FilterGroup<T> {

    private final List<FilterCondition<T>> filterConditions;
    private final String fieldName;
    private final String filterGroupId;

    /**
     * Constructor
     *
     * @param filterGroupId
     *            An unique string to identity this filter group in a list of
     *            filter groups used to build the same query sentence
     * @param fieldName
     *            Entity field over this filter group is going to apply for
     */
    public FilterGroup(String filterGroupId, String fieldName) {
        this.filterGroupId = filterGroupId;
        this.filterConditions = new ArrayList<>();
        this.fieldName = fieldName;
    }

    /**
     * Return this filter represented as a SQL Statement
     *
     * @return
     */
    public String getAsSQLStatement() {
        StringBuilder str = new StringBuilder();

        if (!getFilterConditions().isEmpty()) {
            str.append("(");
        }

        boolean needsOr = false;
        for (FilterCondition<T> fc : getFilterConditions()) {
            if (needsOr) {
                str.append(" or ");
            }
            str.append(fc.getAsSQLStatement());
            needsOr = true;
        }

        if (!getFilterConditions().isEmpty()) {
            str.append(")");
        }
        return str.toString();
    }

    /**
     * Adds a filter condition to the conditions listener
     *
     * @param filterCondition
     */
    public void addFilterCondition(FilterCondition<T> filterCondition) {
        getFilterConditions().add(filterCondition);
    }

    /**
     * Getter for fieldName
     *
     * @return fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Getter for filterGroupId
     *
     * @return filterGroupId
     */
    public String getFilterGroupId() {
        return filterGroupId;
    }

    /**
     * Get the filter condition list
     *
     * @return
     */
    public List<FilterCondition<T>> getFilterConditions() {
        return filterConditions;
    }

    /**
     * Clear conditions list
     */
    public void clearFilterConditions() {
        filterConditions.clear();
    }

    /**
     * Return this condition as query criteria predicate
     *
     * @param cb
     *            Criteria Builder
     * @param fromMap
     *            Map with all entities used in the from, which can be used to
     *            obtain an entity attribute
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Expression<Boolean> getAsPredicate(CriteriaBuilder cb,
            Map<String, From> fromMap) {
        if (getFilterConditions().isEmpty()) {
            return null;
        }
        List<Predicate> conditions = new ArrayList<>();
        for (FilterCondition<T> fc : getFilterConditions()) {
            conditions.add(fc.getAsPredicate(cb, fromMap));
        }
        return cb.or(conditions.toArray(new Predicate[0]));
    }
}
