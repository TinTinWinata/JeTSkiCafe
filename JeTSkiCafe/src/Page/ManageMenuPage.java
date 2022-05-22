package Page;

import java.sql.ResultSet;
import java.util.Vector;

import Database.Connect;
import Helper.Utillities;
import Model.Menu;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class ManageMenuPage {

	private static ManageMenuPage manageMenuPage;
	private Utillities util;
	
	private BorderPane mainPane;
	private GridPane bottomGridPane;
		
	private Label menuIdLbl;
	private Label menuNameLbl;
	private Label menuTypeLbl;
	private Label menuPriceLbl;
	private Label menuStockLbl;
	
	private TextField menuIdTF;
	private TextField menuNameTF;
	private TextField menuPriceTF;
	private TextField menuStockTF;
	
	private Button insertNewBtn;
	private Button removeBtn;
	private Button updateBtn;
	
	private Button insertBtn;
	private Button cancelBtn;
	
	private ComboBox<String> menuTypeCB;
	String[] menuTypeList = {"Main Course", "Appetizer", "Dessert", "Beverage"};
	
	private TableView<Menu> table;
	
	
	public static ManageMenuPage getInstance()
	{
		if(manageMenuPage == null)
		{
			manageMenuPage = new ManageMenuPage();
		}
		return manageMenuPage;
	}
	
	public void init() {
		util = Utillities.getInstance();
		menuTypeCB = new ComboBox<String>(FXCollections.observableArrayList(menuTypeList));
		
		menuIdTF = new TextField();
		menuNameTF = new TextField();
		menuPriceTF = new TextField();
		menuStockTF = new TextField();
		
		menuIdTF.setDisable(true);
		
		menuIdLbl = new Label("Menu ID");
		menuNameLbl = new Label("Menu Name");
		menuTypeLbl = new Label("Menu Type");
		menuPriceLbl = new Label("Menu Price");
		menuStockLbl = new Label("Menu Stock");
		
		table = new TableView<Menu>();
		mainPane = new BorderPane();
		bottomGridPane = new GridPane();
		
		insertBtn = new Button("Insert");
		insertNewBtn = new Button("Insert New Menu");
		updateBtn = new Button("Update Menu");
		removeBtn = new Button("Remove Menu");
		cancelBtn = new Button("Cancel");
		
		insertBtn.setVisible(false);
		cancelBtn.setVisible(false);
	}
	
	public void arrangeComponent()
	{
		bottomGridPane.add(menuIdLbl, 0, 0);
		bottomGridPane.add(menuNameLbl, 0, 1);
		bottomGridPane.add(menuTypeLbl, 0, 2);
		bottomGridPane.add(menuPriceLbl, 2, 0);
		bottomGridPane.add(menuStockLbl, 2, 1);
		
		bottomGridPane.add(menuIdTF, 1, 0);
		bottomGridPane.add(menuNameTF, 1, 1);
		bottomGridPane.add(menuTypeCB, 1, 2);
		bottomGridPane.add(menuPriceTF, 3, 0);
		bottomGridPane.add(menuStockTF, 3, 1);
		
		bottomGridPane.add(insertNewBtn, 4, 0);
		bottomGridPane.add(updateBtn, 4, 1);
		bottomGridPane.add(removeBtn, 4, 2);
		bottomGridPane.add(insertBtn, 5, 0);
		bottomGridPane.add(cancelBtn, 5, 1);
		
		mainPane.setCenter(table);
		mainPane.setBottom(bottomGridPane);
		
	}
	
	public void positioningComponent()
	{
		bottomGridPane.setVgap(10);
		bottomGridPane.setHgap(10);
		bottomGridPane.setPadding(new Insets(10, 10, 10, 10));
		
		mainPane.setAlignment(bottomGridPane, Pos.CENTER);
		
		insertBtn.setMinWidth(150);
		updateBtn.setMinWidth(150);
		removeBtn.setMinWidth(150);
		insertNewBtn.setMinWidth(150);
		cancelBtn.setMinWidth(150);
	}
	
	public void enableInsertButton()
	{
		cancelBtn.setVisible(true);
		insertBtn.setVisible(true);
	}
	
	public void disableInsertButton()
	{
		cancelBtn.setVisible(false);
		insertBtn.setVisible(false);
	}
	
	public void setEvent()
	{
		table.setOnMouseClicked(x -> {
			disableInsertButton();
			Menu selectedMenu = table.getSelectionModel().getSelectedItem();
			if(selectedMenu == null)
			{
				clearTextField();
				return;
			}
			menuIdTF.setText(selectedMenu.getMenuId() + "");
			menuNameTF.setText(selectedMenu.getMenuName());
			menuPriceTF.setText(selectedMenu.getMenuPrice() + "");
			menuStockTF.setText(selectedMenu.getMenuStock() + "");
			menuTypeCB.setValue(selectedMenu.getMenuType());
		});
		updateBtn.setOnMouseClicked(x -> {
			disableInsertButton();
			Menu selectedMenu = table.getSelectionModel().getSelectedItem();
			if(selectedMenu == null)
			{
				clearTextField();
				return;
			}
			String menuName = menuNameTF.getText();
			String menuType = menuTypeCB.getValue();
			int menuPrice = Integer.parseInt(menuPriceTF.getText());
			int menuStock = Integer.parseInt(menuStockTF.getText());
			
			Menu tempMenu = new Menu(99, menuName, menuType, menuPrice, menuStock);
			if(dataValidation(tempMenu))
			{
				selectedMenu.update(menuName, menuType, menuPrice, menuStock);
				refreshPage();
			}
		});
		removeBtn.setOnMouseClicked(x -> {
			disableInsertButton();
			Menu selectedMenu = table.getSelectionModel().getSelectedItem();
			if(selectedMenu == null)
			{
				clearTextField();
				return;
			}
			selectedMenu.remove();
			refreshPage();
		});
		insertNewBtn.setOnMouseClicked(x -> {
			enableInsertButton();
			clearTextField();
			menuIdTF.setText(generateNewMenuId() + "");
		});
		insertBtn.setOnMouseClicked(x -> {
			
//			Get Data
			int menuId = Integer.parseInt(menuIdTF.getText());
			String menuName = menuNameTF.getText();
			String menuType = menuTypeCB.getValue();
			int menuPrice = Integer.parseInt(menuPriceTF.getText());
			int menuStock = Integer.parseInt(menuStockTF.getText());
			
			Menu m = new Menu(menuId, menuName, menuType, menuPrice, menuStock);
			if(dataValidation(m))
			{
				m.save();				
				refreshPage();
			}
			
		});
		cancelBtn.setOnMouseClicked(x -> {
			disableInsertButton();
			clearTextField();
		});	
	}
	
	public boolean dataValidation(Menu m)
	{
		if(m.getMenuName().length() < 5 | m.getMenuName().length() > 25)
		{
			new AlertWindow(AlertType.ERROR, "Name length must be 5 - 20 (inclusively)");
			return false;
		}else if(!util.isDigit(m.getMenuPrice() + ""))
		{
			new AlertWindow(AlertType.ERROR, "Price must be numeric!");
			return false;
		}else if(m.getMenuPrice() < 1)
		{
			new AlertWindow(AlertType.ERROR, "Price must be more than 0");
			return false;
		}else if(m.getMenuType().isEmpty())
		{
			new AlertWindow(AlertType.ERROR, "Please select the menu type!");
			return false;
		}else if(!util.isDigit(m.getMenuStock() + ""))
		{
			new AlertWindow(AlertType.ERROR, "Stock must be numeric!");
			return false;
		}else if(m.getMenuStock() < 1)
		{
			new AlertWindow(AlertType.ERROR, "Stock must be more than 0");
			return false;
		}
		return true;
	}
	
	public void refreshPage()
	{
		addTable();
		clearTextField();
	}
	
	public int generateNewMenuId()
	{
		int id = 1;
		Connect c = Connect.getConnection();
		String q = "SELECT * FROM menu";
		ResultSet rs = c.executeQuery(q);
		try {
			while(rs.next())
			{
				if(rs.isLast())
				{
					int lastId = rs.getInt("menuId");
					id = lastId + 1;
				}
			}
		} catch (Exception e) {
			new AlertWindow(AlertType.ERROR, "Failed generate new ID from menu");
		}
		return id;
	}
	
	public void clearTextField()
	{
		menuIdTF.setText("");
		menuNameTF.setText("");
		menuPriceTF.setText("");
		menuStockTF.setText("");
		menuTypeCB.setValue("");
	}
	
	public void setTable()
	{
		TableColumn<Menu, String> menuId = new TableColumn<Menu, String>("Id");
		TableColumn<Menu, String> menuName = new TableColumn<Menu, String>("Name");
		TableColumn<Menu, String> menuType = new TableColumn<Menu, String>("Type");
		TableColumn<Menu, String> menuPrice = new TableColumn<Menu, String>("Price");
		TableColumn<Menu, String> menuStock = new TableColumn<Menu, String>("Stock");

		menuId.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuId"));
		menuName.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuName"));
		menuType.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuType"));
		menuPrice.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuPrice"));
		menuStock.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuStock"));

		menuId.setMinWidth(Utillities.WIDTH / 5);
		menuName.setMinWidth(Utillities.WIDTH / 5);
		menuType.setMinWidth(Utillities.WIDTH / 5);
		menuPrice.setMinWidth(Utillities.WIDTH / 5);
		menuStock.setMinWidth(Utillities.WIDTH / 5);

		table.getColumns().addAll(menuId, menuName, menuType, menuPrice, menuStock);
	}
	
	public void addTable()
	{
		table.getItems().clear();
		Vector<Menu> menuList = Menu.getMenuFromDB();
		for (Menu menu : menuList) {
			table.getItems().add(menu);
		}
	}
	
	public BorderPane makeManageMenuPage()
	{
		init();
		arrangeComponent();
		positioningComponent();
		setTable();
		addTable();
		setEvent();
		return mainPane;
	}
	
}
