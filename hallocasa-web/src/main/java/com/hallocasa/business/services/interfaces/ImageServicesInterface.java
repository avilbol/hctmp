/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.Image;
import com.hallocasa.business.exceptions.EntityNotFoundException;

/**
 *
 * @author David Mantilla
 */
public interface ImageServicesInterface {

    /**
     * *************************************************************************
     * Constanst
     * *************************************************************************
     */
    /**
     * *************************************************************************
     * Instance variable
     * *************************************************************************
     */
    /**
     * *************************************************************************
     * Constructor
     * *************************************************************************
     */
    /**
     * *************************************************************************
     * Methods
     * *************************************************************************
     */
    /**
     * Finds an image by its id
     *
     * @param imageId
     * @return
     * @throws EntityNotFoundException
     */
    public Image findImageById(Long imageId) throws EntityNotFoundException;

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
}
