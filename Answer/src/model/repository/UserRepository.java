package model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.database.MOTADBHelper;
import model.entity.User;
import model.factory.UserFactory;


public class UserRepository {
	private static final String MYSQL_TABLE = "user";
	private static final String MYSQL_COLUMN_ID = "id";
	private static final String MYSQL_COLUMN_USERNAME = "username"; 
	private static final String MYSQL_COLUMN_PASSWORD = "password";
	private static final String MYSQL_COLUMN_GENDER = "gender";
	private static final String MYSQL_COLUMN_ROLE = "role";
	
	private static User createInstanceFromSet(ResultSet set) throws SQLException {
		String id = set.getString(MYSQL_COLUMN_ID);
		String username = set.getString(MYSQL_COLUMN_USERNAME);
		String password = set.getString(MYSQL_COLUMN_PASSWORD);
		String gender = set.getString(MYSQL_COLUMN_GENDER);
		String role = set.getString(MYSQL_COLUMN_ROLE);
		return UserFactory.createUser(id, username, password, gender, role);
	}
	
	public static ArrayList<User> getUsers() throws ClassNotFoundException, SQLException {
		ArrayList<User> userList = new ArrayList<>();
		String query = String.format("SELECT * FROM %s", MYSQL_TABLE);
		ResultSet set = MOTADBHelper.executeQuery(query);
		while(set.next()) {
			userList.add(createInstanceFromSet(set));
		}
		return userList;
	}
	
	public static User getUserById(String userId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", MYSQL_TABLE, MYSQL_COLUMN_ID, userId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		if(set.next()) {
			return createInstanceFromSet(set);
		}
		return null;
	}
	
	public static User getUserByUsernameAndPassword(String username, String password) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'", MYSQL_TABLE, MYSQL_COLUMN_USERNAME, username, MYSQL_COLUMN_PASSWORD, password);
		ResultSet set = MOTADBHelper.executeQuery(query);
		if(set.next()) {
			return createInstanceFromSet(set);
		}
		return null;
	}
	
	public static void registerUser(String id, String username, String password, String gender, String role) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s')", 	
									MYSQL_TABLE, 
									MYSQL_COLUMN_ID, 
									MYSQL_COLUMN_USERNAME, 
									MYSQL_COLUMN_PASSWORD, 
									MYSQL_COLUMN_GENDER, 
									MYSQL_COLUMN_ROLE,
									id, username, password, gender, role);
		MOTADBHelper.executeUpdate(query);
	}
}
