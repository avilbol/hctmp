package com.hallocasa.utils.constants.parsing.flat;

/**
 * Constants to use in flat parsers
 * @author avilbol
 */
public class FlatParserConstants {

	public static final Integer EN_ID = 1;
	public static final Integer ES_ID = 2;
	public static final Integer DE_ID = 3;
	public static final Integer TITLE_PROP_FIELD_ID = 2;
	public static final Integer BASIC_DESCRIPTION_PROP_FIELD_ID = 3;
	public static final Integer LOC_DESCRIPTION_PROP_FIELD_ID = 4;
	public static final Integer MARKET_PRICE_PROP_FIELD_ID = 5;
	public static final Integer IMAGE_PROP_FIELD_ID = 12;
	public static final Integer ROOMS_PROP_FIELD_ID = 17;
	public static final Integer BATHROOMS_PROP_FIELD_ID = 18;
	public static final String CURRENCY_PRES = "%1$s COP"; // TODO: Do dynamic
	
	/**
	 * Locale response map keys
	 */
	public static final String TEXT_KEY = "text-key";
	public static final String FOUND_KEY = "found-key";
	
	/**
	 * Loads equivalent locale id in database according to the locale in string format
	 * @param locale
	 * 		the locale in string format
	 * @return
	 * 		the locale identifier in database
	 */
	public static Integer localeEquivalent(String locale){
		if(locale == null){
			return EN_ID;
		}
		if(locale.equals("es") || locale.equals("ES")){
			return ES_ID;
		}
		if(locale.equals("en") || locale.equals("EN")){
			return EN_ID;
		}
		if(locale.equals("de") || locale.equals("DE")){
			return DE_ID;
		}
		return EN_ID;
	}
}
