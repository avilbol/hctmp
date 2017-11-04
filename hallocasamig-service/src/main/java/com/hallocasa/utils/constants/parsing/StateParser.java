package com.hallocasa.utils.constants.parsing;

import com.hallocasa.entities.EntityState;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.State;
import com.hallocasa.vo.i.ValueObject;

public class StateParser extends CustomizedParser {

	@Override
	public void initialize() {
	}

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		State voState = (State) vo;
		EntityState entState = (EntityState) ent;
		voState.setCountryId(entState.getCountry().getId());
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
	}

}
