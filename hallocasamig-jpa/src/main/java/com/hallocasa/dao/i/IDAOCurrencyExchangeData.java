package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityCurrencyExchangeData;

public interface IDAOCurrencyExchangeData {

	public List<EntityCurrencyExchangeData> find();
	
	public void save(List<EntityCurrencyExchangeData> exchangeList);
	
	public void clean();
	
	public boolean isTodayUpdated();
}
