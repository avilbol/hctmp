package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Submission of filters to apply in a search
 * @author avillamil
 */
public class HcFilterSubmission implements Serializable, ValueObject{

	private static final long serialVersionUID = 3624461578129545243L;
	
	private HcFilter filter;

	public HcFilter getFilter() {
		return filter;
	}

	public void setFilter(HcFilter filter) {
		this.filter = filter;
	}
}
