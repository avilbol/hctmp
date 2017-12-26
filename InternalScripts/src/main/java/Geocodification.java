import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Geocodification {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Geocodification geo = new Geocodification();
		List<String> calls = geo.apiCallList();
		for(String call : calls){
			geo.callApi(call);
		}
		
	}
	
	private void callApi(String call){
		String[] data = call.split(";");
		String cityId = data[0];
		String query = data[1];
		String template = "https://maps.googleapis.com/maps/api/geocode/json?address=%1$s";
		try {
			URL url = new URL(String.format(template, query));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			StringBuilder sb = new StringBuilder();
			while ((output = br.readLine()) != null) {
				System.out.println(output);
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
				String queryTemplate = "UPDATE hallocasaappmig.city SET default_lat_coordinate=%1$s, default_lng_coordinate=%2$s WHERE id=%3$s;";
				String queryFinal = String.format(queryTemplate, lat, lng, cityId);
				System.out.println(queryFinal);
				write("geocod.out", queryFinal);
			}
			else{
				System.out.println("No results for city " + cityId);
			}
			conn.disconnect();
			Thread.sleep(1000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void write(String filename, String text) throws IOException{
		FileWriter fw = new FileWriter(filename, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(text);
		bw.newLine();
		bw.close();
	}

	private List<String> apiCallList() throws FileNotFoundException, IOException{
		List<String> result = new LinkedList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("togeocod.in"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       result.add(line);
		    }
		}
		return result;
	}
}
