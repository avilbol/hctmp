/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.helpers;

import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.dataentities.app.City;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Alexander Villamil
 */
public class CityVOParser extends HallocasaVOParser<City, CityVO> {

    /**
     * Constructor
     */
    public CityVOParser() {
        super();
    }

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */
    @Override
    protected void copyEntityPropertyToValueObjectProperty(CityVO vo,
            City entity, String propertyName, Object propertyValue,
            Object[] options) throws IllegalAccessException,
            InvocationTargetException {

        super.copyEntityPropertyToValueObjectProperty(vo, entity,
                propertyName, propertyValue, options);

    }

    @Override
    protected void copyVOPropertyToEntityProperty(CityVO vo, City entity,
            String propertyName, Object propertyValue, Object[] options)
            throws IllegalAccessException, InvocationTargetException {

        super.copyVOPropertyToEntityProperty(vo, entity, propertyName,
                propertyValue, options);
    }

    /**
     *
     * @param vo
     * @param entity
     * @param excludeIpList If this is true, the ip list is not copied
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void copyVOToEntity(CityVO vo, City entity,
            boolean excludeIpList) throws IllegalAccessException,
            InvocationTargetException {
        copyVOToEntity(vo, entity, buildOptions(excludeIpList));
    }

    /**
     * @param excludeIpList
     * @return
     */
    private Object[] buildOptions(boolean excludeIpList) {
        return new Object[]{excludeIpList};
    }

    /* Getters & Setters */
}
    

