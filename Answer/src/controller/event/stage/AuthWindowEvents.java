package controller.event.stage;

import controller.Staging;

public class AuthWindowEvents {
	public static void authWindowShown() {
		Staging.getAuthWindow().showLoginPage();
	}
}
