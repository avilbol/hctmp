package com.hallocasa.commons.properties.constants;



public enum PropertyFieldIdentifier {

	LANGUAGES(1),
	MARKET_PRICE(5),
	AREA(6),
	STATE(7),
	CITY(8);
	
	Integer id;

	private PropertyFieldIdentifier(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
