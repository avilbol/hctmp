/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.blog;

import com.hallocasa.business.dataentities.BlogArticle;
import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.model.session.WebSession;
import java.util.HashMap;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import com.hallocasa.view.components.base.BaseComponent;

/**
 *
 * @author David Mantilla
 */
@FacesComponent("ArticleComponent")
public class ArticleComponent extends BaseComponent {

    private enum AttributesEnum {

        blogArticle, showTitle;
    }

    @Override
    protected void initialize() {
        //
    }

    @Override
    protected void saveComponent(FacesContext facesContext,
            HashMap<String, Object> map) {
        //
    }

    @Override
    protected void restoreComponent(FacesContext facesContext,
            HashMap<String, Object> map) {
        //
    }

    /**
     * Return the content of the article
     *
     * @return
     */
    public String getArticleContent() {
        BlogArticle blogArticle
                = (BlogArticle) getAttributes().get(AttributesEnum.blogArticle.name());
        if (blogArticle == null) {
            return null;
        }
        String currentLanguageContent
                = blogArticle.getBodyTransalation().getText(WebSession.getCurrentInstance().getCurrentLanguage());
        return replaceArticleParams(currentLanguageContent);
    }

    /**
     * Replace params in the article
     *
     * @param currentLanguageContent
     * @return
     */
    private String replaceArticleParams(String currentLanguageContent) {
        if (currentLanguageContent == null) {
            return null;
        }
        return currentLanguageContent.replaceAll("\\$P\\{CONTEXT_PATH\\}", 
                SystemConstants.APP_CONTEXT);
    }

}
