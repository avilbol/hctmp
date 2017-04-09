/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.imp;

import static com.hallocasa.systemproperties.SystemConstants.MINI_PROPERTY_IMAGES_PATH;
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

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.filemanager.FileManager;
import com.hallocasa.linkbuilders.UserActivationLinkUtils;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
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
import com.hallocasa.vo.User;
import com.hallocasa.vo.dto.UserListRequest;

/**
 * 
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceImpl implements UserService {

	private String filePathRoot = get(USER_IMAGES_PATH);
	

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);


	@EJB
	private IDAOUser daoUser;
	
	@EJB
	private MailServices mailServices;
	
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
			FileManager.createMinifiedImage(MINI_PROPERTY_IMAGES_PATH, fullFilename, 
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
		Long profileAmmount = daoUser.loadEntityShowableUserCount();
		Long userId = null;
		List<Long> userIdList = null;
		List<EntityUser> entList = new LinkedList<EntityUser>();
		while (counter++ < userListRequest.getUserNumber() && 
				profileAmmount > entList.size()) {
			do {
				userIdList = new LinkedList<>();
				userId = daoUser.fetchRandomUserId(profileAmmount, 
						userListRequest.getExcludeIdList());
			} while (duplicateId(userId, entList));
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
