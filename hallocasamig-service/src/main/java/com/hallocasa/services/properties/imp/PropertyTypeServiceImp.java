package com.hallocasa.services.properties.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyType;
import com.hallocasa.entities.properties.EntityPropertyType;
import com.hallocasa.services.properties.PropertyTypeService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.hcfilter.properties.PropertyType;

/**
 * Service implementation for class {@link PropertyType}
 */
@Stateless
public class PropertyTypeServiceImp implements PropertyTypeService {

	@EJB
	IDAOPropertyType daoPropertyType;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyType> find() {
		List<EntityPropertyType> entList = daoPropertyType.find();
		List<PropertyType> resultList = new LinkedList<>();
		for(EntityPropertyType entPropType : entList){
			resultList.add((PropertyType)HallocasaConvert.toValueObject(entPropType));
		}
		return resultList;
	}
}
