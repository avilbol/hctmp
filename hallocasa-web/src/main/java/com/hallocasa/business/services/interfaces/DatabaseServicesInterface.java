/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.services.interfaces;

import javax.persistence.EntityManager;

/**
 *
 * @author David Mantilla
 */
@Deprecated
public interface DatabaseServicesInterface {

    /**
     * *************************************************************************
     * Methods
     * *************************************************************************
     */
    /**
     * Creates an entity manager
     *
     * @return
     */
    EntityManager createEntityManager();
    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
    
}
