package Page;

import java.sql.ResultSet;
import java.util.Vector;

import Database.Connect;
import Helper.Utillities;
import Model.Menu;
import Model.User;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ManageUserPage {

	private static ManageUserPage manageUserPage;
	private Utillities util;

	private BorderPane mainPane;
	private GridPane bottomGridPane;

	private TableView<User> table;

	private Label userIdLbl;
	private Label userEmailLbl;
	private Label userNameLbl;
	private Label userPasswordLbl;
	private Label userGenderLbl;
	private Label userAgeLbl;
	private Label userRoleLbl;

	private TextField userIdTF;
	private TextField userEmailTF;
	private TextField userNameTF;
	private TextField userPasswordTF;
	private TextField userGenderTF;
	private TextField userAgeTF;
	private TextField userRoleTF;

	private Button insertNewBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button insertBtn;
	private Button cancelBtn;

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
		userPasswordTF = new TextField();
		userGenderTF = new TextField();
		userAgeTF = new TextField();
		userRoleTF = new TextField();
		
		userIdTF.setDisable(true);

		userIdLbl = new Label("User ID");
		userEmailLbl = new Label("User Email");
		userNameLbl = new Label("User Name");
		userPasswordLbl = new Label("User Password");
		userGenderLbl = new Label("User Gender");
		userAgeLbl = new Label("User Age");
		userRoleLbl = new Label("User Role");

	}

	public void arrangeComponent() {
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
		bottomGridPane.add(userPasswordTF, 1, 3);
		bottomGridPane.add(userGenderTF, 3, 0);
		bottomGridPane.add(userAgeTF, 3, 1);
		bottomGridPane.add(userRoleTF, 3, 2);
		
		bottomGridPane.add(insertNewBtn, 4, 0);
		bottomGridPane.add(updateBtn, 4, 1);
		bottomGridPane.add(removeBtn, 4, 2);
		
		bottomGridPane.add(insertBtn, 5, 0);
		bottomGridPane.add(cancelBtn, 5, 1);

		mainPane.setCenter(table);
		mainPane.setBottom(bottomGridPane);
	}

	public void positioningComponent() {
		bottomGridPane.setVgap(10);
		bottomGridPane.setHgap(10);
		bottomGridPane.setPadding(new Insets(10, 10, 10, 10));

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
			userPasswordTF.setText(selectedUser.getUserPassword());
			userGenderTF.setText(selectedUser.getUserGender());
			userAgeTF.setText(selectedUser.getUserAge() + "");
			userRoleTF.setText(selectedUser.getUserRole());
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
			String userPassword = userPasswordTF.getText();
			String userGender = userGenderTF.getText();
			int userAge = Integer.parseInt(userAgeTF.getText());
			String userRole = userRoleTF.getText();

			User tempUser = new User(userId, userEmail, userName, userPassword, userGender, userAge, userRole);
			if (dataValidation(tempUser)) {
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
			String userPassword = userPasswordTF.getText();
			String userGender = userGenderTF.getText();
			int userAge = Integer.parseInt(userAgeTF.getText());
			String userRole = userRoleTF.getText();

			User u = new User(userId, userEmail, userName, userPassword, userGender, userAge, userRole);
			if (dataValidation(u)) {
				u.save();
				refreshPage();
			}

		});
		cancelBtn.setOnMouseClicked(x -> {
			disableInsertButton();
			clearTextField();
		});
	}

	public boolean dataValidation(User u) {
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
		userPasswordTF.setText("");
		userGenderTF.setText("");
		userAgeTF.setText("");
		userRoleTF.setText("");
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
