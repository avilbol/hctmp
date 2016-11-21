package com.hallocasa.dao.hcfilter;

import static com.hallocasa.entities.EntityHcFilter.QUERY_FILTER_BASIC_VALUES;
import static com.hallocasa.entities.EntityHcFilter.QUERY_FILTER_BY_FIELD_KEYS;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.hcfilter.IDAOHcFilter;
import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.vo.hcfilter.HcFilter;
import com.hallocasa.vo.hcfilter.properties.PropertyDropdownFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;

/**
 * DAO for class {@link HcFilter}
 */
@Stateless
public class DAOHcFilter implements IDAOHcFilter {

	@EJB
	private AppPersistenceServices appPersistenceServices;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityHcFilter> find(List<Integer> filterNatureIdList) {
		String query = EntityHcFilter.QUERY_FIND_BY_NATURE;
		List<Object> paramList = new LinkedList<Object>();
		for (Integer filterNatureId : filterNatureIdList) {
			paramList.add(filterNatureId);
		}
		return appPersistenceServices.executeNamedQuery(query, new Object[] { filterNatureIdList },
				EntityHcFilter.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityHcFilter> find() {
		return appPersistenceServices.executeNamedQuery(EntityHcFilter.QUERY_FIND_ALL, new Object[] {},
				EntityHcFilter.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityHcFilter> findByPropertyKeys(List<PropertyDropdownFilterSubmission> filterList) {
		List<Object> attrList = new LinkedList<>();
		String dynamicSearchQuery = "select pf.id from property_field pf";
		String joinSchema = " join (select * from property_field_condition_option where condition_level = %1$d and parent_property_field_id=?%2$d) p%4$d"
				+ " on p%4$d.property_field_id = pf.id AND p%4$d.parent_property_field_option_id IN %3$s ";
		StringBuilder builderCond0 = new StringBuilder(dynamicSearchQuery);
		StringBuilder builderCond1 = new StringBuilder(dynamicSearchQuery);
		Integer filterCounter = 0;
		Integer attrCounter = 0;
		if (filterList.isEmpty()) {
			throw new BadRequestException("You must send at least one property key filter");
		}
		for (PropertyDropdownFilterSubmission pdfs : filterList) {
			if (pdfs.getSelectedFilterOptions().isEmpty()) {
				throw new BadRequestException("You cannot send property key filters " + "without selected options!");
			}
			StringBuilder internalOptBuilder = new StringBuilder("(");
			for (DropdownOption option : pdfs.getSelectedFilterOptions()) {
				internalOptBuilder.append("?" + (++attrCounter) + ",");
				attrList.add(option.getOptionId());
			}
			String options = internalOptBuilder.append(")").toString().replaceAll(",\\)", "\\)");
			Integer propertyFieldId = pdfs.getFilter().getPropertyField().getId();
			String joinSchemaResC0 = String.format(joinSchema, 0, ++attrCounter, options, ++filterCounter);
			String joinSchemaResC1 = String.format(joinSchema, 1, attrCounter, options, ++filterCounter);
			builderCond0.append(joinSchemaResC0);
			builderCond1.append(joinSchemaResC1);
			attrList.add(propertyFieldId);
		}
		String query = builderCond0.toString() + " union " + builderCond1.toString();
		List<Object> idList = appPersistenceServices.executeNativeQuery(query, attrList.toArray());
		String defQuery = idList.isEmpty() ? QUERY_FILTER_BASIC_VALUES : QUERY_FILTER_BY_FIELD_KEYS;
		return appPersistenceServices.executeNamedQuery(defQuery, 
				idList.isEmpty() ? new Object[]{} : new Object[]{idList}, EntityHcFilter.class);
	}

}
