package model.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MOTADBHelper {
	public static ResultSet executeQuery(String query) throws ClassNotFoundException, SQLException {
		Connection conn = MOTADBConnection.getConnection();
		Statement statement = conn.createStatement();
		ResultSet set = statement.executeQuery(query);
		return set;
	}
	
	public static void executeUpdate(String query) throws ClassNotFoundException, SQLException {
		Connection conn = MOTADBConnection.getConnection();
		Statement statement = conn.createStatement();
		statement.executeUpdate(query);
	}
}
