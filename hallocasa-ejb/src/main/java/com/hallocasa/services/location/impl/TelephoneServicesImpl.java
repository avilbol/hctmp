package com.hallocasa.services.location.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.commons.vo.CountryTelephonePrefix;
import com.hallocasa.dataentities.app.CountryTelephonePrefix;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.location.local.TelephoneServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;

/**
 * Service to manage telephone data and prefixes
 */
@Stateless
public class TelephoneServicesImpl implements TelephoneServices {

	@EJB
	AppPersistenceServices persistenceServices;

	public TelephoneServicesImpl() {
		super();
	}

	@Override
	public List<CountryTelephonePrefix> getCountryPrefixList() {
		List<CountryTelephonePrefix> countries = persistenceServices
				.executeNamedQuery(CountryTelephonePrefix.QUERY_FIND_ALL, null, CountryTelephonePrefix.class);

		List<CountryTelephonePrefix> result = ParsersContext.COUNTRY_TELEPHONE_PREFIX_VO_PARSER
				.toValueObjectList(countries, CountryTelephonePrefix.class);
		return result;
	}

}
