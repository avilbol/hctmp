package com.hallocasa.dataentities.wcm;

import com.hallocasa.commons.Language;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the blog_article database table.
 *
 */
@Entity
@Table(name = "blog_article")
@NamedQueries({
    @NamedQuery(name = "BlogArticle.findAll", query = "SELECT b FROM BlogArticle b"),
    @NamedQuery(name = BlogArticle.QUERY_NAME_FIND_BY_CATEGORY, query = "SELECT b FROM BlogArticle b WHERE b.blogCategory = ?1"),
    @NamedQuery(name = BlogArticle.QUERY_NAME_FIND_FEATURED, query = "SELECT b FROM BlogArticle b WHERE b.featured = true")
})
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String QUERY_NAME_FIND_BY_CATEGORY = "BlogArticle.findByCategory";
    public static final String QUERY_NAME_FIND_FEATURED = "BlogArticle.findByFeatured";

    // Burnt articles
    public static final int COLOMBIA_BUY_PROCESS_LIKE_PROPERTY = 1;
    public static final int COLOMBIA_BUY_PROCESS_CONTACT_AGENT = 2;
    public static final int COLOMBIA_BUY_PROCESS_BROKER_SEND_DETAILS = 3;
    public static final int COLOMBIA_BUY_PROCESS_CONTACT_APPRAISER = 4;
    public static final int COLOMBIA_BUY_PROCESS_NEGOTIATE_PRICE = 5;
    public static final int COLOMBIA_BUY_PROCESS_TRANSFER_MONEY = 6;
    public static final int COLOMBIA_BUY_PROCESS_INVEST_MORE_THAN_210 = 7;
    public static final int COLOMBIA_BUY_PROCESS_FLY_TO_COLOMBIA = 8;
    public static final int COLOMBIA_BUY_PROCESS_REPRESENTATIVE_SIGN = 9;
    public static final int COLOMBIA_BUY_PROCESS_YOU_BUY_FROM_ABROAD = 10;
    public static final int COLOMBIA_BUY_PROCESS_YOU_SIGN = 11;
    public static final int COLOMBIA_BUY_PROCESS_ENTERED_LAND_REGISTRY = 12;
    public static final int COLOMBIA_BUY_PROCESS_DOWNLOADS_INTRO = 13;
    public static final int COLOMBIA_BUY_PROCESS_SUMMARY = 15;
    public static final int COLOMBIA_BUY_PROCESS_FAQ = 16;
    public static final int COLOMBIA_BUY_PROCESS_DETAIL_INTRO = 17;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_article_id")
    private int blogArticleId;
    @Column(name = "title")
    private String title;
    @Lob
    private String body;
    @Column(name = "featured")
    private boolean featured;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_category_id")
    private BlogCategory blogCategory;
    @JoinColumn(name = "body_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation bodyTransalation;
    @JoinColumn(name = "title_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation titleTransalation;

    public BlogArticle() {
    }

    public int getBlogArticleId() {
        return this.blogArticleId;
    }

    public void setBlogArticleId(int blogArticleId) {
        this.blogArticleId = blogArticleId;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // bi-directional many-to-one association to BlogCategory
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public BlogCategory getBlogCategory() {
        return this.blogCategory;
    }

    public void setBlogCategory(BlogCategory blogCategory) {
        this.blogCategory = blogCategory;
    }

    /**
     * @return the bodyTransalation
     */
    public Translation getBodyTransalation() {
        return bodyTransalation;
    }

    /**
     * @param bodyTransalation the bodyTransalation to set
     */
    public void setBodyTransalation(Translation bodyTransalation) {
        this.bodyTransalation = bodyTransalation;
    }

    /**
     * @return the titleTransalation
     */
    public Translation getTitleTransalation() {
        return titleTransalation;
    }

    /**
     * @param titleTransalation the titleTransalation to set
     */
    public void setTitleTransalation(Translation titleTransalation) {
        this.titleTransalation = titleTransalation;
    }

    /**
     * @return the featured
     */
    public boolean isFeatured() {
        return featured;
    }

    /**
     * @param featured the featured to set
     */
    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getPreviewText(Language language) {
        if (getBodyTransalation() == null) {
            return null;
        }
        String preview = getBodyTransalation().getText(language);
        if (preview == null) {
            return null;
        }
        return preview.split("<hr class=\"read-more\"/>")[0];
    }

}
