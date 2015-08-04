package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.BlogArticle;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface for blog services
 *
 * @author David Mantilla
 */
@Local
public interface BlogArticleServicesLocal {

    /**
     * Finds an article by is id
     *
     * @param id
     * @return
     */
    public BlogArticle findBlogArticle(Integer id);

    /**
     * Carga la lista de articulos de una categoria
     *
     * @param blogCategoryId If of the category for filtering by
     * @return
     */
    List<BlogArticle> loadArticlesFromCategory(int blogCategoryId);

    /**
     * Carga la lista de articulos
     *
     * @return
     */
    List<BlogArticle> loadFeaturedArticles();

    /**
     * Updates and article
     *
     * @param editedArticle
     */
    public void updateArticle(BlogArticle editedArticle);

}
