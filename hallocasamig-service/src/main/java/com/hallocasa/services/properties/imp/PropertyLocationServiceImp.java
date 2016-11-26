package com.hallocasa.services.properties.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyLocation;
import com.hallocasa.entities.properties.EntityPropertyLocation;
import com.hallocasa.services.properties.PropertyLocationService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.hcfilter.properties.PropertyLocation;

/**
 * Service implementation for class {@link PropertyLocation}
 */
@Stateless
public class PropertyLocationServiceImp implements PropertyLocationService {

	@EJB
	IDAOPropertyLocation daoPropertyLocation;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyLocation> find() {
		List<EntityPropertyLocation> entList = daoPropertyLocation.find();
		List<PropertyLocation> resultList = new LinkedList<>();
		for(EntityPropertyLocation entPropLocation : entList){
			resultList.add((PropertyLocation)HallocasaConvert.toValueObject(entPropLocation));
		}
		return resultList;
	}
}
