package com.hallocasa.utils.resolvers;

import com.hallocasa.services.hcfilters.listers.CityLister;
import com.hallocasa.services.hcfilters.listers.HcLister;
import com.hallocasa.services.hcfilters.listers.NeighborhoodLister;
import com.hallocasa.services.hcfilters.listers.PropertyTypeLister;
import com.hallocasa.services.hcfilters.listers.StateLister;
import com.hallocasa.vo.hcfilter.HcListerOption;

public enum HcListerOptionRes {
	STATE(new StateLister()),
	CITY(new CityLister()),
	NEIGHBORHOOD(new NeighborhoodLister()),
	PROPERTY_TYPE(new PropertyTypeLister());
	
	private HcLister lister;
	
	private HcListerOptionRes(HcLister lister){
		this.lister = lister;
	}
	
	public static HcLister getLister(HcListerOption listerOption) {
		return valueOf(listerOption.name()).lister;
	}
}
