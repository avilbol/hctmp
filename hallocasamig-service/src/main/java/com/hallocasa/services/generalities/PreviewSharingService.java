package com.hallocasa.services.generalities;

import java.io.IOException;

/**
 * Service for load previews of site
 * @author Alexander Villamil
 */
public interface PreviewSharingService {

	/**
	 * Generates a preview for homesite
	 * @param locale
	 * @param browserLocale
	 * @return
	 * @throws IOException 
	 */
	public String homePreview(String locale, String browserLang) throws IOException;
	
	
	/**
	 * Preview in html, detailed property by its id
	 * @param id
	 * @param locale
	 * @param browserLocale
	 * @return
	 * @throws IOException 
	 */
	String previewPropertyById(String id, String locale, String browserLang) throws IOException;
	
	/**
	 * Preview in html, detailed profile by its id
	 * @param id
	 * @param locale
	 * @param browserLocale
	 * @return
	 * @throws IOException 
	 */
	String previewProfileById(Long id, String locale, String browserLocale) throws IOException;
}
