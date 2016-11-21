package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

/**
 * Hallocasa request, value object to obtain data
 * @author Alexander Villamil 
 * *
 */
public class PropertyFilterRequest implements Serializable {

	private static final long serialVersionUID = -6665149200769287476L;

	private List<PropertyFilterSubmission> filterList;
	
	private Integer pageFrom;
	
	private Integer pageTo;

	public List<PropertyFilterSubmission> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<PropertyFilterSubmission> filterList) {
		this.filterList = filterList;
	}

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
}
