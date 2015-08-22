package com.hallocasa.viewmodel.components.filter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import com.hallocasa.dataentities.GeoDelimitation;
import com.hallocasa.dataentities.interfaces.TypeInterface;
import com.hallocasa.services.filter.FilterCondition;
import com.hallocasa.services.filter.FilterCondition.FilterOperation;
import com.hallocasa.services.filter.FilterGroup;
import com.hallocasa.commons.Language;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItem;
import com.hallocasa.view.components.general.filtermenu.FilterMenuItemCondition;

/**
 * This class is the union between the filter model object and the filter view object for making a
 * filter Menu
 *
 * @author David Mantilla
 *
 * @param <T>
 */
public class ManyFilterGroup<T> extends FilterGroup<T> implements
        FilterMenuItem<T> {

    private final String id;
    private String title;
    private final List<FilterSelectItem> optionList;
    private List<T> selectedList;
    private final Language language;
    private Converter converter;

    /**
     *
     * @param id
     * @param fieldName
     * @param title
     * @param optionList
     * @param language
     */
    public ManyFilterGroup(String id, String fieldName, String title,
            List<FilterSelectItem> optionList, Language language) {
        super(id, fieldName);
        this.id = id;
        this.title = title;
        this.language = language;
        this.optionList = optionList;
        this.selectedList = new ArrayList<>();
    }

    @Override
    public void removeFilterMenuItemCondition(
            FilterMenuItemCondition<?> filterMenuItemConditionToRemove) {
        if (getFilterConditions().isEmpty()) {
            throw new UnsupportedOperationException(
                    "The condition list is already empty");
        }

        if (getFilterConditions().contains(filterMenuItemConditionToRemove)) {
            if (filterMenuItemConditionToRemove.getFilterOperation() == FilterOperation.BETWEEN) {
                removeBetweeenFilterCondition(filterMenuItemConditionToRemove);
            } else {
                selectedList.remove(filterMenuItemConditionToRemove
                        .getFirstValue());
            }
        }
        processFilterChange();
    }

    /**
     * Finds and removes a filter condition of the beetween type
     *
     * @param filterMenuItemConditionToRemove
     */
    private void removeBetweeenFilterCondition(
            FilterMenuItemCondition<?> filterMenuItemConditionToRemove) {
        // find the value of the selection
        for (SelectItem si : filterMenuItemConditionToRemove
                .getFilterMenuItem().getFilterMenuItemOptions()) {
            FilterSelectItem fi = (FilterSelectItem) si;
            if (fi.getFilterOperation() == FilterOperation.BETWEEN) {

                if ((fi.getRangeFrom().equals(filterMenuItemConditionToRemove
                        .getFirstValue()))
                        && (fi.getRangeTo()
                        .equals(filterMenuItemConditionToRemove
                                .getSecondValue()))) {
                    selectedList.remove(fi.getValue());
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Option not found");

    }

    @Override
    public FilterMenuItem.FilterType getFilterType() {
        return FilterType.SELECT_MANY;
    }

    @Override
    public Converter getConverter() {
        return converter;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * @param converter the converter to set
     */
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Override
    public FilterSelectItem[] getFilterMenuItemOptions() {
        return optionList.toArray(new FilterSelectItem[]{});
    }

    @SuppressWarnings("unchecked")
    @Override
    public FilterMenuItemCondition<T>[] getFilterMenuItemConditions() {
        return getFilterConditions().toArray(new FilterConditionAdapter[]{});
    }

    /**
     * @return the secondValue
     */
    @Override
    public T getSecondValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param secondValue the secondValue to set
     */
    @Override
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
        int index = 0;
        for (T item : selectedList) {

            // get the selected option
            FilterSelectItem selectedSelectItem = null;
            for (FilterSelectItem fi : getFilterMenuItemOptions()) {
                if (fi.getValue().toString().equals(item.toString())) {
                    selectedSelectItem = fi;
                    break;
                }
            }
            if (selectedSelectItem == null) {
                throw new UnsupportedOperationException(
                        "A selected item has not be found in the options list, how the hell @#%&^@#%&^ ?");
            }

            // build condition
            FilterConditionAdapter<T> filterCondition;

            switch (selectedSelectItem.getFilterOperation()) {
                case EQUALS:
                    filterCondition = buildEquaslCondition(index, item);
                    break;
                case BETWEEN:
                    filterCondition = buildBetweenCondition(index,
                            selectedSelectItem);
                    break;
                case LESS_THAN:
                case LESS_AND_EQUALS_THAN:
                case GREATER_AND_EQUALS_THAN:
                case GREATER_THAN:
                    filterCondition = buildLessOrGreaterThanCondition(index,
                            selectedSelectItem.getLabel(), selectedSelectItem);
                    break;
                default:
                    throw new UnsupportedOperationException("Not yet");
            }

            getFilterConditions().add(filterCondition);
            index++;
        }
    }

    /**
     * Builds a less or greater than condition
     *
     * @param index
     * @param item
     * @return
     */
    @SuppressWarnings("unchecked")
    private FilterConditionAdapter<T> buildLessOrGreaterThanCondition(
            int index, final String label, FilterSelectItem item) {
        FilterConditionAdapter<T> filterCondition;
        filterCondition = new FilterConditionAdapter<T>(this, label, id + "_"
                + (index), getFieldName(), item.getFilterOperation(),
                (T) item.getValue(), null) {

                    @Override
                    public String getLabel() {
                        return label;
                    }

                };
        return filterCondition;
    }

    /**
     * Builds an equal condition with the selected item
     *
     * @param index
     * @param item
     * @return
     */
    private FilterConditionAdapter<T> buildEquaslCondition(int index, T item) {
        FilterConditionAdapter<T> filterCondition;
        filterCondition = new FilterConditionAdapter<T>(this, title, id + "_"
                + (index), getFieldName(),
                FilterCondition.FilterOperation.EQUALS, item, null) {
                    @Override
                    public String getLabel() {
                        Object firstValue = getFirstValue();
                        if (firstValue == null) {
                            return null;
                        }
                        if (firstValue instanceof GeoDelimitation) {
                            GeoDelimitation geoDelimitation = (GeoDelimitation) firstValue;
                            return geoDelimitation.getName();
                        }
                        if (firstValue instanceof TypeInterface) {
                            TypeInterface typeInterface = (TypeInterface) firstValue;
                            return typeInterface.getLabel(language);
                        } else {
                            return firstValue.toString();
                        }
                    }
                };
        return filterCondition;
    }

    /**
     * Builds an equal condition with the selected item
     *
     * @param index
     * @param item
     * @return
     */
    @SuppressWarnings({"unchecked"})
    private FilterConditionAdapter<T> buildBetweenCondition(int index,
            FilterSelectItem item) {
        FilterConditionAdapter<T> filterCondition;
        filterCondition = new FilterConditionAdapter<T>(this, title, id + "_"
                + (index), getFieldName(),
                FilterCondition.FilterOperation.BETWEEN,
                (T) item.getRangeFrom(), (T) item.getRangeTo());
        return filterCondition;
    }

    /**
     * @return the title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<T> getListValue() {
        return selectedList;
    }

    @Override
    public void setListValue(List<T> values) {
        this.selectedList = values;
    }

    @Override
    public void setFirstValue(T value) {
        //
    }

    @Override
    public T getFirstValue() {
        return null;
    }

    /**
     * Get options list
     * @return 
     */
    public List<FilterSelectItem> getOptionList() {
        return optionList;
    }
    
    

}
