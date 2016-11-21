package com.hallocasa.services.properties.imp;

import static com.hallocasa.filemanager.FileManager.cleanFilesStartingWithPrefix;
import static com.hallocasa.filemanager.FileManager.replaceMassive;
import static com.hallocasa.systemproperties.SystemConstants.PROPERTY_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemProperty.get;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toEntity;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.services.properties.PropertyService;
import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.vo.hcfilter.PropertyFilterRequest;
import com.hallocasa.vo.hcfilter.properties.Property;

@Stateless
public class PropertyServiceImp implements PropertyService {

	@EJB
	private IDAOProperty daoProperty;
	
	private String filePathRoot = get(PROPERTY_IMAGES_PATH);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Property property) {
		if(property.getUser() == null || property.getUser().getId() == null){
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
		property.setPublishDate(new Date());
		EntityProperty entityProperty = (EntityProperty) toEntity(property);
		daoProperty.save(entityProperty);
		String propId = entityProperty.getId();
		cleanFilesStartingWithPrefix(filePathRoot, propId);
		replaceMassive(filePathRoot, "new-" + propId, propId);
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
	public List<Property> find(PropertyFilterRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Property> findById(String id) {
		Optional<EntityProperty> entityProperty = daoProperty.findById(id);
		if(!entityProperty.isPresent()){
			return Optional.empty();
		}
		Property property = (Property) toValueObject(entityProperty.get());
		return Optional.of(property);
	}
}
