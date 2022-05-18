package view.stage;

import controller.event.scene.IndexPageEvents;
import controller.event.stage.AppWindowEvents;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.scene.IndexPage;

public class AppWindow extends Stage {
	private final Double STAGE_WIDTH = 1280.0;
	private final Double STAGE_HEIGHT = 720.0;
	private String STAGE_TITLE = "Market of the Arts";
	private IndexPage indexPage = null;
	
	public AppWindow() {
		setTitle(STAGE_TITLE);
		setWidth(STAGE_WIDTH);
		setHeight(STAGE_HEIGHT);
		
		setOnShowing(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				AppWindowEvents.appWindowLoaded();
			}
		});
	}
	
	public void showIndexPage() {
		if(indexPage == null)
			indexPage = new IndexPage();
		setScene(indexPage.getScene());
		IndexPageEvents.indexPageLoaded();
	}
	
	public IndexPage getIndexPage() {
		return indexPage;
	}
}
