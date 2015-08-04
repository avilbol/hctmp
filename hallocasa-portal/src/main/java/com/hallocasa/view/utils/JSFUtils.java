package com.hallocasa.view.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.hallocasa.commons.constants.ViewBundle;

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
    public static String getViewBundleString(String resourceBundleKey)  {
        try {
            return getResourceBundleString(ViewBundle.BUNDLE_NAME,
                    resourceBundleKey);
        } catch (MissingResourceException e) {
            return "??? " + resourceBundleKey + " ???";
        }
    }
}
