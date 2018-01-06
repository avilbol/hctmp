package com.hallocasa.services.properties.imp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyType;
import com.hallocasa.entities.properties.EntityPropertyType;
import com.hallocasa.services.properties.PropertyTypeService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.hcfilter.properties.PropertyType;
import com.hallocasa.vo.hcfilter.properties.dto.PropertyTypeGroupDTO;

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
		for (EntityPropertyType entPropType : entList) {
			resultList.add((PropertyType) HallocasaConvert.toValueObject(entPropType));
		}
		return resultList;
	}
	
	private List<PropertyType> findByGroupIds(List<Integer> groupIds) {
		List<PropertyType> allPropertyTypes = find();
		List<PropertyType> filteredPropertyTypes = new LinkedList<>();
		for(PropertyType propertyType : allPropertyTypes){
			for(Integer groupId : groupIds){
				if(propertyType.getGroup().getId().equals(groupId)){
					filteredPropertyTypes.add(propertyType);
				}
			}
		}
		return filteredPropertyTypes;
	}

	@Override
	public List<PropertyTypeGroupDTO> findGroups() {
		List<PropertyType> propertyTypes = find();
		Map<Integer, PropertyTypeGroupDTO> map = new HashMap<>();
		for(PropertyType propertyType : propertyTypes){
			Integer groupId = propertyType.getGroup().getId();
			if(map.get(groupId) == null){
				map.put(groupId, new PropertyTypeGroupDTO(propertyType.getGroup()));
			}
			map.get(groupId).getPropertyTypeList().add(propertyType);
		}
		return new LinkedList<PropertyTypeGroupDTO>(map.values());
	}

	@Override
	public List<PropertyType> findByParentId(Integer parentId) {
		return findByGroupIds(Arrays.asList(parentId));
	}

	@Override
	public List<PropertyType> findByParentIdList(List<Integer> parentIdList) {
		return findByGroupIds(parentIdList);
	}
}
