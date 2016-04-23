package com.hallocasa.dataentities.app;

import java.io.Serializable;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;

/**
*
* @author Alexander Villamil
*/
@Entity
@Table(name = "telephone")
@NamedQueries({
	@NamedQuery(name = Telephone.QUERY_FIND_BY_USER_ID,
	           query = "select t from Telephone t where t.user = ?1")
	})
public class Telephone implements Serializable, HallocasaEntity {

	/**
	 * Query to search telephone list associated to an user
	 */
	public static final String QUERY_FIND_BY_USER_ID = "Telephone.findByUserId";
	
	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -691726649595146844L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * Country prefix
	 */
	@JoinColumn(name = "prefix_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private CountryTelephonePrefix countryTelephonePrefix;
	
	@OneToOne(mappedBy = "telephone")
	private User user;
	
	/**
	 * Telephone number
	 */
	@Column(name = "number")
	private String number;
	
	public Telephone() {
		super();
	}

	public Telephone(TelephoneVO tvo){
		if(tvo != null){
			this.setNumber(tvo.getNumber());
			CountryTelephonePrefix ctp = new CountryTelephonePrefix();
			ctp.setId(tvo.getCountryTelephonePrefix().getId().intValue());
			ctp.setName(tvo.getCountryTelephonePrefix().getName());
			ctp.setPrefix(tvo.getCountryTelephonePrefix().getPrefix());
			this.setCountryTelephonePrefix(ctp);
		}
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public CountryTelephonePrefix getCountryTelephonePrefix() {
		return countryTelephonePrefix;
	}

	public void setCountryTelephonePrefix(CountryTelephonePrefix countryTelephonePrefix) {
		this.countryTelephonePrefix = countryTelephonePrefix;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}