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
@Table(name = "filter_listing_step")
public class EntityFilterListingStep implements HallocasaEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "sequence_before")
	@Convert(converter=FilterOperationStepConverter.class)
	private FilterOperationStep sequenceBefore;
	
	@JoinColumn(name = "filter_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityHcFilter filter;
	
	@JoinColumn(name = "sequence_filter_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityHcFilter sequenceFilter;
	
	@Column(name = "sequence_after")
	@Convert(converter=FilterOperationStepConverter.class)
	private FilterOperationStep sequenceAfter;


	public FilterOperationStep getSequenceBefore() {
		return sequenceBefore;
	}

	public void setSequenceBefore(FilterOperationStep sequenceBefore) {
		this.sequenceBefore = sequenceBefore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntityHcFilter getSequenceFilter() {
		return sequenceFilter;
	}

	public void setSequenceFilter(EntityHcFilter sequenceFilter) {
		this.sequenceFilter = sequenceFilter;
	}
	
	public EntityHcFilter getFilter() {
		return filter;
	}

	public void setFilter(EntityHcFilter filter) {
		this.filter = filter;
	}

	public FilterOperationStep getSequenceAfter() {
		return sequenceAfter;
	}

	public void setSequenceAfter(FilterOperationStep sequenceAfter) {
		this.sequenceAfter = sequenceAfter;
	}
}
