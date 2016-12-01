package com.hallocasa.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hallocasa.vo.i.ValueObject;

/**
 * Vo representing ammount of currency value
 */
public class CurrencyAmmount implements ValueObject, Serializable {

	private static final long serialVersionUID = 2359051703496235049L;

	private Currency currency;
	
	private BigDecimal ammount;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getAmmount() {
		return ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}
}
