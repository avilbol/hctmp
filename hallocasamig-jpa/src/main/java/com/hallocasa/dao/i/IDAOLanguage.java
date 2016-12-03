package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.properties.EntityLanguage;
import com.hallocasa.vo.Language;

/**
 * Contract for DAO of class {@link Language}
 */
public interface IDAOLanguage {

	/**
	 * Retrieves the application language list
	 * @return the application language list
	 */
	List<EntityLanguage> find();
	
}