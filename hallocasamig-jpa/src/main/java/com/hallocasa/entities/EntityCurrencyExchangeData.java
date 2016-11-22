package com.hallocasa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hallocasa.entities.i.HallocasaEntity;

/**
* This entity class represents a rate exchange between two currencies
* @author avillamil
*/
@Entity
@Table(name = "currency_exchange_data")
@NamedQueries({
   @NamedQuery(name = EntityCurrencyExchangeData.QUERY_FIND_ALL,
   		   query = "select ce from EntityCurrencyExchangeData ce"),
   @NamedQuery(name = EntityCurrencyExchangeData.QUERY_DELETE_ALL,
	   	   query = "delete from EntityCurrencyExchangeData"),
   @NamedQuery(name = EntityCurrencyExchangeData.QUERY_FIND_RATE_EXCHANGE,
   		   query = "select ce.rateExchange from EntityCurrencyExchangeData ce where ce.currencyFrom.id = ?1 "
   		   		+ "AND ce.currencyTo.id = ?2")
})
public class EntityCurrencyExchangeData implements HallocasaEntity {
	
	/**
	 * Query to get all entries of @CurrencyExchangeData
	 */
	public static final String QUERY_FIND_ALL = "CurrencyExchange.Data.QueryFindAll";
	
	/**
	 * Query to delete all entries of @CurrencyExchangeData
	 */
	public static final String QUERY_DELETE_ALL = "CurrencyExchange.Data.QueryDeleteAll";
	
	/**
	 * Query to find rate exchange by currencies identifiers
	 */
	public static final String QUERY_FIND_RATE_EXCHANGE = "CurrencyExchange.Data.QueryFindRateExchange";
	
	/**
	 * Native query the last update of @CurrencyExchangeData
	 */
	public static final String QUERY_LAST_UPDATE = "SELECT MAX(ce.updateDate) FROM EntityCurrencyExchangeData ce";

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name = "currency_from_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityCurrency currencyFrom;
	
	@JoinColumn(name = "currency_to_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityCurrency currencyTo;
	
	@Column(name = "rate_exchange")
	private Double rateExchange;

	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	private Date updateDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntityCurrency getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(EntityCurrency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public EntityCurrency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(EntityCurrency currencyTo) {
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
