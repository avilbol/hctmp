package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;
import com.hallocasa.persistence.converters.RangeOperandConverter;
import com.hallocasa.vo.hcfilter.HcFilter;
import com.hallocasa.vo.hcfilter.RangeOperand;

@Entity
@Table(name="filter_condition")
public class EntityHcFilterCondition implements HallocasaEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="filter_id")
	private EntityHcFilter filter;
	
	@Column(name = "search_specific")
	@Convert(converter=HcBooleanConverter.class)
	private Boolean searchSpecific;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "contains_at_least_one")
	private Boolean containsAtLeastOne;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "contains_all")
	private Boolean containsAll;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "contains_number")
	private Boolean containsNumber;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "number")
	private Boolean number;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "selected_number_any")
	private Boolean selectedNumberAny;
	
	@Convert(converter=RangeOperandConverter.class)
	@Column(name = "min_operand")
	private RangeOperand minOperand;
	
	@Convert(converter=RangeOperandConverter.class)
	@Column(name = "max_operand")
	private RangeOperand maxOperand;
	
	@Column(name = "min_value")
	private double minValue;
	
	@Column(name = "max_value")
	private double maxValue;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "apply_min_constraint")
	private Boolean applyMinConstraint;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "apply_max_constraint")
	private Boolean applyMaxConstraint;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "apply")
	private Boolean apply;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntityHcFilter getFilter() {
		return filter;
	}

	public void setFilter(EntityHcFilter filter) {
		this.filter = filter;
	}

	public Boolean getSearchSpecific() {
		return searchSpecific;
	}

	public Boolean getContainsAtLeastOne() {
		return containsAtLeastOne;
	}

	public Boolean getContainsAll() {
		return containsAll;
	}

	public Boolean getContainsNumber() {
		return containsNumber;
	}

	public Boolean getNumber() {
		return number;
	}

	public Boolean getSelectedNumberAny() {
		return selectedNumberAny;
	}

	public Boolean getApplyMinConstraint() {
		return applyMinConstraint;
	}

	public Boolean getApplyMaxConstraint() {
		return applyMaxConstraint;
	}

	public Boolean getApply() {
		return apply;
	}

	public Boolean isSearchSpecific() {
		return searchSpecific;
	}

	public void setSearchSpecific(Boolean searchSpecific) {
		this.searchSpecific = searchSpecific;
	}

	public Boolean isContainsAtLeastOne() {
		return containsAtLeastOne;
	}

	public void setContainsAtLeastOne(Boolean containsAtLeastOne) {
		this.containsAtLeastOne = containsAtLeastOne;
	}

	public Boolean isContainsAll() {
		return containsAll;
	}

	public void setContainsAll(Boolean containsAll) {
		this.containsAll = containsAll;
	}

	public Boolean isContainsNumber() {
		return containsNumber;
	}

	public void setContainsNumber(Boolean containsNumber) {
		this.containsNumber = containsNumber;
	}

	public Boolean isNumber() {
		return number;
	}

	public void setNumber(Boolean number) {
		this.number = number;
	}

	public Boolean isSelectedNumberAny() {
		return selectedNumberAny;
	}

	public void setSelectedNumberAny(Boolean selectedNumberAny) {
		this.selectedNumberAny = selectedNumberAny;
	}

	public RangeOperand getMinOperand() {
		return minOperand;
	}

	public void setMinOperand(RangeOperand minOperand) {
		this.minOperand = minOperand;
	}

	public RangeOperand getMaxOperand() {
		return maxOperand;
	}

	public void setMaxOperand(RangeOperand maxOperand) {
		this.maxOperand = maxOperand;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public Boolean isApplyMinConstraint() {
		return applyMinConstraint;
	}

	public void setApplyMinConstraint(Boolean applyMinConstraint) {
		this.applyMinConstraint = applyMinConstraint;
	}

	public Boolean isApplyMaxConstraint() {
		return applyMaxConstraint;
	}

	public void setApplyMaxConstraint(Boolean applyMaxConstraint) {
		this.applyMaxConstraint = applyMaxConstraint;
	}

	public Boolean isApply() {
		return apply;
	}

	public void setApply(Boolean apply) {
		this.apply = apply;
	}
}
