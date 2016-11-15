package com.hallocasa.services.properties.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.services.properties.PropertyService;
import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.vo.hcfilter.HcRequest;
import com.hallocasa.vo.hcfilter.properties.Property;

@Stateless
public class PropertyServiceImp implements PropertyService {

	@EJB
	private IDAOProperty daoProperty;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Property property) {
		if(property.getUser() == null){
			throw new BadRequestException("User not specified in property");
		}
		if(property.getPropertyKey() == null){
			throw new BadRequestException("Property key not specified in property");
		}
		if(property.getPropertyKey().getPropertyType() == null){
			throw new BadRequestException("Property type not specified in property");
		}
		if(property.getPropertyKey().getPropertyLocation() == null){
			throw new BadRequestException("Property location not specified in property");
		}
		if(property.getPropertyKey().getPropertyProposal() == null){
			throw new BadRequestException("Property proposal not specified in property");
		}
		if(property.getPropertyKey().getCountry() == null){
			throw new BadRequestException("Country not specified in property");
		}
		if(property.getFieldList() == null){
			throw new BadRequestException("Field list not specified in property");
		}
		if(property.getFieldList().isEmpty()){
			throw new BadRequestException("Field list empty in property");
		}
		if(property.getId() != null){
			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Property> findBasic() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Property> findBasic(HcRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Property findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
