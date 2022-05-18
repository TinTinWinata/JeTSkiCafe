package view.component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Transaction;

public class TransactionTableView extends TableView<Transaction>{
	private TableColumn<Transaction, String> idColumn = null;
	private TableColumn<Transaction, String> dateColumn = null;
	private TableColumn<Transaction, String> titleColumn = null;
	private TableColumn<Transaction, String> sellerColumn = null;
	private TableColumn<Transaction, String> buyerColumn = null;
	private TableColumn<Transaction, String> copiesColumn = null;
	private TableColumn<Transaction, String> pricePerCopyColumn = null;
	private TableColumn<Transaction, String> totalPriceColumn = null;
	
	@SuppressWarnings("unchecked")
	public TransactionTableView() {
		super();
		setEditable(false);
		idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		sellerColumn = new TableColumn<>("Seller");
		sellerColumn.setCellValueFactory(new PropertyValueFactory<>("seller"));
		buyerColumn = new TableColumn<>("Buyer");
		buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
		copiesColumn = new TableColumn<>("Copies");
		copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));
		pricePerCopyColumn = new TableColumn<>("Price Per-Copy");
		pricePerCopyColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerCopy"));
		totalPriceColumn = new TableColumn<>("Total Price");
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		getColumns().setAll(idColumn, dateColumn, titleColumn, sellerColumn, buyerColumn, copiesColumn, pricePerCopyColumn, totalPriceColumn);
	}
}
