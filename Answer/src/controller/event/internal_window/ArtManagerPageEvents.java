package controller.event.internal_window;

import java.sql.SQLException;

import controller.Staging;
import controller.helper.AlertWindowHelper;
import controller.helper.DateFormatHelper;
import controller.helper.ObserverUpdateHelper;
import model.entity.Art;
import model.exception.InvalidDescriptionException;
import model.exception.InvalidIDException;
import model.exception.InvalidPriceException;
import model.exception.InvalidTitleException;
import model.exception.InvalidURLException;
import model.handler.ArtHandler;
import view.internal_window.ArtManagerPage;

public class ArtManagerPageEvents {
	public static void artManagerClosed() {
		Staging.getAppWindow().getIndexPage().destroyArtManagerPage();
	}
	
	public static void artManagerLoaded() {
		Staging.getAppWindow().getIndexPage().getArtManagerPage().getTableView().setItems(ArtHandler.getObservableArtManagerListInstance());
		updateTableView();
	}
	
	public static void tableDataSelected(Art selectedData) {
		if(selectedData != null) {
			ArtManagerPage page = Staging.getAppWindow().getIndexPage().getArtManagerPage();
			page.getIdField().setText(selectedData.getId());
			page.getArtistField().setText(selectedData.getArtistId());
			page.getDateField().setText(DateFormatHelper.getLongDate(selectedData.getPublishedDate()));
			page.getTitleField().setText(selectedData.getTitle());
			page.getUrlField().setText(selectedData.getUrl());
			page.getPriceField().getValueFactory().setValue(selectedData.getPrice());
			page.getDescriptionField().setText(selectedData.getDescription());
		}
	}
	
	public static void publishButtonClicked() {
		try {
			ArtManagerPage page = Staging.getAppWindow().getIndexPage().getArtManagerPage();
			String title = page.getTitleField().getText();
			String url = page.getUrlField().getText();
			Integer price = page.getPriceField().getValue();
			String description = page.getDescriptionField().getText();
			String id = ArtHandler.insertArt(title, url, price, description);
			AlertWindowHelper.showInformationWindow(String.format("Art published with id %s", id));
			updateTableView();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (InvalidTitleException e) {
			AlertWindowHelper.showErrorWindow("Title must be filled!");
		} catch (InvalidURLException e) {
			AlertWindowHelper.showErrorWindow("URL must start with 'http'!");
		} catch (InvalidPriceException e) {
			AlertWindowHelper.showErrorWindow("Price must be more than 0!");
		} catch (InvalidDescriptionException e) {
			AlertWindowHelper.showErrorWindow("Description must be filled!");
		}
	}
	
	public static void updateButtonClicked() {
		try {
			ArtManagerPage page = Staging.getAppWindow().getIndexPage().getArtManagerPage();
			String id = page.getIdField().getText();
			String title = page.getTitleField().getText();
			String url = page.getUrlField().getText();
			Integer price = page.getPriceField().getValue();
			String description = page.getDescriptionField().getText();
			ArtHandler.updateArt(id, title, url, price, description);
			AlertWindowHelper.showInformationWindow(String.format("Art %s updated", id));
			updateTableView();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (InvalidIDException e) {
			AlertWindowHelper.showErrorWindow("Invalid ID. Pick an art, perhaps?");
		} catch (InvalidTitleException e) {
			AlertWindowHelper.showErrorWindow("Title must be filled!");
		} catch (InvalidURLException e) {
			AlertWindowHelper.showErrorWindow("URL must start with 'http'!");
		} catch (InvalidPriceException e) {
			AlertWindowHelper.showErrorWindow("Price must be more than 0!");
		} catch (InvalidDescriptionException e) {
			AlertWindowHelper.showErrorWindow("Description must be filled!");
		}
	}

	public static void withdrawButtonClicked() {
		try {
			ArtManagerPage page = Staging.getAppWindow().getIndexPage().getArtManagerPage();
			String id = page.getIdField().getText();
			ArtHandler.deleteArt(id);
			AlertWindowHelper.showInformationWindow(String.format("Art %s deleted", id));
			page.clearFields();
			updateTableView();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		} catch (InvalidIDException e) {
			AlertWindowHelper.showErrorWindow("Invalid ID. Pick an art, perhaps?");
		}
	}
	
	public static void clearButtonClicked() {
		ArtManagerPage page = Staging.getAppWindow().getIndexPage().getArtManagerPage();
		page.clearFields();
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
