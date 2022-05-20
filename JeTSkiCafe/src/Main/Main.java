package Main;

import Controller.MyStage;
import Page.LoginPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
	
		MyStage myStage = MyStage.getInstance();
		myStage.setStage(arg0);
		
		myStage.setIcon("logo.png");
		
		LoginPage login = LoginPage.getInstance();
		login.loginPage();
		
		myStage.showStage();
	}


}
