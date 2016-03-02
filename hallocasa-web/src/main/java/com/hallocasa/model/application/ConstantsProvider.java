/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.application;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "constants")
@ViewScoped
public class ConstantsProvider implements Serializable {

    /* Static variables */
    private static final long serialVersionUID = -3579643717841029901L;

    public String getImagesPath() {
        return SystemConstants.IMAGES_PATH;
    }

    public String getResourcesPath() {
        return SystemConstants.RESOURCES_PATH;
    }

    public String getContextName() {
        return SystemConstants.APP_CONTEXT;
    }
    
    /**
     * Return url of the view
     * @param viewEnum
     * @return 
     */
    public String getPageUrl(HallocasaViewEnum viewEnum ){
        return viewEnum.getUrl();
    }
    
    /**
     * Return url of the view
     * @param viewEnum
     * @return 
     */
    public String getAbsolutePageUrl(HallocasaViewEnum viewEnum ){
        return SystemConstants.APP_CONTEXT + viewEnum.getUrl();
    }
}
