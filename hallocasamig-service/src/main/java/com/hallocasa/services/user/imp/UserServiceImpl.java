/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.imp;

import static com.hallocasa.systemproperties.SystemConstants.MINI_USER_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemConstants.USER_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemProperty.get;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avsoft.commons.AvsFileManager;
import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.filemanager.FileManager;
import com.hallocasa.linkbuilders.UserActivationLinkUtils;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.services.messaging.local.MailServices.BuildInMailType;
import com.hallocasa.services.security.SecurityTokenService;
import com.hallocasa.services.user.UserService;
import com.hallocasa.utils.constants.ImageParameters;
import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.utils.constants.exceptions.ServiceException;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.utils.security.CodecUtils;
import com.hallocasa.vo.City;
import com.hallocasa.vo.Country;
import com.hallocasa.vo.Language;
import com.hallocasa.vo.State;
import com.hallocasa.vo.User;
import com.hallocasa.vo.UserType;
import com.hallocasa.vo.dto.UserListRequest;
import com.hallocasa.vo.hcfilter.UserFilterRequest;
import com.hallocasa.vo.hcfilter.UserFilterResult;
import com.hallocasa.vo.resultrequest.ResultRequest;

/**
 * 
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceImpl implements UserService {

	private String filePathRoot = get(USER_IMAGES_PATH);
	private String miniFilePathRoot = get(MINI_USER_IMAGES_PATH);
	

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);


	@EJB
	private IDAOUser daoUser;
	
	@EJB
	private MailServices mailServices;
	
	@EJB
	private MailChimpServices mailchimpServices;
	
	@EJB
	private SecurityTokenService securityTokenService;
	
	@Override
	public User find(String email) {
		Optional<EntityUser> entUser = daoUser.find(email);
		if(!entUser.isPresent()){
			throw new ServiceException("The user with specified email cannot be found",
					HttpStatus.SC_NOT_FOUND);
		}
		return (User) toValueObject(entUser.get());
	}
	
	@Override
	public UserFilterResult find(UserFilterRequest request) {
		ResultRequest resultRequest = request.getResultRequest();
		validateRequest(resultRequest);
		String query = generateQuery(EntityUser.QUERY_SEARCH_BY_FILTERS, request);
		String countQuery = generateQuery(EntityUser.QUERY_COUNT_SEARCH_BY_FILTERS, request);
		resultRequest.setOrderBy(new LinkedList<>());
		boolean sortByLessRecent = resultRequest.getOrderByLessRecent() != null 
				&& resultRequest.getOrderByLessRecent();
		boolean sortByMostRecent = resultRequest.getOrderByMostRecent() != null
				&& resultRequest.getOrderByMostRecent();
		if (sortByLessRecent || sortByMostRecent) {
			resultRequest.getOrderBy().add("register_date");
		}
		request.getResultRequest().setAsc(resultRequest.getOrderByLessRecent());
		List<Long> filteredUserIds = daoUser.findIdentifierListByFilterRequest(query,
				resultRequest);
		if (sortByLessRecent || sortByMostRecent) {
			resultRequest.getOrderBy().set(0, "u.registerDate");
		}
		List<EntityUser> users = daoUser.findByUserIdList(filteredUserIds, resultRequest);
		List<User> resultList = toValueObjectList(users);
		Long count = null;
		if (resultRequest.getLoadCount() != null && resultRequest.getLoadCount()) {
			count = daoUser.findIdentifierCountByFilterRequest(countQuery);
		}
		return new UserFilterResult(count, resultList);
	}
	
	private void validateRequest(ResultRequest resultRequest) {
		boolean sortByLessRecent = resultRequest.getOrderByLessRecent() != null
				&& resultRequest.getOrderByLessRecent();
		boolean sortByMostRecent = resultRequest.getOrderByMostRecent() != null
				&& resultRequest.getOrderByMostRecent();
		if (sortByMostRecent && sortByLessRecent) {
			throw new BadRequestException("Sort by most recent and less recent? really?");
		}
		if (resultRequest.getPageFrom() == null || resultRequest.getPageTo() == null) {
			throw new BadRequestException("You must specify pagination (start and end index)");
		}
		if (resultRequest.getPageFrom() > resultRequest.getPageTo()) {
			throw new BadRequestException("Pagination start index greater than end index? really?");
		}
	}
	
	private String generateQuery(String queryBase, UserFilterRequest request) {
		StringBuilder builder = new StringBuilder(queryBase);
		if(request.getName() != null && !request.getName().trim().isEmpty()){
			String condition = " AND (first_name LIKE '%%1$s%' "
					+ "OR last_name LIKE '%%1$s%' OR web_site LIKE '%%1$s%')";
			builder.append(String.format(condition, request.getName()));
		}
		if(request.getCountries() != null && !request.getCountries().isEmpty()){
			String condition = " AND us.country_id IN (%1$s)";
			List<String> identifiers = new ArrayList<>();
			for(Country country : request.getCountries()){
				identifiers.add(String.valueOf(country.getId()));
			}
			String params = StringUtils.join(identifiers.toArray(), ",");
			builder.append(String.format(condition, params));
		}
		if(request.getStates() != null && !request.getStates().isEmpty()){
			String condition = " AND us.state_id IN (%1$s)";
			List<String> identifiers = new ArrayList<>();
			for(State state : request.getStates()){
				identifiers.add(String.valueOf(state.getId()));
			}
			String params = StringUtils.join(identifiers.toArray(), ",");
			builder.append(String.format(condition, params));
		}
		if(request.getCities() != null && !request.getCities().isEmpty()){
			String condition = " AND us.city_id IN (%1$s)";
			List<String> identifiers = new ArrayList<>();
			for(City city : request.getCities()){
				identifiers.add(String.valueOf(city.getId()));
			}
			String params = StringUtils.join(identifiers.toArray(), ",");
			builder.append(String.format(condition, params));
		}
		if(request.getUserTypes() != null && !request.getUserTypes().isEmpty()){
			String condition = " AND ut.user_type_id IN (%1$s)";
			List<String> identifiers = new ArrayList<>();
			for(UserType userType : request.getUserTypes()){
				identifiers.add(String.valueOf(userType.getId()));
			}
			String params = StringUtils.join(identifiers.toArray(), ",");
			builder.append(String.format(condition, params));
		}
		if(request.getLanguages() != null && !request.getLanguages().isEmpty()){
			String condition = " AND us.language IN (%1$s)";
			List<String> identifiers = new ArrayList<>();
			for(Language language : request.getLanguages()){
				identifiers.add(String.valueOf(language.getId()));
			}
			String params = StringUtils.join(identifiers.toArray(), ",");
			builder.append(String.format(condition, params));
		}
		if(request.getCountries() != null && !request.getCountries().isEmpty()){
			String condition = " AND us.country_id IN (%1$s)";
			List<String> identifiers = new ArrayList<>();
			for(Country country : request.getCountries()){
				identifiers.add(String.valueOf(country.getId()));
			}
			String params = StringUtils.join(identifiers.toArray(), ",");
			builder.append(String.format(condition, params));
		}
		return builder.toString();
	}
	
	
	
	@Override
	public void validate(String email) {
		if(email == null){
			throw new BadRequestException("It is needed an email in order "
					+ "to validate it!");
		}
		Optional<EntityUser> entUser = daoUser.find(email);
		if(entUser.isPresent()){
			throw new SecurityException("The email already exists", 
					SecurityException.EMAIL_ALREADY_EXISTS);
		}
	}
	
	@Override
	public User find(long id) {
		Optional<EntityUser> entUser = daoUser.find(id);
		if(!entUser.isPresent()){
			throw new ServiceException("The user with specified id cannot be found",
					HttpStatus.SC_NOT_FOUND);
		}
		LOG.debug("entity user descriptions size: " + 
				entUser.get().getUserDescriptions().size());
		LOG.debug("entity user languages size: " + 
				entUser.get().getUserLanguages().size());
		LOG.debug("entity user descriptions : " + 
				entUser.get().getUserDescriptions());
		LOG.debug("entity user languages : " + 
				entUser.get().getUserLanguages());
		User user = (User) toValueObject(entUser.get());
		LOG.debug("vo user descriptions size: " + user.getUserDescriptions().size());
		LOG.debug("vo user languages size: " + user.getUserLanguages().size());
		LOG.debug("vo user descriptions: " + user.getUserDescriptions());
		LOG.debug("vo user languages: " + user.getUserLanguages());
		return user;
	}
	
	@Override
	public void save(User user, String token) {
		if(token != null && !(token.split("\\:")[0]).equals(user.getEmail())){
			throw new BadRequestException("Invalid operation, inconsistent token");
		}
		String oldImageLink = user.getImageLink();
		boolean newImage = user.getBase64Image() != null;
		if(newImage){
			String fullFilename = FileManager.createFileFromBase64(filePathRoot, 
					user.getBase64Image(), "user" + user.getId());
			AvsFileManager.createMinifiedImage(miniFilePathRoot, fullFilename, 
					ImageParameters.USER_DEFAULT_MINIFIED_IMG_WIDTH, 
					ImageParameters.USER_DEFAULT_MINIFIED_IMG_HEIGHT);
			String[] parts = fullFilename.split("/");
			user.setImageLink(parts[parts.length - 1]);
		}
		daoUser.save((EntityUser) HallocasaConvert.toEntity(user));
		if(newImage){
			FileManager.cleanFilesStartingWithPrefix(filePathRoot, oldImageLink);
		}
	}
	
	@Override
	public Integer loadUserCount() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> addUsersToShowableList(UserListRequest userListRequest) {
		Integer counter = 0;
		if(userListRequest.getUserNumber() == null){
			throw new BadRequestException("attribute 'userNumber' needed when searching");
		}
		Long count = daoUser.loadEntityShowableUserCount(userListRequest.getExcludeIdList());
		Long userId = null;
		List<Long> userIdList = null;
		List<EntityUser> entList = new LinkedList<EntityUser>();
		while (counter++ < userListRequest.getUserNumber() && 
				count > entList.size()) {
			do {
				userIdList = new LinkedList<>();
				userId = daoUser.fetchRandomUserId(count, 
						userListRequest.getExcludeIdList());
			} while (duplicateId(userId, entList));
			LOG.info("Adding user id: " + userId);
			userIdList.add(userId);
			entList.addAll(daoUser.loadUserListByIdList(userIdList));
		}
		return toValueObjectList(entList);
	}
	
	private boolean duplicateId(Long id, List<EntityUser> userIdList) {
		for (EntityUser item : userIdList) {
			if (item.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	private List<User> toValueObjectList(List<EntityUser> entList) {
		List<User> userList = new LinkedList<>();
		for (EntityUser user : entList) {
			userList.add((User) HallocasaConvert.toValueObject(user));
		}
		return userList;
	}
	
	@Override
	public void register(User user, String urlBase) {
		try{
			validate(user.getEmail());
			if(user.getLanguage() == null){
				throw new BadRequestException("please add the attribute 'language' "
						+ "with the current application language");
			}
			String activationUrl = UserActivationLinkUtils.buildUserActivationUrl(
	                user.getEmail(), user.getId(), user.getLanguage(), urlBase);
	        String activationKey = UserActivationLinkUtils.generateActivationKey(
	                user.getId(), user.getEmail());
			sendActivationLinkEmail(user, activationUrl, activationKey);
			user.setPassword(CodecUtils.encryptPassword(user.getPassword()));
			user.setRegisterDate(new Date());
			user.setConfirmedFlag(false);
			save(user, null);
		} catch(MailServicesErrorException e){
			throw new FatalException("Unexpected error", e);
		}
	}
	
	/* Implementation */
    @Override
    public void activateUser(String email, String activationKey) {
    	if(email == null){
    		throw new BadRequestException("Cannot activate user without email attribute");
    	}
    	if(activationKey == null){
    		throw new BadRequestException("Cannot activate user "
    				+ "without email activation key");
    	}
    	UserActivationLinkUtils.validateActivationKey(email, activationKey);
    	Optional<EntityUser> entityUser = daoUser.find(email);
    	if(!entityUser.isPresent()){
    		throw new FatalException("User not registered in system. "
    				+ "Data probably corrupted");
    	}
    	if(entityUser.get().getConfirmedFlag()){
    		throw new SecurityException("User already activated", 
    				SecurityException.EMAIL_ALREADY_EXISTS);
    	}
    	entityUser.get().setConfirmedFlag(true);
    	daoUser.save(entityUser.get());
    }
	
    @Override
    public void sendActivationLinkEmail(User user, String activationLink,
            String activationKey) throws MailServicesErrorException {
        // sends email
        List<String> emails = new ArrayList<>();
        emails.add(user.getEmail());
        Map<String, String> params = new HashMap<>();
        params.put("USER_ACTIVATION_LINK", activationLink);
        params.put("USER_ACTIVATION_KEY", activationKey);
        mailServices.sendMail(BuildInMailType.USER_ACTIVATION,
                new Locale(user.getLanguage().getLocale()), emails, params);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(String userTokenTokenContent){
    	securityTokenService.delete(userTokenTokenContent);
    }
}
