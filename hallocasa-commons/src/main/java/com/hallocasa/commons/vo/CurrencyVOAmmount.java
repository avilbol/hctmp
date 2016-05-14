package com.hallocasa.commons.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CurrencyVOAmmount implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -8419397187231387687L;

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
