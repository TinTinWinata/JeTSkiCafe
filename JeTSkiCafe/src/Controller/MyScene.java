package Controller;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class MyScene {

	private static Scene scene;
	
	public static void changeScene(Parent parent, String title,int width, int height)
	{
		MyStage stage = MyStage.getInstance();
		stage.setTitle(title);
		scene = new Scene(parent, width, height);
		stage.setScene(scene);
	}
	
	public static void changeScene(Parent parent,int width, int height)
	{
		MyStage stage = MyStage.getInstance();
		stage.setTitle("JeTSki Cafe");
		
		scene = new Scene(parent, width, height);
		stage.setScene(scene);
	}
}
