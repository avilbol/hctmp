package com.hallocasa.services.generalities;

import java.util.List;

import com.hallocasa.vo.Language;

/**
 * Service for provide languages
 */
public interface LanguageService {

	/**
	 * Find the overall language list
	 */
	public List<Language> find();

	/**
	 * Find the system language list
	 */
	public List<Language> findSystem();
}
