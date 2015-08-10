/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.blog;

import com.hallocasa.business.dataentities.BlogArticle;
import com.hallocasa.business.services.interfaces.BlogArticleServicesLocal;
import com.hallocasa.viewmodel.viewfacade.Page;
import com.hallocasa.viewmodel.managed.base.BaseManagedBean;
import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

/**
 *
 * @author David Mantilla
 */
public abstract class BlogPageBase extends BaseManagedBean {

    private final Map<Integer, String> seeMoreLinksMap;
    
    /* dependencies */
    @EJB
    private BlogArticleServicesLocal blogArticleServices;
    
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
        AbstractViewFacade.getCurrentInstance().navigate(Page.BLOG_ARTICLE,
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
            url = AbstractViewFacade.getCurrentInstance().buildAbsoluteUrl(Page.BLOG_ARTICLE,
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
