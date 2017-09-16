package com.hallocasa.services.generalities.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import com.hallocasa.services.generalities.LocaleNamingService;

/**
 * Implementation of service capable to load locale equivalences
 * @author avilbol
 */
@Stateless
public class LocaleNamingServiceImp implements LocaleNamingService {
	
	/**
	 * The length of a locale string cannot surpase this length
	 */
	private static final Integer POTENTIAL_LOCALE_LENGTH_LIMIT = 5;
	
	/**
	 * The length of a locale string cannot surpase this length
	 */
	private static final Integer POTENTIAL_LOCALE_LENGTH_MIN = 2;
	
	/**
	 * Default locale returned when potential locale entry is not acceptable
	 */
	private static final String DEFAULT_LOCALE_RESOLUTION = "en";
	
	/**
	 * Default locale returned when potential locale entry is not acceptable
	 */
	private static final String[] ALLOWED_LOCALES = {"en","es","de"};
	
	@Override
	public String standardize(String potentialLocale) {
		String entry = potentialLocale;
		if(potentialLocale != null && (potentialLocale.indexOf(',') > 0 || potentialLocale.indexOf(";") > 0)){
			String[] localeArray = potentialLocale.split(";");
			localeArray = localeArray[0].split(",");
			entry = localeArray[0];
		}
		return parseLocale(entry);
	}
	
	@Override
	public String standardize(String customizedLocale, String browserLocale) {
		return standardize(generatedLocale(customizedLocale) ? customizedLocale : browserLocale);
	}
	
	@Override
	public List<String> standardizeMultiple(String customizedLocale, String browserLocale) {
		List<String> standardList = new LinkedList<String>();
		standardList.add(standardize(customizedLocale, browserLocale));
		if(browserLocale != null){
			standardList.add(standardize(browserLocale));
		}
		return standardList;
	}
	
	
	/**
	 * Transforms the locale to suit the standardization
	 * @param rawLocale
	 * 		The string to process
	 * @return
	 * 		The standardized local
	 */
	private String parseLocale(String rawLocale) {
		String candidateLocale = validLocaleStructure(rawLocale) ? rawLocale.toLowerCase().substring(0, 2) : DEFAULT_LOCALE_RESOLUTION;
		return isInAllowedLocales(candidateLocale) ? candidateLocale : DEFAULT_LOCALE_RESOLUTION;
	}
	
	/**
	 * Specifies if the standardization of locale was managed by default or not
	 * @param rawLocale
	 * 		The string to process
	 * @return
	 * 		The standardized local
	 */
	private boolean generatedLocale(String rawLocale) {
		String candidateLocale = validLocaleStructure(rawLocale) ? rawLocale.toLowerCase().substring(0, 2) : null;
		return isInAllowedLocales(candidateLocale);
	}
	
	/**
	 * Determines if locale string allows to a allowed locale set
	 * @param candidateLocale
	 * 		Locale candidate to evaluate
	 * @return
	 * 		True if locale is in the allowed locales, false if not
	 */
	private boolean isInAllowedLocales(String candidateLocale){
		for(String allowedLocale : ALLOWED_LOCALES){
			if(allowedLocale.equals(candidateLocale)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Specifies if string specified counts with a minimum locale string rules
	 * @param locale
	 * 		The locale string to validate
	 * @return
	 * 		True if the string is between 2 and 5 characters of length
	 */
	private boolean validLocaleStructure(String locale){
		boolean invalidString = locale == null || locale.isEmpty() || locale.length() > POTENTIAL_LOCALE_LENGTH_LIMIT;
		return !(invalidString || locale.length() < POTENTIAL_LOCALE_LENGTH_MIN);
	}
}
