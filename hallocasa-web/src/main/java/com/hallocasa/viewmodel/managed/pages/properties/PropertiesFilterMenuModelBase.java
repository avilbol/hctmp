/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.properties;

import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.hallocasa.dataentities.interfaces.TypeInterface;
import com.hallocasa.services.TypeServices;
import com.hallocasa.services.filter.FilterGroup;
import com.hallocasa.commons.Language;
import com.hallocasa.view.utils.JSFUtils;
import com.hallocasa.model.session.WebSessionImpl;
import com.hallocasa.view.components.general.filtermenu.DefaultFilterMenuModel;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItem;
import com.hallocasa.view.converters.IdentificableConverter;
import com.hallocasa.viewmodel.components.filter.ContainsFilterGroup;
import com.hallocasa.viewmodel.components.filter.FilterSelectItem;
import com.hallocasa.viewmodel.components.filter.ManyFilterGroup;
import com.hallocasa.viewmodel.components.filter.RangeFilterGroup;

/**
 *
 * @author David Mantilla
 */
public abstract class PropertiesFilterMenuModelBase extends DefaultFilterMenuModel {

    protected final TypeServices typeServices;
    protected final Language language;
    protected int index;

    public PropertiesFilterMenuModelBase(TypeServices typeServices, int index) {
        this.typeServices = typeServices;
        this.language = WebSessionImpl.getCurrentInstance().getCurrentLanguage();
        this.index = index;
    }

    /**
     * Create a between filter
     *
     * @param <T>
     * @param valueClass
     * @param attributeName
     * @param labelBundleKey
     */
    protected <T extends Number> void createBetweenFilter(Class<T> valueClass, String attributeName,
            String labelBundleKey) {
        RangeFilterGroup<T> filterGroup = new RangeFilterGroup<>("f" + (index++), attributeName,
                JSFUtils.getViewBundleString(labelBundleKey));
        addFilterMenuItem(filterGroup);
    }

    /**
     * Create contains filter
     *
     * @param <T>
     * @param valueClass
     * @param attributeName
     * @param labelBundleKey
     */
    protected <T> void createContainsFilter(Class<T> valueClass, String attributeName,
            String labelBundleKey) {
        ContainsFilterGroup<T> filterGroup = new ContainsFilterGroup<>("f" + (index++),
                attributeName, JSFUtils.getViewBundleString(labelBundleKey));
        addFilterMenuItem(filterGroup);
    }

    /**
     * Return this model translated as a model Object list: FilterGroups
     *
     * @return
     */
    public List<FilterGroup<?>> getFilterGroups() {
        List<FilterGroup<?>> filterGroups = new ArrayList<>();
        for (FilterMenuItem<?> item : getFilterMenuItems()) {
            filterGroups.add((FilterGroup<?>) item);
        }
        return filterGroups;
    }

    /**
     *
     * @param <T> Class of the value
     * @param attributeName
     * @param typeClass
     * @param labelBundleKey
     * @param converter
     * @param filterOptionList
     */
    protected <T> ManyFilterGroup<T> createManyFilter(Class<T> typeClass, String attributeName,
            String labelBundleKey,
            List<FilterSelectItem> filterOptionList, Converter converter) {
        ManyFilterGroup<T> filterGroup;
        filterGroup = new ManyFilterGroup<>("f" + (index++), attributeName, JSFUtils.
                getViewBundleString(labelBundleKey), filterOptionList, language);
        addFilterMenuItem(filterGroup);
        filterGroup.setConverter(converter);
        return filterGroup;
    }

    /**
     * Creates filter of many selecion based on an attribute of the property
     *
     * @param filterClassType
     * @param attributeName
     * @param labelBundleKey
     */
    @SuppressWarnings(value = "unchecked")
    protected void createTypeFilter(Class<? extends TypeInterface> filterClassType,
            String attributeName,
            String labelBundleKey) {
        // get attribute class
        ManyFilterGroup<TypeInterface> filterGroup;
        List<FilterSelectItem> types = createSelectItemList((List<TypeInterface>) typeServices.
                getTypeList(filterClassType, language));
        filterGroup = new ManyFilterGroup<>("f" + (index++), attributeName, JSFUtils.
                getViewBundleString(labelBundleKey), types, language);
        @SuppressWarnings(value = "rawtypes")
        IdentificableConverter converter = new IdentificableConverter<>();
        for (SelectItem si : types) {
            converter.addElement((TypeInterface) si.getValue());
        }
        filterGroup.setConverter(converter);
        addFilterMenuItem(filterGroup);
    }

    /**
     * Creates a SelectItem list from the a type list
     *
     * @param <T>
     * @param typeList
     * @return
     */
    protected <T extends TypeInterface> List<FilterSelectItem> createSelectItemList(List<T> typeList) {
        List<FilterSelectItem> list = new ArrayList<>();
        for (T item : typeList) {
            FilterSelectItem si = new FilterSelectItem(item, item.getLabel(language));
            list.add(si);
        }
        return list;
    }

    public int getIndex() {
        return index;
    }

}
