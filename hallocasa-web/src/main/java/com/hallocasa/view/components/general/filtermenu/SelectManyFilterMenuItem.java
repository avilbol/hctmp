/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.general.filtermenu;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import com.hallocasa.commons.beans.Identificable;
import com.hallocasa.view.converters.IdentificableConverter;

/**
 *
 * @author David Mantilla
 * @param <T>
 */
public class SelectManyFilterMenuItem<T extends Identificable<?>> implements FilterMenuItem<T> {


    private IdentificableConverter<T> identificableConverter;
    private final List<SelectItem> filterOptions;
    private final String id;
    private final String title;
    private List<T> selectedValues;

    /**
     *
     * @param filterOptions
     * @param title
     * @param id
     */
    public SelectManyFilterMenuItem(List<SelectItem> filterOptions, String title, String id) {
        this.filterOptions = filterOptions;
        this.id = id;
        this.title = title;
        this.selectedValues = new ArrayList<>();
        // Nothing to do
    }

    @Override
    public void validateFirstValue(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Nothing to do
    }

    @Override
    public void validateSecondValue(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Nothing to do
    }

    @Override
    public void processFilterChange() {
        // Nothing to do
    }

    @Override
    public final FilterType getFilterType() {
        return FilterType.SELECT_MANY;
    }

    @Override
    public SelectItem[] getFilterMenuItemOptions() {
        return (SelectItem[]) filterOptions.toArray();
    }

    @Override
    public FilterMenuItemCondition<T>[] getFilterMenuItemConditions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter for title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public Converter getConverter() {
        if (identificableConverter == null) {
            identificableConverter = new IdentificableConverter<>();
            for (SelectItem si : getFilterMenuItemOptions()) {
                identificableConverter.addElement((T) si.getValue());
            }
        }
        return identificableConverter;
    }

    /**
     * Returns id
     *
     * @return
     */
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void removeFilterMenuItemCondition(FilterMenuItemCondition<?> filterMenuItemConditionToRemove) {
        throw new UnsupportedOperationException("Not yet");
    }

    @Override
    public List<T> getListValue() {
        return selectedValues;
    }

    @Override
    public void setListValue(List<T> values) {
       selectedValues = values;
    }

    @Override
    public T getFirstValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T getSecondValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFirstValue(T value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSecondValue(T value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
