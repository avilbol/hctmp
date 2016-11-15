package com.hallocasa.services.properties.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyField;
import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.services.properties.PropertyFieldService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
import com.hallocasa.vo.properties.PropertyField;

@Stateless
public class PropertyFieldServiceImp implements PropertyFieldService {

	@EJB
	private IDAOPropertyField daoPropertyField;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyField> getPropertyFieldList(PropertyKey propertyKey) {
		return toValueObject(daoPropertyField.findByPropertyKeys(propertyKey));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyField> getPropertyFieldList() {
		return toValueObject(daoPropertyField.find());
	}
	
	private List<PropertyField> toValueObject(List<EntityPropertyField> entPropertyFieldList){
		List<PropertyField> propertyFieldList = new LinkedList<PropertyField>();
		for(EntityPropertyField entPropertyField : entPropertyFieldList){
			propertyFieldList.add((PropertyField) HallocasaConvert.toValueObject(entPropertyField));
		}
		return propertyFieldList;
	}
}
