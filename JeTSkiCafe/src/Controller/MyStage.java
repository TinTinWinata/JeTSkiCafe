package Controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MyStage {

	private Stage stage;
	private static MyStage myStage;
	
	public static MyStage getInstance()
	{
		if(myStage == null)
		{
			myStage = new MyStage();																					
		}
		return myStage;
	}
	
	public void setTitle(String title)
	{
		this.stage.setTitle(title);
	}
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	
	public void setScene(Scene scene)
	{
			this.stage.setScene(scene);
	}

	
	public void setIcon(String str)
	{
		Image icon = new Image("file:logo.png");
		this.stage.getIcons().add(icon);
	}
	
	public void showStage()
	{
		this.stage.setResizable(false);
		this.stage.show();
	}

	
	
}
