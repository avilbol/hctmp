package com.hallocasa.dao.i;

import java.util.List;
import java.util.Map;

import com.hallocasa.entities.EntityLocaleEntry;

/**
 * DAO for manipulate locale entries and definitions
 * @author avilbol
 */
public interface IDAOLocaleEntry {

	/**
	 * Returns the overall locale entries in system
	 * @return
	 * 		A list with locale entries managed in system for all locales
	 */
	public List<EntityLocaleEntry> find();
	
	/**
	 * Returns the overall locale entries by specific locale 
	 * @param locale
	 * 		The locale filter
	 * @return
	 * 		The locale entries with locale specified
	 */
	public List<EntityLocaleEntry> find(String locale);

	/**
	 * Merge a list of locale entries with the previous existing
	 * @param localeEntryList
	 * 		List to merge with previous entries
	 */
	public void save(List<EntityLocaleEntry> localeEntryList);
	
	/**
	 * Delete a entry with a specified pnemonic
	 * @param pnemonic
	 * 		pnemonic (alias key) of entry to delete
	 */
	public void delete(String pnemonic);
}
