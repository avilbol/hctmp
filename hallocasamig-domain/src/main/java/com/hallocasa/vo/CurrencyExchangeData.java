package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class CurrencyExchangeData implements ValueObject, Serializable {

	private static final long serialVersionUID = -8112418260962409136L;

	private Double USD;

	private Double EUR;

	private Double GBP;

	private Double COP;

	private Double CAD;

	private Double CHF;

	private Double AUD;

	public Double getUSD() {
		return USD;
	}

	public void setUSD(Double uSD) {
		USD = uSD;
	}

	public Double getEUR() {
		return EUR;
	}

	public void setEUR(Double eUR) {
		EUR = eUR;
	}

	public Double getGBP() {
		return GBP;
	}

	public void setGBP(Double gBP) {
		GBP = gBP;
	}

	public Double getCOP() {
		return COP;
	}

	public void setCOP(Double cOP) {
		COP = cOP;
	}

	public Double getCAD() {
		return CAD;
	}

	public void setCAD(Double cAD) {
		CAD = cAD;
	}

	public Double getCHF() {
		return CHF;
	}

	public void setCHF(Double cHF) {
		CHF = cHF;
	}

	public Double getAUD() {
		return AUD;
	}

	public void setAUD(Double aUD) {
		AUD = aUD;
	}
}
