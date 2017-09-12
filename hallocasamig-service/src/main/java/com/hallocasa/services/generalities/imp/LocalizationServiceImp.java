package com.hallocasa.services.generalities.imp;

import static com.hallocasa.systemproperties.SystemConstants.LOCALIZATION_UI_AUTHORIZATION_KEY;
import static com.hallocasa.systemproperties.SystemProperty.get;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpStatus;

import com.hallocasa.dao.i.IDAOLocaleEntry;
import com.hallocasa.entities.EntityLocaleEntry;
import com.hallocasa.services.generalities.LocaleNamingService;
import com.hallocasa.services.generalities.LocalizationService;
import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.vo.dto.LocaleEntryDTO;

/**
 * Service for localization utilities 
 * @author avillamil
 */
@Stateless
public class LocalizationServiceImp implements LocalizationService {

	/**
	 * Key for avoid unauthorized access
	 */
	private String localizationUiAuthorizationKey = get(LOCALIZATION_UI_AUTHORIZATION_KEY); 
	
	@EJB
	IDAOLocaleEntry daoLocaleEntry;
	
	@EJB
	LocaleNamingService localeNamingService;
	
	@Override
	public List<LocaleEntryDTO> find() throws IllegalAccessException, InvocationTargetException {
		List<EntityLocaleEntry> entityList = daoLocaleEntry.find();
		Map<String, LocaleEntryDTO> localeEntryMap = new HashMap<>();
		for(EntityLocaleEntry localeEntry : entityList){
			LocaleEntryDTO dtoEntry = pullEntry(localeEntry.getPnemonic(), localeEntryMap);
			copyPropertiesToDTO(dtoEntry, localeEntry);
		}
		return new ArrayList<LocaleEntryDTO>(localeEntryMap.values());
	}

	@Override
	public Map<String, String> find(String locale) {
		performLocaleValidation(locale);
		List<EntityLocaleEntry> entityList = daoLocaleEntry.find(locale);
		Map<String, String> localeEntryMap = new HashMap<>();
		for(EntityLocaleEntry localeEntry : entityList){
			localeEntryMap.put(localeEntry.getPnemonic(), localeEntry.getLangValue());
		}
		return localeEntryMap;
	}
	
	@Override
	public Map<String, String> findByPnemonic(String pnemonic) {
		List<EntityLocaleEntry> entityList = daoLocaleEntry.findByPnemonic(pnemonic);
		Map<String, String> localeEntryMap = new HashMap<>();
		for(EntityLocaleEntry localeEntry : entityList){
			String locale = localeNamingService.standardize(localeEntry.getLocale());
			localeEntryMap.put(locale, localeEntry.getLangValue());
		}
		return localeEntryMap;
	}

	@Override
	public void save(List<LocaleEntryDTO> localeEntryDTOList, String securityKey) 
			throws IllegalArgumentException, IllegalAccessException, 
				InvocationTargetException, NoSuchMethodException {
		performSecurityValidation(securityKey);
		List<EntityLocaleEntry> entryList = new ArrayList<>();
		for(LocaleEntryDTO dtoEntry : localeEntryDTOList){
			entryList.addAll(generateLocaleEntriesFromDTO(dtoEntry));
		}
		daoLocaleEntry.save(entryList);
	}

	@Override
	public void save(Map<String, String> keyValueMap, String locale, String securityKey) {
		performSecurityValidation(securityKey);
		performLocaleValidation(locale);
		List<EntityLocaleEntry> entryList = new ArrayList<>();
		for(String key : keyValueMap.keySet()){
			EntityLocaleEntry localeEntry = new EntityLocaleEntry();
			localeEntry.setPnemonic(key);
			localeEntry.setDescription("");
			localeEntry.setLocale(locale);
			localeEntry.setLangValue(keyValueMap.get(key));
			entryList.add(localeEntry);
		}
		daoLocaleEntry.save(entryList);
	}
	
	@Override
	public void delete(String pnemonic, String securityKey){
		performSecurityValidation(securityKey);
		daoLocaleEntry.delete(pnemonic);
	}
	
	/**
	 * Validate that secutityKey provided is equal to established in system
	 * @param securityKey
	 * 		Security key to validate
	 */
	private void performSecurityValidation(String securityKey){
		if(!localizationUiAuthorizationKey.equals(securityKey)){
			throw new SecurityException("Unauthorized access", HttpStatus.SC_UNAUTHORIZED);
		}
	}
	
	/**
	 * Assure locale is managed in system
	 * @param locale
	 * 		locale to validate
	 */
	private void performLocaleValidation(String locale){
		Map<String, String> inverseLocaleEquivalence = loadInverseLocaleEquivalence();
		if(inverseLocaleEquivalence.get(locale) == null){
			throw new BadRequestException("Locale specified is not managed in system");
		}
	}
	
	/**
	 * Establish a locale equivalence. Used for save in database
	 * @return
	 * 		Locale equivalence for locales allowed by system
	 */
	private Map<String, String> loadLocaleEquivalence(){
		Map<String, String> localeEquivalence = new HashMap<>();
		localeEquivalence.put("esES", "es-ES");
		localeEquivalence.put("enUS", "en-US");
		localeEquivalence.put("deDE", "de-DE");
		return localeEquivalence;
	}
	
	/**
	 * Establish a inverse locale equivalence. Used when query from database
	 * @return
	 * 		Locale equivalence for locales saved in database
	 */
	private Map<String, String> loadInverseLocaleEquivalence(){
		Map<String, String> localeEquivalence = loadLocaleEquivalence();
		Map<String, String> inverseLocaleEquivalence = new HashMap<>();
		for(String key : localeEquivalence.keySet()){
			inverseLocaleEquivalence.put(localeEquivalence.get(key), key);
		}
		return inverseLocaleEquivalence;
	}
	
	/**
	 * Build a instance of LocaleEntryDTO using a existing map
	 * @param pnemonic
	 * 		Pnemonic to build or load
	 * @param dtoMap
	 * 		DtoEntry map of reference
	 * @return
	 * 		The DTO build if not exist, or the DTO retrieved from map elsewhere
	 */
	private LocaleEntryDTO pullEntry(String pnemonic, Map<String, LocaleEntryDTO> dtoMap){
		LocaleEntryDTO candidate = dtoMap.get(pnemonic);
		candidate = candidate == null ? new LocaleEntryDTO(pnemonic) : candidate;
		dtoMap.put(pnemonic, candidate);
		return candidate;
	}
	
	/**
	 * Copy properties of locale entry to locale entry DTO
	 * @param dtoEntry
	 * 		Locale Entry DTO to be modified
	 * @param localeEntry
	 * 		Locale Entry with specific info
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void copyPropertiesToDTO(LocaleEntryDTO dtoEntry, EntityLocaleEntry localeEntry) throws IllegalAccessException, InvocationTargetException{
		Map<String, String> localeEquivalence = loadInverseLocaleEquivalence();
		String description = localeEntry.getDescription();
		String locale = localeEntry.getLocale();
		String langValue = localeEntry.getLangValue();
		dtoEntry.setDescription(description);
		BeanUtils.copyProperty(dtoEntry, localeEquivalence.get(locale), langValue);
	}

	/**
	 * Create the locale entries to persist according to dto locale entry definition
	 * @param dtoEntry
	 * 		Locale entry DTO definition
	 * @return
	 * 		A list with locale entries to persist
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 */
	private List<EntityLocaleEntry> generateLocaleEntriesFromDTO(LocaleEntryDTO dtoEntry) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Field [] attributes =  dtoEntry.getClass().getDeclaredFields();
		List<EntityLocaleEntry> localeEntryList = new ArrayList<EntityLocaleEntry>();
		Map<String, String> localeEquivalence = loadLocaleEquivalence();
		for(Field field : attributes){
			String fieldname = field.getName();
			String localeRep = localeEquivalence.get(fieldname);
			String fieldValue = BeanUtils.getProperty(dtoEntry, fieldname);
			if(localeRep != null && fieldValue == null){
				throw new BadRequestException("Error in the request. Please review that all locale entries "
						+ "contain the locale " + fieldname);
			}
			if(localeRep != null){
				EntityLocaleEntry entityLocaleEntry = new EntityLocaleEntry();
				entityLocaleEntry.setPnemonic(dtoEntry.getPnemonic());
				entityLocaleEntry.setLocale(localeRep);
				entityLocaleEntry.setDescription(dtoEntry.getDescription());
				entityLocaleEntry.setLangValue(fieldValue);
				localeEntryList.add(entityLocaleEntry);
			}
		}
		return localeEntryList;
	}
}
