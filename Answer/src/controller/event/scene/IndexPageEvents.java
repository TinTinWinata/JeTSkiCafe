package controller.event.scene;

import controller.Staging;
import model.handler.AuthHandler;
import view.internal_window.ArtManagerPage;
import view.internal_window.MarketPage;
import view.internal_window.TransactionHistoryPage;
import view.scene.IndexPage;

public class IndexPageEvents {
	public static void indexPageLoaded() {
		String role = AuthHandler.getCurrentUserRole();
		
		switch(role) {
			case AuthHandler.ROLE_USER:
				Staging.getAppWindow().getIndexPage().getArtMarket().setVisible(true);
				Staging.getAppWindow().getIndexPage().getArtManager().setVisible(true);
				Staging.getAppWindow().getIndexPage().getTransactionHistory().setVisible(true);
				break;
				
			case AuthHandler.ROLE_ADMINISTRATOR:
				Staging.getAppWindow().getIndexPage().getArtMarket().setVisible(false);
				Staging.getAppWindow().getIndexPage().getArtManager().setVisible(true);
				Staging.getAppWindow().getIndexPage().getTransactionHistory().setVisible(true);
				break;
		}
	}
	
	public static void logoutButtonClicked() {
		IndexPage page = Staging.getAppWindow().getIndexPage();
		ArtManagerPage artManager = page.getArtManagerPage();
		MarketPage artMarket = page.getArtMarketPage();
		TransactionHistoryPage txHistory = page.getTxHistoryPage();
		
		if(artManager != null)
			artManager.getWindow().close();
		
		if(artMarket != null)
			artMarket.getWindow().close();
		
		if(txHistory != null)
			txHistory.getWindow().close();
		
		AuthHandler.logout();
		Staging.showAuthWindow();
	}
	
	public static void artMarketButtonClicked() {
		Staging.getAppWindow().getIndexPage().showArtMarketPage();
	}
	
	public static void artManagerButtonClicked() {
		Staging.getAppWindow().getIndexPage().showArtManagerPage();
	}
	
	public static void transactionHistoryButtonClicked() {
		Staging.getAppWindow().getIndexPage().showTransactionHistoryPage();
	}
}
