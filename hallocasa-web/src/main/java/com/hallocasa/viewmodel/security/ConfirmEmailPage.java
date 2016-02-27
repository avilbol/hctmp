/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.security;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@ManagedBean(name = "confirmEmailPage")
@ViewScoped
public class ConfirmEmailPage {

    @Inject
    private ViewContext viewContext;
    @Inject
    private NavigationHandler navigationHandler;
    @EJB
    private SignUpServices signUpServices;
    @EJB
    private UserServices userServices;

    @PostConstruct
    public void initialize() {
        String email = navigationHandler.getPageParams().get(
                ViewParamEnum.EMAIL.getParamKey());
        String token = navigationHandler.getPageParams().get(
                ViewParamEnum.TOKEN.getParamKey());

        // All parameters are required, if some is missing then is forbidden
        if ((email == null) || (token == null)) {
            throw new ForbiddenException();
        }

        // validates email
        Long userId;
        UserVO userVO;
        userVO = userServices.find(email);
        if (userVO == null) {
            throw new ForbiddenException("");
        }

        // validates token
        String genToken = UserActivationLinkUtils.generateActivationKey(userVO.getId(),
                email);
        if (!genToken.equals(token)) {
            throw new ForbiddenException();
        }

        // activate user
        signUpServices.activateUser(userVO.getId());
    }

    /**
     * Method only for force bean creation
     *
     * @return
     */
    public String launch() {
        return "";
    }

    /**
     * Process event of going to login
     */
    public void processGoToLoginClick() {
        Map<ViewParamEnum, String> params = new HashMap<>();
        params.put(ViewParamEnum.LOGIN_DIALOG, "1");
        navigationHandler.redirectToPage(HallocasaViewEnum.HOME, params);
    }
}
