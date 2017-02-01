package com.hallocasa.utils.constants.parsing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.EntityUserDescription;
import com.hallocasa.entities.EntityUserLanguage;
import com.hallocasa.entities.composedkeys.EntityUserLanguagePK;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.vo.Language;
import com.hallocasa.vo.User;
import com.hallocasa.vo.UserDescription;
import com.hallocasa.vo.UserLanguage;
import com.hallocasa.vo.i.ValueObject;

public class UserParser extends CustomizedParser {

	@Override
	public void initialize() {

	}

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		User user = (User) vo;
		EntityUser entUser = (EntityUser) ent;
		if (entUser.getUserLanguages() != null) {
			for (EntityUserLanguage entUserLang : entUser.getUserLanguages()) {
				if (entUserLang.getIsMainLanguage()) {
					user.setMainSpokenLanguage((Language) HallocasaConvert.toValueObject(entUserLang.getLanguage()));
				}
			}
		}
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
		User user = (User) vo;
		EntityUser entUser = (EntityUser) ent;
		if(entUser.getUserDescriptions() != null){
			user.setUserDescriptions(new LinkedList<>());
			Map<Integer, EntityUserDescription> userDescMap = new HashMap<Integer, EntityUserDescription>(); 
			for(EntityUserDescription entUserDescription : entUser.getUserDescriptions()){
				userDescMap.put(entUserDescription.getLanguage().getId(), entUserDescription);
			}
			for(EntityUserDescription entUserDescription : userDescMap.values()){
				user.getUserDescriptions().add(
						(UserDescription) HallocasaConvert.toValueObject(entUserDescription));
			}
		}
		if(entUser.getUserLanguages() != null){
			user.setUserLanguages(new LinkedList<>());
			Map<Integer, EntityUserLanguage> userLangMap = new HashMap<Integer, EntityUserLanguage>(); 
			for(EntityUserLanguage entUserLanguage : entUser.getUserLanguages()){
				userLangMap.put(entUserLanguage.getLanguage().getId(), entUserLanguage);
			}
			for(EntityUserLanguage entUserLanguage : userLangMap.values()){
				user.getUserLanguages().add(
						(UserLanguage) HallocasaConvert.toValueObject(entUserLanguage));
			}
		}
		
		boolean mainSpokenLanguage = user.getMainSpokenLanguage() != null;
		if(mainSpokenLanguage && user.getUserLanguages() == null){
			user.setUserLanguages(new LinkedList<>());
			user.getUserLanguages().add(new UserLanguage());
			user.getUserLanguages().get(0).setIsMainLanguage(true);
			user.getUserLanguages().get(0).setLanguage(user.getMainSpokenLanguage());
		}
		if(mainSpokenLanguage && entUser.getUserLanguages() != null){
			for(UserLanguage userLang : user.getUserLanguages()){
				if(userLang.getLanguage().getId()
						.equals(user.getMainSpokenLanguage().getId())){
					userLang.setIsMainLanguage(true);
				}
			}
		}
		overrideUserLanguagesAndDescriptions(user, entUser);
	}

	private void overrideUserLanguagesAndDescriptions(User user, EntityUser entUser) {
		if(user.getUserLanguages() == null){
			return;
		}
		entUser.setUserLanguages(new LinkedList<>());
		entUser.setUserDescriptions(new LinkedList<>());
		for(UserLanguage userLang : user.getUserLanguages()){
			entUser.getUserLanguages().add(loadUserLanguageEntityInstance(
					user.getId(), userLang));
		}
		for(UserDescription userDesc : user.getUserDescriptions()){
			entUser.getUserDescriptions().add(loadUserDescriptionEntityInstance(
					user.getId(), userDesc));
		}
	}

	private EntityUserLanguage loadUserLanguageEntityInstance(Long userId, UserLanguage userLanguage) {
		EntityUserLanguage entUserLang = (EntityUserLanguage) HallocasaConvert.toEntity(userLanguage);
		entUserLang.setUser(new EntityUser());
		entUserLang.getUser().setId(userId);
		entUserLang.setEntityUserLanguagePK(new EntityUserLanguagePK());
		entUserLang.getEntityUserLanguagePK().setLanguageId(entUserLang.getLanguage().getId());
		entUserLang.getEntityUserLanguagePK().setUserId(userId);
		return entUserLang;
	}

	private static EntityUserDescription loadUserDescriptionEntityInstance(Long userId, UserDescription userDescription) {
		EntityUserDescription entUserDesc = (EntityUserDescription) HallocasaConvert.toEntity(userDescription);
		entUserDesc.setUser(new EntityUser());
		entUserDesc.getUser().setId(userId);
		entUserDesc.setEntityUserLanguagePK(new EntityUserLanguagePK());
		entUserDesc.getEntityUserLanguagePK().setLanguageId(entUserDesc.getLanguage().getId());
		entUserDesc.getEntityUserLanguagePK().setUserId(userId);
		return entUserDesc;
	}
}
