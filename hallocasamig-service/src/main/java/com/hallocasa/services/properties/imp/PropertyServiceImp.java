package com.hallocasa.services.properties.imp;

import static com.hallocasa.filemanager.FileManager.cleanFilesStartingWithPrefix;
import static com.hallocasa.filemanager.FileManager.replaceMassive;
import static com.hallocasa.systemproperties.SystemConstants.PROPERTY_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemConstants.MINI_PROPERTY_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemProperty.get;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toEntity;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.BOOLEAN;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.DATE;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.DATETIME;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.DOUBLE;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.FILE;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.INT;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.SAME;
import static com.hallocasa.vo.hcfilter.properties.PropertyDatatype.TEXT;
import static java.lang.String.format;

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
import com.hallocasa.vo.hcfilter.properties.PropertyDatatype;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValue;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.properties.PropertyField;
import com.hallocasa.vo.resultrequest.ResultRequest;

@Stateless
public class PropertyServiceImp implements PropertyService {

	@EJB
	private IDAOProperty daoProperty;

	@EJB
	private PropertyCommonsService propertyCommonsService;

	private String propertyImagesPath = get(PROPERTY_IMAGES_PATH);
	private String minifiedPropertyImagesPath = get(MINI_PROPERTY_IMAGES_PATH);

	private static final Integer BASIC_PROPERTIES_RETURN_NUMBER = 10;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Property property, String oAuthToken) {
		if (property.getUser() == null || property.getUser().getId() == null) {
			throw new BadRequestException("User not specified in property");
		}
		if (!(oAuthToken.split("\\:")[0]).equals(property.getUser().getEmail())) {
			throw new BadRequestException("Invalid operation, inconsistent token");
		}
		if (property.getPropertyKey() == null) {
			throw new BadRequestException("Property key not specified in property");
		}
		if (property.getPropertyKey().getPropertyType() == null) {
			throw new BadRequestException("Property type not specified in property");
		}
		if (property.getPropertyKey().getPropertyLocation() == null) {
			throw new BadRequestException("Property location not specified in property");
		}
		if (property.getPropertyKey().getPropertyProposal() == null) {
			throw new BadRequestException("Property proposal not specified in property");
		}
		if (property.getPropertyKey().getCountry() == null) {
			throw new BadRequestException("Country not specified in property");
		}
		if (property.getFieldList() == null) {
			throw new BadRequestException("Field list not specified in property");
		}
		if (property.getFieldList().isEmpty()) {
			throw new BadRequestException("Field list empty in property");
		}
		if (property.getPublishDate() == null) {
			property.setPublishDate(new Date());
		}
		for(PropertyField pfield : property.getFieldList()){
			validatePropertyField(pfield);
		}
		EntityProperty entityProperty = (EntityProperty) toEntity(property);
		daoProperty.save(entityProperty);
		assureImageFileSystem(entityProperty.getId());
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
	public List<Property> loadRandomPropertyList(Integer propertyNumber) {
		Integer counter = 0;
		if (propertyNumber == null) {
			throw new BadRequestException("attribute 'propertyNumber' needed when searching");
		}
		Long propertyAmmount = daoProperty.loadEntityShowablePropertyCount();
		List<String> propertyIdList = new LinkedList<>();
		List<EntityProperty> entList = new LinkedList<>();
		String propertyId = null;
		while (counter++ < propertyNumber && propertyAmmount > entList.size()) {
			do {
				propertyIdList = new LinkedList<>();
				propertyId = daoProperty.fetchRandomPropertyId(propertyAmmount);
			} while (duplicateId(propertyId, entList));
			propertyIdList.add(propertyId);
			entList.addAll(propertyCommonsService.getPropertyListBy(propertyIdList, null));
		}
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
		if (sortByLessRecent || sortByMostRecent) {
			resultRequest.getOrderBy().add("publish_date");
		}
		request.getResultRequest().setAsc(sortByLessRecent);
		List<String> filteredIdProperties = daoProperty.findIdentifierListByFilterRequest(query, paramMap,
				resultRequest);
		if (sortByLessRecent || sortByMostRecent) {
			resultRequest.getOrderBy().set(0, "p.publishDate");
		}
		List<EntityProperty> filteredProperties = propertyCommonsService.getPropertyListBy(filteredIdProperties,
				resultRequest);
		Long count = null;
		if (resultRequest.getLoadCount() != null && resultRequest.getLoadCount()) {
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
		if (!entityProperty.isPresent()) {
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
		cleanFilesStartingWithPrefix(propertyImagesPath, propertyId);
	}

	@Override
	public List<Property> findByUser(Integer userId) {
		List<String> userIdProperties = daoProperty.findByUser(userId);
		ResultRequest resultRequest = new ResultRequest();
		resultRequest.setAsc(false);
		resultRequest.setOrderBy(new LinkedList<>());
		List<EntityProperty> userProperties = propertyCommonsService.getPropertyListBy(userIdProperties, resultRequest);
		return propertyCommonsService.toValueObjectList(userProperties);
	}

	private void validateRequest(PropertyFilterRequest request) {
		boolean sortByLessRecent = sortByLessRecent(request.getResultRequest());
		boolean sortByMostRecent = sortByMostRecent(request.getResultRequest());
		if (sortByMostRecent && sortByLessRecent) {
			throw new BadRequestException("Sort by most recent and less recent? really?");
		}
		if (request.getResultRequest().getPageFrom() == null || request.getResultRequest().getPageTo() == null) {
			throw new BadRequestException("You must specify pagination (start and end index)");
		}
		if (request.getResultRequest().getPageFrom() > request.getResultRequest().getPageTo()) {
			throw new BadRequestException("Pagination start index greater than end index? really?");
		}
	}

	private boolean sortByLessRecent(ResultRequest request) {
		return request.getOrderByLessRecent() != null && request.getOrderByLessRecent();
	}

	private boolean sortByMostRecent(ResultRequest request) {
		return request.getOrderByMostRecent() != null && request.getOrderByMostRecent();
	}

	private String generateQuery(String base, PropertyFilterRequest request, HashMap<String, Object> paramMap) {
		StringBuilder fieldBuilder = new StringBuilder("");
		StringBuilder filterBuilder = new StringBuilder("");
		StringBuilder joinBuilder = new StringBuilder("");
		Integer attrNumber = 1;
		for (PropertyFilterSubmission filterSubmission : request.getFilterList()) {
			FilterWorkerOption fwo = filterSubmission.getPropertyFilter().getFilter().getFilterWorkerOption();
			FilterWorker filterWorker = FilterWorkerOptionRes.getFilterWorker(fwo);
			fieldBuilder.append(", " + filterWorker.loadParametersQuery(filterSubmission, attrNumber));
			joinBuilder.append(filterWorker.loadJoinQuery(filterSubmission, attrNumber));
			filterBuilder.append(filterBuilder.toString().isEmpty() ? " WHERE" : " AND ")
					.append(filterWorker.loadWhereQuery(filterSubmission, attrNumber));
			attrNumber = filterWorker.addParams(filterSubmission, paramMap, attrNumber);
		}
		base = base.replaceAll("%%FIELDS%%", fieldBuilder.toString());
		base = base.replaceAll("%%JOINS%%", joinBuilder.toString());
		base = base.replaceAll("%%FILTERS%%", filterBuilder.toString());
		return base;
	}

	private void validatePropertyField(PropertyField propertyField) {
		String err = "Property field id: " + propertyField.getId() + ", type to review: %1$s";
		for (PropertyFieldValue pfValue : propertyField.getFieldValueList()) {
			if (!isDataTypeGeneric(propertyField)) {
				matchType(pfValue.getData1(), propertyField.getData1Type(), format(err, "data1"));
				matchType(pfValue.getData2(), propertyField.getData2Type(), format(err, "data2"));
				matchType(pfValue.getData3(), propertyField.getData3Type(), format(err, "data3"));
				matchType(pfValue.getText(), propertyField.getTextType(), format(err, "text"));
			}
			if (isDataTypeGeneric(propertyField) && pfValue.getIdentifier() == null) {
				throw new BadRequestException(String.format("Error validating data types matching: " 
						+ "please review " +  format(err, "identifier")));
			}
		}
	}

	private boolean isDataTypeGeneric(PropertyField propertyField) {
		boolean same = true;
		same = same && propertyField.getData1Type().equals(SAME);
		same = same && propertyField.getData2Type().equals(SAME);
		same = same && propertyField.getData3Type().equals(SAME);
		same = same && propertyField.getTextType().equals(SAME);
		return same;
	}

	private void matchType(PropertyFieldValueSpec pfValueSpec, PropertyDatatype propertyDataType, String dataStr) {
		boolean valid = false;
		if(propertyDataType.equals(SAME) || pfValueSpec != null){
			valid = valid || (propertyDataType.equals(BOOLEAN) && pfValueSpec.getBoolVal() != null);
			valid = valid || (propertyDataType.equals(DATE) && pfValueSpec.getDateVal() != null);
			valid = valid || (propertyDataType.equals(DATETIME) && pfValueSpec.getDateVal() != null);
			valid = valid || (propertyDataType.equals(DOUBLE) && pfValueSpec.getDoubleVal() != null);
			valid = valid || (propertyDataType.equals(INT) && pfValueSpec.getIntVal() != null);
			valid = valid || (propertyDataType.equals(DOUBLE) && pfValueSpec.getDoubleVal() != null);
			valid = valid || (propertyDataType.equals(FILE) && pfValueSpec.getStrVal() != null);
			valid = valid || (propertyDataType.equals(TEXT) && pfValueSpec.getStrVal() != null);
			valid = valid || propertyDataType.equals(SAME);
		}
		if (!valid) {
			throw new BadRequestException("Error validating data types matching: " + "please review " + dataStr);
		}
	}

	private boolean duplicateId(String id, List<EntityProperty> propertyList) {
		for (EntityProperty item : propertyList) {
			if (item.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	private void assureImageFileSystem(String propId){
		cleanFilesStartingWithPrefix(propertyImagesPath, propId);
		cleanFilesStartingWithPrefix(minifiedPropertyImagesPath, propId);
		replaceMassive(propertyImagesPath, "new-" + propId, propId);
		replaceMassive(minifiedPropertyImagesPath, "new-" + propId, propId);
	}
}
