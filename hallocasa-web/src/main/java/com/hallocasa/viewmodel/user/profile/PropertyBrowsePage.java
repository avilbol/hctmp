package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.properties.constants.PropertyFieldIdentifier;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.filters.converters.CurrencyFilterConverter;
import com.hallocasa.filters.converters.PropertyFieldFilter;
import com.hallocasa.filters.converters.PropertyFilter;
import com.hallocasa.model.application.CurrencyGlobalApplication;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.PropertyFilteringServices;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;

@ManagedBean
@ViewScoped
public class PropertyBrowsePage implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6006649582382274792L;

	@EJB
	CountryServices countryServices;

	@EJB
	private PropertyServices propertyServices;

	@EJB
	private PropertyFilteringServices propertyFilteringServices;

	@Inject
	private WebSession webSession;

	@Inject
	private CurrencyGlobalApplication currencyGlobalApplication;

	private HallocasaApplicationImpl halloCasaApplication = HallocasaApplicationImpl.getInstance();;

	/**
	 * States list
	 */
	private List<StateVO> states;

	/**
	 * States list
	 */
	private List<CityVO> cities;

	/**
	 * States list
	 */
	private List<StateVO> selectedStates;

	/**
	 * States list
	 */
	private List<CityVO> selectedCities;

	/**
	 * Language list
	 */
	private List<Language> selectedLanguages;

	/**
	 * Property type list
	 */
	private List<PropertyTypeVO> propertyTypes;

	/**
	 * Selected property type list
	 */
	private List<PropertyTypeVO> selectedPropertyTypes;

	/**
	 * Property type filter state
	 */
	private Boolean propertyTypeFilterState;

	/**
	 * Map to search filter state (activate, unknown or deactivated)
	 */
	private Map<Integer, Boolean> activateFilterStateMap;

	/**
	 * Navigation handler
	 */
	@Inject
	private NavigationHandler navigationHandler;

	/**
	 * Language list
	 */
	private List<Language> languageList;

	/**
	 * Property VO list
	 */
	private List<PropertyVO> propertyList;

	/**
	 * Filter for properties
	 */
	private PropertyFilter propertyFilterScheme;

	/**
	 * Default constructor
	 */
	public PropertyBrowsePage() {
	}

	/**
	 * Initialize the bean
	 */
	@PostConstruct
	public void initialize() {
		states = countryServices.getStates(1l);
		cities = new ArrayList<CityVO>();
		propertyFilterScheme = propertyFilteringServices.getFilterScheme();
		propertyList = propertyServices.find(processFilter(propertyFilterScheme));
		languageList = halloCasaApplication.getLanguages();
		propertyTypes = halloCasaApplication.getPropertyTypes();
		activateFilterStateMap = new HashMap<>();
		propertyTypeFilterState = false;
	}

	/**
	 * Process country selection event
	 */
	public void processStateSelect() {
		if (selectedStates.isEmpty() || selectedCities == null)
			selectedCities = new ArrayList<CityVO>();
		else {
			cities = new ArrayList<CityVO>();
			for (StateVO state : selectedStates)
				cities.addAll(countryServices.getCities(state.getId()));
			List<CityVO> cityListToDelete = new ArrayList<CityVO>();
			for (CityVO cityA : selectedCities) {
				boolean foundCityvalue = false;
				for (CityVO cityT : cities) {
					if (cityT.getId().equals(cityA.getId())) {
						foundCityvalue = true;
						break;
					}
				}
				if (!foundCityvalue)
					cityListToDelete.add(cityA);
			}
			selectedCities.removeAll(cityListToDelete);
		}
	}

	public void activateFilter(String propertyField) {
		PropertyFieldIdentifier identifier = PropertyFieldIdentifier.valueOf(propertyField);
		activateFilterStateMap.put(identifier.getId(), true);
		reloadProperties();
	}

	public void deactivateFilter(String propertyField) {
		PropertyFieldIdentifier identifier = PropertyFieldIdentifier.valueOf(propertyField);
		activateFilterStateMap.put(identifier.getId(), false);
		reloadProperties();
	}

	public boolean isFilterActive(String propertyField) {
		PropertyFieldIdentifier identifier = PropertyFieldIdentifier.valueOf(propertyField);
		Boolean state = activateFilterStateMap.get(identifier.getId());
		return state != null && state;
	}

	/**
	 * Process click event over edit button
	 */
	public void goToViewProperty(PropertyVO propertyVO) {
		HashMap<ViewParamEnum, String> params = new HashMap<ViewParamEnum, String>();
		params.put(ViewParamEnum.PROPERTY_ID, propertyVO.getId().toString());
		navigationHandler.redirectToPage(HallocasaViewEnum.PROPERTY_DETAIL, params);
	}

	public void reloadProperties() {
		//propertyFilterScheme = propertyFilteringServices.getFilterScheme();
		PropertyFilter filter = new PropertyFilter();
		if (propertyTypeFilterState && selectedPropertyTypes != null && !selectedPropertyTypes.isEmpty()) {
			filter.setPropertyTypeList(selectedPropertyTypes);
		}
		filter.setPropertyFieldFilters(new ArrayList<PropertyFieldFilter>());
		if (isFilterActive("LANGUAGES") && selectedLanguages != null && !selectedLanguages.isEmpty()) {
			PropertyFieldFilter selectedFilter = propertyFilterScheme
					.getPropertyFieldFilter(PropertyFieldIdentifier.LANGUAGES.getId());
			filter.getPropertyFieldFilters().add(selectedFilter);
			selectedFilter.setStringValues(new ArrayList<String>());
			for (Language language : selectedLanguages)
				selectedFilter.getStringValues().add(language.toString());
		}
		if (isFilterActive("STATE") && selectedStates != null && !selectedStates.isEmpty()) {
			PropertyFieldFilter selectedFilter = propertyFilterScheme
					.getPropertyFieldFilter(PropertyFieldIdentifier.STATE.getId());
			filter.getPropertyFieldFilters().add(selectedFilter);
			selectedFilter.setIntValues(new ArrayList<Integer>());
			for (StateVO state : selectedStates)
				selectedFilter.getIntValues().add(state.getId().intValue());
		}
		if (isFilterActive("CITY") && selectedCities != null && !selectedCities.isEmpty()) {
			PropertyFieldFilter selectedFilter = propertyFilterScheme
					.getPropertyFieldFilter(PropertyFieldIdentifier.CITY.getId());
			filter.getPropertyFieldFilters().add(selectedFilter);
			selectedFilter.setIntValues(new ArrayList<Integer>());
			for (CityVO city : selectedCities)
				selectedFilter.getIntValues().add(city.getId().intValue());
		}
		PropertyFieldFilter selectedFilter = getSchemeFilter("MARKET_PRICE");
		if (isFilterActive("MARKET_PRICE")
				&& (selectedFilter.getValueFrom() != null || selectedFilter.getValueTo() != null)){
			setupFilterConverter(selectedFilter);
			filter.getPropertyFieldFilters().add(selectedFilter);
		}
		selectedFilter = getSchemeFilter("AREA");
		if (isFilterActive("AREA") && (selectedFilter.getValueFrom() != null || selectedFilter.getValueTo() != null)){
			filter.getPropertyFieldFilters().add(selectedFilter);
		}
		propertyList = propertyServices.find(filter);
	}

	private void setupFilterConverter(PropertyFieldFilter selectedFilter) {
		CurrencyFilterConverter crcyFilterConverter = new CurrencyFilterConverter();
		crcyFilterConverter.setConversionExchangeRateList(currencyGlobalApplication.getConversionExchangeRateList());
		crcyFilterConverter.setCurrencies(currencyGlobalApplication.getCurrencies());
		crcyFilterConverter.setMoneyTo(webSession.getCurrentCurrency());
		crcyFilterConverter.setObjectProperty("currency.id");
		selectedFilter.setFilterConverter(crcyFilterConverter);
	}

	public List<PropertyVO> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyVO> propertyList) {
		this.propertyList = propertyList;
	}

	public PropertyFilter getPropertyFilter() {
		if (propertyFilterScheme == null)
			propertyFilterScheme = propertyFilteringServices.getFilterScheme();
		return propertyFilterScheme;
	}

	public void setPropertyFilter(PropertyFilter propertyFilter) {
		this.propertyFilterScheme = propertyFilter;
	}

	public List<StateVO> getStates() {
		return states;
	}

	public void setStates(List<StateVO> states) {
		this.states = states;
	}

	public List<CityVO> getCities() {
		return cities;
	}

	public void setCities(List<CityVO> cities) {
		this.cities = cities;
	}

	public List<StateVO> getSelectedStates() {
		return selectedStates;
	}

	public void setSelectedStates(List<StateVO> selectedStates) {
		this.selectedStates = selectedStates;
	}

	public List<CityVO> getSelectedCities() {
		return selectedCities;
	}

	public void setSelectedCities(List<CityVO> selectedCities) {
		this.selectedCities = selectedCities;
	}

	public List<Language> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}

	public List<PropertyTypeVO> getPropertyTypes() {
		return propertyTypes;
	}

	public void setPropertyTypes(List<PropertyTypeVO> propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

	public List<Language> getSelectedLanguages() {
		return selectedLanguages;
	}

	public void setSelectedLanguages(List<Language> selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
	}

	public Boolean getPropertyTypeFilterState() {
		return propertyTypeFilterState;
	}

	public void setPropertyTypeFilterState(Boolean propertyTypeFilterState) {
		this.propertyTypeFilterState = propertyTypeFilterState;
		reloadProperties();
	}

	public String getName(MultiLanguageText name) {
		return name.getText(webSession.getCurrentLanguage());
	}

	public List<PropertyTypeVO> getSelectedPropertyTypes() {
		return selectedPropertyTypes;
	}

	public void setSelectedPropertyTypes(List<PropertyTypeVO> selectedPropertyTypes) {
		this.selectedPropertyTypes = selectedPropertyTypes;
	}

	public PropertyFieldFilter getSchemeFilter(String key) {
		return propertyFilterScheme.getPropertyFieldFilter(PropertyFieldIdentifier.valueOf(key).getId());
	}

	private PropertyFilter processFilter(PropertyFilter filter) {
		PropertyFilter filterResult = new PropertyFilter();
		filterResult.setPropertyFieldFilters(new ArrayList<PropertyFieldFilter>());
		for (PropertyFieldFilter fieldFilter : filter.getPropertyFieldFilters()) {
			if (fieldFilter.getStringValue() != null || fieldFilter.getStringValues() != null
					|| fieldFilter.getValueFrom() != null || fieldFilter.getValueTo() != null)
				filterResult.getPropertyFieldFilters().add(fieldFilter);
		}
		return filterResult;
	}
}
