package com.hallocasa.services.generalities;

import java.util.List;

import com.hallocasa.vo.PreferredSetting;

/**
 * Service for preferred settings
 */
public interface PreferredSettingService {

	/**
	 * Find the overall preferred settings
	 */
	public List<PreferredSetting> find();
}
