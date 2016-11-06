package com.hallocasa.helpers;

import java.lang.reflect.InvocationTargetException;

import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.dataentities.app.properties.PropertyProposal;

/**
 * Parser of propertyProposalVO to propertyProposal and vice-versa
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class PropertyProposalVOParser extends HallocasaVOParser<PropertyProposal, PropertyProposalVO> {

    /**
     * Constructor
     */
    public PropertyProposalVOParser() {
        super();
    }

    /* static fields */

    /* instance variables */

    /* constructors */

    /* Methods */
    @Override
    protected void copyEntityPropertyToValueObjectProperty(PropertyProposalVO vo,
            PropertyProposal entity, String propertyName, Object propertyValue,
            Object[] options) throws IllegalAccessException,
            InvocationTargetException {

        super.copyEntityPropertyToValueObjectProperty(vo, entity,
                propertyName, propertyValue, options);

    }

    @Override
    protected void copyVOPropertyToEntityProperty(PropertyProposalVO vo, PropertyProposal entity,
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
    public void copyVOToEntity(PropertyProposalVO vo, PropertyProposal entity,
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