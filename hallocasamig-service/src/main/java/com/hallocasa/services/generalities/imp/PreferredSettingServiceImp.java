package com.hallocasa.services.generalities.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOPreferredSetting;
import com.hallocasa.entities.EntityPreferredSetting;
import com.hallocasa.services.generalities.PreferredSettingService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.PreferredSetting;

/**
 * Service implementation for preferred settings
 * @author Alexander
 */
@Stateless
public class PreferredSettingServiceImp implements PreferredSettingService {

	@EJB
	IDAOPreferredSetting daoPreferredSetting;
	
	@Override
	public List<PreferredSetting> find() {
		return toValueObject(daoPreferredSetting.find());
	}
	
	/**
	 * Transform entity list to vo list
	 * @param entList
	 * @return
	 */
	private List<PreferredSetting> toValueObject(List<EntityPreferredSetting> entList){
		List<PreferredSetting> list = new LinkedList<PreferredSetting>();
		for(EntityPreferredSetting entPreferredSetting : entList){
			list.add((PreferredSetting) HallocasaConvert.toValueObject(entPreferredSetting));
		}
		return list;
	}
}
