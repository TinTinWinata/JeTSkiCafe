package view.stage;

import controller.event.scene.LoginPageEvents;
import controller.event.scene.RegisterPageEvents;
import controller.event.stage.AuthWindowEvents;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.scene.LoginPage;
import view.scene.RegisterPage;

public class AuthWindow extends Stage {
	private final String STAGE_TITLE = "Authentication";
	private LoginPage loginPage = null;
	private RegisterPage registerPage = null;
	
	public AuthWindow() {
		setTitle(STAGE_TITLE);
		setResizable(false);
		
		setOnShowing(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				AuthWindowEvents.authWindowShown();
			}
		});
	}
	
	public void showLoginPage() {
		if(loginPage == null)
			loginPage = new LoginPage();
		setScene(loginPage.getScene());
		LoginPageEvents.loginPageLoaded();
	}
	
	public void showRegisterPage() {
		if(registerPage == null)
			registerPage = new RegisterPage();
		setScene(registerPage.getScene());
		RegisterPageEvents.registerPageLoaded();
	}
	
	public LoginPage getLoginPage() {
		return loginPage;
	}
	
	public RegisterPage getRegisterPage() {
		return registerPage;
	}
	
	
}
