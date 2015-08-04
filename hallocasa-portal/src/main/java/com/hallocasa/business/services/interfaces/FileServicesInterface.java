/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.File;
import com.hallocasa.business.exceptions.ServiceException;

import java.io.InputStream;

/**
 *
 * @author David Mantilla
 */
public interface FileServicesInterface {

    /**
     * ************************************************************************
     * Constanst
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Instance variable
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Constructor
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Methods
     * *************************************************************************
     */
    /**
     * Get a File entity as input stream
     * @param dbFile
     * @return
     * @throws ServiceException
     */
    public InputStream getFileInputStream(File dbFile) throws ServiceException;

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
}
