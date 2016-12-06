package com.hallocasa.services.properties.imp;

import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

import com.hallocasa.dao.i.hcfilter.IDAOHcFilter;
import com.hallocasa.dao.i.properties.IDAOPropertyField;
import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.services.hcfilters.listers.HcLister;
import com.hallocasa.services.properties.PropertyListerService;
import com.hallocasa.utils.resolvers.HcListerOptionRes;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

@Stateless
public class PropertyListerServiceImp implements PropertyListerService {

	@EJB
	IDAOHcFilter daoHcFilter;
	
	@EJB
	IDAOPropertyField daoPropertyField;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DropdownOption> listFieldOptions(Integer fieldId, List<PropertyField> propertyFieldList) {
		Optional<EntityPropertyField> field = daoPropertyField.findById(fieldId);
		if(!field.isPresent()){
			throw new BadRequestException("Invalid property field");
		}
		if(field.get().getHcListerOption() == null){
			throw new BadRequestException("Property field has not listers associated");
		}
		HcLister lister = HcListerOptionRes.getLister(field.get().getHcListerOption());
		return lister.loadFieldOptions(propertyFieldList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DropdownOption> listFilterOptions(Integer filterId,
			List<PropertyFilterSubmission> filterSubmissionList) {
		Optional<EntityHcFilter> filter = daoHcFilter.findById(filterId);
		if(!filter.isPresent()){
			throw new BadRequestException("Invalid property filter");
		}
		if(filter.get().getHcListerOption() == null){
			throw new BadRequestException("Property filter has not listers associated");
		}
		HcLister lister = HcListerOptionRes.getLister(filter.get().getHcListerOption());
		return lister.loadFilterOptions(filterSubmissionList);
	}

}
