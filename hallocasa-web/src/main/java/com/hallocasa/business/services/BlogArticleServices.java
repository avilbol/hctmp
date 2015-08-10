package com.hallocasa.business.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.hallocasa.business.dataentities.BlogArticle;
import com.hallocasa.business.services.interfaces.BlogArticleServicesLocal;
import java.util.List;
import javax.persistence.Query;

/**
 * Blog Article services implementation
 *
 * @author David Mantilla
 */
@Stateless
public class BlogArticleServices implements BlogArticleServicesLocal {

    @PersistenceContext(unitName = "RealStateDatabasePU")
    private EntityManager em;

    /**
     * Search an article by its id
     *
     * @param id
     * @return the found blogArticle, null if no article is found with that id
     */
    @Override
    public BlogArticle findBlogArticle(Integer id) {
        return em.find(BlogArticle.class, id);
    }

    /**
     * Carga la lista de articulos
     *
     * @return
     */
    @Override
    public List<BlogArticle> loadFeaturedArticles() {
        Query q = em.createNamedQuery(BlogArticle.QUERY_NAME_FIND_FEATURED);
        List<BlogArticle> list = q.getResultList();
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
        BlogArticle blogArticle = em.find(BlogArticle.class, articlecategoryId);
        Query q = em.createNamedQuery(BlogArticle.QUERY_NAME_FIND_BY_CATEGORY);
        q.setParameter(1, blogArticle);
        List<BlogArticle> list = q.getResultList();
        return list;
    }

    @Override
    public void updateArticle(BlogArticle editedArticle) {
        BlogArticle blogArticle = em.find(BlogArticle.class, editedArticle.getBlogArticleId());
        blogArticle.getTitleTransalation().setTextDe(editedArticle.getTitleTransalation().getTextDe());
        blogArticle.getTitleTransalation().setTextEn(editedArticle.getTitleTransalation().getTextEn());
        blogArticle.getTitleTransalation().setTextEs(editedArticle.getTitleTransalation().getTextEs());

        blogArticle.getBodyTransalation().setTextDe(editedArticle.getBodyTransalation().getTextDe());
        blogArticle.getBodyTransalation().setTextEn(editedArticle.getBodyTransalation().getTextEn());
        blogArticle.getBodyTransalation().setTextEs(editedArticle.getBodyTransalation().getTextEs());

        em.merge(editedArticle);
    }
}
