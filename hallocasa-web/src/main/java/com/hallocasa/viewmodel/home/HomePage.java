/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.home;

import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author david
 */
@ManagedBean(name = "homePage")
@ViewScoped
public class HomePage implements Serializable {

    // dependencies
    @Inject
    private NavigationHandler navigationHandler;

    /**
     * Initialize bean
     */
    @PostConstruct
    public void initialize() {
        String loginDialogStr = navigationHandler.getPageParams().get(
                ViewParamEnum.LOGIN_DIALOG.getParamKey());
        if (loginDialogStr != null && loginDialogStr.equals("1")) {
            processLoginDialogRequest();
        }
    }

    /**
     * Dummy method for forcing creation
     */
    public void launch() {
        // just for forcing creation
    }

    /**
     * Process the request of the opening for the login dialog
     */
    private void processLoginDialogRequest() {
        RequestContext.getCurrentInstance().execute("PF('login-dialog').show()");
    }
}
