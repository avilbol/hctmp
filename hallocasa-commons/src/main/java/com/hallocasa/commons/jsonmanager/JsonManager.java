package com.hallocasa.commons.jsonmanager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hallocasa.commons.exceptions.services.ErrorJsonResponseException;
import com.hallocasa.commons.vo.properties.filters.ComparatorType;

/**
 * Manager for specific operations between json and java objects
 * @author Alexander Villamil
 *
 */
public class JsonManager {

	private static final String example = "{'success':true,'terms':'https:\\currencylayer.com\\terms','privacy':'https:\\currencylayer.com\\privacy','timestamp':1466863267,'source':'USD','quotes':{'USDAED':3.67315,'USDAFN':68.889999,'USDALL':123.889503,'USDAMD':476.320007,'USDANG':1.790403,'USDAOA':165.735001,'USDARS':14.91995,'USDAUD':1.337525,'USDAWG':1.79,'USDAZN':1.544704,'USDBAM':1.761304,'USDBBD':2,'USDBDT':78.004951,'USDBGN':1.761404,'USDBHD':0.37706,'USDBIF':1687.800049,'USDBMD':1.00005,'USDBND':1.34645,'USDBOB':6.885041,'USDBRL':3.37285,'USDBSD':0.99404,'USDBTC':0.00147,'USDBTN':67.885002,'USDBWP':10.98895,'USDBYR':20070,'USDBZD':1.995041,'USDCAD':1.300104,'USDCDF':960.000362,'USDCHF':0.97237,'USDCLF':0.024604,'USDCLP':665.405029,'USDCNY':6.538604,'USDCOP':2882.745117,'USDCRC':545.48999,'USDCUC':1,'USDCUP':1.00036,'USDCVE':99.172997,'USDCZK':24.420504,'USDDJF':177.725006,'USDDKK':6.698904,'USDDOP':45.674999,'USDDZD':109.959999,'USDEEK':14.05404,'USDEGP':8.82625,'USDERN':16.180392,'USDETB':21.770392,'USDEUR':0.899443,'USDFJD':2.07085,'USDFKP':0.659804,'USDGBP':0.730781,'USDGEL':2.310391,'USDGGP':0.731243,'USDGHS':3.91039,'USDGIP':0.77104,'USDGMD':42.80504,'USDGNF':8960.400391,'USDGTQ':7.590304,'USDGYD':206.529953,'USDHKD':7.761204,'USDHNL':22.54245,'USDHRK':6.77585,'USDHTG':62.426449,'USDHUF':286.005005,'USDIDR':13410,'USDILS':3.886604,'USDIMP':0.731243,'USDINR':67.888451,'USDIQD':1158.949951,'USDIRR':30589.000352,'USDISK':124.224998,'USDJEP':0.731243,'USDJMD':124.719948,'USDJOD':0.70805,'USDJPY':102.254997,'USDKES':101.080002,'USDKGS':67.386398,'USDKHR':4057.800049,'USDKMF':443.449951,'USDKPW':900.00035,'USDKRW':1172.244995,'USDKWD':0.30185,'USDKYD':0.820383,'USDKZT':338.034943,'USDLAK':8059.950195,'USDLBP':1497.400024,'USDLKR':146.005005,'USDLRD':84.669998,'USDLSL':15.10695,'USDLTL':3.048704,'USDLVL':0.62055,'USDLYD':1.353204,'USDMAD':9.749104,'USDMDL':19.560949,'USDMGA':3245.199951,'USDMKD':55.409039,'USDMMK':1174.849976,'USDMNT':1936.503751,'USDMOP':7.99425,'USDMRO':354.459991,'USDMUR':35.434502,'USDMVR':15.390378,'USDMWK':707.47998,'USDMXN':18.916901,'USDMYR':4.094039,'USDMZN':62.549999,'USDNAD':15.10695,'USDNGN':279.554993,'USDNIO':28.591299,'USDNOK':8.491104,'USDNPR':108.615997,'USDNZD':1.401804,'USDOMR':0.384815,'USDPAB':0.994045,'USDPEN':3.312704,'USDPGK':3.16635,'USDPHP':46.982498,'USDPKR':104.139999,'USDPLN':4.009404,'USDPYG':5652.000341,'USDQAR':3.64095,'USDRON':4.07965,'USDRSD':111.364998,'USDRUB':65.676003,'USDRWF':741.984985,'USDSAR':3.74995,'USDSBD':7.79935,'USDSCR':13.10995,'USDSDG':6.084704,'USDSEK':8.48425,'USDSGD':1.353104,'USDSHP':0.693904,'USDSLL':5057.750371,'USDSOS':584.000338,'USDSRD':7.038038,'USDSTD':22034.5,'USDSVC':8.697704,'USDSYP':219.856995,'USDSZL':15.115038,'USDTHB':35.303001,'USDTJS':7.868504,'USDTMT':3.49995,'USDTND':2.20295,'USDTOP':2.17993,'USDTRY':2.92935,'USDTTD':6.62965,'USDTWD':32.432999,'USDTZS':2177.800049,'USDUAH':24.73695,'USDUGX':3364.503631,'USDUSD':1,'USDUYU':30.490367,'USDUZS':2938.709961,'USDVEF':9.950366,'USDVND':22185,'USDVUV':110.080002,'USDWST':2.520492,'USDXAF':590.71283,'USDXAG':0.056338,'USDXAU':0.00075,'USDXCD':2.703606,'USDXDR':0.714304,'USDXOF':590.71283,'USDXPF':107.462502,'USDYER':249.050003,'USDZAR':15.087604,'USDZMK':5156.103593,'USDZMW':10.83895,'USDZWL':322.355011}}";
	
	/**
	 * Map the data of the value of a specific property from json response, in a map key-value
	 * @param json
	 * 		Json that must have a object-like property
	 * @param propertyKey
	 * 		Property object-like to search
	 * @return
	 * 		The map with key-value list
	 */
	public static Map<String, Object> fromJson(String json, String propertyKey) throws ErrorJsonResponseException{
		try{
			JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
			Type mapType = new TypeToken<Map<String, Object>>(){}.getType();  
			Map<String, Object> son = new Gson().fromJson(obj.get(propertyKey), mapType);
			return son;
		} catch(NullPointerException e){
			throw new ErrorJsonResponseException(e);
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
	
	public static Object loadValue(String json, ComparatorType comparatorType, String propertyName){
		if(comparatorType.equals(ComparatorType.VALUE))
			return json;
		if(comparatorType.equals(ComparatorType.OBJECT_PROPERTY))
			return loadProperty(json, propertyName);
		if(comparatorType.equals(ComparatorType.LIST_OBJECT_PROPERTY)){
			List<String> resultList = new ArrayList<String>();
			JsonArray jsonParsedArray = new JsonParser().parse(json).getAsJsonArray();
			for(JsonElement element : jsonParsedArray)
				resultList.add(loadProperty(element.toString(), propertyName));
			return resultList;
		}
		return null;
	}
	
}
