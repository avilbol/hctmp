/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.SportPossibility;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jhon Fredy Martínez Realpe
 */
public interface SportServicesInterface extends Serializable {

    /**
     * Declaration method to query all SportPossibility belonging to a
     * PropertyEnvirontment.
     *
     * @param propertyEnvirontmentId Unique identifier of the
     * PropertyEnvirontment.
     * @return List<SportPossibility>
     */
    List<SportPossibility> findByPropertyEnvirontmentId(int propertyEnvirontmentId);
}
