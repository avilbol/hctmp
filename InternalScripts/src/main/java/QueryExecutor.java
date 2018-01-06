import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class QueryExecutor {

	static String myDriver = "org.gjt.mm.mysql.Drivern";
	static String myUrl1 = "jdbc:mysql://104.236.244.61:3306/hallocasaappmig?user=hallocasa&password=r8thUgeT=stEwaWa";
	static String myUrl2 = "jdbc:mysql://104.236.244.61:3307/hallocasaappmig?user=hallocasa&password=r8thUgeT=stEwaWa";
	static String user = "hallocasa";
	static String password = "r8thUgeT=stEwaWa";
	static Connection conn1 = null;
	static Connection conn2 = null;

	public static Connection getConn1() throws ClassNotFoundException, SQLException{
		if(conn1 == null){
			String myDriver = "com.mysql.jdbc.Driver";
			Class.forName(myDriver);
			conn1 = DriverManager.getConnection(myUrl1, user, password);
		}
		return conn1;
	}
	
	public static Connection getConn2() throws ClassNotFoundException, SQLException{
		if(conn2 == null){
			String myDriver = "com.mysql.jdbc.Driver";
			Class.forName(myDriver);
			conn2 = DriverManager.getConnection(myUrl2, user, password);
		}
		return conn2;
	}
	
	public static Map<Integer, String> consultar(String query) {
		try {
			Connection conn = getConn2();
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			Map<Integer, String> references = new HashMap<>();
			while(rs.next()){
				references.put(rs.getInt("id"), rs.getString("search_text"));
			}
			st.close();
			return references;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public static void actualizar(String query){
		try {
			Statement st1 = getConn1().createStatement();
			Statement st2 = getConn2().createStatement();
			st1.executeUpdate(query);
			st2.executeUpdate(query);
			st1.close();
			st2.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
