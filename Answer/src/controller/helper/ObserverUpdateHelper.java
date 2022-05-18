package controller.helper;

import java.sql.SQLException;

import model.handler.ArtHandler;
import model.handler.TransactionHandler;

public class ObserverUpdateHelper {
	public static void updateAllObservers() throws ClassNotFoundException, SQLException {
		TransactionHandler.updateObservableCartListInstance();
		TransactionHandler.updateObservableTransactionListInstance();
		ArtHandler.updateObservableArtManagerListInstance();
		ArtHandler.updateObservableArtMarketListInstance();
	}
}
