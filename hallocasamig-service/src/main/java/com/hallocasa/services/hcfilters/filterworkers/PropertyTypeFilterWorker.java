package com.hallocasa.services.hcfilters.filterworkers;

import static java.lang.String.format;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

@Stateless
public class PropertyTypeFilterWorker {
	
	// Property filter ids of reference
	// TODO: Make this dependent of context
	private static final Integer PROPERTY_TYPE_GROUP_FILTER_ID = 35;
	private static final Integer PROPERTY_TYPE_FILTER_ID = 1;
		
	/**
	 * Dynamic join query base. All dynamic elements are loaded according
	 * to the location filters requested. For example, if property types are
	 * included in the search, then PROPERTY_TYPE_RELATIONSHIP_FIELD,
	 * PROPERTY_TYPE_RELATIONSHIP_JOIN, PROPERTY_TYPE_GROUP_BY and PROPERTY_TYPE_CONDITION 
	 * must be replaced by specific query fragments
	 */
	private static final String BASE_LOCATION_QUERY = 
			" LEFT JOIN" 
			+ " property_type ptype "
			+ "ON p0.property_type_id = ptype.id " 
			+ "JOIN ( " 
			+ "SELECT "
			+ "  ptypegroup.id AS property_type_group_id" 
			+ "  <<PROPERTY_TYPE_RELATIONSHIP_FIELD>>"
			+ "FROM" 
			+ " property_type_group ptypegroup" 
			+ " <<PROPERTY_TYPE_RELATIONSHIP_JOIN>> "
			+ "WHERE "
			+ " ptypegroup.id IN <<PROPERTY_TYPE_GROUP_OPTIONS>> "
			+ "GROUP BY ptypegroup.id <<PROPERTY_TYPE_GROUP_BY>> " 
			+ ") ptypenet "
			+ "ON " 
			+ " ptype.group_id = ptypenet.property_type_group_id " 
			+ " <<PROPERTY_TYPE_CONDITION>>";
	
	/**
	 * Replacement of <<..._RELATIONSHIP_JOIN>>. Join fragment query used in 
	 * dynamic query for relate the countries to states
	 * to cities to neighborhoods as filter entries. %1$s is the table name (country,
	 * state, city or neighborhood), %2$s is an alias for the table in join,
	 * %3$s is the alias of parent for this table (f.e the alias of table country if
	 * we are doing join with state table), %4$s is a standard prefix for the foreign
	 * key of the parent in current table (always complemented by an _id). %4$s limits
	 * the join only to options selected in filter. %5$s filters the options to the
	 * identifiers selected by user. %6$s is an additional condition (OR, AND).
	 * If none option selected to the filter
	 * this fragment never appear in query and the <<..._RELATIONSHIP_JOIN>> will be
	 * replaced by blank
	 */
	private static final String RELATIONSHIP_JOIN_QUERY = " LEFT JOIN"
			+ " %1$s %2$s"
			+ " ON"
			+ " (%3$s.id = %2$s.%4$s_id %6$s)"
			+ " AND"
			+ " %2$s.id IN %5$s";
	
	/**
	 * Replacement of <<..._RELATIONSHIP_FIELD>>. Columns to extract from relationship
	 * countries to states to cities to neighborhoods query. %1$s is the table alias
	 * and %2$s is a readable prefix (f.e for country_id)
	 */
	private static final String RELATIONSHIP_FIELD = ", %1$s.id AS %2$s_id";
	
	/**
	 * Replacement of <<..._GROUP_BY>>. Fields to make the query only return the 
	 * relationships between the location ids and the parents / children. %1$s is
	 * the table alias.
	 */
	private static final String GROUP_BY = ", %1$s.id";
	
	/**
	 * Replacement of <<..._CONDITION>>. Core filter element. Conditions the join query
	 * in order to match the property field values only to the relationship requested.
	 * Use of wildcard with the comparation to null in location. %1$s is the unique name
	 * defined in JOIN_QUERY fragment.
	 */
	private static final String CONDITION = " AND (ptype.id = ptypenet.%1$s_id OR ptypenet.%1$s_id IS NULL)";

	/**
	 * The property filters related with property types (property type group 
	 * and property type) will be processed with an unique filter worker. 
	 * Therefore, they must be extracted from the main property filter submission 
	 * list
	 * @param filterList
	 * @return
	 */
	public List<PropertyFilterSubmission> extractLocationFromRequest(List<PropertyFilterSubmission> filterList) {
		List<PropertyFilterSubmission> ptypeFilterList = new LinkedList<>();
		List<Integer> indexesToRemove = new LinkedList<>();
		for (PropertyFilterSubmission submission : filterList) {
			Integer pfilterId = submission.getPropertyFilter().getFilter().getId();
			boolean ptypePfId = pfilterId == PROPERTY_TYPE_FILTER_ID
					|| pfilterId == PROPERTY_TYPE_GROUP_FILTER_ID;
			if (ptypePfId) {
				ptypeFilterList.add(submission);
				indexesToRemove.add(0, filterList.indexOf(submission));
			}
		}
		for (Integer index : indexesToRemove) {
			filterList.remove(index.intValue());
		}
		return ptypeFilterList;
	}

	/**
	 * Build a join filter query, based in a network of location filters,
	 * related with each other
	 * 
	 * @param filterSubmissionList
	 *            The filter submission elements
	 */
	public String loadJoinQuery(List<PropertyFilterSubmission> filterSubmissionList) {
		if (filterSubmissionList.isEmpty()) {
			return "";
		}
		String baseQuery = BASE_LOCATION_QUERY;
		for (PropertyFilterSubmission filterSubmission : filterSubmissionList) {
			String target = "";
			String targetAlias = "";
			String parentTarget = "";
			String parentTargetAbbr = "";
			String additionalCondition = "";
			if(filterSubmission.getPropertyFilter().getFilter().getId() == PROPERTY_TYPE_GROUP_FILTER_ID){
				target = "property_type_group";
			}
			if(filterSubmission.getPropertyFilter().getFilter().getId() == PROPERTY_TYPE_FILTER_ID){
				target = "property_type";
				targetAlias = "ptype";
				parentTarget = "group";
				parentTargetAbbr = "ptypegroup";
			}
			String options = WorkerUtils.commaSeparated(filterSubmission.getSelectedFilterOptions());
			baseQuery = replace(baseQuery, "<<%1$s_RELATIONSHIP_FIELD>>", target, RELATIONSHIP_FIELD, targetAlias, target);
			baseQuery = replace(baseQuery, "<<%1$s_RELATIONSHIP_JOIN>>", target, RELATIONSHIP_JOIN_QUERY, target, 
					targetAlias, parentTargetAbbr, parentTarget, options, additionalCondition);
			baseQuery = replace(baseQuery, "<<%1$s_GROUP_BY>>", target, GROUP_BY, targetAlias);
			baseQuery = replace(baseQuery, "<<%1$s_CONDITION>>", target, CONDITION, target);
			baseQuery = baseQuery.replaceAll(format("<<%1$s_OPTIONS>>", target.toUpperCase()), options);
		}
		return baseQuery.replaceAll("\\<\\<([A-Z]|_)+\\>\\>", "");
	}
	
	/**
	 * Executes a replace for dynamic query. The target to search is dynamic, so
	 * two parsing process (format methods) are needed. The base query is affected, and
	 * a dynamic target parsed is replaced by a parse of a dynamic query and its args
	 * @param baseQuery
	 * 		Base query to replace some part
	 * @param dynamicTarget
	 * 		Target of base query to replace - dynamic
	 * @param baseWord
	 * 		Argument to use to parse the dynamic target
	 * @param fragmentQuery
	 * 		Dynamic fragment query to use as a replace
	 * @param args
	 * 		Arguments to parse dynamic fragment query
	 * @return
	 * 		The base query replaced
	 */
	private String replace(String baseQuery, String dynamicTarget, 
			String baseWord, String fragmentQuery, Object... args){
		return baseQuery
				.replaceAll(format(dynamicTarget, baseWord.toUpperCase())
						, format(fragmentQuery, args));
	}
}
