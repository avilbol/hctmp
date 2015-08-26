package com.hallocasa.model.controlaccess;

import com.hallocasa.commons.vo.AppAccessInfoVO;
import com.hallocasa.commons.vo.UseCaseVO;
import com.hallocasa.model.application.HallocasaApplication;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.UserServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * AccessValidator implementation
 *
 * @author David Mantilla
 * @since 1.7
 */
@Named("accessValidator")
@SessionScoped
public class AccessValidatorImpl implements AccessValidator, Serializable {

    /* Static variables */
    private static final long serialVersionUID = -3594590690446235427L;
    private static final Logger LOG = Logger
            .getLogger(AccessValidatorImpl.class.getName());

    /* Dependencies */
    @Inject
    private WebSession webSession;
    @Inject
    private UserServices userServices;
    @Inject
    private HallocasaApplication hallocasaApplication;

    /* Instance variables */
    private List<UseCaseEnum> useCasesSet;

    /**
     * Default Constructor
     */
    public AccessValidatorImpl() {
    }

    /**
     * Constructor
     *
     * @param webSession
     * @param userServices
     */
    public AccessValidatorImpl(WebSession webSession,
            UserServices userServices) {
        super();
        this.webSession = webSession;
        this.userServices = userServices;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.model.controlaccess.AccessValidator#validateAccessToView
     * (com.mobiera.social.model.controlaccess.SocialViewEnum)
     */
    @Override
    public boolean validateAccessToView(HallocasaViewEnum hallocasaViewEnum) {
        // public page, always has access:
        if (!hallocasaViewEnum.isRequiresLogin()) {
            return true;
        }
        // is not public and user is not logged then reject it
        if (hallocasaViewEnum.isRequiresLogin()) {
            if (!webSession.isLogged()) {
                return false;
            }
        }
        // if view hasn't got use-cases means no restriction to logged user:
        if (hallocasaViewEnum.getUseCases().length == 0) {
            return true;
        }
        // validate restrictions:
        for (UseCaseEnum useCaseEnum : hallocasaViewEnum.getUseCases()) {
            if (validateAccessToUseCase(useCaseEnum)) {
                return true;
            }
        }
        // If none use-case found view hasn't access
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobiera.social.model.controlaccess.AccessValidator#
     * validateAccessToUseCase
     * (com.mobiera.social.model.controlaccess.UseCaseEnum)
     */
    @Override
    public boolean validateAccessToUseCase(UseCaseEnum useCaseEnum) {
        if (useCasesSet == null) {
            loadAppAccessInformation();
        }
        return containsUseCase(useCasesSet, useCaseEnum);
    }

    /**
     * Validates if the user's use-cases list contains the given use-case, or it
     * contains a given's use-case parent. For example, if the given use-case is
     * 'edit' then 'edit' and also 'main' is looked for
     *
     * @param array
     * @param toFind
     * @return
     */
    private boolean containsUseCase(List<UseCaseEnum> userUseCases,
            UseCaseEnum toFind) {
        if (userUseCases.contains(toFind)) {
            return true;
        }
        for (UseCaseEnum u : userUseCases) {
            if (userUseCases.contains(u)) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobiera.social.model.controlaccess.AccessValidator#clearCache()
     */
    @Override
    public void clear() {
        useCasesSet = null;
    }

    /**
     * Load access information
     */
    private void loadAppAccessInformation() {

        useCasesSet = new ArrayList<>();

        AppAccessInfoVO accessInfoVO;
        if (webSession.isLogged()) {
            accessInfoVO = userServices.getAppAccessInfo(webSession
                    .getCurrentUser().getId());

            // allowed use-cases
            for (UseCaseVO useCaseVO : accessInfoVO.getUseCases()) {
                UseCaseEnum u = UseCaseEnum.findByName(useCaseVO
                        .getUseCaseName());
                if (u == null) {
                    LOG.log(Level.WARNING, "Use case {0} is not in the Enumeration. It will be ignored",
                            useCaseVO.getUseCaseName());
                } else {
                    useCasesSet.add(u);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobiera.social.model.controlaccess.AccessValidator#
     * validateAccessToUseCases
     * (com.mobiera.social.model.controlaccess.UseCaseEnum[])
     */
    @Override
    public boolean validateAccessToUseCases(UseCaseEnum... useCaseEnums) {
        for (UseCaseEnum u : useCaseEnums) {
            if (validateAccessToUseCase(u)) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobiera.social.model.controlaccess.AccessValidator#
     * validateAccessToUseCasesAll
     * (com.mobiera.social.model.controlaccess.UseCaseEnum[])
     */
    @Override
    public boolean validateAccessToUseCasesAll(UseCaseEnum... useCaseEnums) {
        if (useCaseEnums.length == 0) {
            return false;
        }
        for (UseCaseEnum u : useCaseEnums) {
            if (!validateAccessToUseCase(u)) {
                return false;
            }
        }
        return true;
    }

}
