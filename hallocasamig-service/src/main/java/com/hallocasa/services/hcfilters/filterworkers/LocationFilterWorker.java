package com.hallocasa.services.hcfilters.filterworkers;

import static java.lang.String.format;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import com.hallocasa.vo.hcfilter.HcFilterEntry;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterEntry;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

/**
 * Filter worker used when a query filter apply for a group of property filter
 * submissions. In this case, for location related things
 * 
 * @author avilbol
 */
@Stateless
public class LocationFilterWorker {

	// Property field ids of reference
	// TODO: Make this dependent of context
	private static final Integer COUNTRY_PF_ID = 34;
	private static final Integer STATE_PF_ID = 7;
	private static final Integer CITY_PF_ID = 8;
	private static final Integer NEIGHBORHOOD_PF_ID = 15;
	
	/**
	 * Dynamic join query base. All dynamic elements are loaded according
	 * to the location filters requested. For example, if cities are
	 * included in the search, then CITY_JOIN, CITY_RELATIONSHIP_FIELD,
	 * CITY_RELATIONSHIP_JOIN, CITY_GROUP_BY and CITY_CONDITION must be replaced
	 * by specific query fragments
	 */
	private static final String BASE_LOCATION_QUERY = " <<STATE_JOIN>>" 
			+ " <<CITY_JOIN>>" 
			+ " <<NEIGHBORHOOD_JOIN>> " 
			+ " JOIN (" 
			+ "  SELECT "
			+ "  co.id AS country_id" 
			+ "  <<STATE_RELATIONSHIP_FIELD>>" 
			+ "  <<CITY_RELATIONSHIP_FIELD>>"
			+ "  <<NEIGHBORHOOD_RELATIONSHIP_FIELD>> " 
			+ "FROM" 
			+ " country co" 
			+ " <<STATE_RELATIONSHIP_JOIN>> "
			+ " <<CITY_RELATIONSHIP_JOIN>> " 
			+ " <<NEIGHBORHOOD_RELATIONSHIP_JOIN>> " 
			+ "WHERE "
			+ " co.id IN <<COUNTRY_OPTIONS>> "
			+ "GROUP BY co.id <<STATE_GROUP_BY>> <<CITY_GROUP_BY>> <<NEIGHBORHOOD_GROUP_BY>> " 
			+ ") location "
			+ "ON " 
			+ " prop.country_id = location.country_id " 
			+ " <<STATE_CONDITION>>" 
			+ " <<CITY_CONDITION>>"
			+ " <<NEIGHBORHOOD_CONDITION>>";
	
	/**
	 * Query fragment to replace the <<..._JOIN>> dynamic parts in base location
	 * query. %1$s is a unique name the becomes pluralized (state to states, f.e).
	 * %2$s is a value that filters the property field value entries to only one
	 * property field id (f.e, 7 to specify only states)
	 */
	private static final String JOIN_QUERY = "LEFT JOIN" 
			+ " property_field_value pfv_%1$ss"
			+ " on prop.property_id = pfv_%1$ss.property_id" 
			+ " and pfv_%1$ss.property_field_id=%2$s";
	
	/**
	 * Replacement of <<..._RELATIONSHIP_JOIN>>. Join fragment query used in 
	 * dynamic query for relate the countries to states
	 * to cities to neighborhoods as filter entries. %1$s is the table name (country,
	 * state, city or neighborhood), %2$s is an alias for the table in join,
	 * %3$s is the alias of parent for this table (f.e the alias of table country if
	 * we are doing join with state table), %4$s is a standard prefix for the foreign
	 * key of the parent in current table (always complemented by an _id). %4$s limits
	 * the join only to options selected in filter. If none option selected to the filter
	 * this fragment never appear in query and the <<..._RELATIONSHIP_JOIN>> will be
	 * replaced by blank
	 */
	private static final String RELATIONSHIP_JOIN_QUERY = " LEFT JOIN"
			+ " %1$s %2$s"
			+ " ON"
			+ " %3$s.id = %2$s.%4$s_id"
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
	private static final String CONDITION = " AND (pfv_%1$ss.identifier = location.%1$s_id OR location.%1$s_id IS NULL)";

	/**
	 * The property filters related with location (country, state, city and
	 * neighborhood) will be processed with an unique filter worker. Therefore,
	 * they must be extracted from the main property filter submission list
	 * 
	 * @param filterList
	 * @return
	 */
	public List<PropertyFilterSubmission> extractLocationFromRequest(List<PropertyFilterSubmission> filterList) {
		List<PropertyFilterSubmission> locationFilterList = new LinkedList<>();
		List<Integer> indexesToRemove = new LinkedList<>();
		for (PropertyFilterSubmission submission : filterList) {
			Integer pfId = submission.getPropertyFilter().getPropertyField().getId();
			boolean locationPfId = pfId == COUNTRY_PF_ID || pfId == STATE_PF_ID || pfId == CITY_PF_ID
					|| pfId == NEIGHBORHOOD_PF_ID;
			if (locationPfId) {
				locationFilterList.add(submission);
				indexesToRemove.add(0, filterList.indexOf(submission));
			}
		}
		for (Integer index : indexesToRemove) {
			filterList.remove(index.intValue());
		}
		return locationFilterList;
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
			if(filterSubmission.getPropertyFilter().getPropertyField().getId() == COUNTRY_PF_ID){
				target = "country";
			}
			if(filterSubmission.getPropertyFilter().getPropertyField().getId() == STATE_PF_ID){
				target = "state";
				targetAlias = "st";
				parentTarget = "country";
				parentTargetAbbr = "co";
			}
			if(filterSubmission.getPropertyFilter().getPropertyField().getId() == CITY_PF_ID){
				target = "city";
				targetAlias = "ci";
				parentTarget = "state";
				parentTargetAbbr = "st";
			}
			if(filterSubmission.getPropertyFilter().getPropertyField().getId() == NEIGHBORHOOD_PF_ID){
				target = "neighborhood";
				targetAlias = "ne";
				parentTarget = "city";
				parentTargetAbbr = "ci";
			}
			Integer pfId = filterSubmission.getPropertyFilter().getPropertyField().getId();
			String options = WorkerUtils.commaSeparated(filterSubmission.getSelectedFilterOptions());
			baseQuery = replace(baseQuery, "<<%1$s_JOIN>>", target, JOIN_QUERY, target, pfId);
			baseQuery = replace(baseQuery, "<<%1$s_RELATIONSHIP_FIELD>>", target, RELATIONSHIP_FIELD, targetAlias, target);
			baseQuery = replace(baseQuery, "<<%1$s_RELATIONSHIP_JOIN>>", target, RELATIONSHIP_JOIN_QUERY, target, 
					targetAlias, parentTargetAbbr, parentTarget, options);
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
	
	/**
	 * Test case
	 * @param args
	 */
	public static void main(String[] args) {
		LocationFilterWorker locationFilterWorker = new LocationFilterWorker();
		List<PropertyFilterSubmission> list = new LinkedList<>();
		PropertyFilterSubmission propertyFilterSubmission =  new PropertyFilterSubmission();
		PropertyFilterEntry entry = new PropertyFilterEntry();
		HcFilterEntry filter = new HcFilterEntry();
		List<DropdownOption> dropdownOptionList = new LinkedList<>();
		DropdownOption dropdownOpt = new DropdownOption();
		PropertyField propertyField = new PropertyField();
		propertyField.setId(STATE_PF_ID);
		entry.setPropertyField(propertyField);
		entry.setFilter(filter);
		dropdownOpt.setOptionId(5);
		dropdownOptionList.add(dropdownOpt);
		dropdownOpt = new DropdownOption();
		dropdownOpt.setOptionId(8);
		dropdownOptionList.add(dropdownOpt);
		dropdownOpt = new DropdownOption();
		dropdownOpt.setOptionId(1);
		dropdownOptionList.add(dropdownOpt);
		propertyFilterSubmission.setSelectedFilterOptions(dropdownOptionList);
		propertyFilterSubmission.setPropertyFilter(entry);
		
		propertyFilterSubmission.setSelectedFilterOptions(dropdownOptionList);
		propertyFilterSubmission.setPropertyFilter(entry);
		list.add(propertyFilterSubmission);

		propertyFilterSubmission =  new PropertyFilterSubmission();
		entry = new PropertyFilterEntry();
		filter = new HcFilterEntry();
		dropdownOptionList = new LinkedList<>();
		dropdownOpt = new DropdownOption();
		propertyField = new PropertyField();
		propertyField.setId(COUNTRY_PF_ID);
		entry.setPropertyField(propertyField);
		entry.setFilter(filter);
		dropdownOpt.setOptionId(1);
		dropdownOptionList.add(dropdownOpt);
		dropdownOpt = new DropdownOption();
		dropdownOpt.setOptionId(3);
		dropdownOptionList.add(dropdownOpt);
		dropdownOpt = new DropdownOption();
		dropdownOpt.setOptionId(2);
		dropdownOptionList.add(dropdownOpt);
		propertyFilterSubmission.setSelectedFilterOptions(dropdownOptionList);
		propertyFilterSubmission.setPropertyFilter(entry);
		list.add(propertyFilterSubmission);
		
		propertyFilterSubmission =  new PropertyFilterSubmission();
		entry = new PropertyFilterEntry();
		filter = new HcFilterEntry();
		dropdownOptionList = new LinkedList<>();
		dropdownOpt = new DropdownOption();
		propertyField = new PropertyField();
		propertyField.setId(CITY_PF_ID);
		entry.setPropertyField(propertyField);
		entry.setFilter(filter);
		dropdownOpt.setOptionId(1);
		dropdownOptionList.add(dropdownOpt);
		propertyFilterSubmission.setSelectedFilterOptions(dropdownOptionList);
		propertyFilterSubmission.setPropertyFilter(entry);
		list.add(propertyFilterSubmission);
		
		System.out.println(locationFilterWorker.loadJoinQuery(list));
	}
	

}
