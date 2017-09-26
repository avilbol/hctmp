package com.hallocasa.utils.constants.parsing.flat;

import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.FOUND_KEY;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.TEXT_KEY;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @param standardLocaleList
	 */
	public FlatUser transform(EntityUser entityUser, List<String> standardLocaleList) {
		FlatUser flatUser = new FlatUser();
		flatUser.setEmail(entityUser.getEmail());
		flatUser.setFirstName(entityUser.getFirstName());
		flatUser.setLastName(entityUser.getLastName());
		flatUser.setUserDescription(processByLocaleAlternates(entityUser.getUserDescriptions(), standardLocaleList));
		flatUser.setImageUrl(entityUser.getImageLink());
		return flatUser;
	}
	
	/**
	 * Establish the adequate user description to show, according to a locale alternates
	 * @param descriptionList
	 * 		the list of candidate user descriptions to show
	 * @param locale alternates
	 * 		the locale alternates tan can match a user description
	 * @return
	 * 		the user description to show (translation - applied)
	 */
	private String processByLocaleAlternates(List<EntityUserDescription> descriptionList, List<String> standardLocaleList) {
		Map<String, String> result = null;
		for(String locale : standardLocaleList){
			result = processByLocale(descriptionList, locale);
			if(Boolean.valueOf(result.get(FOUND_KEY))){
				return result.get(TEXT_KEY);
			}
		}
		return result.get(TEXT_KEY);
	}

	/**
	 * Establish the adequate user description to show, according to a locale
	 * @param descriptionList
	 * 		the list of candidate user descriptions to show
	 * @param locale
	 * 		the locale to use as selector
	 * @return
	 * 		the user description to show (translation - applied) and a flag specifying if the locale match
	 * 		any user description
	 */
	private Map<String,String> processByLocale(List<EntityUserDescription> descriptionList, String locale) {
		Integer selectedIndex = 0;
		Integer currentIndex = 0;
		boolean foundUserDescription = false;
		Integer localeEquivalent = FlatParserConstants.localeEquivalent(locale);
		for(EntityUserDescription userDescription : descriptionList){
			if(userDescription.getLanguage().getId() == localeEquivalent){
				foundUserDescription = true;
				selectedIndex = currentIndex;
			}
			currentIndex++;
		}
		EntityUserDescription userDescription = descriptionList.get(selectedIndex);
		Map<String, String> result = new HashMap<>();
		result.put(TEXT_KEY, userDescription.getValue());
		result.put(FOUND_KEY, String.valueOf(foundUserDescription));
		return result;
	}
	
}
