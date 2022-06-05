package Page;

import Controller.MyScene;
import Helper.Utillities;
import Model.User;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MenuPage {

	private static MenuPage menuPage;
	
	private User activeUser;
	
	private BorderPane mainPane;
	ListView<String> listView;
	private MenuBar menuBar;
	
	private Menu userMenu;
	private Menu transactionMenu;
	private Menu manageMenu;
	private Menu adminMenu;
	
	private MenuItem logoff;
	private MenuItem exit;
	private MenuItem order;
	private MenuItem transaction;
	private MenuItem menu;
	private MenuItem user;
	
	public static MenuPage getInstance()
	{
		if(menuPage == null)
		{
			menuPage = new MenuPage();
		}
		return menuPage;
	}
	
	public void userInit()
	{
		logoff = new MenuItem("Logoff");
		exit = new MenuItem("Exit");
		order = new MenuItem("Order Menu");	
		transaction = new MenuItem("Transaction");
		
		mainPane = new BorderPane();
		
		menuBar = new MenuBar();
		
		userMenu = new Menu("User");
		transactionMenu = new Menu("Transaction");
	}
	
	public void userComponent()
	{
		userMenu.getItems().add(logoff);
		userMenu.getItems().add(exit);
		
		transactionMenu.getItems().add(order);
		transactionMenu.getItems().add(transaction);
		
		menuBar.getMenus().add(userMenu);
		menuBar.getMenus().add(transactionMenu);
		
		mainPane.setTop(menuBar);
	}
	
	public void adminInit()
	{
		logoff = new MenuItem("Logoff");
		exit = new MenuItem("Exit");
		
		menu = new MenuItem("Manage Menu");
		user = new MenuItem("Manage User");
		
		mainPane = new BorderPane();
		menuBar = new MenuBar();
		
		adminMenu = new Menu("Admin");
		manageMenu = new Menu("Manage");
	}
	
	public void adminComponent()
	{
		adminMenu.getItems().add(logoff);
		adminMenu.getItems().add(exit);
		
		manageMenu.getItems().add(menu);
		manageMenu.getItems().add(user);
		
		menuBar.getMenus().add(adminMenu);
		menuBar.getMenus().add(manageMenu);
		
		mainPane.setTop(menuBar);
	}
	

	public void setEvent()
	{
		transaction.setOnAction(x -> {
			TransactionPage transactionPage = TransactionPage.getInstance();
			mainPane.setCenter(transactionPage.makeTransactionPage());
		});
		
		exit.setOnAction(x -> {
			System.exit(0);
		});
		logoff.setOnAction(x -> {
			User.setActiveUser(null);
			LoginPage loginPage = LoginPage.getInstance();
			loginPage.loginPage();
		});
		order.setOnAction(x -> {
			OrderPage orderPage = OrderPage.getInstance();
			mainPane.setCenter(orderPage.makeTransactionPage());
		});
	}
	
	public void adminEvent()
	{
		menu.setOnAction(x -> {
			ManageMenuPage manageMenuPage = ManageMenuPage.getInstance();
			mainPane.setCenter(manageMenuPage.makeManageMenuPage());
		});
		user.setOnAction(x -> {
			ManageUserPage manageUserPage = ManageUserPage.getInstance();
			mainPane.setCenter(manageUserPage.makeManageUserPage());
		});
		logoff.setOnAction(x -> {
			User.setActiveUser(null);
			LoginPage loginPage = LoginPage.getInstance();
			loginPage.loginPage();
		});
		exit.setOnAction(x -> {
			System.exit(0);
		});
	}
	
	public void makeUserPage()
	{		
		userInit();
		userComponent();
		setEvent();
		setWallpaper();
		MyScene.changeScene(mainPane, "JeTSki Cafe",Utillities.WIDTH, Utillities.HEIGHT);
	}
	
	public void makeAdminPage()
	{
		
		adminInit();
		adminComponent();
		adminEvent();
		setWallpaper();
		MyScene.changeScene(mainPane, "JetSki Cafe", Utillities.WIDTH, Utillities.HEIGHT);
	}
	
	
	public void menuPage()
	{
		activeUser = User.getActiveUser();
		
		if(activeUser.getUserRole().equals("user"))
		{
			makeUserPage();
		}else if(activeUser.getUserRole().equals("admin"))
		{
			makeAdminPage();
		}
	}
	
	public void setWallpaper()
	{
		Image wallpaper = new Image("file:cafe.jpg");
		ImageView imageView = new ImageView(wallpaper);
		imageView.setOpacity(0.7);
//	      imageView.setX(10);
//	      imageView.setY(10);
//	      imageView.setFitWidth(575);
//	      imageView.setPreserveRatio(true);
	      
		mainPane.setCenter(imageView);
	}
	
}
