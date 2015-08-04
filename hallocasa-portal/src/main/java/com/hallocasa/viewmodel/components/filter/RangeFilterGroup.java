package com.hallocasa.viewmodel.components.filter;

import com.hallocasa.business.services.filter.FilterCondition;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.validator.ValidatorException;

import com.hallocasa.business.services.filter.FilterGroup;
import com.hallocasa.view.utils.JSFUtils;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItem;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItemCondition;

import javax.faces.convert.NumberConverter;
import javax.faces.model.SelectItem;

/**
 * This class is the union between the filter model object and the filter view
 * object for making a filter Menu
 *
 * @author David Mantilla
 *
 * @param <T>
 */
public class RangeFilterGroup<T extends Number> extends FilterGroup<T>
		implements FilterMenuItem<T> {

	private static final String PROPERTY_FILTER_MIN = "Property.Filter.Min";

	private T firstValue;
	private T secondValue;
	private final List<FilterConditionAdapter<T>> filterConditions;
	private final String id;
	private String title;

	/**
	 *
	 * @param id
	 * @param fieldName
	 * @param title
	 */
	public RangeFilterGroup(String id, String fieldName, String title) {
		super(id, fieldName);
		this.id = id;
		this.title = title;
		this.filterConditions = new ArrayList<>();
	}

	@Override
	public FilterMenuItem.FilterType getFilterType() {
		return FilterType.RANGE;
	}

	@Override
	public Converter getConverter() {
		return new NumberConverter();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public SelectItem[] getFilterMenuItemOptions() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FilterMenuItemCondition<T>[] getFilterMenuItemConditions() {
		return filterConditions.toArray(new FilterMenuItemCondition[] {});
	}

	/**
	 * @return the firstValue
	 */
	@Override
	public T getFirstValue() {
		return firstValue;
	}

	/**
	 * @param firstValue
	 *            the firstValue to set
	 */
	@Override
	public void setFirstValue(T firstValue) {
		this.firstValue = firstValue;
	}

	/**
	 * @return the secondValue
	 */
	@Override
	public T getSecondValue() {
		return secondValue;
	}

	/**
	 * @param secondValue
	 *            the secondValue to set
	 */
	@Override
	public void setSecondValue(T secondValue) {
		this.secondValue = secondValue;
	}

	/**
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void validateFirstValue(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value != null) {
			Number number = (Number) value;
			if (number.doubleValue() < 0) {
				//
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						JSFUtils.getViewBundleString(PROPERTY_FILTER_MIN), ""));
			}
		}
	}

	@Override
	public void validateSecondValue(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		//
	}

	@Override
	public void processFilterChange() {
		filterConditions.clear();
		if ((firstValue != null) && (secondValue != null)) {
			FilterConditionAdapter<T> filterCondition = new FilterConditionAdapter<>(
					this, title, id, getFieldName(),
					FilterCondition.FilterOperation.BETWEEN, firstValue,
					secondValue);
			filterConditions.add(filterCondition);
		}
	}

	@Override
	public void removeFilterMenuItemCondition(
			FilterMenuItemCondition<?> filterMenuItemConditionToRemove) {
		if (filterConditions.isEmpty()) {
			throw new UnsupportedOperationException(
					"Condition list is already empty");
		}
		if (filterMenuItemConditionToRemove != filterConditions.get(0)) {
			throw new IllegalArgumentException(
					"The condition doesn't belong this item");
		}
		firstValue = null;
		secondValue = null;
		processFilterChange();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getFilterConditions() {
		return filterConditions;
	}

	@Override
	public List<T> getListValue() {
		return null;
	}

	@Override
	public void setListValue(List<T> values) {
		//
	}

}
