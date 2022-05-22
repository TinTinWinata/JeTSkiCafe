package Page;

import java.sql.ResultSet;
import java.util.Vector;

import Database.Connect;
import Helper.Utillities;
import Model.Menu;
import Model.User;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ManageUserPage {

	private static ManageUserPage manageUserPage;
	private Utillities util;

	private BorderPane mainPane;
	private GridPane bottomGridPane;
	private GridPane rbGridPane;

	private TableView<User> table;

	private Label userIdLbl;
	private Label userEmailLbl;
	private Label userNameLbl;
	private Label userPasswordLbl;
	private Label userAgeLbl;
	private Label userRoleLbl;
	private Label maleLbl;
	private Label femaleLbl;
	private Label userGenderLbl;

	private ToggleGroup genderTg;
	
	private TextField userIdTF;
	private TextField userEmailTF;
	private TextField userNameTF;
	
	private PasswordField userPasswordPF;
	private RadioButton maleRb;
	private RadioButton femaleRb;
	
	private TextField userAgeTF;

	private Button insertNewBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button insertBtn;
	private Button cancelBtn;
	
	private String[] roleList = {"user", "admin"};
	
	private ComboBox<String> roleCb;

	public static ManageUserPage getInstance() {
		if (manageUserPage == null) {
			manageUserPage = new ManageUserPage();
		}
		return manageUserPage;
	}

	public void init() {
		util = Utillities.getInstance();

		mainPane = new BorderPane();
		bottomGridPane = new GridPane();
		rbGridPane = new GridPane();
		table = new TableView<User>();

		insertNewBtn = new Button("Insert new User");
		removeBtn = new Button("Remove User");
		updateBtn = new Button("Update User");
		insertBtn = new Button("Insert");
		cancelBtn = new Button("Cancel");
		
		insertBtn.setVisible(false);
		cancelBtn.setVisible(false);

		userIdTF = new TextField();
		userEmailTF = new TextField();
		userNameTF = new TextField();
		userPasswordPF = new PasswordField();
		userAgeTF = new TextField();
		
		genderTg = new ToggleGroup();
		maleRb = new RadioButton();
		femaleRb = new RadioButton();
		
		userIdTF.setDisable(true);
		
		userGenderLbl = new Label("Gender");
		maleLbl = new Label("Male");
		femaleLbl = new Label("Female");
		userIdLbl = new Label("User ID");
		userEmailLbl = new Label("User Email");
		userNameLbl = new Label("User Name");
		userPasswordLbl = new Label("User Password");
		userAgeLbl = new Label("User Age");
		userRoleLbl = new Label("User Role");

		roleCb = new ComboBox<>(FXCollections.observableArrayList(roleList));
	}

	public void arrangeComponent() {
		rbGridPane.add(maleLbl, 0, 0);
		rbGridPane.add(maleRb, 1, 0);
		rbGridPane.add(femaleLbl, 2, 0);
		rbGridPane.add(femaleRb, 3, 0);
		
		
		bottomGridPane.add(userIdLbl, 0, 0);
		bottomGridPane.add(userEmailLbl, 0, 1);
		bottomGridPane.add(userNameLbl, 0, 2);
		bottomGridPane.add(userPasswordLbl, 0, 3);
		bottomGridPane.add(userGenderLbl, 2, 0);
		bottomGridPane.add(userAgeLbl, 2, 1);
		bottomGridPane.add(userRoleLbl, 2, 2);

		bottomGridPane.add(userIdTF, 1, 0);
		bottomGridPane.add(userEmailTF, 1, 1);
		bottomGridPane.add(userNameTF, 1, 2);
		bottomGridPane.add(userPasswordPF, 1, 3);
		bottomGridPane.add(rbGridPane, 3, 0);
		bottomGridPane.add(userAgeTF, 3, 1);
		bottomGridPane.add(roleCb, 3, 2);
		
		bottomGridPane.add(insertNewBtn, 4, 0);
		bottomGridPane.add(updateBtn, 4, 1);
		bottomGridPane.add(removeBtn, 4, 2);
		
		bottomGridPane.add(insertBtn, 5, 0);
		bottomGridPane.add(cancelBtn, 5, 1);

		mainPane.setCenter(table);
		mainPane.setBottom(bottomGridPane);
	}

	public void positioningComponent() {
		rbGridPane.setHgap(5);
		rbGridPane.setPadding(new Insets(4, 0,0,0));
		
		bottomGridPane.setVgap(10);
		bottomGridPane.setHgap(10);
		bottomGridPane.setPadding(new Insets(10, 10, 10, 10));
		
		mainPane.setAlignment(bottomGridPane, Pos.CENTER);
		
		int BUTTON_WIDTH = 100;
		
		cancelBtn.setMinWidth(BUTTON_WIDTH);
		insertBtn.setMinWidth(BUTTON_WIDTH);
		insertNewBtn.setMinWidth(BUTTON_WIDTH);
		removeBtn.setMinWidth(BUTTON_WIDTH);
		updateBtn.setMinWidth(BUTTON_WIDTH);
	}

	public void enableInsertButton() {
		
		insertBtn.setVisible(true);
		cancelBtn.setVisible(true);
	}

	public void disableInsertButton() {
		
		insertBtn.setVisible(false);
		cancelBtn.setVisible(false);
	}

	public void setEvent() {
		table.setOnMouseClicked(x -> {
			disableInsertButton();
			User selectedUser = table.getSelectionModel().getSelectedItem();
			if (selectedUser == null) {
				clearTextField();
				return;
			}
			
			userIdTF.setText(selectedUser.getUserId() + "");
			userEmailTF.setText(selectedUser.getUserEmail());
			userNameTF.setText(selectedUser.getUserName());
			userPasswordPF.setText(selectedUser.getUserPassword());
			
			String gender = selectedUser.getUserGender();
			if(gender.equals("Male"))
			{
				maleRb.setSelected(true);
			}else {
				femaleRb.setSelected(true);
			}
			
			userAgeTF.setText(selectedUser.getUserAge() + "");
			roleCb.setValue(selectedUser.getUserRole());
		});
		updateBtn.setOnMouseClicked(x -> {
			disableInsertButton();
			User selectedUser = table.getSelectionModel().getSelectedItem();
			if (selectedUser == null) {
				clearTextField();
				return;
			}
			int userId = Integer.parseInt(userIdTF.getText());
			String userEmail = userEmailTF.getText();
			String userName = userNameTF.getText();
			String userPassword = userPasswordPF.getText();
			
			String userGender = getGenderFromRb();

			
			int userAge = Integer.parseInt(userAgeTF.getText());
			String userRole = roleCb.getValue();

			User tempUser = new User(userId, userEmail, userName, userPassword, userGender, userAge, userRole);
			if (dataValidation(tempUser, "update")) {
				selectedUser.update(userEmail, userName, userPassword, userGender, userAge, userRole);
				refreshPage();
			}
		});
		removeBtn.setOnMouseClicked(x -> {
			disableInsertButton();
			User selectedUser = table.getSelectionModel().getSelectedItem();
			if (selectedUser == null) {
				clearTextField();
				return;
			}
			selectedUser.remove();
			refreshPage();
		});
		insertNewBtn.setOnMouseClicked(x -> {
			enableInsertButton();
			clearTextField();
			userIdTF.setText(generateNewUserId() + "");
		});
		insertBtn.setOnMouseClicked(x -> {

//			Get Data
			int userId = Integer.parseInt(userIdTF.getText());
			String userEmail = userEmailTF.getText();
			String userName = userNameTF.getText();
			String userPassword = userPasswordPF.getText();
			String userGender = getGenderFromRb();
			int userAge = Integer.parseInt(userAgeTF.getText());
			String userRole = roleCb.getValue();

			User u = new User(userId, userEmail, userName, userPassword, userGender, userAge, userRole);
			if (dataValidation(u, "insert")) {
				u.save();
				refreshPage();
			}

		});
		cancelBtn.setOnMouseClicked(x -> {
			disableInsertButton();
			clearTextField();
		});
	}
	
	public String getGenderFromRb()
	{
		String g = null;
		if(maleRb.isSelected())
		{
			g = "Male";
		}else if(femaleRb.isSelected())
		{
			g = "Female";
		}
		return g;
	}

	public boolean dataValidation(User u, String str) {
		
		if(str == "insert")
		{
			if(util.isEmailExists(u.getUserEmail()))
			{
				new AlertWindow(AlertType.ERROR, "Email already registered! Please input a different email");
				return false;
			}
		}
		
		if(u.getUserName().length() < 5 || u.getUserName().length() > 25)
		{
			new AlertWindow(AlertType.ERROR, "Name must be 5 - 25 characters!");
			return false;
		}else if(!u.getUserEmail().endsWith(".com"))
		{
			new AlertWindow(AlertType.ERROR, "Please input a valid email");
			return false;
		}else if(u.getUserAge() < 12 || u.getUserAge() > 99)
		{
			new AlertWindow(AlertType.ERROR, "Age must be 12 - 99");
			return false;
		}else if(u.getUserPassword().equals(u.getUserName()))
		{
			new AlertWindow(AlertType.ERROR, "Password cannot be same as username");
			return false;
		}else if(u.getUserPassword().length() < 5 || u.getUserPassword().length()> 20) {
			new AlertWindow(AlertType.ERROR, "Password lenght must be 5 - 20 characters");
			return false;
		}
		else if(u.getUserGender() == null)
		{
			new AlertWindow(AlertType.ERROR, "Please input the gender");
			return false;
		}
		return true;
	}
	


	public void refreshPage() {
		addTable();
		clearTextField();
	}

	public int generateNewUserId() {
		int id = 1;
		Connect c = Connect.getConnection();
		String q = "SELECT * FROM user";
		ResultSet rs = c.executeQuery(q);
		try {
			while (rs.next()) {
				if (rs.isLast()) {
					int lastId = rs.getInt("userId");
					id = lastId + 1;
				}
			}
		} catch (Exception e) {
			new AlertWindow(AlertType.ERROR, "Failed generate new ID from user");
		}
		return id;
	}

	public void clearTextField() {
		userIdTF.setText("");
		userEmailTF.setText("");
		userNameTF.setText("");
		userPasswordPF.setText("");
		maleRb.setSelected(false);
		femaleRb.setSelected(false);
		userAgeTF.setText("");
		roleCb.setValue("");
	}

	public void setTable() {
		int TOTAL_COLUMN = 7;

		TableColumn<User, String> userId = new TableColumn<User, String>("User ID");
		TableColumn<User, String> userEmail = new TableColumn<User, String>("User Email");
		TableColumn<User, String> userName = new TableColumn<User, String>("User Name");
		TableColumn<User, String> userPassword = new TableColumn<User, String>("User Password");
		TableColumn<User, String> userGender = new TableColumn<User, String>("User Gender");
		TableColumn<User, String> userAge = new TableColumn<User, String>("User Age");
		TableColumn<User, String> userRole = new TableColumn<User, String>("User Role");

		userId.setCellValueFactory(new PropertyValueFactory<User, String>("userId"));
		userEmail.setCellValueFactory(new PropertyValueFactory<User, String>("userEmail"));
		userName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		userPassword.setCellValueFactory(new PropertyValueFactory<User, String>("userPassword"));
		userGender.setCellValueFactory(new PropertyValueFactory<User, String>("userGender"));
		userAge.setCellValueFactory(new PropertyValueFactory<User, String>("userAge"));
		userRole.setCellValueFactory(new PropertyValueFactory<User, String>("userRole"));

		userId.setMinWidth(Utillities.WIDTH / TOTAL_COLUMN);
		userEmail.setMinWidth(Utillities.WIDTH / TOTAL_COLUMN);
		userName.setMinWidth(Utillities.WIDTH / TOTAL_COLUMN);
		userPassword.setMinWidth(Utillities.WIDTH / TOTAL_COLUMN);
		userGender.setMinWidth(Utillities.WIDTH / TOTAL_COLUMN);
		userAge.setMinWidth(Utillities.WIDTH / TOTAL_COLUMN);
		userRole.setMinWidth(Utillities.WIDTH / TOTAL_COLUMN);

		table.getColumns().addAll(userId, userEmail, userName, userPassword, userGender, userAge, userRole);
	}

	public void addTable() {
		table.getItems().clear();
		Vector<User> userList = User.getUserFromDB();
		for (User user : userList) {
			table.getItems().add(user);
		}
	}

	public BorderPane makeManageUserPage() {
		init();
		arrangeComponent();
		positioningComponent();
		setTable();
		addTable();
		setEvent();
		return mainPane;
	}

}
