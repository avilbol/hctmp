package com.hallocasa.services;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.dataentities.app.Currency;
import com.hallocasa.dataentities.app.CurrencyExchangeData;
import com.hallocasa.helpers.CurrencyExchangeDataParser;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.interfaces.CurrencyExchangeDataServices;
import com.hallocasa.services.interfaces.CurrencyServices;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;

/**
 * Service implementation for currencies
 * 
 * @author avillamil
 *
 */
@Stateless
public class CurrencyServicesImpl implements CurrencyServices {

	@EJB
	private AppPersistenceServices appPersistenceServices;

	@EJB
	private PropertyServices propertyServices;

	@EJB
	private CurrencyExchangeDataServices currencyExchangeDataServices;

	@Override
	public List<CurrencyVO> find() {
		return ParsersContext.CURRENCY_VO_PARSER.toValueObjectList(
				appPersistenceServices.executeNamedQuery(Currency.QUERY_FIND_ALL, null, Currency.class),
				CurrencyVO.class);
	}

	@Override
	public void persistExchangeData(Map<String, Object> exchangeDataMap) {
		List<CurrencyVO> currencies = find();
		List<CurrencyExchangeData> cedataList = new CurrencyExchangeDataParser()
				.toSimpleList(exchangeDataMap, currencies);
		currencyExchangeDataServices.save(cedataList);
	}

}
