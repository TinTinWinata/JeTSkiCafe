package Page;

import Helper.Utillities;
import Model.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ManageMenuPage {

	private static ManageMenuPage manageMenuPage;
	
	private BorderPane mainPane;
	private GridPane bottomGridPane;
	
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
		mainPane = new BorderPane();
		bottomGridPane = new GridPane();
	}
	
	public void arrangeComponent()
	{
		mainPane.setCenter(table);
//		bottomGridPane.add(, 0, 0);
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
	
	public BorderPane makeManageMenuPage()
	{
		init();
		arrangeComponent();
		
		setTable();
		
		return mainPane;
	}
	
}
