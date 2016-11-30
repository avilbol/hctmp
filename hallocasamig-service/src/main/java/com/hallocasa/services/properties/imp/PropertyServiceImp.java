package com.hallocasa.services.properties.imp;

import static com.hallocasa.filemanager.FileManager.cleanFilesStartingWithPrefix;
import static com.hallocasa.filemanager.FileManager.replaceMassive;
import static com.hallocasa.systemproperties.SystemConstants.PROPERTY_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemProperty.get;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toEntity;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.services.hcfilters.filterworkers.FilterWorker;
import com.hallocasa.services.properties.PropertyCommonsService;
import com.hallocasa.services.properties.PropertyService;
import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.utils.resolvers.FilterWorkerOptionRes;
import com.hallocasa.vo.hcfilter.FilterWorkerOption;
import com.hallocasa.vo.hcfilter.PropertyFilterRequest;
import com.hallocasa.vo.hcfilter.properties.Property;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

@Stateless
public class PropertyServiceImp implements PropertyService {

	@EJB
	private IDAOProperty daoProperty;
	
	@EJB
	private PropertyCommonsService propertyCommonsService;
	
	private String filePathRoot = get(PROPERTY_IMAGES_PATH);
	
	private static final Integer BASIC_PROPERTIES_RETURN_NUMBER = 10;
	
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
		if(property.getPublishDate() == null){
			property.setPublishDate(new Date());
		}
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
		List<EntityProperty> entList = daoProperty.findBasicRandom(BASIC_PROPERTIES_RETURN_NUMBER);
		return propertyCommonsService.toValueObjectList(entList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Property> find(PropertyFilterRequest request) {
		boolean sortByLessRecent = request.getSortByLessRecent() != null && request.getSortByLessRecent();
		boolean sortByMostRecent = request.getSortByMostRecent() != null && request.getSortByMostRecent();
		validateRequest(request);
		String query = EntityProperty.QUERY_SEARCH_BY_FILTERS;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		StringBuilder fieldBuilder = new StringBuilder("");
		StringBuilder filterBuilder = new StringBuilder("");
		StringBuilder joinBuilder = new StringBuilder(""); 
		List<String> orderByParamList = new LinkedList<>();
		List<PropertyFilterSubmission> submissionList = request.getFilterList();
		Integer attrNumber = 1;
		for(PropertyFilterSubmission filterSubmission : submissionList){
			FilterWorkerOption fwo = filterSubmission.getPropertyFilter().getFilter().getFilterWorkerOption();
			FilterWorker filterWorker = FilterWorkerOptionRes.getFilterWorker(fwo);
			fieldBuilder.append(", " + filterWorker.loadParametersQuery(filterSubmission));
			joinBuilder.append(filterWorker.loadJoinQuery(filterSubmission, attrNumber));
			filterBuilder.append(filterBuilder.toString().isEmpty() ? "" : " AND ")
				.append(filterWorker.loadWhereQuery(filterSubmission, attrNumber));
			attrNumber = filterWorker.addParams(filterSubmission, paramMap, attrNumber);
		}
		if(sortByLessRecent || sortByMostRecent){
			orderByParamList.add("p.publishDate");
		}
		query = query.replaceAll("%%FIELDS%%", fieldBuilder.toString());
		query = query.replaceAll("%%JOINS%%", joinBuilder.toString());
		query = query.replaceAll("%%FILTERS%%", filterBuilder.toString());
		List<String> filteredIdProperties = daoProperty.findIdentifierListByFilterRequest(query, paramMap, 
				request.getPageFrom(), request.getPageTo());
		List<EntityProperty> filteredProperties = propertyCommonsService.getPropertyListBy(filteredIdProperties, 
				orderByParamList, sortByLessRecent);
		return propertyCommonsService.toValueObjectList(filteredProperties);
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String propertyId) {
		daoProperty.delete(propertyId);
		cleanFilesStartingWithPrefix(filePathRoot, propertyId);
	}

	@Override
	public List<Property> findByUser(Integer userId) {
		List<String> userIdProperties = daoProperty.findByUser(userId);
		List<EntityProperty> userProperties = propertyCommonsService.getPropertyListBy(userIdProperties, 
				new LinkedList<>(), false);
		return propertyCommonsService.toValueObjectList(userProperties);
	}
	
	private void validateRequest(PropertyFilterRequest request){
		boolean sortByLessRecent = request.getSortByLessRecent() != null && request.getSortByLessRecent();
		boolean sortByMostRecent = request.getSortByMostRecent() != null && request.getSortByMostRecent();
		if(sortByMostRecent && sortByLessRecent){
			throw new BadRequestException("Sort by most recent and less recent? really?");
		}
		if(request.getPageFrom() == null || request.getPageTo() == null){
			throw new BadRequestException("You must specify pagination (start and end index)");
		}
		if(request.getPageFrom() > request.getPageTo()){
			throw new BadRequestException("Pagination start index greater than end index? really?");
		}
	}
}
