package controller.event.scene;

import java.sql.SQLException;

import controller.Staging;
import controller.helper.AlertWindowHelper;
import model.exception.InvalidLoginCredentialsException;
import model.handler.AuthHandler;

public class LoginPageEvents {
	public static void loginButtonClicked() {
		String username = Staging.getAuthWindow().getLoginPage().getUsernameField().getText();
		String password = Staging.getAuthWindow().getLoginPage().getPasswordField().getText();
		
		try {
			AuthHandler.login(username, password);
			AlertWindowHelper.showInformationWindow("Logged in!");
			Staging.showAppWindow();
		} catch (ClassNotFoundException e1) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e1) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (InvalidLoginCredentialsException e1) {
			AlertWindowHelper.showErrorWindow("Invalid username / password!");
		}
	}
	
	public static void registerButtonClicked() {
		try {			
			Staging.getAuthWindow().showRegisterPage();
		}
		catch(NullPointerException e) {}
	}
	
	public static void loginPageLoaded() {
		Staging.getAuthWindow().getLoginPage().getUsernameField().setText("");
		Staging.getAuthWindow().getLoginPage().getPasswordField().setText("");
	}
}
