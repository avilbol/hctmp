package com.hallocasa.model.application;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.joda.time.DateTimeComparator;

import com.hallocasa.commons.exceptions.services.ErrorJsonResponseException;
import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.services.interfaces.CurrencyExchangeDataServices;
import com.hallocasa.services.interfaces.CurrencyServices;

/**
 * Application managed bean for currencies
 * @author Alexander Villamil
 */
@ManagedBean(name = "currencyContext", eager = true)
@ApplicationScoped
public class CurrencyGlobalApplication implements Serializable {
	
	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -8491168013276209768L;

	/**
	 * USD money, used for compute rate exchanges 
	 */
	public static CurrencyVO USD_MONEY = null;
	
	/**
	 * Abbreviation for USD money
	 */
	public static final String USD_ABBR = "USD";
	
	/**
	 * Abbreviation for COP money
	 */
	public static final String COP_ABBR = "COP";
	
	@EJB
	private CurrencyServices currencyServices;

	@EJB
	private CurrencyExchangeDataServices currencyExchangeDataServices;

	private List<CurrencyVO> currencies;
	
	private Map<String, Object> conversionExchangeRateList;
	
	@PostConstruct
	public void initialize() {
		USD_MONEY = searchByAb(USD_ABBR);
	}
	
	public Map<String, Object> getConversionExchangeRateList() {
		Date lastUpdate = currencyExchangeDataServices.findLastUpdate();
		int compareResult = -1;
		if(lastUpdate != null){
			compareResult = DateTimeComparator.getDateOnlyInstance().compare(lastUpdate, new Date());
		}
		if (compareResult < 0) {
			try {
				conversionExchangeRateList = currencyExchangeDataServices.refreshWithLiveRates();
			} catch (ErrorJsonResponseException e) {
				// TODO: set error here
			}
		}
		if (conversionExchangeRateList == null || conversionExchangeRateList.isEmpty()){
			conversionExchangeRateList = currencyExchangeDataServices.find();
		}
		return conversionExchangeRateList;
	}

	public void setConversionExchangeRateList(Map<String, Object> conversionExchangeRateList) {
		this.conversionExchangeRateList = conversionExchangeRateList;
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
		Map<String, Object> conversionExchangeRateList = getConversionExchangeRateList();
		return (Double) conversionExchangeRateList.get(fullAb);
	}
	
	public Double rateExchange(BigDecimal value, CurrencyVO currencyFrom, CurrencyVO currencyTo){
		if(currencyFrom.getId().equals(currencyTo.getId())){
			return value.doubleValue();
		}
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
	public Double getConvertedValue(BigDecimal value, CurrencyVO currencyFrom, CurrencyVO currencyTo){
		if(currencyTo.getAbbreviation().equals("USD")){
			Double rate = getRateExchange(currencyTo, currencyFrom);
			return value.multiply(new BigDecimal(1/rate)).doubleValue();
		}
		Double rate = getRateExchange(currencyFrom, currencyTo);
		if(rate != null){
			return value.multiply(new BigDecimal(rate)).doubleValue();
		}
		Double rateBridge = getRateExchange(USD_MONEY, currencyFrom);
		Double rateFinal = getRateExchange(USD_MONEY, currencyTo);
		BigDecimal intermediaryValue = value.multiply(new BigDecimal(1/rateBridge));
		BigDecimal finalValue = intermediaryValue.multiply(new BigDecimal(rateFinal));
		return finalValue.doubleValue();
	}
	
	/**
	 * Search in the currency list, the item with id specified
	 * @param id
	 * 		id specified
	 * @return
	 * 		currency with id specified, null if it does not exist
	 */
	public CurrencyVO searchById(Integer id){
		Map<Integer, CurrencyVO> currencyMap = new HashMap<>();
		for(CurrencyVO cvo : getCurrencies())
			currencyMap.put(cvo.getId(), cvo);
		return currencyMap.get(id);
	}
	
	/**
	 * Search in the currency list, the item with abbreviation specified
	 * @param ab
	 * 		abbreviation specified
	 * @return
	 * 		currency with abbreviation specified, null if it does not exist
	 */
	public CurrencyVO searchByAb(String ab){
		Map<String, CurrencyVO> currencyMap = new HashMap<>();
		for(CurrencyVO cvo : getCurrencies())
			currencyMap.put(cvo.getAbbreviation(), cvo);
		return currencyMap.get(ab);
	}

	public List<CurrencyVO> getCurrencies() {
		if (currencies == null || currencies.isEmpty()) {
			currencies = currencyServices.find();
		}
		return currencies;
	}

	public void setCurrencies(List<CurrencyVO> currencies) {
		this.currencies = currencies;
	}
}
