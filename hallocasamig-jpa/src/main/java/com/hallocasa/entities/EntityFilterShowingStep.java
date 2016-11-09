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
import com.hallocasa.persistence.converters.FilterOperationStepConverter;
import com.hallocasa.vo.hcfilter.FilterOperationStep;

@Entity
@Table(name = "filter_showing_step")
public class EntityFilterShowingStep implements HallocasaEntity{

	@Id
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="filter_id")
	private EntityHcFilter  filter;
	
	@Column(name = "sequence_before")
	@Convert(converter=FilterOperationStepConverter.class)
	private FilterOperationStep sequenceBefore;
	
	@JoinColumn(name = "filter_condition_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityHcFilterCondition filterCondition;
	
	@Column(name = "sequence_after")
	@Convert(converter=FilterOperationStepConverter.class)
	private FilterOperationStep sequenceAfter;

	public FilterOperationStep getSequenceBefore() {
		return sequenceBefore;
	}

	public void setSequenceBefore(FilterOperationStep sequenceBefore) {
		this.sequenceBefore = sequenceBefore;
	}

	public EntityHcFilterCondition getFilterCondition() {
		return filterCondition;
	}

	public void setFilterCondition(EntityHcFilterCondition filterCondition) {
		this.filterCondition = filterCondition;
	}

	public FilterOperationStep getSequenceAfter() {
		return sequenceAfter;
	}

	public void setSequenceAfter(FilterOperationStep sequenceAfter) {
		this.sequenceAfter = sequenceAfter;
	}

	public EntityHcFilter getFilter() {
		return filter;
	}

	public void setFilter(EntityHcFilter filter) {
		this.filter = filter;
	}
}
