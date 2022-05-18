package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.stage.AppWindow;
import view.stage.AuthWindow;

public class Staging extends Application{

	private static AuthWindow auth = null;
	private static AppWindow app = null;
	
	@Override
	public void start(Stage arg0) throws Exception {
		showAuthWindow();
	}
	
	public static void showAuthWindow() {
		if(auth == null)
			auth = new AuthWindow();
		auth.show();
		if(app != null)
			app.hide();
	}
	
	public static void showAppWindow() {
		if(app == null)
			app = new AppWindow();
		app.show();
		if(auth != null)
			auth.hide();
	}
	
	public static AuthWindow getAuthWindow() {
		return auth;
	}
	
	public static AppWindow getAppWindow() {
		return app;
	}
}
