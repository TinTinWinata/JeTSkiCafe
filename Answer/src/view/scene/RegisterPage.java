package view.scene;

import controller.event.scene.RegisterPageEvents;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RegisterPage extends Page{

	private GridPane grid = null;
	private Text title = null;
	private Text idText = null;
	private TextField idField = null;
	private Text usernameText = null;
	private TextField usernameField = null;
	private Text passwordText = null;
	private PasswordField passwordField = null;
	private Text genderText = null;
	private VBox radioContainer = null;
	private ToggleGroup genderGroup = null;
	private RadioButton radioMale = null;
	private RadioButton radioFemale = null;
	private HBox buttonContainer = null;
	private Button loginButton = null;
	private Button registerButton = null;

	public RegisterPage() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		// TODO decide background
//		grid.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
			title = new Text("REGISTER");
			title.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
			grid.add(title, 0, 0, 2, 1);

			idText = new Text("User ID");
			grid.add(idText, 0, 1, 1, 1);
			
			idField = new TextField();
			idField.setDisable(true);
			grid.add(idField, 1, 1, 1, 1);
			
			usernameText = new Text("Username");
			grid.add(usernameText, 0, 2, 1, 1);
			
			usernameField = new TextField();
			grid.add(usernameField, 1, 2, 1, 1);
			usernameField.setPromptText("Username");
			
			passwordText = new Text("Password");
			grid.add(passwordText, 0, 3, 1, 1);
			
			passwordField = new PasswordField();
			grid.add(passwordField, 1, 3, 1, 1);
			passwordField.setPromptText("Password");
			
			genderText = new Text("Gender");
			grid.add(genderText, 0, 4, 1, 1);
			
			radioContainer = new VBox();
			radioContainer.setAlignment(Pos.CENTER_LEFT);
			radioContainer.setSpacing(10);
				genderGroup = new ToggleGroup();
					radioMale = new RadioButton("Male");
					radioMale.setToggleGroup(genderGroup);
					radioContainer.getChildren().add(radioMale);
					
					radioFemale = new RadioButton("Female");
					radioFemale.setToggleGroup(genderGroup);
					radioContainer.getChildren().add(radioFemale);
			grid.add(radioContainer, 1, 4, 1, 1);
			
			buttonContainer = new HBox();
			buttonContainer.setAlignment(Pos.CENTER_RIGHT);
			buttonContainer.setSpacing(10);
			
				registerButton = new Button("Register");
				registerButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						RegisterPageEvents.registerButtonClicked();
					}
				});
				buttonContainer.getChildren().add(registerButton);
				
				loginButton = new Button("Login");
				loginButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						RegisterPageEvents.loginButtonClicked();
					}
				});
				buttonContainer.getChildren().add(loginButton);
				
			grid.add(buttonContainer, 1, 5, 1, 1);
			
		this.scene = new Scene(grid);
	}

	public TextField getIdField() {
		return idField;
	}
	
	public TextField getUsernameField() {
		return usernameField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public ToggleGroup getGenderGroup() {
		return genderGroup;
	}
}
