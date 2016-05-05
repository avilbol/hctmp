package com.hallocasa.commons.vo;

import java.math.BigDecimal;

public class CurrencyVOAmmount {

	private CurrencyVO currency;
	
	private BigDecimal value;

	public CurrencyVO getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyVO currency) {
		this.currency = currency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
