package Model;

import java.sql.ResultSet;
import java.util.Vector;

import Database.Connect;
import Page.AlertWindow;
import javafx.scene.control.Alert.AlertType;

public class User {

	private Integer userId;
	private String userEmail;
	private String userName;
	private String userPassword;
	private String userGender;
	private Integer userAge;
	private String userRole;
	
	private static User activeUser;

	
	public static void setActiveUser(User u)
	{
		activeUser = u;
	}
	
	public static User getActiveUser()
	{
		return activeUser;
	}
	
	public User(Integer userId, String userEmail, String userName, String userPassword, String userGender,
			Integer userAge, String userRole) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userAge = userAge;
		this.userRole = userRole;
	}
	
	public void save()
	{
		Connect connect = Connect.getConnection();
		try {
		String query = String.format("INSERT INTO user VALUES ('%d','%s','%s','%s','%s',%d,'%s')"
				,userId, userEmail, userName, userPassword, userGender, userAge, userRole);
		connect.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void remove()
	{
		Connect c = Connect.getConnection();
		String query = String.format("DELETE FROM user WHERE userId = %d", this.userId);
		c.executeUpdate(query);
	}
	
	public void update(String userEmail, String userName, String userPassword, String userGender,int userAge, String userRole)
	{
		Connect c = Connect.getConnection();
		String query = String.format("UPDATE user SET userEmail = '%s', userName = '%s', userPassword = '%s', userGender = '%s', userAge = %d, userRole = '%s' WHERE userId = %d",
				userEmail, userName, userPassword, userGender, userAge, userRole, this.userId);
		c.executeUpdate(query);
	}
	
	public static Vector<User> getUserFromDB()
	{
		Vector<User> userList = new Vector<>();
		Connect c = Connect.getConnection();
		String query = "SELECT * FROM user";
		ResultSet rs = c.executeQuery(query);
		try {
			while(rs.next())
			{
				int userId = rs.getInt("userId");
				String userEmail = rs.getString("userEmail");
				String userName = rs.getString("userName");
				String userPassword = rs.getString("userPassword");
				String userGender = rs.getString("userGender");
				int userAge = rs.getInt("userAge");
				String userRole = rs.getString("userRole");
				
				User u = new User(userId, userEmail, userName, userPassword, userGender, userAge, userRole);
				userList.add(u);
			}
		} catch (Exception e) {
			new AlertWindow(AlertType.ERROR, "Failed take user data from DB");
		}
		return userList;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	
}
