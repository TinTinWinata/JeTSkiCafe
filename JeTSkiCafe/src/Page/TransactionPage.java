package Page;

import java.sql.ResultSet;

import Database.Connect;
import Helper.Utillities;
import Model.Transaction;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TransactionPage {

	private static TransactionPage transactionPage;
	
	private BorderPane mainBorderPane;
	private GridPane bottomGridPane;
	
	private TableView<Transaction> table;
	
	private Label menuNameLbl;
	private Label menuTypeLbl;
	private Label menuPriceLbl;
	private Label quantityLbl;
	
	private TextField menuNameTF;
	private TextField menuTypeTF;
	private TextField menuPriceTF;
	private TextField quantityTF;
	
	public static TransactionPage getInstance()
	{
		if(transactionPage == null)
		{
			transactionPage = new TransactionPage();
		}
		return transactionPage;
	}
	
	public void init()
	{
		menuNameLbl = new Label("Menu Name");
		menuTypeLbl  = new Label("Menu Type");
		menuPriceLbl = new Label("Menu price");
		quantityLbl = new Label("Quantity");
		
		menuNameTF = new TextField();
		menuTypeTF = new TextField();
		menuPriceTF = new TextField();
		quantityTF = new TextField();
		
		bottomGridPane = new GridPane();
		table = new TableView<Transaction>();
		mainBorderPane = new BorderPane();
	}
	
	public void setTable()
	{
		TableColumn<Transaction, String> transactionId = new TableColumn<Transaction, String>("Transaction ID");
		TableColumn<Transaction, String> transactionDate = new TableColumn<Transaction, String>("Date");
		TableColumn<Transaction, String> transactionPrice = new TableColumn<Transaction, String>("Price");
		
		transactionId.setCellValueFactory(new PropertyValueFactory<Transaction, String>("id"));
		transactionDate.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
		transactionPrice.setCellValueFactory(new PropertyValueFactory<Transaction, String>("price"));
		
		transactionId.setMinWidth(Utillities.WIDTH / 3);
		transactionDate.setMinWidth(Utillities.WIDTH / 3);
		transactionPrice.setMinWidth(Utillities.WIDTH / 3);
		
		table.getColumns().addAll(transactionId, transactionDate, transactionPrice);	
	}
	
	public void addTableData()
	{
		Connect c = Connect.getConnection();
		String query = "SELECT * FROM transaction";
		ResultSet rs = c.executeQuery(query);
		try {
			while(rs.next())
			{
				int transactionId = rs.getInt("transactionid");
				int userId = rs.getInt("userId");
				String transactionDate = rs.getString("transactionDate");
				Transaction t = new Transaction(transactionId, userId, transactionDate);
				table.getItems().add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setComponent()
	{
		bottomGridPane.add(menuNameLbl, 0, 0);
		bottomGridPane.add(menuTypeLbl, 0, 1);
		bottomGridPane.add(menuPriceLbl, 2, 0);
		bottomGridPane.add(quantityLbl, 2, 1);
		
		bottomGridPane.add(menuNameTF, 1, 0);
		bottomGridPane.add(menuPriceTF, 1, 1);
		bottomGridPane.add(menuTypeTF, 3, 0);
		bottomGridPane.add(quantityTF, 3, 1);
	}
	
	public void arrangeComponent()
	{
		mainBorderPane.setCenter(table);
		mainBorderPane.setBottom(bottomGridPane);
	}
	
	public void positioningComponent()
	{
		bottomGridPane.setHgap(10);
		bottomGridPane.setVgap(10);
		bottomGridPane.setPadding(new Insets(10, 10, 10, 10));
	}
	
	public BorderPane makeTransactionPage()
	{
		init();
		setComponent();
		arrangeComponent();
		positioningComponent();
		setTable();
		return mainBorderPane;
	}
	
}
