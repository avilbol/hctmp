import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Geocodification {
	
	Map<Integer, String> references = new HashMap<>();
	
	private static final String queryLoad = "select \r\n" + 
			"city.id AS id,\r\n" + 
			"concat(country.country_name, ',', state.state_name, ',', city.city_name) as search_text\r\n" + 
			" from \r\n" + 
			"hallocasaappmig.city city,\r\n" + 
			"hallocasaappmig.state state,\r\n" + 
			"hallocasaappmig.country country\r\n" + 
			"where city.state_id = state.id\r\n" + 
			"and state.country_id = country.id\r\n" + 
			"and city.default_lat_coordinate IS NULL";
	
	private static final String queryUpdate = "update hallocasaappmig.city\r\n" + 
			"set default_lat_coordinate = %1$s,\r\n" + 
			"default_lng_coordinate = %2$s,\r\n" + 
			"default_zoom = %3$s\r\n" + 
			"where city.id = %4$s";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try{
			Geocodification geo = new Geocodification();
			geo.setupMap();
			List<Integer> cityIds = null;
			do{
				cityIds = new ArrayList<>(geo.references.keySet());
				for(Integer cityId : cityIds){
					String searchText = geo.references.get(cityId);
					if(geo.callApi(searchText, cityId)){
						System.out.println("SUCCESS " + cityId);
						geo.references.remove(cityId);
					}	
				}
			} while(!cityIds.isEmpty());
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("FATAL " + e);
		} 
	}
	
	
	
	private void setupMap(){
		references = QueryExecutor.consultar(queryLoad);
	}
	
	private boolean callApi(String searchText, Integer cityId){
		searchText = searchText.replaceAll("'", "%27");
		searchText = searchText.replaceAll(" ", "%20");
		searchText = searchText.replaceAll("ö", "o");
		searchText = searchText.replaceAll("á", "a");
		searchText = searchText.replaceAll("é", "e");
		searchText = searchText.replaceAll("í", "i");
		searchText = searchText.replaceAll("ó", "o");
		searchText = searchText.replaceAll("ú", "u");
		searchText = searchText.replaceAll("ü", "u");
		String template = "https://maps.googleapis.com/maps/api/geocode/json?address=%1$s";
		try {
			URL url = new URL(String.format(template, searchText));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			//System.out.println("Output from Server .... \n");
			StringBuilder sb = new StringBuilder();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Gson gson = new GsonBuilder().create();
			JsonObject obj = gson.fromJson(sb.toString(), JsonObject.class);
			if(obj.get("results").getAsJsonArray().size() > 0){
				JsonObject loc = obj.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
				Double lat = loc.get("lat").getAsDouble();
				Double lng = loc.get("lng").getAsDouble();
				String queryToUpdate = String.format(queryUpdate, lat, lng, 5, cityId);
				QueryExecutor.actualizar(queryToUpdate);
			}
			else{
				System.out.println("No results for city or quota exceeded " + cityId);
				return false;
			}
			conn.disconnect();
			Thread.sleep(100);
		} catch(RuntimeException e){
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error " + e);
			return false;
		}
		return true;
	}
}
