package com.hallocasa.view.navigation;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.model.controlaccess.UseCaseEnum;
import com.hallocasa.viewmodel.managed.pages.blog.BlogArticlePage;
import com.hallocasa.viewmodel.managed.pages.blog.BlogIndexPage;
import com.hallocasa.viewmodel.managed.pages.buyprocess.BuyProcessPage;
import com.hallocasa.viewmodel.managed.pages.links.LinksPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.hallocasa.view.navigation.ViewParamEnum.*;
import com.hallocasa.viewmodel.user.profile.ProfileReadPage;

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
    //
    /**
     * Page that is shown every time user is not allowed to enter the page is
     * trying to get into
     */
    FORBIDDEN(HallocasaViewNames.FORBIDDEN, "/commons/forbidden.xhtml",
            null, false, null),
    /**
     * Page that is shown every time user has entered a non existing page
     */
    PAGE_NOT_FOUND(HallocasaViewNames.PAGE_NOT_FOUND, "/commons/page-not-found.xhtml",
            null, false, null),
    //
    // access controlled pages

    /**
     * Page to log in
     */
    LOGIN(HallocasaViewNames.LOGIN, "/pages/login/index.xhtml", null, false, null),
    /**
     * Page to registering new partner
     */
    REGISTER(HallocasaViewNames.REGISTER, "", null, false, null),
    /**
     * Default page when the user is logged
     */
    HOME(HallocasaViewNames.HOME, "/home/index.xhtml", null, true, new ViewParamEnum[]{
        ViewParamEnum.LOGIN_DIALOG}),
    /**
     * Page for user ask the password to be recovery by asking an email
     */
    PASSWORD_RECOVERY_REQUEST(HallocasaViewNames.PASSWORD_RECOVERY_REQUEST,
            "", null, false, null),
    /**
     * Page for account information
     */
    MY_PROFILE(HallocasaViewNames.USER_PROFILE_VIEW, "/user/pages/profile.xhtml",
            ProfileReadPage.class, true, null, UseCaseEnum.SEE_MY_PROFILE),
    /**
     * Page for user recover password writing a new one
     */
    PASSWORD_RECOVERY(HallocasaViewNames.PASSWORD_RECOVERY, "",
            null, false, null),
    /**
     * Page for user activation after a registering process
     */
    USER_ACTIVATION(HallocasaViewNames.USER_ACTIVATION, "/access/pages/confirm-email.xhtml",
            null, false, new ViewParamEnum[]{TOKEN, EMAIL}),
    /**
     * Page for buying process
     */
    BUYING_PROCESS(HallocasaViewNames.BUY_PROCESS, "/pages/buyprocess/index.xhtml",
            BuyProcessPage.class, false, null),
    /**
     * Blog index page
     */
    BLOG_INDEX(HallocasaViewNames.BLOG_INDEX, "/pages/blog/index.xhtml",
            BlogIndexPage.class, false, null),
    /**
     * Links list page
     */
    LINKS(HallocasaViewNames.LINKS, "/pages/links/index.xhtml", LinksPage.class,
            false, null),
    /**
     * Blog article detail
     */
    BLOG_ARTICLE(HallocasaViewNames.BLOG_ARTICLE, "/pages/blog/article.xhtml",
            BlogArticlePage.class, false, null);

    /* Static */
    public static final HallocasaViewEnum DEFAULT_VIEW = HOME;

    /* Instance variables */
    private final String viewName;
    private final Class<?> viewClass;
    private final UseCaseEnum[] useCases;
    private final boolean requiresLogin;
    private final String url;
    private ViewParamEnum[] supportedParams;

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
            ViewParamEnum[] supportedParam,
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
     * Getter for supportedParams
     *
     * @return supportedParams
     */
    public ViewParamEnum[] getSupportedParams() {
        return supportedParams;
    }

    /**
     * @return
     */
    public String getAbsolutePath() {
        return getAbsolutePath(null);
    }

    /**
     * @param params
     * @return
     */
    public String getAbsolutePath(Map<String, String> params) {
        StringBuilder str = new StringBuilder();
        str.append(SystemConstants.SERVER_URL);
        str.append(SystemConstants.APP_CONTEXT);
        str.append(url);

        if (params != null) {
            str.append("?");
            for (String key : params.keySet()) {
                str.append("&").append(key).append("=").append(params.get(key));
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
     * Find a view given its URL
     *
     * @param url
     * @return
     */
    public static HallocasaViewEnum findByUrl(String url) {
        for (HallocasaViewEnum s : values()) {
            if (s.url.equals(url)) {
                return s;
            }
        }
        throw new IllegalArgumentException(url + " not found in the enum");
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
        List<UseCaseEnum> useCaseEnums = new ArrayList<>();
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
