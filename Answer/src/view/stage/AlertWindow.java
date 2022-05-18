package view.stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertWindow { 
	
	public AlertWindow(AlertType alertType, String content) {
		
		Alert alert = new Alert(alertType);
		String[] titles = {"-", "Notification", "Warning", "Confirm", "Error"};
		String title = titles[alertType.ordinal()];
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		
		alert.showAndWait();
	}
}
