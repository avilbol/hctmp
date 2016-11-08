package com.hallocasa.vo.hcfilter;

import java.util.List;

/**
 * Schema to choice when show or hide the filters
 * @author avillamil
 */
public class HcFilterShowingSchema {

	private List<FilterApplySequenceElement> conditionList;
	private ShowChoice choice;
	public List<FilterApplySequenceElement> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<FilterApplySequenceElement> conditionList) {
		this.conditionList = conditionList;
	}
	public ShowChoice getChoice() {
		return choice;
	}
	public void setChoice(ShowChoice choice) {
		this.choice = choice;
	}
}
