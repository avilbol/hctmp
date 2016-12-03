package com.hallocasa.utils.resolvers;

import com.hallocasa.services.hcfilters.filterworkers.BooleanFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.CountryFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.CurrencyFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.DropdownFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.FilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.PropertyLocationFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.PropertyProposalFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.PropertyTypeFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.RangeFilterWorker;
import com.hallocasa.services.hcfilters.filterworkers.RoiFilterWorker;
import com.hallocasa.vo.hcfilter.FilterWorkerOption;

public enum FilterWorkerOptionRes {
	PROPERTY_LOCATION_KEY(new PropertyLocationFilterWorker()),
	PROPERTY_TYPE_KEY(new PropertyTypeFilterWorker()),
	COUNTRY_KEY(new CountryFilterWorker()),
	PROPERTY_PROPOSAL_KEY(new PropertyProposalFilterWorker()),
	RANGE(new RangeFilterWorker()),
	DROPDOWN(new DropdownFilterWorker()),
	BOOLEAN(new BooleanFilterWorker()),
	CURRENCY_RANGE(new CurrencyFilterWorker()),
	ROI(new RoiFilterWorker());
	
	private FilterWorker filterWorker;

	private FilterWorkerOptionRes(FilterWorker filterWorker) {
		this.filterWorker = filterWorker;
	}
	
	public static FilterWorker getFilterWorker(FilterWorkerOption filterWorkerOption) {
		return valueOf(filterWorkerOption.name()).filterWorker;
	}
}
