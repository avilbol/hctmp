/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.blog;

import com.hallocasa.dataentities.BlogArticle;
import com.hallocasa.dataentities.Translation;
import com.hallocasa.services.interfaces.BlogArticleServicesLocal;
import com.hallocasa.view.navigation.NavigationHandler;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "blogArticleEditionPage")
@ViewScoped
public class BlogArticleEditionPage extends BlogPageBase {

    public static final String QUERY_STRING_ARTICLE_ID = "id";
    public static final String QUERY_STRING_LANGUAGE = "lang";
    private BlogArticle editedArticle;
    private String articleBody;
    private String articleTitle;
    private String editionLanguage;

    /* dependencies */
    @EJB
    private BlogArticleServicesLocal blogArticleServices;
    @Inject
    private NavigationHandler navigationHandler;
    
    
    


    /**
     * Initialize managed bean
     */
    @PostConstruct
    public void initialize() {
        loadArticle();
    }

    /**
     * Loads edited article
     */
    private void loadArticle() {
        String articleIdStr = navigationHandler.getPageParams().get(QUERY_STRING_ARTICLE_ID);
        editedArticle = blogArticleServices.findBlogArticle(Integer.valueOf(articleIdStr));
        editionLanguage = navigationHandler.getPageParams().get(QUERY_STRING_LANGUAGE);

        Translation titleTranslation = editedArticle.getTitleTransalation();
        Translation bodyTranslation = editedArticle.getBodyTransalation();

        switch (editionLanguage) {
            case "es":
                articleTitle = titleTranslation.getTextEs();
                articleBody = bodyTranslation.getTextEs();
                break;
            case "de":
                articleTitle = titleTranslation.getTextDe();
                articleBody = bodyTranslation.getTextDe();
                break;
            case "en":
                articleTitle = titleTranslation.getTextEn();
                articleBody = bodyTranslation.getTextEn();
                break;
            default:
                throw new UnsupportedOperationException();
        }

    }

    /**
     * Gets edited article
     *
     * @return
     */
    public BlogArticle getEditedArticle() {
        return editedArticle;
    }

    // Listeners
    /**
     * On save button click listener
     */
    public void processSaveButtonClick() {
        Translation titleTranslation = editedArticle.getTitleTransalation();
        Translation bodyTranslation = editedArticle.getBodyTransalation();
        
        switch (editionLanguage) {
            case "es":
                titleTranslation.setTextEs(articleTitle);
                bodyTranslation.setTextEs(articleBody);
                break;
            case "de":
                titleTranslation.setTextDe(articleTitle);
                bodyTranslation.setTextDe(articleBody);
                break;
            case "en":
                 titleTranslation.setTextEn(articleTitle);
                bodyTranslation.setTextEn(articleBody);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        blogArticleServices.updateArticle(editedArticle);
    }

    /**
     * @return the articleBody
     */
    public String getArticleBody() {
        return articleBody;
    }

    /**
     * @param articleBody the articleBody to set
     */
    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    /**
     * @return the articleTitle
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * @param articleTitle the articleTitle to set
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

}
