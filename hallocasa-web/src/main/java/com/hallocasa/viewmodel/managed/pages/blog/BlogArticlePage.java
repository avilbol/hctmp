/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.blog;

import com.hallocasa.dataentities.BlogArticle;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "blogArticlePage")
@ViewScoped
public class BlogArticlePage extends BlogPageBase {

    private static final String QUERY_PARAM_ARTICLE_ID = "article";
    private BlogArticle article;
    private String articleDescription;


    /* dependencies */
    private final AbstractViewFacade abstractViewFacade;

    /**
     * Default constructor
     */
    public BlogArticlePage() {
        abstractViewFacade = AbstractViewFacade.getCurrentInstance();
    }

    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {
        // loads article from view paramters
        String articleIdStr = abstractViewFacade.getViewParams().
                get(QUERY_PARAM_ARTICLE_ID);
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
        articleDescription = article.getPreviewText(WebSession.getCurrentInstance().getCurrentLanguage());
        articleDescription = articleDescription.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        articleDescription = articleDescription.substring(0, Math.min(150, articleDescription.length()));
        articleDescription = articleDescription.concat("...");
        articleDescription = StringEscapeUtils.unescapeHtml(articleDescription);
    }

    /**
     * Builds params map for this page
     *
     * @param articleId
     * @return
     */
    public static Map<String, String> buildParamsMap(Integer articleId) {
        Map<String, String> map = new HashMap<>();
        map.put(QUERY_PARAM_ARTICLE_ID, articleId.toString());
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
