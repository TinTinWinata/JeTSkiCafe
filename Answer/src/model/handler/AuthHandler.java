package model.handler;

import java.sql.SQLException;
import java.util.Random;

import model.entity.User;
import model.exception.InvalidGenderException;
import model.exception.InvalidLoginCredentialsException;
import model.exception.InvalidPasswordException;
import model.exception.InvalidUsernameException;
import model.repository.UserRepository;

public class AuthHandler {
	public static final String ROLE_USER = "user";
	public static final String ROLE_ADMINISTRATOR = "administrator";
	
	private static String nextUserId = null;
	private static User currentUser = null;
	
	public static void login(String username, String password) throws ClassNotFoundException, SQLException, InvalidLoginCredentialsException {
		User user = UserRepository.getUserByUsernameAndPassword(username, password);
		if(user == null)
			throw new InvalidLoginCredentialsException();
		currentUser = user;
	} 
	
	public static void register(String username, String password, String gender) throws ClassNotFoundException, SQLException, InvalidUsernameException, InvalidPasswordException, InvalidGenderException {
		if(username.length() < 5 || username.length() > 30) {
			throw new InvalidUsernameException();
		}
		if(password.length() < 5 || password.length() > 30) {
			throw new InvalidPasswordException();
		}
		if(gender == null) {
			throw new InvalidGenderException();
		}
		String id = getNextUserId();
		gender = gender.toLowerCase();
		String role = "user";
		UserRepository.registerUser(id, username, password, gender, role);
	}
	
	public static void logout() {
		currentUser = null;
	}
	
	public static String generateRandomUniqueUserId() throws ClassNotFoundException, SQLException {
		Random random = new Random();
		String id = null;
		
		do {
			id = "USR";
			id += Math.abs(random.nextInt() % 10);
			id += Math.abs(random.nextInt() % 10);
			id += Math.abs(random.nextInt() % 10);
		}while(UserRepository.getUserById(id) != null);
		
		nextUserId = id;
		
		return getNextUserId();
	}
	
	public static String getNextUserId() {
		return nextUserId;
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static String getCurrentUserId() {
		return currentUser.getId();
	}
	
	public static String getCurrentUserUsername() throws NullPointerException {
		return currentUser.getUsername();
	}
	
	public static String getCurrentUserRole() throws NullPointerException {
		return currentUser.getRole();
	}
}
