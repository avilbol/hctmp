import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LocationCreator {

	static Map<String, City> oldCitiesMap = new HashMap<String, City>();

	static Map<String, State> statesMap = new HashMap<String, State>();
	static Map<String, City> citiesMap = new HashMap<String, City>();
	static List<Neighborhood> neighborhoodsList = new ArrayList<Neighborhood>();
	
	static int stateConsecutive = 151;
	static int cityConsecutive = 3348;
	static int neighboorhoodConsecutive = 9077;

	public static void main(String[] args) {
		//run(1, "Colombia", 2242);
		//run(2, "Argentina", 4250);
		//run(3, "Chile", 347);
		//run(4, "Panama", 577);
		//run(5, "Peru", 1025);
		//run(8, "Costa Rica", 343);
		//run(9, "Ecuador", 545);
		//run(6, "Canada", 2678);
		run(7, "USA", 42202);
		//run(10, "Mexico", 2242);
	}

	private static void run(int countryId, String countryName, Integer rows) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("oldcities.xlsx"));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;

			for (int r = 0; r < 7123; r++) {
				row = sheet.getRow(r);
				if(row == null){
					System.out.println("stop");
				}
				String e0 = row.getCell(0).getStringCellValue();
				String e1 = row.getCell(1).getStringCellValue();
				String e2 = row.getCell(2).getStringCellValue();
				String e3 = row.getCell(3).getStringCellValue();
				String e4 = row.getCell(4).getStringCellValue();
				String e5 = "" + row.getCell(5).getNumericCellValue();
				oldCitiesMap.put(cityKey(e2, e1, e0), new City(e3, e4, e5));
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("location.xlsx"));
			XSSFSheet sheet = wb.getSheet(countryName);
			XSSFRow row;
			
			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				String e0 = row.getCell(0).getStringCellValue();
				String e1 = row.getCell(1).getStringCellValue();
				if(row.getCell(2) == null){
					continue;
				}
				String e2 = row.getCell(2).getStringCellValue();
				Integer e3 = null;
				try{
					e3 = (int) row.getCell(3).getNumericCellValue();
				}catch(NullPointerException e){}
				String stateKey = stateKey(e0, e1);
				String cityKey = cityKey(e0, e1, e2);
				
				Double latitude = null;
				Double longitude = null;
				try{
					latitude = row.getCell(5).getNumericCellValue();
					longitude = row.getCell(6).getNumericCellValue();
				} catch(Exception e){}
				
				if (statesMap.get(stateKey) == null && !e1.trim().isEmpty()) {
					State state = new State(e1, ++stateConsecutive, countryId);
					statesMap.put(stateKey, state);
				}
				State state = statesMap.get(stateKey);
				if (citiesMap.get(cityKey) == null && !e2.trim().isEmpty()) {
					City city = new City(e2, ++cityConsecutive, state.id);
					city.setDefaultLatCoordinate(latitude);
					city.setDefaultLngCoordinate(longitude);
					city.setDefaultZoom(latitude != null ? 5 : null);
					citiesMap.put(cityKey, city);
				}
				City city = citiesMap.get(cityKey);
				City oldCity = oldCitiesMap.get(cityKey);
				if(oldCity != null){
					city.defaultLatCoordinate = oldCity.defaultLatCoordinate;
					city.defaultLngCoordinate = oldCity.defaultLngCoordinate;
					city.defaultZoom = oldCity.defaultZoom;
				}
				if(e3 != null){
					neighborhoodsList.add(new Neighborhood(String.valueOf(e3), ++neighboorhoodConsecutive, city.id));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		BufferedWriter bwCities = null;
		FileWriter fwCities = null;
		BufferedWriter bwStates = null;
		FileWriter fwStates = null;
		BufferedWriter bwNeighborhoods = null;
		FileWriter fwNeighborhoods = null;

		try {

			fwCities = new FileWriter("cities.out");
			bwCities = new BufferedWriter(fwCities);
			
			fwStates = new FileWriter("states.out");
			bwStates = new BufferedWriter(fwStates);
			
			fwNeighborhoods = new FileWriter("neighborhoods.out");
			bwNeighborhoods = new BufferedWriter(fwNeighborhoods);
			
			for(State state : statesMap.values()){
				bwStates.write(state.toString());
				bwStates.newLine();
			}
			
			for(City city : citiesMap.values()){
				bwCities.write(city.toString());
				bwCities.newLine();
			}
			
			for(Neighborhood neighborhood : neighborhoodsList){
				bwNeighborhoods.write(neighborhood.toString());
				bwNeighborhoods.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				bwCities.close();
				bwStates.close();
				bwNeighborhoods.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

	private static String cityKey(String country, String state, String city) {
		return Comparator.normalize(country + "**" + state + "**" + city);
	}

	private static String stateKey(String country, String state) {
		return Comparator.normalize(country + "**" + state);
	}

}
