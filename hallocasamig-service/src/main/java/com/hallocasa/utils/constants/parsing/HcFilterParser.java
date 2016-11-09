package com.hallocasa.utils.constants.parsing;

import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.DROPDOWN;
import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.RANGE;
import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.YESNO;

import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.hcfilter.BooleanFilter;
import com.hallocasa.vo.hcfilter.DropdownFilter;
import com.hallocasa.vo.hcfilter.HcFilterTypeNature;
import com.hallocasa.vo.hcfilter.RangeFilter;
import com.hallocasa.vo.i.ValueObject;

@SuppressWarnings("rawtypes")
public class HcFilterParser extends CustomizedParser {

	@Override
	public void initialize() {
		addIgnoreVOField("filterType");
	}

	@Override
	public void setupParsingClass(HallocasaEntity entity, Class clazz) {
		EntityHcFilter hcFilter = (EntityHcFilter) entity;
		HcFilterTypeNature filterTypeNature = hcFilter.getFilterType().getFilterTypeNature();
		boolean dropdownBehav = filterTypeNature.equals(DROPDOWN);
		boolean rangeBehav = filterTypeNature.equals(RANGE);
		boolean yesnoBehav = filterTypeNature.equals(YESNO);
		this.setSpecificDestClass(clazz);
		if (dropdownBehav) {
			this.setSpecificDestClass(DropdownFilter.class);
		}
		if (rangeBehav) {
			this.setSpecificDestClass(RangeFilter.class);
		}
		if (yesnoBehav) {
			this.setSpecificDestClass(BooleanFilter.class);
		}
	};

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		// Nothing to do
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
		// Nothing to do 
	}
}
