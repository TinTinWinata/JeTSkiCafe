package controller.event.stage;

import controller.Staging;
import model.handler.AuthHandler;

public class AppWindowEvents {
	public static void appWindowLoaded() {
		Staging.getAppWindow().showIndexPage();
		String username = AuthHandler.getCurrentUserUsername();
		String role = AuthHandler.getCurrentUserRole();
		String title = String.format("(%s) %s | Market of the Arts", role, username);
		Staging.getAppWindow().setTitle(title);
	}
}
