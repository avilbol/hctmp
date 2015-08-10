package com.hallocasa.business.dataentities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the blog_category database table.
 * 
 */
@Entity
@Table(name = "blog_category")
@NamedQuery(name = "BlogCategory.findAll", query = "SELECT b FROM BlogCategory b")
public class BlogCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blog_category_id")
	private int blogCategoryId;
	private String description;

	@OneToMany(mappedBy = "blogCategory")
	private List<BlogArticle> blogArticles;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_blog_category_id")
	private BlogCategory blogCategory;

	@OneToMany(mappedBy = "blogCategory")
	private List<BlogCategory> blogCategories;

	public BlogCategory() {
	}

	public int getBlogCategoryId() {
		return this.blogCategoryId;
	}

	public void setBlogCategoryId(int blogCategoryId) {
		this.blogCategoryId = blogCategoryId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BlogArticle> getBlogArticles() {
		return this.blogArticles;
	}

	public void setBlogArticles(List<BlogArticle> blogArticles) {
		this.blogArticles = blogArticles;
	}

	public BlogArticle addBlogArticle(BlogArticle blogArticle) {
		getBlogArticles().add(blogArticle);
		blogArticle.setBlogCategory(this);

		return blogArticle;
	}

	public BlogArticle removeBlogArticle(BlogArticle blogArticle) {
		getBlogArticles().remove(blogArticle);
		blogArticle.setBlogCategory(null);

		return blogArticle;
	}

	public BlogCategory getBlogCategory() {
		return this.blogCategory;
	}

	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}

	public List<BlogCategory> getBlogCategories() {
		return this.blogCategories;
	}

	public void setBlogCategories(List<BlogCategory> blogCategories) {
		this.blogCategories = blogCategories;
	}

	public BlogCategory addBlogCategory(BlogCategory blogCategory) {
		getBlogCategories().add(blogCategory);
		blogCategory.setBlogCategory(this);

		return blogCategory;
	}

	public BlogCategory removeBlogCategory(BlogCategory blogCategory) {
		getBlogCategories().remove(blogCategory);
		blogCategory.setBlogCategory(null);

		return blogCategory;
	}

}