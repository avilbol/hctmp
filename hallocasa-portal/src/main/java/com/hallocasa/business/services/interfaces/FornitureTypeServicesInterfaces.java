/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.FornitureType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
public interface FornitureTypeServicesInterfaces extends Serializable {

    /**
     * Declaration method to query all FornitureType.
     *
     * @return List<FornitureType>.
     */
    List<FornitureType> findAll();
}
