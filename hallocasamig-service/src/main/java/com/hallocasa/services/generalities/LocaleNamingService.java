package com.hallocasa.services.generalities;

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
}
