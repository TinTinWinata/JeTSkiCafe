package Page;

import Controller.MyScene;
import Model.User;
import javafx.scene.layout.BorderPane;

public class Menu {

	private static Menu menu;
	private User activeUser;
	
	private BorderPane mainPane;
	
	
	public static Menu getInstance()
	{
		if(menu == null)
		{
			menu = new Menu();
		}
		return menu;
	}
	
	public void makeUserPage()
	{
		
	}
	
	public void makeAdminPage()
	{
		
		
		
	}
	
	public void menuPage()
	{
		activeUser = User.getActiveUser();
		
		if(activeUser.getUserRole() == "user")
		{
			
		}
		
//		MyScene.changeScene(parent, , width, height);
	}
	
}
