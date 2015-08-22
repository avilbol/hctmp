package com.hallocasa.viewmodel.managed.pages.buyprocess;

import com.hallocasa.dataentities.BlogArticle;

public class LinkedBlogArticle {

	private BlogArticle blogArticle;
	private LinkedBlogArticle nextBlogArticle;

	/**
	 * @param countryPage
	 */
	public LinkedBlogArticle() {

	}

	/**
	 * @return the nextBlogArticle
	 */
	public LinkedBlogArticle getNextBlogArticle() {
		return nextBlogArticle;
	}

	/**
	 * @param nextBlogArticle
	 *            the nextBlogArticle to set
	 */
	public void setNextBlogArticle(LinkedBlogArticle nextBlogArticle) {
		this.nextBlogArticle = nextBlogArticle;
	}

	/**
	 * @return the blogArticle
	 */
	public BlogArticle getBlogArticle() {
		return blogArticle;
	}

	/**
	 * @param blogArticle the blogArticle to set
	 */
	public void setBlogArticle(BlogArticle blogArticle) {
		this.blogArticle = blogArticle;
	}

}