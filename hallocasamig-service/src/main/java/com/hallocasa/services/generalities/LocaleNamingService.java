package com.hallocasa.services.generalities;

import java.util.List;

/**
 * Service capable of interpret and transform to convenient or adequate locale name
 * @author avilbol
 */
public interface LocaleNamingService {

	/**
	 * Loads locale standard in hc systems, according to locale string entry which is very similar
	 * or equivalent to the standard
	 * @param potentialLocale
	 * 		The locale string entry to standardize
	 * @return
	 * 		The locale standardized
	 */
	String standardize(String potentialLocale);
	
	/**
	 * Loads locale standard in hc systems, depending of the headers and query strings supplied in URL
	 * related to Accept-Language header and/or user specified locale (explicit in URL)
	 * @param customizedLocale
	 * 		User specified locale setted explicitly
	 * @param browserLocale
	 * 		Header of lang provided by browser
	 * @return
	 * 		The locale standardized
	 */
	String standardize(String customizedLocale, String browserLocale);

	/**
	 * Load a list of standardized locale alternates, depending of the headers and query strings supplied in URL
	 * related to Accept-Language header and/or user specified locale (explicit in URL). Have alternates is useful
	 * when a locale is valid and standard, but the property/profile does not have a user/property/location description
	 * in that language locale, so in that case, it must be tried with a locale alternative
	 * @param customizedLocale
	 * 		User specified locale setted explicitly
	 * @param browserLocale
	 * 		Header of lang provider by browser
	 * @return
	 * 		The alternate locales standarized
	 */
	List<String> standardizeMultiple(String customizedLocale, String browserLocale);
}
