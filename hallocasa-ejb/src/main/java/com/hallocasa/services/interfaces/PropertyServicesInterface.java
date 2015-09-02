/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.dataentities.wcm.Property;
import com.hallocasa.dataentities.wcm.PropertyTopography;
import com.hallocasa.services.filter.FilterGroup;
import com.hallocasa.services.pagination.PageIndexInfo;
import com.hallocasa.services.pagination.PageList;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
public interface PropertyServicesInterface extends Serializable {

    /**
     * Declaration method to query Propertys by Id.
     *
     * @param id Unique identifier of the Property.
     * @return Property.
     */
    Property findById(int id);

    /**
     * Declaration method to query all PropertyTopography by propertyId.
     *
     * @param propertyId Unique identifier of the Property.
     * @return List<>
     */
    List<PropertyTopography> findByPropertyId(int propertyId);

    /**
     * Declaration method to query all Properties.
     *
     * @return List<>.
     */
    List<Property> findAll();

    /**
     * Declaration method to query all Properties that meet selected filters.
     *
     * @param filters
     * @param pageIndexInfo
     * @return List<>.
     */
    public PageList<Property> getProperties(List<FilterGroup<?>> filters, 
            PageIndexInfo pageIndexInfo);
}
