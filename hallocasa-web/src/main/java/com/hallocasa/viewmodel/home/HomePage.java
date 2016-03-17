/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.home;

import com.hallocasa.dataentities.app.Token;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.services.interfaces.SecurityServices;
import com.hallocasa.view.navigation.HallocasaViewEnum;
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
    
    @Inject
    private SecurityServices securityServices;

    private Integer userIdInRecoveryProcess;
    
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
        String passwordRecoveryToken = navigationHandler.getPageParams()
                .get(ViewParamEnum.RECOVERY_PASSWORD.getParamKey());
        if(passwordRecoveryToken != null){
            Token token = securityServices.getToken(passwordRecoveryToken);
            if(token != null){
                HallocasaApplicationImpl.getInstance()
                        .setUserIdInRecoveryProcess(token.getIdAssociated());
                processPasswordRecoveryDialogRequest();
            }
            else{
                navigationHandler.redirectToPage(HallocasaViewEnum.FORBIDDEN);
            }
        }
    }

    /**
     * Dummy method for forcing creation
     */
    public void launch() {
        // just for forcing creation
    }

    public void goToExploreProfiles(){
    	navigationHandler.redirectToPage(HallocasaViewEnum.BROWSE_PROFILE);
    }
    
    /**
     * Process the request of the opening for the login dialog
     */
    private void processLoginDialogRequest() {
        RequestContext.getCurrentInstance().execute("PF('login-dialog').show()");
    }
    
    /**
     * Process the request of the opening for the login dialog
     */
    private void processPasswordRecoveryDialogRequest() {
        RequestContext.getCurrentInstance().execute("PF('password-retype-dialog').show()");
    }

    public Integer getUserIdInRecoveryProcess() {
        return userIdInRecoveryProcess;
    }

    public void setUserIdInRecoveryProcess(Integer userIdInRecoveryProcess) {
        this.userIdInRecoveryProcess = userIdInRecoveryProcess;
    }
    
    
}
