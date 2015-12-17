/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.blog;

import com.hallocasa.dataentities.wcm.BlogArticle;
import com.hallocasa.view.navigation.HallocasaViewNames;
import com.hallocasa.model.session.WebSessionImpl;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = HallocasaViewNames.BLOG_ARTICLE)
@ViewScoped
public class BlogArticlePage extends BlogPageBase {
    
    private BlogArticle article;
    private String articleDescription;

    /* dependencies */
    @Inject
    private NavigationHandler navigationHandler;

    /**
     * Default constructor
     */
    public BlogArticlePage() {
    }

    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {
        // loads article from view paramters
        String articleIdStr = navigationHandler.getPageParams().
                get(ViewParamEnum.ARTICLE_ID.name());
        Integer articleId = null;
        try {
            articleId = new Integer(articleIdStr);
        } catch (NumberFormatException e) {
            throw new ArticleNotFoundException();
        }
        if (articleId == null) {
            throw new ArticleNotFoundException();
        }
        article = findArticle(articleId);
        if (article == null) {
            throw new ArticleNotFoundException();
        }

        // creates and articleDescription
        articleDescription = article.getPreviewText(WebSessionImpl.getCurrentInstance().getCurrentLanguage());
        articleDescription = articleDescription.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        articleDescription = articleDescription.substring(0, Math.min(150, articleDescription.length()));
        articleDescription = articleDescription.concat("...");
        articleDescription = StringEscapeUtils.unescapeHtml(articleDescription);
    }

    /**
     * Builds parameter map for this page
     *
     * @param articleId
     * @return
     */
    public static Map<ViewParamEnum, String> buildParamsMap(Integer articleId) {
        Map<ViewParamEnum, String> map = new HashMap<>();
        map.put(ViewParamEnum.ARTICLE_ID, articleId.toString());
        return map;
    }

    /**
     * Getter for article
     *
     * @return
     */
    public BlogArticle getArticle() {
        return article;
    }

    /**
     * Getter for articleDescription
     *
     * @return articleDescription
     */
    public String getArticleDescription() {
        return articleDescription;
    }

}
