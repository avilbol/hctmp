/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.globalapp.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.dataentities.app.Currency;
import com.hallocasa.dataentities.app.UserType;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.globalapp.GlobalAppServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;

/**
 * 
 * @author avillamil
 */
@ManagedBean(name = "globalApp", eager = true)
@ApplicationScoped
@Stateless
public class GlobalAppServicesImpl implements GlobalAppServices {

	List<CountryVO> countries;

	List<UserTypeVO> userTypes;

	List<CurrencyVO> currencies;

	@EJB
	private AppPersistenceServices appPersistenceServices;

	@PostConstruct
	public void initialize() {
	}

	@Override
	public List<CountryVO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryVO> countries) {
		this.countries = countries;
	}

	@Override
	public List<UserTypeVO> getUserTypes() {
		if (userTypes == null) {
			List<UserType> rawUserTypes = appPersistenceServices
					.executeNamedQuery(UserType.QUERY_FIND_ALL, null,
							UserType.class);
			userTypes = ParsersContext.USER_TYPE_VO_PARSER.toValueObjectList(
					rawUserTypes, UserTypeVO.class);
		}
		return userTypes;
	}

	public void setUserTypes(List<UserTypeVO> userTypes) {
		this.userTypes = userTypes;
	}


	public List<CurrencyVO> getCurrencies() {
		if (currencies == null) {
			List<Currency> rawCurrencies = appPersistenceServices
					.executeNamedQuery(UserType.QUERY_FIND_ALL, null,
							Currency.class);
			//userTypes = ParsersContext.USER_TYPE_VO_PARSER.toValueObjectList(
			//		rawUserTypes, UserTypeVO.class);
		}
		return currencies;
	}

	public void currencies(List<CurrencyVO> currencies) {
		this.currencies = currencies;
	}
}
