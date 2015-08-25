package com.hallocasa.model.controlaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Enumeration to control all the views in the application
 *
 * @author David Mantilla
 * @since 1.7
 *
 */
@SuppressWarnings({"javadoc", "rawtypes"})
public enum HallocasaViewEnum {

    // Public pages
    /**
     * Page that is shown every time user is not allowed to enter the page is
     * trying to get into
     */
    FORBIDDEN(HallocasaViewNames.FORBIDDEN, "/pages/commons/forbidden.xhml", 
        null, false),
    // access controlled pages
    /**
     * Page to log in
     */
    LOGIN(HallocasaViewNames.LOGIN, "/pages/login/index.xhtml",null, false),
    /**
     * Page to registering new partner
     */
    REGISTER(HallocasaViewNames.REGISTER, null, false),
    /**
     * Default page when the user is logged
     */
    HOME(HallocasaViewNames.HOME, null, true),
    /**
     * Page for user ask the password to be recovery by asking an email
     */
    PASSWORD_RECOVERY_REQUEST(HallocasaViewNames.PASSWORD_RECOVERY_REQUEST,
            null, false),
    /**
     * Page for account information
     */
    MY_ACCOUNT(HallocasaViewNames.MY_ACCOUNT, null, true,
            UseCaseEnum.SEE_EDIT_MY_ACCOUNT),
    /**
     * Page for user recover password writing a new one
     */
    PASSWORD_RECOVERY(HallocasaViewNames.PASSWORD_RECOVERY,
            null, false),
    /**
     * Page for user activation after a registering process
     */
    USER_ACTIVATION(HallocasaViewNames.USER_ACTIVATION,
            null, false);

    /* Static */

    /* Instance variables */
    private final String viewName;
    private final Class<?> viewClass;
    private final UseCaseEnum[] useCases;
    private final boolean requiresLogin;
    private final String url;

    /**
     * Constructor
     *
     * @param viewName Name to identify page
     * @param viewClass Class of the view
     * @param requiresLogin If this is true means page doesn't require any
     * use-case but it does require user is logged in
     * @param useCases useCases that the page uses. Whit those use-cases, the
     * control access will evaluate if the use has or not access to the page. At
     * least one use-case access is required to be able to enter to the page,
     * otherwise forbidden page is redirected
     */
    private HallocasaViewEnum(String viewName,
            String url,
            Class<?> viewClass, boolean requiresLogin,
            UseCaseEnum... useCases) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.useCases = useCases;
        this.url = url;
        this.requiresLogin = requiresLogin;
    }

    /* Getters & Setters */
    /**
     * @return the viewName
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * @return the viewClass
     */
    public Class<?> getViewClass() {
        return viewClass;
    }

    /**
     * @return the useCases
     */
    public UseCaseEnum[] getUseCases() {
        return useCases;
    }

    /**
     * @param serverBaseUrl
     * @param appServerContext
     * @return
     */
    public String getAbsolutePath(String serverBaseUrl, String appServerContext) {
        return getAbsolutePath(null, serverBaseUrl, appServerContext);
    }

    /**
     * @param params
     * @param serverBaseUrl
     * @param appServerContext
     * @return
     */
    public String getAbsolutePath(Map<String, String> params,
            String serverBaseUrl, String appServerContext) {
        StringBuilder str = new StringBuilder();
        str.append(serverBaseUrl);
        str.append(appServerContext);
        str.append("/#!");
        str.append(viewName);

        if (params != null) {
            for (String key : params.keySet()) {
                str.append("/").append(key).append("/").append(params.get(key));
            }
        }
        return str.toString();
    }

    /* Static utils */
    /**
     * Identify the enumeration of a view given the view object
     *
     * @param hallocasaView
     * @return
     * @throws IllegalArgumentException when the view is not in the enumeration
     */
    public static HallocasaViewEnum identifyView(Object hallocasaView) {
        for (HallocasaViewEnum e : values()) {
            if (e.getViewClass().equals(hallocasaView.getClass())) {
                return e;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Find a view given its class
     *
     * @param clazz
     * @return
     */
    public static HallocasaViewEnum findByClass(
            Class<?> clazz) {

        for (HallocasaViewEnum s : values()) {
            if (s.viewClass.equals(clazz)) {
                return s;
            }
        }
        throw new IllegalArgumentException(clazz + " not found in the enum");
    }

    /**
     * Getter for requiresLogin
     *
     * @return the requiresLogin
     */
    public boolean isRequiresLogin() {
        return requiresLogin;
    }

    /**
     * 
     * @return 
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param view1
     * @param view2
     * @return
     */
    private static UseCaseEnum[] joinUseCasesArrayFromView(
            UseCaseEnum[]... arrays) {
        List<UseCaseEnum> useCaseEnums = new ArrayList<UseCaseEnum>();
        for (UseCaseEnum[] array : arrays) {
            for (UseCaseEnum u : array) {
                if (!useCaseEnums.contains(u)) {
                    useCaseEnums.add(u);
                }
            }
        }
        return useCaseEnums.toArray(new UseCaseEnum[0]);
    }

}
