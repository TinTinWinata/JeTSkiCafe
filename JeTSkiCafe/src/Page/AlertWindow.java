package Page;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertWindow {
	
	public AlertWindow(AlertType alertType, String title, String content)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public AlertWindow(AlertType alertType, String content)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle("JeTSki Cafe");
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
