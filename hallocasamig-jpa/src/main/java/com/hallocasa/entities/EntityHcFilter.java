package com.hallocasa.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;
import com.hallocasa.persistence.converters.ShowChoiceConverter;
import com.hallocasa.vo.hcfilter.ShowChoice;

@Entity
@Table(name = "filter")
public class EntityHcFilter implements HallocasaEntity {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@JoinColumn(name = "filter_type_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityHcFilterType filterType;

	@JoinColumn(name = "filter_nature_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityHcFilterNature filterNature;

	@Column(name = "min_value")
	private double minValue;

	@Column(name = "max_value")
	private double maxValue;

	@Column(name = "yes_text")
	private String yesText;

	@Column(name = "no_text")
	private String noText;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "use_static_filter_options")
	private Boolean useStaticFilterOptions;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "force_all_filter_options")
	private Boolean forceAllFilterOptions;

	@Column(name = "api_operations")
	private String apiOperation;

	@Convert(converter = ShowChoiceConverter.class)
	@Column(name = "show_choice")
	private ShowChoice showChoice;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "min_value_date_in_past")
	private Boolean minValueDateInPast;
	
	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "min_value_date_in_future")
	private Boolean minValueDateInFuture;
	
	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "max_value_date_in_past")
	private Boolean maxValueDateInPast;
	
	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "max_value_date_in_future")
	private Boolean maxValueDateInFuture;

	@OneToMany(mappedBy = "filter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EntityFilterShowingStep> showingStepList;

	@OneToMany(mappedBy = "filter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EntityFilterListingStep> listingStepList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityHcFilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(EntityHcFilterType filterType) {
		this.filterType = filterType;
	}

	public EntityHcFilterNature getFilterNature() {
		return filterNature;
	}

	public void setFilterNature(EntityHcFilterNature filterNature) {
		this.filterNature = filterNature;
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

	public String getYesText() {
		return yesText;
	}

	public void setYesText(String yesText) {
		this.yesText = yesText;
	}

	public String getNoText() {
		return noText;
	}

	public void setNoText(String noText) {
		this.noText = noText;
	}

	public Boolean getUseStaticFilterOptions() {
		return useStaticFilterOptions;
	}

	public void setUseStaticFilterOptions(Boolean useStaticFilterOptions) {
		this.useStaticFilterOptions = useStaticFilterOptions;
	}

	public Boolean getForceAllFilterOptions() {
		return forceAllFilterOptions;
	}

	public void setForceAllFilterOptions(Boolean forceAllFilterOptions) {
		this.forceAllFilterOptions = forceAllFilterOptions;
	}

	public String getApiOperation() {
		return apiOperation;
	}

	public void setApiOperation(String apiOperation) {
		this.apiOperation = apiOperation;
	}

	public ShowChoice getShowChoice() {
		return showChoice;
	}

	public void setShowChoice(ShowChoice showChoice) {
		this.showChoice = showChoice;
	}

	public Boolean getMinValueDateInPast() {
		return minValueDateInPast;
	}

	public void setMinValueDateInPast(Boolean minValueDateInPast) {
		this.minValueDateInPast = minValueDateInPast;
	}

	public Boolean getMinValueDateInFuture() {
		return minValueDateInFuture;
	}

	public void setMinValueDateInFuture(Boolean minValueDateInFuture) {
		this.minValueDateInFuture = minValueDateInFuture;
	}

	public Boolean getMaxValueDateInPast() {
		return maxValueDateInPast;
	}

	public void setMaxValueDateInPast(Boolean maxValueDateInPast) {
		this.maxValueDateInPast = maxValueDateInPast;
	}

	public Boolean getMaxValueDateInFuture() {
		return maxValueDateInFuture;
	}

	public void setMaxValueDateInFuture(Boolean maxValueDateInFuture) {
		this.maxValueDateInFuture = maxValueDateInFuture;
	}

	public List<EntityFilterShowingStep> getShowingStepList() {
		return showingStepList;
	}

	public void setShowingStepList(List<EntityFilterShowingStep> showingStepList) {
		this.showingStepList = showingStepList;
	}

	public List<EntityFilterListingStep> getListingStepList() {
		return listingStepList;
	}

	public void setListingStepList(List<EntityFilterListingStep> listingStepList) {
		this.listingStepList = listingStepList;
	}
}
