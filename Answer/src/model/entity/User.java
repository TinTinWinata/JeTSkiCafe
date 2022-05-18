package model.entity;

public class User {
	private String id = null;
	private String username = null;
	private String password = null;
	private String gender = null;
	private String role = null;
	
	public User() {
	}

	public User(String id, String username, String password, String gender, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
