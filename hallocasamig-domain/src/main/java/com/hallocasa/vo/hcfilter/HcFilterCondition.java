package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class HcFilterCondition  implements Serializable, ValueObject{
	
	private static final long serialVersionUID = -4729721857801582824L;

	private Integer filterId;

	public Integer getFilterId() {
		return filterId;
	}

	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}
}
