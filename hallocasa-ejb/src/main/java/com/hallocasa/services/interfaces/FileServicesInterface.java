/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.dataentities.File;

import java.io.InputStream;

/**
 *
 * @author David Mantilla
 */
public interface FileServicesInterface {

 
    /**
     * Get a File entity as input stream
     * @param dbFile
     * @return
     * @throws ServiceException
     */
    public InputStream getFileInputStream(File dbFile) throws ServiceException;


}
