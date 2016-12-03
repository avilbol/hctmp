package com.hallocasa.vo.resultrequest;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

public class ResultRequest implements ValueObject, Serializable {

	private static final long serialVersionUID = 564562683817848566L;

	private Integer pageFrom;
	
	private Integer pageTo;
	
	private Boolean orderByMostRecent;
	
	private Boolean orderByLessRecent;
	
	private Boolean asc;
	
	private Boolean loadCount;
	
	List<String> orderBy;

	public Integer getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(Integer pageFrom) {
		this.pageFrom = pageFrom;
	}

	public Integer getPageTo() {
		return pageTo;
	}

	public void setPageTo(Integer pageTo) {
		this.pageTo = pageTo;
	}

	public Boolean getOrderByMostRecent() {
		return orderByMostRecent;
	}

	public void setOrderByMostRecent(Boolean orderByMostRecent) {
		this.orderByMostRecent = orderByMostRecent;
	}

	public Boolean getOrderByLessRecent() {
		return orderByLessRecent;
	}

	public void setOrderByLessRecent(Boolean orderByLessRecent) {
		this.orderByLessRecent = orderByLessRecent;
	}

	public Boolean getAsc() {
		return asc;
	}

	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

	public Boolean getLoadCount() {
		return loadCount;
	}

	public void setLoadCount(Boolean loadCount) {
		this.loadCount = loadCount;
	}

	public List<String> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(List<String> orderBy) {
		this.orderBy = orderBy;
	}
}
