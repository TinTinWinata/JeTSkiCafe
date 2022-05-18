package view.internal_window;

import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;

public class InternalWindow {
	protected Window window = null;
	
	public InternalWindow() {
		window = new Window();
		window.setLayoutX(0);
		window.setLayoutY(0);
		window.setPrefSize(1280, 720);
		window.getRightIcons().add(new MinimizeIcon(window));
		window.getRightIcons().add(new CloseIcon(window));
	}

	public Window getWindow() {
		return window;
	}
}
