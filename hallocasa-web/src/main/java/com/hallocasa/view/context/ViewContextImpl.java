/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.context;

import com.hallocasa.view.utils.JSFUtils;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author david
 */
@Named
@SessionScoped
public class ViewContextImpl implements ViewContext, Serializable {

    @Override
    public void showGlobalErrorMessage(String summaryKey, String detailKey) {
        JSFUtils.addFacesError(JSFUtils.getViewBundleString(summaryKey),
                JSFUtils.getViewBundleString(detailKey));
    }

}
