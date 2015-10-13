/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.application;

import com.hallocasa.commons.Language;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.hallocasa.services.interfaces.FileServicesInterface;
import com.hallocasa.services.interfaces.ImageServicesInterface;
import com.hallocasa.services.persistence.local.WcmPersistenceServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    @Deprecated
    private WcmPersistenceServices persistenceServices;
    @EJB
    @Deprecated
    private ImageServicesInterface imageServices;
    @EJB
    @Deprecated
    private FileServicesInterface fileServices;
    
    private List<Language> languages;

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
        languages = new ArrayList<>();
        languages.add(Language.en);
        languages.add(Language.es);
        languages.add(Language.de);
        
    }

    /**
     * @return the databaseServices
     */
    @Deprecated
    public WcmPersistenceServices getPersistenceServices() {
        return persistenceServices;
    }

    /**
     * Getter for the ImageServices EJB
     *
     * @return EJB ImageServices
     */
    @Deprecated
    public ImageServicesInterface getImageServices() {
        return imageServices;
    }

    /**
     * Getter for the ImageServices EJB
     *
     * @return EJB ImageServices
     */
    @Deprecated
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

    /**
     * Return the list of available languages
     * @return 
     */
    public List<Language> getLanguages() {
        return languages;
    }
    
    
}
