package com.hallocasa.services.currencies.imp;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCurrency;
import com.hallocasa.entities.EntityCurrency;
import com.hallocasa.services.currencies.CurrencyService;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.Currency;

/**
 * Implementation of services for currencies
 */
@Stateless
public class CurrencyServiceImp implements CurrencyService {

	@EJB
	IDAOCurrency daoCurrency;
	
	@Override
	public List<Currency> find() {
		List<EntityCurrency> crcyList = daoCurrency.find();
		List<Currency> resultList = new LinkedList<>();
		for(EntityCurrency entCrcy : crcyList){
			resultList.add((Currency) HallocasaConvert.toValueObject(entCrcy));
		}
		return resultList;
	}

}
