package com.hallocasa.viewmodel.components.filter;

import com.hallocasa.business.services.filter.FilterCondition;
import com.hallocasa.business.services.filter.FilterGroup;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItem;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItemCondition;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

/**
 * This class is the union between the filter model object and the filter view
 * object for making a filter Menu with the option of a containing part of text
 * in a field
 *
 * @author David Mantilla
 *
 * @param <T>
 */
public class ContainsFilterGroup<T> extends FilterGroup<T> implements
		FilterMenuItem<T> {

	private T firstValue;
	private final String id;
	private String title;

	/**
	 *
	 * @param id
	 * @param fieldName
	 * @param title
	 */
	public ContainsFilterGroup(String id, String fieldName, String title) {
		super(id, fieldName);
		this.id = id;
		this.title = title;
	}

	@Override
	public void removeFilterMenuItemCondition(
			FilterMenuItemCondition<?> filterMenuItemConditionToRemove) {
		if (getFilterConditions().isEmpty()) {
			throw new UnsupportedOperationException(
					"The condition list is already empty");
		}
		if (getFilterConditions().get(0) != filterMenuItemConditionToRemove) {
			throw new IllegalArgumentException(
					"The condition doesn't belong this item");
		}
		firstValue = null;
		processFilterChange();
	}

	@Override
	public FilterMenuItem.FilterType getFilterType() {
		return FilterType.CONTAINS;
	}

	@Override
	public Converter getConverter() {
		return null;
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
		return getFilterConditions().toArray(new FilterConditionAdapter[] {});
	}

	/**
	 * @return the firstValue
	 */
	public T getFirstValue() {
		return firstValue;
	}

	/**
	 * @param firstValue
	 *            the firstValue to set
	 */
	public void setFirstValue(T firstValue) {
		this.firstValue = firstValue;
	}

	/**
	 * @return the secondValue
	 */
	public T getSecondValue() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param secondValue
	 *            the secondValue to set
	 */
	public void setSecondValue(T secondValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void validateFirstValue(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		// Nothing to do by now
	}

	@Override
	public void validateSecondValue(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void processFilterChange() {
		getFilterConditions().clear();
		if ((firstValue != null) && (!firstValue.toString().isEmpty())) {
			FilterConditionAdapter<T> filterCondition;
			filterCondition = new FilterConditionAdapter<>(this, title, id,
					getFieldName(), FilterCondition.FilterOperation.LIKE,
					firstValue, null);
			getFilterConditions().add(filterCondition);
		}
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
	public List<T> getListValue() {
		return null;
	}

	@Override
	public void setListValue(List<T> values) {
		// /
	}

}
