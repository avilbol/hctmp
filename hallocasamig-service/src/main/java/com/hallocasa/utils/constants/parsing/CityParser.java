package com.hallocasa.utils.constants.parsing;

import com.hallocasa.entities.EntityCity;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.City;
import com.hallocasa.vo.i.ValueObject;

public class CityParser extends CustomizedParser {

	@Override
	public void initialize() {
	}

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		City voCity = (City) vo;
		EntityCity entCity = (EntityCity) ent;
		voCity.setCountryId(entCity.getState().getCountry().getId());
		voCity.setStateId(entCity.getState().getId());
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
	}
}
