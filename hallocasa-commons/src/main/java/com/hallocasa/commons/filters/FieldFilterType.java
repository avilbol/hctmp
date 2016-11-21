package com.hallocasa.commons.filters;

/**
 * Value object for type of property filters
 * @author Alexander Villamil
 */
public enum FieldFilterType {
	RANGE,
	UNIQUE_SELECT,
	MULTIPLE_SELECT_OR,
	MULTIPLE_SELECT_AND,
	BOOLEAN,
	CUSTOM
}
