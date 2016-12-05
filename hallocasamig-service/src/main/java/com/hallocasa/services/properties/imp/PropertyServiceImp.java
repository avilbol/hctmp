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
import com.hallocasa.vo.hcfilter.PropertyFilterResult;
import com.hallocasa.vo.hcfilter.properties.Property;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.resultrequest.ResultRequest;

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
	
	@Override
	public List<Property> addPropertiesToShowableList(Integer propertyNumber) {
		Integer counter = 0;
		if(propertyNumber == null){
			throw new BadRequestException("attribute 'propertyNumber' needed when searching");
		}
		Integer propertyAmmount = daoProperty.loadEntityShowablePropertyCount();
		List<String> propertyIdList = new LinkedList<>();
		String propertyId = null;
		while (counter++ < propertyNumber && 
				propertyAmmount > propertyIdList.size()) {
			do {
				propertyId = daoProperty.fetchRandomPropertyId(propertyAmmount);
			} while (duplicateId(propertyId, propertyIdList));
			propertyIdList.add(propertyId);
		}
		List<EntityProperty> entList = propertyCommonsService
				.getPropertyListBy(propertyIdList, null);
		return propertyCommonsService.toValueObjectList(entList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyFilterResult find(PropertyFilterRequest request) {
		ResultRequest resultRequest = request.getResultRequest();
		boolean sortByLessRecent = sortByLessRecent(resultRequest);
		boolean sortByMostRecent = sortByMostRecent(resultRequest);
		validateRequest(request);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String query = generateQuery(EntityProperty.QUERY_SEARCH_BY_FILTERS, request, paramMap);
		String countQuery = generateQuery(EntityProperty.QUERY_COUNT_SEARCH_BY_FILTERS, request, paramMap);
		resultRequest.setOrderBy(new LinkedList<>());
		if(sortByLessRecent || sortByMostRecent){
			resultRequest.getOrderBy().add("publish_date");
		}
		request.getResultRequest().setAsc(sortByLessRecent);
		List<String> filteredIdProperties = daoProperty.findIdentifierListByFilterRequest(query, 
				paramMap, resultRequest);
		if(sortByLessRecent || sortByMostRecent){
			resultRequest.getOrderBy().set(0, "p.publishDate");
		}
		List<EntityProperty> filteredProperties = propertyCommonsService.getPropertyListBy(filteredIdProperties, 
				resultRequest);
		Long count = null;
		if(resultRequest.getLoadCount() != null && resultRequest.getLoadCount()){
			count = daoProperty.findIdentifierCountByFilterRequest(countQuery, paramMap);
		}
		List<Property> propertyList = propertyCommonsService.toValueObjectList(filteredProperties);
		return new PropertyFilterResult(count, propertyList);
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
		ResultRequest resultRequest = new ResultRequest();
		resultRequest.setAsc(false);
		resultRequest.setOrderBy(new LinkedList<>());
		List<EntityProperty> userProperties = propertyCommonsService.getPropertyListBy(userIdProperties, 
				resultRequest);
		return propertyCommonsService.toValueObjectList(userProperties);
	}
	
	private void validateRequest(PropertyFilterRequest request){
		boolean sortByLessRecent = sortByLessRecent(request.getResultRequest());
		boolean sortByMostRecent = sortByMostRecent(request.getResultRequest());
		if(sortByMostRecent && sortByLessRecent){
			throw new BadRequestException("Sort by most recent and less recent? really?");
		}
		if(request.getResultRequest().getPageFrom() == null || request.getResultRequest().getPageTo() == null){
			throw new BadRequestException("You must specify pagination (start and end index)");
		}
		if(request.getResultRequest().getPageFrom() > request.getResultRequest().getPageTo()){
			throw new BadRequestException("Pagination start index greater than end index? really?");
		}
	}
	
	private boolean sortByLessRecent(ResultRequest request){
		return request.getOrderByLessRecent() != null && request.getOrderByLessRecent();
	}
	
	private boolean sortByMostRecent(ResultRequest request){
		return request.getOrderByMostRecent() != null && request.getOrderByMostRecent();
	}
	
	private String generateQuery(String base, PropertyFilterRequest request, HashMap<String, Object> paramMap){
		StringBuilder fieldBuilder = new StringBuilder("");
		StringBuilder filterBuilder = new StringBuilder("");
		StringBuilder joinBuilder = new StringBuilder(""); 
		Integer attrNumber = 1;
		for(PropertyFilterSubmission filterSubmission : request.getFilterList()){
			FilterWorkerOption fwo = filterSubmission.getPropertyFilter().getFilter().getFilterWorkerOption();
			FilterWorker filterWorker = FilterWorkerOptionRes.getFilterWorker(fwo);
			fieldBuilder.append(", " + filterWorker.loadParametersQuery(filterSubmission, attrNumber));
			joinBuilder.append(filterWorker.loadJoinQuery(filterSubmission, attrNumber));
			filterBuilder.append(filterBuilder.toString().isEmpty() ? "" : " AND ")
				.append(filterWorker.loadWhereQuery(filterSubmission, attrNumber));
			attrNumber = filterWorker.addParams(filterSubmission, paramMap, attrNumber);
		}
		base = base.replaceAll("%%FIELDS%%", fieldBuilder.toString());
		base = base.replaceAll("%%JOINS%%", joinBuilder.toString());
		base = base.replaceAll("%%FILTERS%%", filterBuilder.toString());
		return base;
	}
	
	private boolean duplicateId(String id, List<String> idList) {
		for (String item : idList) {
			if (item.equals(id)) {
				return true;
			}
		}
		return false;
	}
}
