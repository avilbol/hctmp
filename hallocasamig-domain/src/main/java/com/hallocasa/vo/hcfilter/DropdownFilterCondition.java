package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.options.DropdownOption;

/**
 * Condition applied to filter, in order to validate some of his properties
 * or get the source list according to their values
 * @author avillamil
 */
public class DropdownFilterCondition extends HcFilterCondition implements ValueObject, Serializable {

	private static final long serialVersionUID = -5446697320556073566L;
	private boolean searchSpecific;
	private boolean containsAtLeastOne;
	private boolean containsAll;
	private boolean containsNumber;
	private Integer number;
	
	private Integer selectedNumberAny;
	
	public boolean isContainsAtLeastOne() {
		return containsAtLeastOne;
	}
	public void setContainsAtLeastOne(boolean containsAtLeastOne) {
		this.containsAtLeastOne = containsAtLeastOne;
	}
	public boolean isContainsAll() {
		return containsAll;
	}
	public void setContainsAll(boolean containsAll) {
		this.containsAll = containsAll;
	}
	public boolean isContainsNumber() {
		return containsNumber;
	}
	public void setContainsNumber(boolean containsNumber) {
		this.containsNumber = containsNumber;
	}
	public boolean isSearchSpecific() {
		return searchSpecific;
	}
	public void setSearchSpecific(boolean searchSpecific) {
		this.searchSpecific = searchSpecific;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getSelectedNumberAny() {
		return selectedNumberAny;
	}
	public void setSelectedNumberAny(Integer selectedNumberAny) {
		this.selectedNumberAny = selectedNumberAny;
	}
}
