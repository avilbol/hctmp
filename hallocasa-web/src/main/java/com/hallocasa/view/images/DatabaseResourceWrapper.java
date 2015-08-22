/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.images;

import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.model.application.ApplicationContext;
import com.hallocasa.dataentities.File;
import com.hallocasa.dataentities.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;
import javax.faces.context.FacesContext;

/**
 *
 * @author David Mantilla Esmosoft
 */
public class DatabaseResourceWrapper extends ResourceWrapper {

    private InputStream content;
    private File afh;
    private Resource resource;
    private String resourceName;
    
    @SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(DatabaseResourceWrapper.class.getName());

    public DatabaseResourceWrapper(Image dbImage, String resourceName) {
        super();
        this.resourceName = resourceName;
        this.afh = dbImage.getFile();
    }

    @Override
    public String getRequestPath() {
        StringBuilder str = new StringBuilder(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/javax.faces.resource/");
        str.append(resourceName);
        str.append("?ln=database");
        return str.toString();
    }

    @Override
    public String getContentType() {
        return afh.getContentType();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return getContent();
    }

    @Override
    public Map<String, String> getResponseHeaders() {
        return new HashMap<String, String>();
    }

    @Override
    public URL getURL() {
        return super.getURL();
    }

    @Override
    public boolean userAgentNeedsUpdate(FacesContext context) {
        return true;
    }

    public InputStream getContent() {
        if (content == null) {
            try {
                content = ApplicationContext.getInstance().getFileServices().getFileInputStream(afh);
            } catch (ServiceException ex) {
                Logger.getLogger(DatabaseResourceWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return content;
    }

    @Override
    public Resource getWrapped() {
        return resource;
    }
}
