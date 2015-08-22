/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.dataentities.Image;
import com.hallocasa.exceptions.EntityNotFoundException;

/**
 *
 * @author David Mantilla
 */
public interface ImageServicesInterface {

    /**
     * Finds an image by its id
     *
     * @param imageId
     * @return
     * @throws EntityNotFoundException
     */
    public Image findImageById(Long imageId) throws EntityNotFoundException;

}
