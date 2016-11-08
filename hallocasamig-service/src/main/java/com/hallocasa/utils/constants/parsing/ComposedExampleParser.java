package com.hallocasa.utils.constants.parsing;

import java.util.Arrays;

import com.hallocasa.entities.EntityComposedExample;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.ComposedExample;
import com.hallocasa.vo.i.ValueObject;

public class ComposedExampleParser extends CustomizedParser {

	@Override
	public void initialize() {
		addIgnoreVOField("ocurrences");
	}

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		EntityComposedExample entityComposedExample = (EntityComposedExample) ent;
		ComposedExample composedExample = (ComposedExample) vo;
		composedExample.setOcurrences(Arrays.asList(entityComposedExample.getOcurrences()));
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
		EntityComposedExample entityComposedExample = (EntityComposedExample) ent;
		ComposedExample composedExample = (ComposedExample) vo;
		entityComposedExample.setOcurrences("" + composedExample.getOcurrences().size());
	}
}
