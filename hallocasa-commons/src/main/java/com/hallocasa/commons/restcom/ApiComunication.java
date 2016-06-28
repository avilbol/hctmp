package com.hallocasa.commons.restcom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiComunication {

	/**
	 * Currency Layer URL
	 */
	public static final String CURRENCY_LAYER_API_URL = "http://apilayer.net/api/";
	
	/**
	 * Currency Layer live function
	 */
	public static final String CURRENCY_LAYER_LIVE_FUNC = "live";
	
	 public static String callMethod(String apiUrl, String resource, Map<String, String> data) throws IOException {
		 	StringBuilder urlResult = new StringBuilder();
		 	StringBuilder queryStrResult = new StringBuilder();
		 	for(String key: data.keySet()){
		 		boolean emptyQueryStrList = queryStrResult.toString().isEmpty();
		 		queryStrResult.append(emptyQueryStrList ? "?" : "&");
		 		queryStrResult.append(key);
		 		queryStrResult.append("=");
		 		queryStrResult.append(data.get(key));
		 	}
		 	urlResult.append(apiUrl);
		 	urlResult.append(resource);
		 	urlResult.append(queryStrResult.toString());
		 	URL url = new URL(urlResult.toString());
	        URLConnection connection = url.openConnection();

	        connection.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	        wr.flush();

	        InputStream in = connection.getInputStream();
	        BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));

	        StringBuffer sBuffer = new StringBuffer();
	        String inputLine;
	        while ((inputLine = res.readLine()) != null)
	            sBuffer.append(inputLine);

	        res.close();

	        return sBuffer.toString();
	    }

	
	
	
	public static final String call() throws IOException{
		Map<String, String> data;
		data = new HashMap<>();
		data.put("access_key", "20d1d3c7f07d836de8a9f82a55e33ab8");
		String url = CURRENCY_LAYER_API_URL;
		String response = callMethod(CURRENCY_LAYER_API_URL, "live",  data);
		System.out.println(response);
		JsonObject jo = new JsonObject();
		JsonParser jp = new JsonParser();
		
		return null;
	}
	
}
