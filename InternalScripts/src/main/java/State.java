
public class State {

	public String name;
	public Integer id;
	public Integer countryId;
	public State(String name, Integer id, Integer countryId) {
		super();
		this.name = name;
		this.id = id;
		this.countryId = countryId;
	}
	@Override
	public String toString() {
		return String.format("(%1$s, '%2$s', %3$s),"
				, id, name, countryId);
	}
	
}
