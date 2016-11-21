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
 * 
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

	private Map<String, Object> conversionExchangeRateList;

	/**
	 * Abbreviation for COP money
	 */
	public static final String COP_ABBR = "COP";

	@EJB
	private CurrencyServices currencyServices;

	@EJB
	private CurrencyExchangeDataServices currencyExchangeDataServices;

	private List<CurrencyVO> currencies;

	@PostConstruct
	public void initialize() {
		USD_MONEY = searchByAb(USD_ABBR);
	}

	public Double exchange(BigDecimal value, CurrencyVO currencyFrom, CurrencyVO currencyTo) {
		return currencyServices.exchange(value, currencyFrom, currencyTo, 
				getConversionExchangeRateList(), getCurrencies());
	}

	/**
	 * Search in the currency list, the item with id specified
	 * 
	 * @param id
	 *            id specified
	 * @return currency with id specified, null if it does not exist
	 */
	public CurrencyVO searchById(Integer id) {
		Map<Integer, CurrencyVO> currencyMap = new HashMap<>();
		for (CurrencyVO cvo : getCurrencies())
			currencyMap.put(cvo.getId(), cvo);
		return currencyMap.get(id);
	}

	/**
	 * Search in the currency list, the item with abbreviation specified
	 * 
	 * @param ab
	 *            abbreviation specified
	 * @return currency with abbreviation specified, null if it does not exist
	 */
	public CurrencyVO searchByAb(String ab) {
		Map<String, CurrencyVO> currencyMap = new HashMap<>();
		for (CurrencyVO cvo : getCurrencies())
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

	public Map<String, Object> getConversionExchangeRateList() {
		Date lastUpdate = currencyExchangeDataServices.findLastUpdate();
		int compareResult = -1;
		if (lastUpdate != null) {
			compareResult = DateTimeComparator.getDateOnlyInstance().compare(lastUpdate, new Date());
		}
		if (compareResult < 0) {
			try {
				conversionExchangeRateList = currencyExchangeDataServices.refreshWithLiveRates();
			} catch (ErrorJsonResponseException e) {
				// TODO: set error here
			}
		}
		if (conversionExchangeRateList == null || conversionExchangeRateList.isEmpty()) {
			conversionExchangeRateList = currencyExchangeDataServices.find();
		}
		return conversionExchangeRateList;
	}

	public void setConversionExchangeRateList(Map<String, Object> conversionExchangeRateList) {
		this.conversionExchangeRateList = conversionExchangeRateList;
	}
}
