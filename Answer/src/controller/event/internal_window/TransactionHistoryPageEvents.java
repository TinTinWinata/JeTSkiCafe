package controller.event.internal_window;

import java.sql.SQLException;

import controller.Staging;
import controller.helper.AlertWindowHelper;
import model.handler.TransactionHandler;

public class TransactionHistoryPageEvents {
	public static void txHistoryClosed() {
		Staging.getAppWindow().getIndexPage().destroyTxHistoryPage();
	}
	
	public static void transactionHistoryLoaded() {
		Staging.getAppWindow().getIndexPage().getTxHistoryPage().getTxTableView().setItems(TransactionHandler.getObservableTransactionListInstance());
		updateTable();
	}
	
	public static void updateTable() {
		try {
			TransactionHandler.updateObservableTransactionListInstance();
		} catch (ClassNotFoundException e) {
			AlertWindowHelper.showJDBCErrorWindow();
		} catch (SQLException e) {
			AlertWindowHelper.showSQLErrorWindow();
		}
	}
}
