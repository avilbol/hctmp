package com.hallocasa.services;

import javax.ejb.Stateless;
import com.hallocasa.dataentities.BlogArticle;
import com.hallocasa.services.interfaces.BlogArticleServicesLocal;
import com.hallocasa.services.interfaces.PersistenceServices;
import java.util.List;
import javax.ejb.EJB;

/**
 * Blog Article services implementation
 *
 * @author David Mantilla
 */
@Stateless
public class BlogArticleServices implements BlogArticleServicesLocal {

    @EJB
    private PersistenceServices persistenceServices;

    /**
     * Search an article by its id
     *
     * @param id
     * @return the found blogArticle, null if no article is found with that id
     */
    @Override
    public BlogArticle findBlogArticle(Integer id) {
        return persistenceServices.findEntity(BlogArticle.class, id);
    }

    /**
     * Load articles list
     *
     * @return
     */
    @Override
    public List<BlogArticle> loadFeaturedArticles() {
        List<BlogArticle> list = persistenceServices.executeNamedQuery(
                BlogArticle.QUERY_NAME_FIND_FEATURED, null, BlogArticle.class);
        return list;
    }

    /**
     * Loads article list by category
     *
     * @param articlecategoryId
     * @return
     */
    @Override
    public List<BlogArticle> loadArticlesFromCategory(int articlecategoryId) {
        BlogArticle blogArticle = persistenceServices.findEntity(
                BlogArticle.class, articlecategoryId);
        return persistenceServices.executeNamedQuery(BlogArticle.QUERY_NAME_FIND_BY_CATEGORY,
                new Object[]{blogArticle}, BlogArticle.class);
    }

    @Override
    public void updateArticle(BlogArticle editedArticle) {
        BlogArticle blogArticle = persistenceServices.findEntity(BlogArticle.class, 
                editedArticle.getBlogArticleId());
        blogArticle.getTitleTransalation().setTextDe(
                editedArticle.getTitleTransalation().getTextDe());
        blogArticle.getTitleTransalation().setTextEn(
                editedArticle.getTitleTransalation().getTextEn());
        blogArticle.getTitleTransalation().setTextEs(
                editedArticle.getTitleTransalation().getTextEs());

        blogArticle.getBodyTransalation().setTextDe(
                editedArticle.getBodyTransalation().getTextDe());
        blogArticle.getBodyTransalation().setTextEn(
                editedArticle.getBodyTransalation().getTextEn());
        blogArticle.getBodyTransalation().setTextEs(
                editedArticle.getBodyTransalation().getTextEs());

        persistenceServices.mergeEntity(editedArticle);
    }
}
