package controller.event.internal_window;

import java.sql.SQLException;

import controller.Staging;
import controller.helper.AlertWindowHelper;
import controller.helper.ObserverUpdateHelper;
import model.entity.Art;
import model.entity.Cart;
import model.exception.ArtNotSelectedException;
import model.exception.CartIsEmptyException;
import model.exception.CartNotSelectedException;
import model.exception.InsiderTradingException;
import model.exception.InvalidCopiesException;
import model.handler.ArtHandler;
import model.handler.TransactionHandler;

public class ArtMarketPageEvents {
	public static void artMarketClosed() {
		Staging.getAppWindow().getIndexPage().destroyArtMarketPage();
	}
	
	public static void artMarketLoaded() {
		Staging.getAppWindow().getIndexPage().getArtMarketPage().getArtTableView().setItems(ArtHandler.getObservableArtMarketListInstance());
		Staging.getAppWindow().getIndexPage().getArtMarketPage().getCartTableView().setItems(TransactionHandler.getObservableCartListInstance());
		updateTableView();
	}
	
	public static void artTableDataSelected(Art selectedData) {
		if(selectedData != null) {
			TransactionHandler.setSelectedArt(selectedData);
		}
	}
	
	public static void cartTableDataSelected(Cart selectedData) {
		if(selectedData != null) {
			TransactionHandler.setSelectedCart(selectedData);
		}
	}
	
	public static void addCartButtonClicked() {
		try {
			Integer copies = Staging.getAppWindow().getIndexPage().getArtMarketPage().getCopiesField().getValue();
			TransactionHandler.addToCart(copies);
			updateTableView();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (ArtNotSelectedException e) {
			AlertWindowHelper.showErrorWindow("Art not selected. Select an art, perhaps?");
		} catch (InvalidCopiesException e) {
			AlertWindowHelper.showErrorWindow("Copies must be more than 0!");
		} catch (InsiderTradingException e) {
			AlertWindowHelper.showErrorWindow("You can't buy your own art, that's insider trading!");
		}
	}
	
	public static void removeCartButtonClicked() {
		try {
			Integer copies = Staging.getAppWindow().getIndexPage().getArtMarketPage().getCopiesField().getValue();
			TransactionHandler.removeFromCart(copies);
			updateTableView();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (CartNotSelectedException e) {
			AlertWindowHelper.showErrorWindow("Cart not selected. Select one from your cart, perhaps?");
		} catch (InvalidCopiesException e) {
			AlertWindowHelper.showErrorWindow("Copies must be more than 0!");
		}
	}
	
	public static void checkoutButtonClicked() {
		try {
			TransactionHandler.checkout();
			AlertWindowHelper.showInformationWindow("Transaction recorded!");
			updateTableView();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (CartIsEmptyException e) {
			AlertWindowHelper.showErrorWindow("Your cart is empty. Get some more arts, perhaps?");
		}
	}
	
	private static void updateTableView() {
		try {
			ObserverUpdateHelper.updateAllObservers();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		}
	}
}
