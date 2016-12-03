package com.hallocasa.services.properties.imp;

import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.entities.properties.EntityPropertyFieldValue;
import com.hallocasa.services.properties.PropertyCommonsService;
import com.hallocasa.vo.hcfilter.properties.Property;
import com.hallocasa.vo.resultrequest.ResultRequest;

@Stateless
public class PropertyCommonsServiceImp implements PropertyCommonsService {

	@EJB
	IDAOProperty daoProperty;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Property> toValueObjectList(List<EntityProperty> entityList) {
		List<Property> propertyList = new LinkedList<Property>();
		for(EntityProperty entityProperty: entityList){
			propertyList.add((Property) toValueObject(entityProperty));
		}
		return propertyList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityProperty> getPropertyListBy(List<String> propertyIdList, ResultRequest resultRequest) {
		if(propertyIdList.isEmpty()){
			return new LinkedList<EntityProperty>();
		}
		List<EntityProperty> entPropertyList = daoProperty.findByPropertyIdList(propertyIdList, resultRequest);
		List<EntityPropertyFieldValue> entPfvList = daoProperty.findValuesByPropertyIdList(propertyIdList);
		Map<String, EntityProperty> entPropertyMap = new HashMap<String, EntityProperty>();
		for(EntityProperty entityProperty : entPropertyList){
			entPropertyMap.put(entityProperty.getId(), entityProperty);
		}
		for(EntityPropertyFieldValue entityPfv : entPfvList){
			addPropertyFieldValue(entPropertyMap.get(entityPfv.getProperty().getId()), entityPfv);
		}
		return entPropertyList;
	}
	
	private void addPropertyFieldValue(EntityProperty property, EntityPropertyFieldValue entPfv){
		if(property.getFieldValueList() == null){
			property.setFieldValueList(new LinkedList<>());
		}
		property.getFieldValueList().add(entPfv);
	}
}
