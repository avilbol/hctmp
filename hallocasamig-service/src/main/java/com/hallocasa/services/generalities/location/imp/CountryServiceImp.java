package com.hallocasa.services.generalities.location.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCountry;
import com.hallocasa.entities.EntityCountry;
import com.hallocasa.services.generalities.location.CountryService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.Country;

@Stateless
public class CountryServiceImp implements CountryService {

	@EJB
	IDAOCountry daoCountry;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Country> find() {
		return toValueObject(daoCountry.find());
	}

	private List<Country> toValueObject(List<EntityCountry> entList){
		List<Country> countryList = new LinkedList<Country>();
		for(EntityCountry entCountry : entList){
			countryList.add((Country) HallocasaConvert.toValueObject(entCountry));
		}
		return countryList;
	}
}
