/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.commons.exceptions.services.ServiceException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;

import com.hallocasa.dataentities.wcm.File;
import com.hallocasa.services.constants.ServiceErrorMessage;
import com.hallocasa.services.interfaces.FileServicesInterface;

/**
 * @author David Mantilla
 */
@Stateless
public class FileServices implements FileServicesInterface {

    private static final String FILES_PATH;
    public static final String USER_IMAGES_PATH;
    private static final Logger LOG = Logger.getLogger(FileServices.class
            .getName());
    public static final String EJB_RESOURCE_NAME = "FileServices";

    static {
        Properties properties = new Properties();
        try {
            properties.load(FileServices.class.getClassLoader()
                    .getResourceAsStream(
                            "hallocasa.config.properties"));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        FILES_PATH = properties.getProperty("Persistance.Files.filesPath");
        USER_IMAGES_PATH = properties.getProperty("Persistance.Files.userImagesPath");
    }
    
    
   
    
    /**
     * Get the inputstream from a File data entity
     *
     * @param dbFile
     * @return
     * @throws ServiceException
     */
    @Override
    public InputStream getFileInputStream(File dbFile) throws ServiceException {
        try {
            return new FileInputStream(new java.io.File(FILES_PATH
                    + dbFile.getFileName()));
        } catch (FileNotFoundException e) {
            throw new ServiceException(
                    "Error trying to get file in the server",
                    ServiceErrorMessage.FILE_SERVICES_GET_FILE_ERROR, e);
        }
    }

}
