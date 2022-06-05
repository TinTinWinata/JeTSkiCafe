package Page;

import java.sql.ResultSet;

import Database.Connect;
import Helper.Utillities;
import Model.Cart;
import Model.Menu;
import Model.Transaction;
import Model.TransactionDetail;
import Model.User;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OrderPage {

	private static OrderPage orderPage;

	private BorderPane mainBorderPane;

	private GridPane bottomGridPane;
	private GridPane uiGridPane;

	private TableView<Menu> table;
	private TableView<Cart> cartTable;

	private Spinner<Integer> quantitySpinner;

	private Button addToCartBtn;
	private Button removeCartBtn;
	private Button orderBtn;

	private Utillities util;

	public static OrderPage getInstance() {
		if (orderPage == null) {
			orderPage = new OrderPage();
		}
		return orderPage;
	}

	public void init() {
		util = Utillities.getInstance();

		mainBorderPane = new BorderPane();
		bottomGridPane = new GridPane();
		uiGridPane = new GridPane();

		table = new TableView<Menu>();
		cartTable = new TableView<Cart>();

		addToCartBtn = new Button("Add to cart");
		removeCartBtn = new Button("Remove cart");
		orderBtn = new Button("Order");
		quantitySpinner = new Spinner<Integer>(1, 99, 1);
	}

	public void arrangeComponent() {
		bottomGridPane.add(cartTable, 0, 0);
		bottomGridPane.add(uiGridPane, 1, 0);

		uiGridPane.add(quantitySpinner, 0, 1);
		uiGridPane.add(addToCartBtn, 0, 2);
		uiGridPane.add(removeCartBtn, 0, 3);
		uiGridPane.add(orderBtn, 0, 4);

		mainBorderPane.setCenter(table);
		mainBorderPane.setBottom(bottomGridPane);
	}

	public void positioningComponent() {
		bottomGridPane.setHgap(10);
		bottomGridPane.setVgap(10);
		bottomGridPane.setPadding(new Insets(10, 10, 10, 10));

		cartTable.minHeight(Utillities.HEIGHT / 2);

		uiGridPane.setVgap(10);

		addToCartBtn.setMinWidth(400);
		removeCartBtn.setMinWidth(400);
		orderBtn.setMinWidth(400);
		quantitySpinner.setMinWidth(400);
	}

	public void addTableData() {
		table.getItems().clear();
		Connect c = Connect.getConnection();
		String query = "SELECT * FROM menu";
		ResultSet rs = c.executeQuery(query);
		try {
			while (rs.next()) {
				int menuId = rs.getInt("menuId");
				String menuName = rs.getString("menuName");
				String menuType = rs.getString("menuType");
				int menuPrice = rs.getInt("menuPrice");
				int menuStock = rs.getInt("menuStock");
				Menu m = new Menu(menuId, menuName, menuType, menuPrice, menuStock);
				table.getItems().add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTable() {
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

		TableColumn<Cart, String> cartMenuId = new TableColumn<Cart, String>("menuId");
		TableColumn<Cart, String> quantity = new TableColumn<Cart, String>("quantity");

		cartMenuId.setCellValueFactory(new PropertyValueFactory<Cart, String>("menuId"));
		quantity.setCellValueFactory(new PropertyValueFactory<Cart, String>("quantity"));

		cartMenuId.setMinWidth(Utillities.WIDTH / 4);
		quantity.setMinWidth(Utillities.WIDTH / 4);

		cartTable.getColumns().addAll(cartMenuId, quantity);
	}

	public void addCartTable() {
		cartTable.getItems().clear();
		Connect con = Connect.getConnection();
		String query = "SELECT * FROM cart";
		ResultSet rs = con.executeQuery(query);
		try {
			while (rs.next()) {
				int menuId = rs.getInt("menuId");
				int quantity = rs.getInt("quantity");
				Cart c = new Cart(User.getActiveUser().getUserId(), menuId, quantity);
				cartTable.getItems().add(c);
			}
		} catch (Exception e) {

		}
	}
	
	
	private void refreshPage()
	{
		addTableData();
		
	}

	private boolean isCartExists(Menu m) {
		Connect con = Connect.getConnection();
		String query = "SELECT * FROM cart";
		ResultSet rs = con.executeQuery(query);
		try {
			while (rs.next()) {
				int userId = rs.getInt("userId");
				int menuId = rs.getInt("menuId");
				if (m.getMenuId().equals(menuId) && User.getActiveUser().getUserId().equals(userId)) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	
	
	public void setEvent() {		
		addToCartBtn.setOnMouseClicked(x -> {
			Menu selectedMenu = table.getSelectionModel().getSelectedItem();
			if (selectedMenu == null) {
				new AlertWindow(AlertType.ERROR, "Please select at least 1 menu");
			} else if (isCartExists(selectedMenu)) {
				new AlertWindow(AlertType.ERROR, "Menu already in the cart");
			} else if(quantitySpinner.getValue() > selectedMenu.getMenuStock()){
				new AlertWindow(AlertType.ERROR, "Quantity can't exceed the stock");
			}else{
				int quantity = quantitySpinner.getValue();
				Cart newCart = new Cart(User.getActiveUser().getUserId(), selectedMenu.getMenuId(), quantity);
				newCart.save();
				addCartTable();
			}
		}); 
		removeCartBtn.setOnMouseClicked(x -> {
			Cart selectedCart = cartTable.getSelectionModel().getSelectedItem();
			if (selectedCart == null) {
				new AlertWindow(AlertType.ERROR, "Please select the cart");
			} else {
				selectedCart.remove();
				addCartTable();
			}
		});
		orderBtn.setOnMouseClicked(x -> {
			ObservableList<Cart> cartList = cartTable.getItems();

			if (cartList.isEmpty()) {
				new AlertWindow(AlertType.ERROR, "Please select at least 1 menu");
				return;
			}

			Transaction t = new Transaction(User.getActiveUser().getUserId(), util.getDate());
			t.save();

			for (Cart cart : cartList) {
				Menu.loseStock(cart.getMenuId(), cart.getQuantity());
				
				TransactionDetail td = new TransactionDetail(t.getTransactionId(), cart.getMenuId(), cart.getQuantity());
				td.save();
				cart.remove();
			}
			
			cartTable.getItems().clear();
			refreshPage();
		});
	}

	public int generateTransactionId() {
		int id = 1;
		Connect c = Connect.getConnection();
		String q = "SELECT * FROM transaction";
		ResultSet rs = c.executeQuery(q);
		try {
			while (rs.next()) {
				if (rs.last()) {
					int lastId = rs.getInt("transactionId");
					id = lastId + 1;
					break;
				}
			}
		} catch (Exception e) {
		}
		return id;
	}

	public BorderPane makeTransactionPage() {
		init();
		arrangeComponent();
		positioningComponent();
		setTable();
		addTableData();
		addCartTable();
		setEvent();
		return mainBorderPane;
	}

}
