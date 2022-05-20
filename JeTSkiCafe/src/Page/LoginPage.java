package Page;

import java.sql.ResultSet;

import Controller.MyScene;
import Database.Connect;
import Model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class LoginPage {
	
	private static LoginPage loginPage;
	
	public static LoginPage getInstance()
	{
		if(loginPage == null)
		{
			loginPage = new LoginPage();
		}
		return loginPage;
	}
	
	public void loginPage()
	{
		MyScene.changeScene(makeLoginBorder(), "Login", 300, 150);
	}
	
	public BorderPane makeLoginBorder() {
//		Initiate
		BorderPane borderPane = new BorderPane();

		GridPane gridPane = new GridPane();
		GridPane gridPaneBottom = new GridPane();
		BorderPane borderPaneMiddle = new BorderPane();

		Label emailLbl = new Label("Email");
		Label passwordLbl = new Label("Password");

		TextField emailTF = new TextField();

		PasswordField passwordPF = new PasswordField();

		Button loginBtn = new Button("Login");
		Button registerBtn = new Button("Register");

//		Arrange Component
		borderPane.setCenter(borderPaneMiddle);
		borderPane.setBottom(gridPaneBottom);

		gridPaneBottom.add(loginBtn, 0, 1);
		gridPaneBottom.add(registerBtn, 1, 1);

		gridPane.add(emailLbl, 0, 0);
		gridPane.add(passwordLbl, 0, 1);

		gridPane.add(emailTF, 1, 0);
		gridPane.add(passwordPF, 1, 1);

		borderPaneMiddle.setCenter(gridPane);

//		Positioning
		borderPane.setAlignment(gridPaneBottom, Pos.CENTER);
		borderPane.setAlignment(borderPaneMiddle, Pos.CENTER);

		borderPane.setPadding(new Insets(10));

		gridPane.setAlignment(Pos.CENTER_LEFT);
		gridPane.setVgap(10);

		gridPaneBottom.setAlignment(Pos.CENTER);
		gridPaneBottom.setHgap(10);
		gridPaneBottom.setPadding(new Insets(0, 0, 3, 0));

		emailTF.setMaxWidth(320);
		passwordLbl.setMaxWidth(320);

		emailLbl.setMinWidth(100);
		passwordLbl.setMinWidth(100);

//		Button listener
		loginBtn.setOnMouseClicked(event -> {
			
			String inpEmail = emailTF.getText();
			String inpPassword = passwordPF.getText();
			
			String error = validateLogin(inpEmail, inpPassword);
			
			if (error != null) {
				Label errorLbl = new Label(error);
				errorLbl.setTextFill(Color.RED);
				borderPaneMiddle.setBottom(errorLbl);
				borderPaneMiddle.setAlignment(errorLbl, Pos.CENTER);
				borderPaneMiddle.setPadding(new Insets(0, 0, 5, 0));
			} else {
				AlertWindow alertWindow = new AlertWindow(AlertType.INFORMATION, "Information", "Sucess Login!");
				System.out.println("Success Login!");
				MenuPage menuPage = MenuPage.getInstance();
				menuPage.menuPage();
			}
		});

		registerBtn.setOnMouseClicked(event -> {
			RegisterPage register = RegisterPage.getInstance();
			register.registerPage();
		});

		return borderPane;
	}
	public String validateLogin(String email, String password)
	{
		if(email.isEmpty() || password.isEmpty())
		{
			return "Fields cannot be empty !";
		}
		
		if(checkLogin(email, password) == false)
		{
			return "User not found";
		}
		
		return null;
	}
	public boolean checkLogin(String inpEmail, String inpPassword) {
		Connect connect = Connect.getConnection();
		String query = "SELECT * FROM user";
		ResultSet rs = connect.executeQuery(query);

		try {
			while (rs.next()) {
				String email = rs.getString("userEmail");
				String password = rs.getString("userPassword");
				if (inpEmail.equals(email) && inpPassword.equals(password)) {
					int id =  rs.getInt("userId");
					String name = rs.getString("userName");
					String gender = rs.getString("userGender");
					int age = rs.getInt("userAge");
					String role = rs.getString("userRole");
					
					User u = new User(id, email, name, password, gender, age, role);
					User.setActiveUser(u);
					
					return true;
				}

			}
		} catch (Exception e) {
		}
		return false;
	}

}
