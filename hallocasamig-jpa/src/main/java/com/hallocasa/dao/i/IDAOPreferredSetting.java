package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityPreferredSetting;

/**
 * DAO contract for access to country preferred settings
 * @author Alexander
 */
public interface IDAOPreferredSetting {

	/**
	 * Returns the overall preferred country settings in system
	 * @return
	 * 		A list with all preferred settings
	 */
	public List<EntityPreferredSetting> find();
}
