/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.blog;

import com.hallocasa.dataentities.wcm.BlogArticle;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.services.interfaces.BlogArticleServicesLocal;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.viewmodel.managed.base.BaseManagedBean;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author David Mantilla
 */
public abstract class BlogPageBase extends BaseManagedBean {

    private final Map<Integer, String> seeMoreLinksMap;
    
    /* dependencies */
    @EJB
    private BlogArticleServicesLocal blogArticleServices;
    @Inject
    private NavigationHandler navigationHandler;
    
    /**
     * Default constructor
     */
    public BlogPageBase() {
        seeMoreLinksMap = new HashMap<>();
    }

    /**
     * Escucha para el clic en ir a un articulo
     *
     * @param event
     */
    public void onArticleSeeMoreClick(ActionEvent event) {
        Integer articleId = (Integer) event.getComponent().getAttributes().get("articleId");
        if (articleId == null) {
            throw new IllegalArgumentException("articleId can't be null");
        }
        navigationHandler.redirectToPage(HallocasaViewEnum.BLOG_ARTICLE,
                BlogArticlePage.buildParamsMap(articleId));
    }

    /**
     * Get see more url
     *
     * @param articleId
     * @return
     */
    public String getSeeMoreUrl(Integer articleId) {

        if (articleId == null) {
            throw new IllegalArgumentException("articleId no puede ser null");
        }
        String url = seeMoreLinksMap.get(articleId);
        if (url == null) {
            url = navigationHandler.buildAbsoluteUrl(HallocasaViewEnum.BLOG_ARTICLE,
                    BlogArticlePage.buildParamsMap(articleId));
        }
        return url;
    }
    
    /**
     * Finds article given its id
     * @param articleId
     * @return 
     */
    protected BlogArticle findArticle(int articleId){
        return blogArticleServices.findBlogArticle(articleId);
    }
    
    
}
