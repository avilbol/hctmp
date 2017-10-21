package com.hallocasa.services.generalities.location.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOState;
import com.hallocasa.entities.EntityState;
import com.hallocasa.services.generalities.location.StateService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.State;

@Stateless
public class StateServiceImp implements StateService {

	@EJB
	IDAOState daoState;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<State> findByCountryId(Integer countryId) {
		return toValueObject(daoState.findByCountryId(countryId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<State> findByCountriesId(List<Integer> countryIdList) {
		return toValueObject(daoState.findByCountriesId(countryIdList));
	}
	
	@Override
	public List<State> findByParentId(Integer parentId) {
		return findByCountryId(parentId);
	}

	@Override
	public List<State> findByParentIdList(List<Integer> parentIdList) {
		return findByCountriesId(parentIdList);
	}
	
	private List<State> toValueObject(List<EntityState> entList){
		List<State> stateList = new LinkedList<State>();
		for(EntityState entState : entList){
			stateList.add((State) HallocasaConvert.toValueObject(entState));
		}
		return stateList;
	}
}
