/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * This class represents a condition which can be added to a filter
 *
 * @author David Mantilla
 * @param <T>
 */
public class FilterCondition<T> {

    public enum FilterOperation {

        EQUALS, GREATER_THAN, LESS_THAN, BETWEEN, LIKE, GREATER_AND_EQUALS_THAN, LESS_AND_EQUALS_THAN;
    }

    public static final String ENTITY_NAME = "entity";

    private final String fieldName;
    private final String conditionId;
    private final FilterOperation filterOperation;
    private final Map<String, Object> values;

    /**
     *
     * @param conditionId An unique string that identifies this condition in a FilterGroups userd
     * for the same query creation
     * @param fieldName Entity field name
     * @param filterOperation Filter operation type
     */
    public FilterCondition(String conditionId, String fieldName,
            FilterOperation filterOperation) {
        this.conditionId = conditionId;
        this.fieldName = fieldName;
        this.filterOperation = filterOperation;

        this.values = new HashMap<>();
        this.values.put(conditionId + "_1", null);
        if (this.filterOperation.equals(FilterOperation.BETWEEN)) {
            this.values.put(conditionId + "_2", null);
        }
    }

    /**
     * Return this condition as a SQL statement
     *
     * @return
     */
    public String getAsSQLStatement() {

        StringBuilder str;
        switch (filterOperation) {
            case BETWEEN:
                str = new StringBuilder();
                str.append("((").append(buildFieldName(fieldName))
                        .append(" >= :").append(conditionId).append("_1")
                        .append(" )");
                str.append("and");
                str.append("(").append(buildFieldName(fieldName)).append(".")
                        .append(" <= :").append(conditionId).append("_2")
                        .append(" ))");
                return str.toString();
            case GREATER_THAN:
                str = new StringBuilder();
                str.append("(").append(buildFieldName(fieldName)).append(".")
                        .append(" > :").append(conditionId).append("_1")
                        .append(" )");
                return str.toString();
            case GREATER_AND_EQUALS_THAN:
                str = new StringBuilder();
                str.append("(").append(buildFieldName(fieldName)).append(".")
                        .append(" >= :").append(conditionId).append("_1")
                        .append(" )");
                return str.toString();
            case EQUALS:
                str = new StringBuilder();
                str.append("(").append(buildFieldName(fieldName)).append(".")
                        .append(" = :").append(conditionId).append("_1")
                        .append(" )");
                return str.toString();
            case LESS_THAN:
                str = new StringBuilder();
                str.append("(").append(buildFieldName(fieldName)).append(".")
                        .append(" < :").append(conditionId).append("_1")
                        .append(" )");
                return str.toString();
            case LESS_AND_EQUALS_THAN:
                str = new StringBuilder();
                str.append("(").append(buildFieldName(fieldName)).append(".")
                        .append(" <= :").append(conditionId).append("_1")
                        .append(" )");
                return str.toString();
            case LIKE:
                str = new StringBuilder();
                str.append("(").append(buildFieldName(fieldName)).append(".")
                        .append(" like :").append(conditionId).append("_1")
                        .append(" )");
                return str.toString();
            default:
                throw new UnsupportedOperationException();
        }

    }

    /**
     * Return a value by its value key. A value key is the conditionId plus _1 or _2. Only Beetwen
     * condition has two values, any other has only one value
     *
     * @param valueKey
     * @return
     */
    public Object getValue(String valueKey) {
        if (!values.containsKey(valueKey)) {
            throw new IllegalArgumentException(valueKey
                    + "is not in the values map");
        }
        return values.get(valueKey);
    }

    /**
     * @return
     */
    public String getEntityIdentifier() {
        return ENTITY_NAME;
    }

    /**
     * Set value by its value key. A value key is the conditionId plus _1 or _2. Only Beetwen
     * condition has two values, any other has only one
     *
     * @param valueKey
     * @param value
     */
    public void setValue(String valueKey, Object value) {
        if (!values.containsKey(valueKey)) {
            throw new IllegalArgumentException(valueKey
                    + "is not in the values map");
        }
        values.put(valueKey, value);
    }

    /**
     * Getter for filterOperation
     *
     * @return filterOperation
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Get conditionId
     *
     * @return filterOperation
     */
    public String getConditionId() {
        return conditionId;
    }

    /**
     * Return the count of values the condition has
     *
     * @return
     */
    public int getValuesCount() {
        return values.size();
    }

    /**
     * Return the list of param values key
     *
     * @return
     */
    public Set<String> getValuesKeySet() {
        return values.keySet();
    }

    /**
     * Get filterOperation
     *
     * @return filterOperation
     */
    public FilterOperation getFilterOperation() {
        return filterOperation;
    }

    /**
     * If the field name starts with '\' then it means is not relative to entity so it must be used
     * like it is, otherwise the current fieldName is concatanted to the ENTITY_NAME constant
     *
     * @param fieldName
     * @return
     */
    private Object buildFieldName(String fieldName) {
        if (fieldName.startsWith("\\")) {
            return fieldName.replaceFirst("\\\\", "");
        } else {
            return ENTITY_NAME.concat(".").concat(fieldName);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Predicate getAsPredicate(CriteriaBuilder cb, Map<String, From> fromMap) {
        switch (filterOperation) {
            case BETWEEN:
			return cb.between(
					(Path<Comparable>) buildPath(fieldName, fromMap,
							Comparable.class),
					(Comparable) getValue(conditionId + "_1"),
					(Comparable) getValue(conditionId + "_2"));
            case GREATER_THAN:
                return cb.greaterThan((Path<Comparable>) buildPath(fieldName, fromMap, Comparable.class),
                        (Comparable) getValue(conditionId + "_1"));
            case GREATER_AND_EQUALS_THAN:
                return cb.greaterThanOrEqualTo((Path<Comparable>) buildPath(fieldName, fromMap, Comparable.class),
                        (Comparable) getValue(conditionId + "_1"));
            case EQUALS:
                return cb.equal((Path) buildPath(fieldName, fromMap, Object.class),
                        getValue(conditionId + "_1"));
            case LESS_THAN:
                return cb.lessThan((Path<Comparable>) buildPath(fieldName, fromMap, Comparable.class),
                        (Comparable) getValue(conditionId + "_1"));
            case LESS_AND_EQUALS_THAN:
                return cb.lessThanOrEqualTo((Path<Comparable>) buildPath(fieldName, fromMap, Comparable.class),
                        (Comparable) getValue(conditionId + "_1"));
            case LIKE:
                return cb.like((Path<String>) buildPath(fieldName, fromMap, String.class),
                        (String) getValue(conditionId + "_1"));
            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * Builds the path of the field
     *
     * @param <Y> Class of the field
     * @param fieldName string field name
     * @param fromMap Map with all entities used in the from, which can be used to obtain an entityF
     * @param expectedClass
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public <Y> Path<Y> buildPath(String fieldName, Map<String, From> fromMap, Class<Y> expectedClass) {

        // if starts with '\' means that is not the main entity but a joined one, the first name in 
        // the path is the name of the entity, afther then is attribute path
        if (fieldName.startsWith("\\")) {
            String[] fields = fieldName.replace("\\", "").split("\\.");
			Path<Y> path = fromMap.get(fields[0]).get(fields[1]);
            for (int i = 2; i < fields.length; i++) {
                path = path.get(fields[i]);
            }
            return path;
        } // if doesn't start with '\' means that is main entity 
        else {
            String[] fields = fieldName.split("\\.");
            Path<Y> path = fromMap.get(ENTITY_NAME).get(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                path = path.get(fields[i]);
            }
            return path;
        }
    }

}
