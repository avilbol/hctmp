/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.home;

import com.hallocasa.commons.StrategySort;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.Token;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.SecurityServices;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

    @EJB
	private PropertyServices propertyServices;
    
    private Integer userIdInRecoveryProcess;
    
    private List<PropertyVO> propertyList;
    
    private static final Integer PROPERTY_AMMOUNT_TO_SHOW = 10;
    
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
        }
        propertyList = propertyServices.find(PROPERTY_AMMOUNT_TO_SHOW,
				StrategySort.RANDOM);
    }

    /**
     * Dummy method for forcing creation
     */
    public void launch() {
        // just for forcing creation
    }
    
    public void goToProperty(String id){
		HashMap<ViewParamEnum, String> params = new HashMap<ViewParamEnum, String>();
		params.put(ViewParamEnum.PROPERTY_ID, id);
		navigationHandler.redirectToPage(HallocasaViewEnum.PROPERTY_DETAIL, params);
	}
    
    public void goToProfile(UserVO userVO){
		HashMap<ViewParamEnum, String> params = new HashMap<ViewParamEnum, String>();
		params.put(ViewParamEnum.USER_ID, userVO.getId().toString());
		navigationHandler.redirectToPage(HallocasaViewEnum.PUBLIC_PROFILE, params);
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

	public List<PropertyVO> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyVO> propertyList) {
		this.propertyList = propertyList;
	}
}
