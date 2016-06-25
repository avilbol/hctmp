package com.hallocasa.dataentities.app;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
* This entity class represents a rate exchange between two currencies
* @author Alexander Villamil
*/
@Entity
@Table(name = "property")
@NamedQueries({
   @NamedQuery(name = CurrencyExchangeData.QUERY_FIND_BY_CURRENCY_FROM,
           query = "select ce from CurrencyExchangeData ce where p.currency_from_id = ?1"),
   @NamedQuery(name = CurrencyExchangeData.QUERY_FIND_ALL,
   		   query = "select ce from CurrencyExchangeData ce"),
   @NamedQuery(name = CurrencyExchangeData.QUERY_LAST_UPDATE,
	   query = "select max(ce.updateDate) from CurrencyExchangeData ce")
})
public class CurrencyExchangeData {

	public static final String QUERY_FIND_BY_CURRENCY_FROM = "CurrencyExchange.Data.QueryFindByCurrencyFrom";
	
	/**
	 * Query to get all entries of @CurrencyExchangeData
	 */
	public static final String QUERY_FIND_ALL = "CurrencyExchange.Data.QueryFindAll";
	
	/**
	 * Query the last update of @CurrencyExchangeData
	 */
	public static final String QUERY_LAST_UPDATE = "CurrencyExchange.Data.QueryLastUpdate";
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name = "currency_from_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Currency currencyFrom;
	
	@JoinColumn(name = "currency_to_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Currency currencyTo;
	
	@Column(name = "rate_exchange")
	private Double rateExchange;

	@Column(name = "update_date")
	private Date updateDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Currency getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public Double getRateExchange() {
		return rateExchange;
	}

	public void setRateExchange(Double rateExchange) {
		this.rateExchange = rateExchange;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
