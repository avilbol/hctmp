package com.hallocasa.services.generalities.location.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCountryTelephonePrefix;
import com.hallocasa.entities.EntityCountryTelephonePrefix;
import com.hallocasa.services.generalities.location.CountryTelephonePrefixService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.CountryTelephonePrefix;

@Stateless
public class CountryTelephonePrefixServiceImp implements CountryTelephonePrefixService {

	@EJB
	IDAOCountryTelephonePrefix daoCountryTelephonePrefix;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CountryTelephonePrefix> find() {
		return toValueObject(daoCountryTelephonePrefix.find());
	}

	private List<CountryTelephonePrefix> toValueObject(List<EntityCountryTelephonePrefix> entList){
		List<CountryTelephonePrefix> countryTelephonePrefixList = new LinkedList<CountryTelephonePrefix>();
		for(EntityCountryTelephonePrefix entCountryTelephonePrefix : entList){
			countryTelephonePrefixList.add((CountryTelephonePrefix) HallocasaConvert.toValueObject(entCountryTelephonePrefix));
		}
		return countryTelephonePrefixList;
	}
}
