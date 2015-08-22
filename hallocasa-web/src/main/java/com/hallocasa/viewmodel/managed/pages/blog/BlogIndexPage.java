/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.blog;

import com.hallocasa.dataentities.BlogArticle;
import com.hallocasa.services.interfaces.BlogArticleServicesLocal;
import com.hallocasa.commons.conversion.ConversionUtils;
import com.hallocasa.viewmodel.exceptions.ForbiddenException;
import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "blogIndexPage")
@ViewScoped
public class BlogIndexPage extends BlogPageBase {

    public static final String QUERY_PARAM_TYPE = "type";
    public static final String QUERY_PARAM_CATEGORY = "cat";
    public static final String TYPE_CATEGORY_LAYOUT = "ctlay";
    public static final String TYPE_FEATURED = "feat";
    private List<BlogArticle> articles;
    private BlogArticle firstArticle;
    private boolean loadedArticles = false;

    /* dependencies */
    @EJB
    private BlogArticleServicesLocal blogArticleServices;
    private final AbstractViewFacade viewFacade;

    /**
     * Default constructor
     */
    public BlogIndexPage() {
        this.viewFacade = AbstractViewFacade.getCurrentInstance();
    }

    /**
     * Initialize managed bean
     */
    @PostConstruct
    public void initialize() {
        loadArticles();
    }

    /**
     * @return the articles
     */
    public List<BlogArticle> getArticles() {
        return articles;
    }

    /**
     * Carga la lista de artículos
     */
    private void loadArticles() {
        if (!loadedArticles) {

            String type = viewFacade.getViewParams().get(QUERY_PARAM_TYPE);

            // Articulos principales
            if (type != null ? type.equals(TYPE_CATEGORY_LAYOUT) : false) {
                Integer catId = ConversionUtils.silentIntToStr(
                        viewFacade.getViewParams().get(QUERY_PARAM_CATEGORY), null);
                if (catId == null) {
                    throw new ForbiddenException();
                }
                articles = blogArticleServices.loadArticlesFromCategory(catId);

            } // Articulos de una categoria
            else {
                articles = blogArticleServices.loadFeaturedArticles();
            }
            if (!getArticles().isEmpty()) {
                firstArticle = getArticles().get(0);
            }
            if (articles.size() > 1) {
                articles = articles.subList(1, articles.size());
            } else {
                articles = new ArrayList<>();
            }
            loadedArticles = true;
        }
    }

    /**
     * @return the firstArticle
     */
    public BlogArticle getFirstArticle() {
        if (firstArticle == null) {
            loadArticles();
        }
        return firstArticle;
    }

    /**
     * @param type Tipo de presentación. puede ser cualquiera de los valores de
     * TYPE_xxxx
     * @param idCategory Id de la categoria en caso de que fuera de tipo
     * CATEGORY_LAYOUT, de lo contrario debe ser null
     * @return el mapa de parámetros construido
     */
    public static Map<String, String> buildParamsMap(String type, Integer idCategory) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(QUERY_PARAM_TYPE, type);
        if (idCategory != null) {
            hashMap.put(QUERY_PARAM_CATEGORY, idCategory.toString());
        }
        return hashMap;
    }
}
