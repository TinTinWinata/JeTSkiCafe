package Page;

import Helper.Utillities;
import Model.Cart;
import Model.Menu;
import Model.Transaction;
import javafx.geometry.Insets;
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
	
	public static OrderPage getInstance()
	{
		if(orderPage == null)
		{
			orderPage = new OrderPage();
		}
		return orderPage;
	}
	
	public void init()
	{
		mainBorderPane = new BorderPane();
		bottomGridPane = new GridPane();
		uiGridPane = new GridPane();
		
		table = new TableView<Menu>();
		cartTable = new TableView<Cart>();
		
		addToCartBtn = new Button("Add to cart");
		removeCartBtn = new Button("Remove cart");
		orderBtn = new Button("Order");
		quantitySpinner = new Spinner<Integer>(1, 10, 1);
	}
	
	
	public void arrangeComponent()
	{
		bottomGridPane.add(cartTable, 0, 0);
		bottomGridPane.add(uiGridPane, 1, 0);
		
		uiGridPane.add(quantitySpinner, 0, 1);
		uiGridPane.add(addToCartBtn, 0, 2);
		uiGridPane.add(removeCartBtn, 0, 3);
		uiGridPane.add(orderBtn, 0, 4);
		
		mainBorderPane.setCenter(table);
		mainBorderPane.setBottom(bottomGridPane);
	}
	
	public void positioningComponent()
	{
		bottomGridPane.setHgap(10);
		bottomGridPane.setVgap(10);
		bottomGridPane.setPadding(new Insets(10, 10, 10, 10));
		
		cartTable.minHeight(Utillities.HEIGHT/ 2);
		
		uiGridPane.setVgap(10);
		
		addToCartBtn.setMinWidth(400);
		removeCartBtn.setMinWidth(400); 
		orderBtn.setMinWidth(400); 
		quantitySpinner.setMinWidth(400);
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
		
		menuId.setMinWidth(Utillities.WIDTH / 3);
		menuName.setMinWidth(Utillities.WIDTH / 3);
		menuType.setMinWidth(Utillities.WIDTH / 3);
		menuPrice.setMinWidth(Utillities.WIDTH / 3);
		menuStock.setMinWidth(Utillities.WIDTH / 3);
		
		table.getColumns().addAll(menuId, menuName, menuType, menuPrice, menuStock);	
		
		TableColumn<Cart, String> cartMenuId =  new TableColumn<Cart, String>("menuId");
		TableColumn<Cart, String> quantity =  new TableColumn<Cart, String>("quantity");
		
		cartMenuId.setCellValueFactory(new PropertyValueFactory<Cart, String>("menuId"));
		quantity.setCellValueFactory(new PropertyValueFactory<Cart, String>("quantity"));
		
		cartMenuId.setMinWidth(Utillities.WIDTH / 4);
		quantity.setMinWidth(Utillities.WIDTH / 4);
		
		cartTable.getColumns().addAll(cartMenuId, quantity);
	}
	
	public BorderPane makeTransactionPage()
	{
		init();
		arrangeComponent();
		positioningComponent();
		setTable();
		return mainBorderPane;
	}
	
}
