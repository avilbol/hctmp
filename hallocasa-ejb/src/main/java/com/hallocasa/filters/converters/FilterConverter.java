package com.hallocasa.filters.converters;

/**
 * Converter to apply in search filtering
 * @author Alexander Villamil
 */
public interface FilterConverter {

	Object transform(Object propertyToTransform, String[] helpers);
	
	public String getObjectProperty();

	public void setObjectProperty(String objectProperty);
}
