package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MOTADBConnection {
	private static final String MYSQL_HOST = "127.0.0.1";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "mota_db";
	private static final String MYSQL_LOGIN = "root";
	private static final String MYSQL_PASSWORD = "";
	
	private static Connection conn = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {		
		if(conn == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionString = String.format("jdbc:mysql://%s:%s/%s", MYSQL_HOST, MYSQL_PORT, MYSQL_DATABASE);
			conn = DriverManager.getConnection(connectionString, MYSQL_LOGIN, MYSQL_PASSWORD);
		}

		return conn;
	}
	
	public static Connection closeConnection() throws SQLException {
		if(conn != null) {
			conn.close();
			conn = null;
		}
		return null;
	}
}
