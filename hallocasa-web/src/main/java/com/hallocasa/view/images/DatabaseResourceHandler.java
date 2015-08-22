/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.images;

import com.hallocasa.model.application.ApplicationContext;
import com.hallocasa.dataentities.Image;
import com.hallocasa.exceptions.EntityNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;

public class DatabaseResourceHandler extends ResourceHandlerWrapper {

    /**
     * ************************************************************************
     * Constanst
     * **************************************************************************
     */
    private static final Logger LOG = Logger.getLogger(DatabaseResourceHandler.class.getName());

    /**
     * ************************************************************************
     * Instance variable
     * **************************************************************************
     */
    private ResourceHandler wrapped;

    public DatabaseResourceHandler(ResourceHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ResourceHandler getWrapped() {
        return this.wrapped;
    }

    /**
     * Create the resource
     *
     * @param resourceName
     * @param libraryName
     * @return
     */
    @Override
    public Resource createResource(String resourceName, String libraryName) {
        if (libraryName.equals("dbimages")) {

            // Get the image from the database
            ApplicationContext applicationContext = ApplicationContext.getInstance();
            Long id = new Long(resourceName);
            Image dbImage = null;
            try {
                dbImage = applicationContext.getImageServices().findImageById(id);
            } catch (EntityNotFoundException ex) {
                LOG.log(Level.SEVERE, null, ex);
                return null;
            }

            return new DatabaseResourceWrapper(dbImage, resourceName);
        } else {
            Resource resource = super.createResource(resourceName, libraryName);
            return resource == null ? null : new RegularResourceWrapper(resource);
        }
    }

    public static String fileIdToResourceName(Integer id) {
        StringBuilder str = new StringBuilder("database:");
        str.append(id);
        return str.toString();
    }
}
