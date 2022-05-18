package controller.event.scene;

import java.sql.SQLException;

import controller.Staging;
import controller.helper.AlertWindowHelper;
import javafx.scene.control.RadioButton;
import model.exception.InvalidGenderException;
import model.exception.InvalidPasswordException;
import model.exception.InvalidUsernameException;
import model.handler.AuthHandler;
import view.scene.RegisterPage;

public class RegisterPageEvents {
	public static void registerPageLoaded() {
		String id = "";
		try {
			id = AuthHandler.generateRandomUniqueUserId();
			Staging.getAuthWindow().getRegisterPage().getIdField().setText(id);
			Staging.getAuthWindow().getRegisterPage().getUsernameField().setText("");
			Staging.getAuthWindow().getRegisterPage().getPasswordField().setText("");
			Staging.getAuthWindow().getRegisterPage().getGenderGroup().selectToggle(null);
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		}
	}
	
	public static void registerButtonClicked() {
		RegisterPage registerPage = Staging.getAuthWindow().getRegisterPage();
		String username = registerPage.getUsernameField().getText();
		String password = registerPage.getPasswordField().getText();
		RadioButton selectedButton = (RadioButton) registerPage.getGenderGroup().getSelectedToggle();
		String gender = selectedButton == null ? null : selectedButton.getText();
		
		try {
			AuthHandler.register(username, password, gender);
			AlertWindowHelper.showInformationWindow("Registration successful!");
			Staging.getAuthWindow().showLoginPage();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (InvalidUsernameException e) {
			AlertWindowHelper.showErrorWindow("Username must be between 5 - 30 characters!");
		} catch (InvalidPasswordException e) {
			AlertWindowHelper.showErrorWindow("Password must be between 5 - 30 characters!");
		} catch (InvalidGenderException e) {
			AlertWindowHelper.showErrorWindow("Gender must be picked!");
		}
	}
	
	public static void loginButtonClicked() {
		Staging.getAuthWindow().showLoginPage();
	}	
}
