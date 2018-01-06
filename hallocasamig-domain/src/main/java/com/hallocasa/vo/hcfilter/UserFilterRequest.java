package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.City;
import com.hallocasa.vo.Country;
import com.hallocasa.vo.Language;
import com.hallocasa.vo.State;
import com.hallocasa.vo.UserType;
import com.hallocasa.vo.resultrequest.ResultRequest;

public class UserFilterRequest implements Serializable {

	private static final long serialVersionUID = -6665149200769287476L;
	private ResultRequest resultRequest;
	
	private String name;
	private List<Country> countries;
	private List<State> states;
	private List<City> cities;
	private List<UserType> userTypes;
	private List<Language> languages;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<UserType> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<UserType> userTypes) {
		this.userTypes = userTypes;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public ResultRequest getResultRequest() {
		if(resultRequest == null){
			resultRequest = new ResultRequest();
		}
		return resultRequest;
	}

	public void setResultRequest(ResultRequest resultRequest) {
		this.resultRequest = resultRequest;
	}
}
