
public class City {

	public String name;
	public Integer id;
	public Integer stateId;
	public Double defaultLatCoordinate;
	public Double defaultLngCoordinate;
	public Integer defaultZoom;
	
	public City(String name, Integer id, Integer stateId) {
		super();
		this.name = name.replaceAll("'", "''");
		this.id = id;
		this.stateId = stateId;
		this.defaultLatCoordinate = 0.0;
		this.defaultLngCoordinate = 0.0;
		this.defaultZoom = 0;
	}
	
	
	public City(String defaultLatCoordinate, String defaultLngCoordinate, String defaultZoom) {
		super();
		this.defaultLatCoordinate = 0.0;
		this.defaultLngCoordinate = 0.0;
		this.defaultZoom = 0;
		try{
			this.defaultLatCoordinate = Double.parseDouble(defaultLatCoordinate);
			this.defaultLngCoordinate = Double.parseDouble(defaultLngCoordinate);
			this.defaultZoom = (int)Double.parseDouble(defaultZoom);
		} catch(NumberFormatException e){}
	}

	@Override
	public String toString() {
		return String.format("(%1$s, '%2$s', %3$s, %4$s, %5$s, %6$s),"
				, id, name, stateId, defaultLatCoordinate, defaultLngCoordinate, defaultZoom);
	}


	public Double getDefaultLatCoordinate() {
		return defaultLatCoordinate;
	}


	public void setDefaultLatCoordinate(Double defaultLatCoordinate) {
		this.defaultLatCoordinate = defaultLatCoordinate;
	}


	public Double getDefaultLngCoordinate() {
		return defaultLngCoordinate;
	}


	public void setDefaultLngCoordinate(Double defaultLngCoordinate) {
		this.defaultLngCoordinate = defaultLngCoordinate;
	}


	public Integer getDefaultZoom() {
		return defaultZoom;
	}


	public void setDefaultZoom(Integer defaultZoom) {
		this.defaultZoom = defaultZoom;
	}
	
}
