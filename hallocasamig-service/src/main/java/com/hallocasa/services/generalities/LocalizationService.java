package com.hallocasa.services.generalities;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.hallocasa.vo.dto.LocaleEntryDTO;

/**
 * Service for provide locale translations
 */
public interface LocalizationService {

	/**
	 * Find the overall locale translation list
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public List<LocaleEntryDTO> find() throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * Build a map key-value locale for specific locale definition language
	 * @param locale
	 * 		The locale to query in order to build map key-value
	 * @return
	 * 		A map key-value with overall locale elements
	 */
	public Map<String, String> find(String locale);

	/**
	 * Merge the locale entry list with the existing previous
	 * @param localeEntryList
	 * 		Locale Entry List to persist - merge
	 * @param securityKey
	 * 		Security key to avoid unauthorized applications use
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 */
	public void save(List<LocaleEntryDTO> localeEntryList, String securityKey) 
			throws IllegalArgumentException, IllegalAccessException, 
			InvocationTargetException, NoSuchMethodException;
	
	/**
	 * Merge the json key-object with the existing previous, using the established
	 * locale
	 * @param keyValueList
	 * 		Key value entry list to merge
	 * @param locale
	 * 		Locale to use for this key value entry list
	 * @param securityKey
	 * 		Security key to avoid unauthorized applications use
	 */
	public void save(Map<String, String> keyValueList, String locale, String securityKey);
	
	
	/**
	 * Delete a local entry with specified pnemonic
	 * @param pnemonic
	 * 		Pnemonic of locale entry to delete
	 * @param securityKey
	 * 		Security key to avoid unauthorized applications use
	 */
	public void delete(String pnemonic, String securityKey);

	/**
	 * Build a map key-value locale for specific pnemonic definition
	 * @param pnemonic
	 * 		The pnemonic to query
	 * @return
	 * 		A map key-value with overall pnemonic element translations
	 */
	public Map<String, String> findByPnemonic(String pnemonic);
}
