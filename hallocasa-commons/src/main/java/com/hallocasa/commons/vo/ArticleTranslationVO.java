package com.hallocasa.commons.vo;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for ArticleTranslation
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ArticleTranslationVO implements ValueObject {

	/* static fields */
	private static final long serialVersionUID = 1L;

	/* instance variables */
	private String body;
	private String title;
	private Long articleId;
	private Language language;

	/* constructors */

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for body
	 * 
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Setter for body
	 * 
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
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
	 * Getter for articleId
	 * 
	 * @return the articleId
	 */
	public Long getArticleId() {
		return articleId;
	}

	/**
	 * Setter for articleId
	 * 
	 * @param articleId the articleId to set
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * Getter for language
	 * 
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Setter for language
	 * 
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

}
