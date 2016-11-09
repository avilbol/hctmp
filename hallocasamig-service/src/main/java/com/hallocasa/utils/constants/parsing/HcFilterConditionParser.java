package com.hallocasa.utils.constants.parsing;

import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.DROPDOWN;
import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.RANGE;
import static com.hallocasa.vo.hcfilter.HcFilterTypeNature.YESNO;

import com.hallocasa.entities.EntityHcFilterCondition;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.hcfilter.BooleanFilterCondition;
import com.hallocasa.vo.hcfilter.DropdownFilterCondition;
import com.hallocasa.vo.hcfilter.HcFilterCondition;
import com.hallocasa.vo.hcfilter.HcFilterTypeNature;
import com.hallocasa.vo.hcfilter.RangeFilterCondition;
import com.hallocasa.vo.i.ValueObject;

@SuppressWarnings("rawtypes")
public class HcFilterConditionParser extends CustomizedParser {

	@Override
	public void initialize() {
		addIgnoreVOField("filter");
	}

	@Override
	public void setupParsingClass(HallocasaEntity entity, Class clazz) {
		EntityHcFilterCondition hcFilterCondition = (EntityHcFilterCondition) entity;
		HcFilterTypeNature filterTypeNature = hcFilterCondition.getFilter()
				.getFilterType().getFilterTypeNature();
		boolean dropdownBehav = filterTypeNature.equals(DROPDOWN);
		boolean rangeBehav = filterTypeNature.equals(RANGE);
		boolean yesnoBehav = filterTypeNature.equals(YESNO);
		this.setSpecificDestClass(clazz);
		if (dropdownBehav) {
			this.setSpecificDestClass(DropdownFilterCondition.class);
		}
		if (rangeBehav) {
			this.setSpecificDestClass(RangeFilterCondition.class);
		}
		if (yesnoBehav) {
			this.setSpecificDestClass(BooleanFilterCondition.class);
		}
	};

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		((HcFilterCondition)vo).setFilterId((
				(EntityHcFilterCondition)ent).getFilter().getId());
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
		// Nothing to do 
	}
}
