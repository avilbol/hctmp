package com.hallocasa.utils.constants.parsing;

import com.hallocasa.entities.EntityNeighborhood;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.Neighborhood;
import com.hallocasa.vo.i.ValueObject;

public class NeighborhoodParser extends CustomizedParser {

	@Override
	public void initialize() {
	}

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		Neighborhood voNeighborhood = (Neighborhood) vo;
		EntityNeighborhood entNeighborhood = (EntityNeighborhood) ent;
		if(entNeighborhood.getGenericUse()!= null && !entNeighborhood.getGenericUse()){
			voNeighborhood.setCountryId(entNeighborhood.getCity().getState().getCountry().getId());
			voNeighborhood.setStateId(entNeighborhood.getCity().getState().getId());
			voNeighborhood.setCityId(entNeighborhood.getCity().getId());
		}
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
	}
}
