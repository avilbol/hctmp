
public class Neighborhood {

	public String name;
	public Integer id;
	public Integer cityId;
	public Neighborhood(String name, Integer id, Integer cityId) {
		super();
		this.name = name.replaceAll("'", "''");
		this.id = id;
		this.cityId = cityId;
	}
	
	@Override
	public String toString() {
		return String.format("(%1$s, '%2$s', 0, 0, %3$s),"
				, id, name, cityId);
	}
}

