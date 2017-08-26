package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class CurrencyExchangeDataSummary implements ValueObject, Serializable{

	private static final long serialVersionUID = -1077001400697489369L;

	private CurrencyExchangeData USD;
	private CurrencyExchangeData EUR;
	private CurrencyExchangeData GBP;
	private CurrencyExchangeData COP;
	private CurrencyExchangeData CAD;
	private CurrencyExchangeData CHF;
	private CurrencyExchangeData AUD;
	private CurrencyExchangeData MXN;
	private CurrencyExchangeData PAB;
	private CurrencyExchangeData CRC;
	private CurrencyExchangeData ARS;
	private CurrencyExchangeData CLP;
	
	public CurrencyExchangeData getUSD() {
		return USD;
	}

	public void setUSD(CurrencyExchangeData uSD) {
		USD = uSD;
	}

	public CurrencyExchangeData getEUR() {
		return EUR;
	}

	public void setEUR(CurrencyExchangeData eUR) {
		EUR = eUR;
	}

	public CurrencyExchangeData getGBP() {
		return GBP;
	}

	public void setGBP(CurrencyExchangeData gBP) {
		GBP = gBP;
	}

	public CurrencyExchangeData getCOP() {
		return COP;
	}

	public void setCOP(CurrencyExchangeData cOP) {
		COP = cOP;
	}

	public CurrencyExchangeData getCAD() {
		return CAD;
	}

	public void setCAD(CurrencyExchangeData cAD) {
		CAD = cAD;
	}

	public CurrencyExchangeData getCHF() {
		return CHF;
	}

	public void setCHF(CurrencyExchangeData cHF) {
		CHF = cHF;
	}

	public CurrencyExchangeData getAUD() {
		return AUD;
	}

	public void setAUD(CurrencyExchangeData aUD) {
		AUD = aUD;
	}

	public CurrencyExchangeData getMXN() {
		return MXN;
	}

	public void setMXN(CurrencyExchangeData mXN) {
		MXN = mXN;
	}

	public CurrencyExchangeData getPAB() {
		return PAB;
	}

	public void setPAB(CurrencyExchangeData pAB) {
		PAB = pAB;
	}

	public CurrencyExchangeData getCRC() {
		return CRC;
	}

	public void setCRC(CurrencyExchangeData cRC) {
		CRC = cRC;
	}

	public CurrencyExchangeData getARS() {
		return ARS;
	}

	public void setARS(CurrencyExchangeData aRS) {
		ARS = aRS;
	}

	public CurrencyExchangeData getCLP() {
		return CLP;
	}

	public void setCLP(CurrencyExchangeData cLP) {
		CLP = cLP;
	}
}
