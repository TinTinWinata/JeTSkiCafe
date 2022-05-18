package view.scene;

import controller.event.scene.LoginPageEvents;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginPage extends Page{
	
	private GridPane grid = null;
	private Text title = null;
	private Text usernameText = null;
	private TextField usernameField = null;
	private Text passwordText = null;
	private PasswordField passwordField = null;
	private HBox buttonContainer = null;
	private Button loginButton = null;
	private Button registerButton = null;

	public LoginPage() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
//		grid.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
			title = new Text("LOGIN");
			title.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
			grid.add(title, 0, 0, 2, 1);
			
			usernameText = new Text("Username");
			grid.add(usernameText, 0, 1, 1, 1);
			
			usernameField = new TextField();
			grid.add(usernameField, 1, 1, 1, 1);
			usernameField.setPromptText("Username");
			
			passwordText = new Text("Password");
			grid.add(passwordText, 0, 2, 1, 1);
			
			passwordField = new PasswordField();
			grid.add(passwordField, 1, 2, 1, 1);
			passwordField.setPromptText("Password");
			
			buttonContainer = new HBox();
			buttonContainer.setAlignment(Pos.CENTER_RIGHT);
			buttonContainer.setSpacing(10);
			
				loginButton = new Button("Login");
				loginButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						LoginPageEvents.loginButtonClicked();
					}
				});
				buttonContainer.getChildren().add(loginButton);
				
				registerButton = new Button("Register");
				registerButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						LoginPageEvents.registerButtonClicked();
					}
				});
				buttonContainer.getChildren().add(registerButton);
				
			grid.add(buttonContainer, 1, 3, 1, 1);
			
		this.scene = new Scene(grid);
	}

	public TextField getUsernameField() {
		return usernameField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}
}
