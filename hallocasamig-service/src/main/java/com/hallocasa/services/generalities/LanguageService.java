package com.hallocasa.services.generalities;

import java.util.List;

import com.hallocasa.vo.Language;

/**
 * Service for provide languages
 */
public interface LanguageService {

	/**
	 * Find the system languages
	 * @return the languages of system
	 */
	public List<Language> find();
	
}
