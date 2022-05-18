package view.scene;

import controller.event.internal_window.ArtManagerPageEvents;
import controller.event.internal_window.ArtMarketPageEvents;
import controller.event.internal_window.TransactionHistoryPageEvents;
import controller.event.scene.IndexPageEvents;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.internal_window.ArtManagerPage;
import view.internal_window.MarketPage;
import view.internal_window.TransactionHistoryPage;

public class IndexPage extends Page{
	
	private BorderPane border = null;
	private MenuBar menuBar = null;
	private Menu menu = null;
	private MenuItem artMarket = null;
	private MenuItem artManager = null;
	private MenuItem transactionHistory = null;
	private Menu accountMenu = null;
	private MenuItem logoutMenu = null;
	private Pane internalFrame = null;
	private ArtManagerPage artManagerPage = null;
	private MarketPage artMarketPage = null;
	private TransactionHistoryPage txHistoryPage = null;
	
	public IndexPage() {
		border = new BorderPane();
		
		menuBar = new MenuBar();
			// Menu for Art Market, Art Manager, and Transaction History
			menu = new Menu("Menu");
				artMarket = new MenuItem("Art Market");
				artMarket.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						IndexPageEvents.artMarketButtonClicked();
					}
				});
				menu.getItems().add(artMarket);
				
				artManager = new MenuItem("Art Manager");
				artManager.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						IndexPageEvents.artManagerButtonClicked();
					}
				});
				menu.getItems().add(artManager);
				
				transactionHistory = new MenuItem("Transactions");
				transactionHistory.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						IndexPageEvents.transactionHistoryButtonClicked();
					}
				});
				menu.getItems().add(transactionHistory);
			menuBar.getMenus().add(menu);

			// Menu for Log out
			accountMenu = new Menu("Account");
				logoutMenu = new MenuItem("Logout");
				logoutMenu.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						IndexPageEvents.logoutButtonClicked();
					}
				});
				accountMenu.getItems().add(logoutMenu);
			menuBar.getMenus().add(accountMenu);
		border.setTop(menuBar);
		
		internalFrame = new Pane();
		String imageUrl = getClass().getResource("/statue_of_david_15.png").toString();
		BackgroundImage background = new BackgroundImage(new Image(imageUrl, 512, 512, true, true), 
				BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, 
				new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
				BackgroundSize.DEFAULT);
		internalFrame.setBackground(new Background(background));
		border.setCenter(internalFrame);
		
		this.scene = new Scene(border);
	}
	
	public void showArtManagerPage() {
		if(artManagerPage == null) {
			artManagerPage = new ArtManagerPage();
			artManagerPage.getWindow().setOnClosedAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					ArtManagerPageEvents.artManagerClosed();
				}
			});
			internalFrame.getChildren().add(artManagerPage.getWindow());			
			ArtManagerPageEvents.artManagerLoaded();
		}
	}
	
	public void showArtMarketPage() {
		if(artMarketPage == null) {
			artMarketPage = new MarketPage();
			artMarketPage.getWindow().setOnClosedAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					ArtMarketPageEvents.artMarketClosed();
				}
			});
			internalFrame.getChildren().add(artMarketPage.getWindow());
			ArtMarketPageEvents.artMarketLoaded();
		}
	}
	
	public void showTransactionHistoryPage() {
		if(txHistoryPage == null) {
			txHistoryPage = new TransactionHistoryPage();
			txHistoryPage.getWindow().setOnClosedAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					TransactionHistoryPageEvents.txHistoryClosed();
				}
			});
			internalFrame.getChildren().add(txHistoryPage.getWindow());
			TransactionHistoryPageEvents.transactionHistoryLoaded();
		}
	}
	
	public void destroyArtManagerPage() {
		artManagerPage = null;
	}
	
	public void destroyArtMarketPage() {
		artMarketPage = null;
	}
	
	public void destroyTxHistoryPage() {
		txHistoryPage = null;
	}

	public MenuItem getArtMarket() {
		return artMarket;
	}

	public MenuItem getArtManager() {
		return artManager;
	}

	public MenuItem getTransactionHistory() {
		return transactionHistory;
	}
	
	public Pane getInternalFrame() {
		return internalFrame;
	}

	public ArtManagerPage getArtManagerPage() {
		return artManagerPage;
	}

	public MarketPage getArtMarketPage() {
		return artMarketPage;
	}

	public TransactionHistoryPage getTxHistoryPage() {
		return txHistoryPage;
	}
}
