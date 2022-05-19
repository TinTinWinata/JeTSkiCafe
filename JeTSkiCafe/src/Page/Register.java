package Page;

import java.sql.ResultSet;

import Controller.MyScene;
import Database.Connect;
import Model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Register {
	
	private static Register registerPage;
	
	public static Register getInstance()
	{
		if(registerPage == null)
		{
			registerPage = new Register();
		}
		return registerPage;
	}
	
	
	public void registerPage()
	{
		MyScene.changeScene(makeRegisterBorder(), "Register", 330, 400);
	}
	
	public BorderPane makeRegisterBorder() {
		BorderPane borderPane = new BorderPane();
//		Initiate

		GridPane gridPane = new GridPane();
		BorderPane borderPaneMiddle = new BorderPane();
		GridPane gridPaneBottom = new GridPane();

		GridPane gridPaneRadioButton = new GridPane();

		ToggleGroup genderToggle = new ToggleGroup();

		RadioButton maleBtn = new RadioButton();
		RadioButton femaleBtn = new RadioButton();

		Label emailLbl = new Label("Email");
		Label nameLbl = new Label("Name");
		Label passwordLbl = new Label("Password");
		Label confirmPasswordLbl = new Label("Confirm Password");
		Label ageLbl = new Label("Age");
		Label genderLbl = new Label("Gender");
		Label maleLbl = new Label("Male");
		Label femaleLbl = new Label("Female");

		TextField emailTF = new TextField();
		TextField nameTF = new TextField();

		PasswordField passwordPF = new PasswordField();
		PasswordField confirmPasswordPF = new PasswordField();

		Spinner ageSpinner = new Spinner(12, 99, 12);

		Button registerBtn = new Button("Register");

//		Arrange Component
		borderPane.setCenter(borderPaneMiddle);
		borderPane.setBottom(gridPaneBottom);

		maleBtn.setToggleGroup(genderToggle);
		femaleBtn.setToggleGroup(genderToggle);

		gridPaneBottom.add(registerBtn, 0, 1);

		gridPaneRadioButton.add(maleBtn, 1, 0);
		gridPaneRadioButton.add(femaleBtn, 1, 1);
		gridPaneRadioButton.add(maleLbl, 0, 0);
		gridPaneRadioButton.add(femaleLbl, 0, 1);

		gridPane.add(emailLbl, 0, 0);
		gridPane.add(nameLbl, 0, 1);
		gridPane.add(passwordLbl, 0, 2);
		gridPane.add(confirmPasswordLbl, 0, 3);
		gridPane.add(ageLbl, 0, 4);
		gridPane.add(genderLbl, 0, 5);

		gridPane.add(emailTF, 1, 0);
		gridPane.add(nameTF, 1, 1);
		gridPane.add(passwordPF, 1, 2);
		gridPane.add(confirmPasswordPF, 1, 3);
		gridPane.add(ageSpinner, 1, 4);
		gridPane.add(gridPaneRadioButton, 1, 5);

		borderPaneMiddle.setCenter(gridPane);

//		Positioning
		borderPane.setAlignment(gridPaneBottom, Pos.CENTER);
		borderPane.setAlignment(gridPane, Pos.CENTER);

		borderPane.setPadding(new Insets(10));

		gridPaneRadioButton.setPadding(new Insets(5, 0, 0, 0));
		gridPaneRadioButton.setHgap(10);
		gridPaneRadioButton.setVgap(3);

		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);

		gridPaneBottom.setPadding(new Insets(0, 0, 20, 0));
		gridPaneBottom.setAlignment(Pos.CENTER);
		gridPaneBottom.setHgap(10);

		emailTF.setMaxWidth(320);
		passwordLbl.setMaxWidth(320);

		emailLbl.setMinWidth(100);
		passwordLbl.setMinWidth(100);

//		Button listener
		registerBtn.setOnMouseClicked(event -> {

			String inpEmail = emailTF.getText();
			String inpName = nameTF.getText();
			String inpPassword = passwordPF.getText();
			String inpConfirmPassword = confirmPasswordPF.getText();
			int inpAge = (int) ageSpinner.getValue();

			String gender = null;

			if (maleBtn.isSelected()) {
				gender = "Male";
			} else if (femaleBtn.isSelected()) {
				gender = "Female";
			} else {
				gender = null;
			}

			String error = validateRegister(inpEmail, inpName, inpPassword, inpConfirmPassword, gender);
			if (error == null) {
				User user = new User(generateId(), inpEmail, inpName, inpPassword, gender, inpAge, "user");
				user.save();
				Login login = Login.getInstance();
				login.loginPage();
			} else {
				Label errorLbl = new Label(error);
				errorLbl.setTextFill(Color.RED);
				borderPaneMiddle.setBottom(errorLbl);
				borderPaneMiddle.setAlignment(errorLbl, Pos.CENTER);
				borderPaneMiddle.setMargin(errorLbl, new Insets(0, 0, 5, 0));
			}

		});
		return borderPane;
	}
	
	public int generateId() {
		int id = 1;
		try {
			Connect connect = Connect.getConnection();
			String query = "SELECT * FROM user";
			ResultSet rs = connect.executeQuery(query);

			while (rs.next()) {
				if(rs.isLast())
				{					
					int lastId = rs.getInt("userId");
					id = lastId + 1;
				}else {
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	

	public String validateRegister(String email, String name, String password, String confirmPassword, String gender) {

		if (email == null || name == null || password == null || confirmPassword == null || gender == null) {
			return "Fields cannot be empty !";
		}

		if (!email.endsWith("@gmail.com")) {
			return "Please input a validate email";
		} else if (checkEmailExists(email)) {
			return "Email already exists!";
		} else if (name.length() < 5 || name.length() > 30) {
			return "Name length  must be 5 - 30";
		} else if (password.length() < 5 || password.length() > 20) {
			return "Password lenght must be 5 - 20";
		} else if (password.equals(name)) {
			return "Password cannot be same with name";
		} else if (!confirmPassword.equals(password)) {
			return "Confirm password not same with the password";
		} else if (gender == null) {
			return "Gender cannot be empty";
		}
		return null;
	}
	public boolean checkEmailExists(String e) {
		Connect connect = Connect.getConnection();
		String query = "SELECT * FROM user";
		ResultSet rs = connect.executeQuery(query);
		try {
			while (rs.next()) {
				if (rs.getString("userEmail").equals(e)) {
					return true;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return false;
	}
	
}
