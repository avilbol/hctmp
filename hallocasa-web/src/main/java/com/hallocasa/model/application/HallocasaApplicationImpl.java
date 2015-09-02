/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.application;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.hallocasa.services.interfaces.FileServicesInterface;
import com.hallocasa.services.interfaces.ImageServicesInterface;
import com.hallocasa.services.persistence.local.WcmPersistenceServices;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "applicationContext", eager = true)
@ApplicationScoped
public class HallocasaApplicationImpl implements HallocasaApplication, 
        Serializable {

    @EJB
    private WcmPersistenceServices persistenceServices;
    @EJB
    private ImageServicesInterface imageServices;
    @EJB
    private FileServicesInterface fileServices;

    /**
     * Getter for the current instance of the application context
     *
     * @return
     */
    public static HallocasaApplicationImpl getInstance() {
        return getInstance(FacesContext.getCurrentInstance());
    }

    /**
     *
     * @param facesContext
     * @return
     */
    public static HallocasaApplicationImpl getInstance(FacesContext facesContext) {
        return facesContext.getApplication()
                .evaluateExpressionGet(facesContext, "#{applicationContext}",
                        HallocasaApplicationImpl.class);
    }

    @PostConstruct
    public void initialize() {

    }

    /**
     * @return the databaseServices
     */
    public WcmPersistenceServices getPersistenceServices() {
        return persistenceServices;
    }

    /**
     * Getter for the ImageServices EJB
     *
     * @return EJB ImageServices
     */
    public ImageServicesInterface getImageServices() {
        return imageServices;
    }

    /**
     * Getter for the ImageServices EJB
     *
     * @return EJB ImageServices
     */
    public FileServicesInterface getFileServices() {
        return fileServices;
    }

    /**
     *
     * @return
     */
    public String getVersion() {
        return "0.0.0.1";
    }
}
