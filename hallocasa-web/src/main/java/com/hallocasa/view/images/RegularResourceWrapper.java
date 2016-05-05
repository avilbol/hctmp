/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.images;

import com.hallocasa.model.application.HallocasaApplicationImpl;
import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;

/**
 *
 * @author David Mantilla Esmosoft
 */
public class RegularResourceWrapper extends ResourceWrapper {

    private final javax.faces.application.Resource resource;

    public RegularResourceWrapper(Resource resource) {
        this.resource = resource;
    }

    @Override
    public Resource getWrapped() {
        return this.resource;
    }

    @Override
    public String getRequestPath() {
        if (resource != null) {
            String requestPath = resource.getRequestPath();

            // get current revision
            String revision = HallocasaApplicationImpl.getInstance().getVersion();

            if (requestPath.contains("?")) {
                requestPath = requestPath + "&rv=" + revision;
            } else {
                requestPath = requestPath + "?rv=" + revision;
            }
            return requestPath;
        }
        return null;
    }

    @Override
    public String getContentType() {
        return getWrapped().getContentType();
    }
}
