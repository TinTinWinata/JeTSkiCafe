package controller.helper;

import javafx.scene.control.Alert.AlertType;
import view.stage.AlertWindow;

public class AlertWindowHelper {
	public static void showJDBCErrorWindow() {
		new AlertWindow(AlertType.ERROR, "JDBC driver not found!");
	}
	
	public static void showSQLErrorWindow() {
		new AlertWindow(AlertType.ERROR, "There has been an error in the database!");
	}
	
	public static void showInformationWindow(String message) {
		new AlertWindow(AlertType.INFORMATION, message);
	}
	
	public static void showErrorWindow(String message) {
		new AlertWindow(AlertType.ERROR, message);
	}
}
