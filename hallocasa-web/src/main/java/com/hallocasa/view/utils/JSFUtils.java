package com.hallocasa.view.utils;

import com.hallocasa.view.i18n.Messages;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import javax.faces.application.FacesMessage;

public class JSFUtils {

    /**
     * Returns a message from the bundle language file
     *
     * @param resourceBundleName
     * @param resourceBundleKey
     * @return
     * @throws MissingResourceException
     */
    public static String getResourceBundleString(String resourceBundleName,
            String resourceBundleKey) throws MissingResourceException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication()
                .getResourceBundle(facesContext, resourceBundleName);
        return bundle.getString(resourceBundleKey);
    }

    /**
     * Returns a message from the bundle language file
     *
     * @param resourceBundleKey
     * @return
     * @throws MissingResourceException
     */
    public static String getViewBundleString(String resourceBundleKey) {
        if (resourceBundleKey == null) {
            return null;
        }
        try {
            return getResourceBundleString(Messages.FACES_BUNDLE_NAME,
                    resourceBundleKey);
        } catch (MissingResourceException e) {
            return "??? " + resourceBundleKey + " ???";
        }
    }

    /**
     * Adds a global faces error message
     *
     * @param summary
     * @param detail
     */
    public static void addFacesError(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, summary, detail));
    }

    /**
     * Adds a faces error message tied to a component
     *
     * @param componentId Id from the component for attaching message to
     * @param summary
     * @param detail
     */
    public static void addFacesError(String componentId, String summary,
            String detail) {
        FacesContext.getCurrentInstance().addMessage(componentId,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }
}
