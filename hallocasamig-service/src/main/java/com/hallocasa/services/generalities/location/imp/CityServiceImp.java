package com.hallocasa.services.generalities.location.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCity;
import com.hallocasa.entities.EntityCity;
import com.hallocasa.services.generalities.location.CityService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.City;

@Stateless
public class CityServiceImp implements CityService {

	@EJB
	IDAOCity daoCity;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<City> findByStateId(Integer stateId) {
		return toValueObject(daoCity.findByStateId(stateId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<City> findByStatesId(List<Integer> stateIdList) {
		return toValueObject(daoCity.findByStatesId(stateIdList));
		
	}
	
	private List<City> toValueObject(List<EntityCity> entList){
		List<City> cityList = new LinkedList<City>();
		for(EntityCity entCity : entList){
			cityList.add((City) HallocasaConvert.toValueObject(entCity));
		}
		return cityList;
	}

	@Override
	public List<City> findByParentId(Integer parentId) {
		return findByStateId(parentId);
	}

	@Override
	public List<City> findByParentIdList(List<Integer> parentIdList) {
		return findByStatesId(parentIdList);
	}
}
