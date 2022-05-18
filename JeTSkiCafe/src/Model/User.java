package Model;

import Database.Connect;

public class User {

	private Integer userId;
	private String userEmail;
	private String userName;
	private String userPassword;
	private String userGender;
	private Integer userAge;
	private String userRole;
	
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
		System.out.println("Query : " + query);
		connect.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
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
