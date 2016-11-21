package com.hallocasa.filters.converters;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.services.CurrencyServicesImpl;
import com.hallocasa.services.interfaces.CurrencyServices;

/**
 * Converter to apply in search filtering for currencies
 * 
 * @author Alexander Villamil
 */
public class CurrencyFilterConverter implements FilterConverter {

	@EJB
	private CurrencyServices currencyServices;

	private Map<String, Object> conversionExchangeRateList;

	private List<CurrencyVO> currencies;

	private CurrencyVO moneyTo;

	private String objectProperty;
	
	public CurrencyServices getCurrencyServices() {
		return currencyServices;
	}

	public void setCurrencyServices(CurrencyServices currencyServices) {
		this.currencyServices = currencyServices;
	}

	public List<CurrencyVO> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<CurrencyVO> currencies) {
		this.currencies = currencies;
	}

	public Map<String, Object> getConversionExchangeRateList() {
		return conversionExchangeRateList;
	}

	public void setConversionExchangeRateList(Map<String, Object> conversionExchangeRateList) {
		this.conversionExchangeRateList = conversionExchangeRateList;
	}

	@Override
	public Object transform(Object propertyToTransform, String[] helpers) {
		BigDecimal moneyValueFrom = new BigDecimal((String) propertyToTransform);
		CurrencyVO moneyFrom = searchById(Integer.parseInt(helpers[0]));
		currencyServices = new CurrencyServicesImpl();
		return currencyServices.exchange(moneyValueFrom, moneyFrom, moneyTo, 
				conversionExchangeRateList, currencies).toString();
	}

	/**
	 * Search in the currency list, the item with abbreviation specified
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

	public CurrencyVO getMoneyTo() {
		return moneyTo;
	}

	public void setMoneyTo(CurrencyVO moneyTo) {
		this.moneyTo = moneyTo;
	}

	public String getObjectProperty() {
		return objectProperty;
	}

	public void setObjectProperty(String objectProperty) {
		this.objectProperty = objectProperty;
	}
}
