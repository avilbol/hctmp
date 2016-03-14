package com.hallocasa.helpers;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.dataentities.app.Country;
import java.lang.reflect.InvocationTargetException;

/**
 * Parser of countryVO to country and vice-versa
 *
 * @author David Mantilla
 * @since 1.7
 */
public class CountryVOParser extends HallocasaVOParser<Country, CountryVO> {

    /**
     * Constructor
     */
    public CountryVOParser() {
        super();
    }

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */
    @Override
    protected void copyEntityPropertyToValueObjectProperty(CountryVO vo,
            Country entity, String propertyName, Object propertyValue,
            Object[] options) throws IllegalAccessException,
            InvocationTargetException {

        super.copyEntityPropertyToValueObjectProperty(vo, entity,
                propertyName, propertyValue, options);

    }

    @Override
    protected void copyVOPropertyToEntityProperty(CountryVO vo, Country entity,
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
    public void copyVOToEntity(CountryVO vo, Country entity,
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