package com.hallocasa.services;

import java.math.BigDecimal;
import java.util.HashMap;
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
	
	private Map<String, Object> conversionExchangeRateList;
	
	private List<CurrencyVO> currencies;
	
	/**
	 * Abbreviation for USD money
	 */
	public static final String USD_ABBR = "USD";

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
	
	/**
	 * Get the exchange rate between two currencies
	 * @param currencyFrom
	 * @param currencyTo
	 * @return
	 */
	public Double getRateExchange(CurrencyVO currencyFrom, CurrencyVO currencyTo){
		Map<Integer, CurrencyVO> currencyMap = new HashMap<>();
		for(CurrencyVO cvo : getCurrencies()){
			currencyMap.put(cvo.getId(), cvo);
		}
		Integer idCurrencyFrom = currencyFrom.getId();
		Integer idCurrencyTo = currencyTo.getId();
		String currencyFromAb = currencyMap.get(idCurrencyFrom).getAbbreviation();
		String currencyToAb = currencyMap.get(idCurrencyTo).getAbbreviation();
		String fullAb = currencyFromAb.trim() + currencyToAb.trim();
		return (Double) conversionExchangeRateList.get(fullAb);
	}
	
	public Double exchange(BigDecimal value, CurrencyVO currencyFrom, 
			CurrencyVO currencyTo, Map<String, Object> conversionExchangeRateList,
			List<CurrencyVO> currencies){
		if(currencyFrom.getId().equals(currencyTo.getId())){
			return value.doubleValue();
		}
		this.conversionExchangeRateList = conversionExchangeRateList;
		this.currencies = currencies;
		return getConvertedValue(value, currencyFrom, currencyTo);
	}
	
	/**
	 * Get converted value of a currency value
	 * @param value
	 * 		The value to convert
	 * @param currencyFrom
	 * 		Currency source
	 * @param currencyTo
	 * 		Currency destiny
	 * @return
	 * 		The value expressed in currency destiny
	 */
	private Double getConvertedValue(BigDecimal value, CurrencyVO currencyFrom, CurrencyVO currencyTo){
		CurrencyVO usdMoney = searchByAb(USD_ABBR);
		if(currencyTo.getAbbreviation().equals(USD_ABBR)){
			Double rate = getRateExchange(currencyTo, currencyFrom);
			return value.multiply(new BigDecimal(1/rate)).doubleValue();
		}
		Double rate = getRateExchange(currencyFrom, currencyTo);
		if(rate != null){
			return value.multiply(new BigDecimal(rate)).doubleValue();
		}
		Double rateBridge = getRateExchange(usdMoney, currencyFrom);
		Double rateFinal = getRateExchange(usdMoney, currencyTo);
		BigDecimal intermediaryValue = value.multiply(new BigDecimal(1/rateBridge));
		BigDecimal finalValue = intermediaryValue.multiply(new BigDecimal(rateFinal));
		return finalValue.doubleValue();
	}
	
	public List<CurrencyVO> getCurrencies() {
		if (currencies == null || currencies.isEmpty()) {
			currencies = find();
		}
		return currencies;
	}

	public void setCurrencies(List<CurrencyVO> currencies) {
		this.currencies = currencies;
	}
	
	/**
	 * Search in the currency list, the item with abbreviation specified
	 * @param ab
	 * 		abbreviation specified
	 * @return
	 * 		currency with abbreviation specified, null if it does not exist
	 */
	private CurrencyVO searchByAb(String ab){
		Map<String, CurrencyVO> currencyMap = new HashMap<>();
		for(CurrencyVO cvo : getCurrencies())
			currencyMap.put(cvo.getAbbreviation(), cvo);
		return currencyMap.get(ab);
	}
}
