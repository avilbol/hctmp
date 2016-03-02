package com.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object with access information for a given application
 *
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppAccessInfo", propOrder = {"useCases", "operations",
    "supervisedOperations", "profiles", "informationGroups"})
public class AppAccessInfoVO implements ValueObject {

    /* static fields */

    /* instance variables */


    /* constructors */
   

}
