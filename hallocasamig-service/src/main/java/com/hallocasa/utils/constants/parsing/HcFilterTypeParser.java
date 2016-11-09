package com.hallocasa.utils.constants.parsing;

import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.DROPDOWN;
import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.RANGE;
import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.YESNO;

import com.hallocasa.entities.EntityHcFilterType;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.hcfilter.BooleanFilterType;
import com.hallocasa.vo.hcfilter.DropdownFilterType;
import com.hallocasa.vo.hcfilter.HcFilterTypeNature;
import com.hallocasa.vo.hcfilter.RangeFilterType;
import com.hallocasa.vo.i.ValueObject;

@SuppressWarnings("rawtypes")
public class HcFilterTypeParser extends CustomizedParser {

	@Override
	public void initialize() {
		addIgnoreVOField("filterType");
	}

	@Override
	public void setupParsingClass(HallocasaEntity entity, Class clazz) {
		EntityHcFilterType hcFilterType = (EntityHcFilterType) entity;
		HcFilterTypeNature filterTypeNature = hcFilterType.getFilterTypeNature();
		boolean dropdownBehav = filterTypeNature.equals(DROPDOWN);
		boolean rangeBehav = filterTypeNature.equals(RANGE);
		boolean yesnoBehav = filterTypeNature.equals(YESNO);
		this.setSpecificDestClass(clazz);
		if (dropdownBehav) {
			this.setSpecificDestClass(DropdownFilterType.class);
		}
		if (rangeBehav) {
			this.setSpecificDestClass(RangeFilterType.class);
		}
		if (yesnoBehav) {
			this.setSpecificDestClass(BooleanFilterType.class);
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
