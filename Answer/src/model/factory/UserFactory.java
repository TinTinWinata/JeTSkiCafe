package model.factory;

import model.entity.User;

public class UserFactory {
	public static User createUser(String id, String username, String password, String gender, String role) {
		return new User(id, username, password, gender, role);
	}
}
