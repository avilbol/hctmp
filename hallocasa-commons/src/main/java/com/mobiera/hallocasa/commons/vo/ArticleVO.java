package com.mobiera.hallocasa.commons.vo;

import java.util.List;

import com.mobiera.hallocasa.commons.vo.enums.ArticleCategory;
import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Article VO
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ArticleVO implements ValueObject {

	/* static fields */
	private static final long serialVersionUID = -8339924993523178990L;
	public static final String articleTranslations_ = "articleTranslations";

	/* instance variables */

	private Long id;
	private String title;
	private ArticleCategory articleCategory;
	private List<ArticleTranslationVO> articleTranslations;

	/* constructors */

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter for articleCategoryId
	 * 
	 * @return the articleCategoryId
	 */
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	/**
	 * Setter for articleCategoryId
	 * 
	 * @param articleCategory the articleCategoryId to set
	 */
	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}

	/**
	 * Getter for articleTranslations
	 * 
	 * @return the articleTranslations
	 */
	public List<ArticleTranslationVO> getArticleTranslations() {
		return articleTranslations;
	}

	/**
	 * Setter for articleTranslations
	 * 
	 * @param articleTranslations the articleTranslations to set
	 */
	public void setArticleTranslations(
		List<ArticleTranslationVO> articleTranslations) {
		this.articleTranslations = articleTranslations;
	}

}
