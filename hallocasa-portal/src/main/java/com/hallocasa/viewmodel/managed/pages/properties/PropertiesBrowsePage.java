package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hallocasa.business.dataentities.Property;
import com.hallocasa.business.services.TypeServices;
import com.hallocasa.business.services.filter.FilterGroup;
import com.hallocasa.business.services.interfaces.GeoDelimitationServicesInterface;
import com.hallocasa.business.services.interfaces.PropertyServicesInterface;
import com.hallocasa.business.services.pagination.PageIndexInfo;
import com.hallocasa.business.services.pagination.PageList;
import com.hallocasa.view.components.datalist.LazyPageListDataModel;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItem;
import org.primefaces.context.RequestContext;

/**
 * This class represents the controller for the properties filtering page
 *
 * @author David Mantilla
 */
@ManagedBean(name = "propertiesBrowsePage")
@ViewScoped
public class PropertiesBrowsePage implements Serializable {

    private static final long serialVersionUID = -6874720085659900768L;
    private LazyPageListDataModel<Property> propertiesDataModel;

    /* dependencies */
    @EJB
    private PropertyServicesInterface propertyServices;
    @EJB
    private TypeServices typeServices;
    @EJB
    private GeoDelimitationServicesInterface geoDelimitationServices;

    /* instance variables */
    private SurroundingFilterMenuModel surroundingFilterMenuModel;
    private AdditionalFilterMenuModel additionalFilterMenuModel;
    private FreeTimeFilterMenuModel freeTimeFilterMenuModel;
    private HealthFilterMenuModel healthFilterMenuModel;
    private SecurityFilterMenuModel securityFilterMenuModel;
    private FamilyFilterMenuModel familyFilterMenuModel;
    private MainFilterMenuModel mainFilterMenuModel;

    @PostConstruct
    public void initialize() {
        createPropertiesDataModel();
        createFilterLists();
    }

    /**
     * Create properties Data Model
     */
    private void createPropertiesDataModel() {
        propertiesDataModel = new LazyPageListDataModel<Property>() {
            private static final long serialVersionUID = 4122401245344475025L;

            @Override
            protected PageList<Property> loadPage(
                    final PageIndexInfo indexInfo, Map<String, Object> filters) {
                List<FilterGroup<?>> filterGroups = joinFilterGroups();
                return propertyServices.getProperties(
                        filterGroups, indexInfo);
            }
        };
    }

    /**
     * Creates a filter
     */
    private void createFilterLists() {
        mainFilterMenuModel = new MainFilterMenuModel(typeServices,
                geoDelimitationServices);
        additionalFilterMenuModel = new AdditionalFilterMenuModel(typeServices);
        surroundingFilterMenuModel = new SurroundingFilterMenuModel(
                typeServices);
        freeTimeFilterMenuModel = new FreeTimeFilterMenuModel(typeServices);
        healthFilterMenuModel = new HealthFilterMenuModel(typeServices);
        familyFilterMenuModel = new FamilyFilterMenuModel(typeServices);
        securityFilterMenuModel = new SecurityFilterMenuModel(typeServices);
    }

    /**
     * Create a unified list of filters joining all filters menu
     *
     * @return
     */
    private List<FilterGroup<?>> joinFilterGroups() {
        List<FilterGroup<?>> list = new ArrayList<>();
        list.addAll(mainFilterMenuModel.getFilterGroups());
        list.addAll(additionalFilterMenuModel.getFilterGroups());
        list.addAll(surroundingFilterMenuModel.getFilterGroups());
        list.addAll(freeTimeFilterMenuModel.getFilterGroups());
        list.addAll(healthFilterMenuModel.getFilterGroups());
        list.addAll(familyFilterMenuModel.getFilterGroups());
        list.addAll(securityFilterMenuModel.getFilterGroups());

        return list;
    }

    /**
     * Getter for propertiesDataModel
     *
     * @return propertiesDataModel
     */
    public LazyPageListDataModel<Property> getPropertiesDataModel() {
        return propertiesDataModel;
    }

    /**
     * @return the surroundingFilterMenuModel
     */
    public SurroundingFilterMenuModel getSurroundingFilterMenuModel() {
        return surroundingFilterMenuModel;
    }

    /**
     * @return the additionalFilterMenuModel
     */
    public AdditionalFilterMenuModel getAdditionalFilterMenuModel() {
        return additionalFilterMenuModel;
    }

    /**
     * @return the freeTimeFilterMenuModel
     */
    public FreeTimeFilterMenuModel getFreeTimeFilterMenuModel() {
        return freeTimeFilterMenuModel;
    }

    /**
     * @return the healthFilterMenuModel
     */
    public HealthFilterMenuModel getHealthFilterMenuModel() {
        return healthFilterMenuModel;
    }

    /**
     * @return the securityFilterMenuModel
     */
    public SecurityFilterMenuModel getSecurityFilterMenuModel() {
        return securityFilterMenuModel;
    }

    /**
     * @return the familyFilterMenuModel
     */
    public FamilyFilterMenuModel getFamilyFilterMenuModel() {
        return familyFilterMenuModel;
    }

    /**
     * @return the mainFilterMenuModel
     */
    public MainFilterMenuModel getMainFilterMenuModel() {
        return mainFilterMenuModel;
    }

    /**
     * Listener for main filter change
     *
     * @param changedMenuItem
     */
    public <Y> void onMainFilterMenuChange(FilterMenuItem<Y> changedMenuItem) {

        if (changedMenuItem.getId().equals(
                mainFilterMenuModel.getStateFilter().getId())) {
            mainFilterMenuModel.reloadCities(changedMenuItem.getListValue());

            RequestContext.getCurrentInstance().update(
                    "mainFilterMenu:fiterMenuAccordeon:3:filterTab");
        }
    }
}
