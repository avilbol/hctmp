package com.hallocasa.utils.constants.parsing.flat;

import java.util.List;

import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.EntityUserDescription;
import com.hallocasa.vo.hcfilter.properties.FlatUser;

/**
 * Specific parser to transform a entity profile object to a DTO Flat Profile
 * @author Alexander Villamil
 */
public class FlatProfileParser {

	/**
	 * Executes the transform process using constants attached
	 * @param entityUser
	 * @param locale
	 */
	public FlatUser transform(EntityUser entityUser, String locale) {
		FlatUser flatUser = new FlatUser();
		flatUser.setEmail(entityUser.getEmail());
		flatUser.setFirstName(entityUser.getFirstName());
		flatUser.setLastName(entityUser.getLastName());
		flatUser.setUserDescription(processByLocale(entityUser.getUserDescriptions(), locale));
		flatUser.setImageUrl(entityUser.getImageLink());
		return flatUser;
	}

	/**
	 * Establish the adequate user description to show, according to a locale
	 * @param descriptionList
	 * 		the list of candidate user descriptions to show
	 * @param locale
	 * 		the locale to use as selector
	 * @return
	 * 		the user description to show (translation - applied)
	 */
	private String processByLocale(List<EntityUserDescription> descriptionList, String locale) {
		Integer selectedIndex = 0;
		Integer currentIndex = 0;
		Integer localeEquivalent = FlatParserConstants.localeEquivalent(locale);
		for(EntityUserDescription userDescription : descriptionList){
			if(userDescription.getLanguage().getId() == localeEquivalent){
				selectedIndex = currentIndex;
			}
			currentIndex++;
		}
		EntityUserDescription userDescription = descriptionList.get(selectedIndex);
		return userDescription.getValue();
	}
	
}
