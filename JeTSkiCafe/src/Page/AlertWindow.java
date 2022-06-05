package Page;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AlertWindow {
	
	public AlertWindow(AlertType alertType, String title, String content)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:logo.png"));
		alert.showAndWait();
	}
	
	public AlertWindow(AlertType alertType, String content)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle("JeTSki Cafe");
		alert.setHeaderText(null);
		alert.setContentText(content);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:logo.png"));
		alert.showAndWait();
	}
	

}
