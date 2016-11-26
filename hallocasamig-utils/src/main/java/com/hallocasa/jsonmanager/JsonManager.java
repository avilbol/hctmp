package com.hallocasa.jsonmanager;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hallocasa.utils.constants.exceptions.FatalException;

/**
 * Manager for specific operations between json and java objects
 * @author Alexander Villamil
 */
public class JsonManager {

	/**
	 * Map the data of the value of a specific property from json response, in a map key-value
	 * @param json
	 * 		Json that must have a object-like property
	 * @param propertyKey
	 * 		Property object-like to search
	 * @return
	 * 		The map with key-value list
	 */
	public static Map<String, Object> fromJson(String json, String propertyKey) {
		try{
			JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
			Type mapType = new TypeToken<Map<String, Object>>(){}.getType();  
			Map<String, Object> son = new Gson().fromJson(obj.get(propertyKey), mapType);
			return son;
		} catch(NullPointerException e){
			throw new FatalException("Cannot deserialize json response from api", e);
		}
	}
	
	public static String loadProperty(String json, String propertyName){
		String[] propertyAccessValues = propertyName.split("\\.");
		JsonElement result = new JsonParser().parse(json);
		for(String propertyAccessValue : propertyAccessValues){
			result = result.getAsJsonObject().get(propertyAccessValue);
		}
		return result.getAsString();
	}
}
