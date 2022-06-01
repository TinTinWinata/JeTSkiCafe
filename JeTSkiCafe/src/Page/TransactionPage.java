package Page;

import java.sql.ResultSet;
import java.util.Vector;

import Database.Connect;
import Helper.Utillities;
import Model.Transaction;
import Model.TransactionDetail;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TransactionPage {

	
	private static TransactionPage transactionPage;
	
	private BorderPane mainBorderPane;
	private GridPane menuGridPane;
	private GridPane bottomGridPane;
	
	private TableView<Transaction> table;
	private TableView<TransactionDetail> tableTd;
	
	private Label menuNameLbl;
	private Label menuTypeLbl;
	private Label menuPriceLbl;
	private Label quantityLbl;
	
	private TextField menuNameTF;
	private TextField menuTypeTF;
	private TextField menuPriceTF;
	private TextField quantityTF;
	
	private Button removeBtn;
	
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
		
		menuNameTF.setDisable(true);
		menuTypeTF.setDisable(true);
		menuPriceTF.setDisable(true);
		quantityTF.setDisable(true);
		
		menuGridPane = new GridPane();
		bottomGridPane = new GridPane();
		
		removeBtn = new Button("Remove Transaction");
		
		table = new TableView<Transaction>();
		tableTd = new TableView<TransactionDetail>();
		
		mainBorderPane = new BorderPane();
	}
	
	public void setTable()
	{
		TableColumn<Transaction, String> transactionId = new TableColumn<Transaction, String>("Transaction ID");
		TableColumn<Transaction, String> transactionDate = new TableColumn<Transaction, String>("Date");
		TableColumn<Transaction, String> transactionPrice = new TableColumn<Transaction, String>("Price");
		
		transactionId.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionId"));
		transactionDate.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionDate"));
		transactionPrice.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionPrice"));
		
		transactionId.setMinWidth(Utillities.WIDTH / 3);
		transactionDate.setMinWidth(Utillities.WIDTH / 3);
		transactionPrice.setMinWidth(Utillities.WIDTH / 3);
		
		table.getColumns().addAll(transactionId, transactionDate, transactionPrice);
		
		
		TableColumn<TransactionDetail, String> transcationDetailId = new TableColumn<TransactionDetail, String>("Transaction Detail ID");
		TableColumn<TransactionDetail, String> menuId = new TableColumn<TransactionDetail, String>("Menu Id");
		TableColumn<TransactionDetail, String> quantity = new TableColumn<TransactionDetail, String>("Quantity");
		
		transcationDetailId.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("transactionDetailId"));
		menuId.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("menuId"));
		quantity.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("quantity"));
		
		tableTd.getColumns().addAll(transcationDetailId, menuId, quantity);
	}
	
	public void addTableData()
	{
		Connect c = Connect.getConnection();
		String query = "SELECT * FROM transaction";
		ResultSet rs = c.executeQuery(query);
		table.getItems().clear();
		
		Vector<Transaction> transactionList = new Vector<>();
		
		try {
			while(rs.next())
			{
				int transactionId = rs.getInt("transactionId");
				int userId = rs.getInt("userId");
				String transactionDate = rs.getString("transactionDate");
				Transaction t = new Transaction(transactionId, userId, transactionDate);
				transactionList.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Transaction t : transactionList) {
			t.countPrice();
			t.setTransactionDetail();
			table.getItems().add(t);
		}
	}
	
	public void setComponent()
	{
		menuGridPane.add(menuNameLbl, 0, 0);
		menuGridPane.add(menuTypeLbl, 0, 1);
		menuGridPane.add(menuPriceLbl, 2, 0);
		menuGridPane.add(quantityLbl, 2, 1);
		
		menuGridPane.add(menuNameTF, 1, 0);
		menuGridPane.add(menuPriceTF, 1, 1);
		menuGridPane.add(menuTypeTF, 3, 0);
		menuGridPane.add(quantityTF, 3, 1);
		
		menuGridPane.add(removeBtn, 0, 2);
		
		bottomGridPane.add(menuGridPane, 0, 0);
		bottomGridPane.add(tableTd, 1, 0);
	}
	
	public void arrangeComponent()
	{
		mainBorderPane.setCenter(table);
		mainBorderPane.setBottom(bottomGridPane);
	}
	
	public void positioningComponent()
	{
		menuGridPane.setHgap(10);
		menuGridPane.setVgap(10);
		menuGridPane.setPadding(new Insets(10, 10, 10, 10));
		
	}
	
	public void setEvent()
	{
		table.setOnMouseClicked(x -> {
			Transaction selectedT = table.getSelectionModel().getSelectedItem();
			tableTd.getItems().clear();
			
			if(selectedT == null)
			{
				return;
			}
			
			for (TransactionDetail td : selectedT.getTdList()) {
				td.setMenu();
				tableTd.getItems().add(td);
			}
		});
		tableTd.setOnMouseClicked(x -> {
			TransactionDetail selectedTd = tableTd.getSelectionModel().getSelectedItem();
			
			if(selectedTd == null)
			{
				return;
			}
			menuNameTF.setText(selectedTd.getMenu().getMenuName());
			menuTypeTF.setText(selectedTd.getMenu().getMenuType());
			menuPriceTF.setText(String.valueOf(selectedTd.getMenu().getMenuPrice()));
			quantityTF.setText(String.valueOf(selectedTd.getQuantity()));
		});
		removeBtn.setOnMouseClicked(x -> {
			tableTd.getItems().clear();
			Transaction selectedT = table.getSelectionModel().getSelectedItem();
			
			if(selectedT == null)
			{
				new AlertWindow(AlertType.ERROR, "Please select at least 1 transaction!");
				return;
			}
			
			Connect c = Connect.getConnection();
			String query = String.format("DELETE FROM transactionDetail WHERE transactionId = %s", selectedT.getTransactionId());
			c.executeUpdate(query);
			
			selectedT.remove();
			addTableData();
		});
	}
	
	public BorderPane makeTransactionPage()
	{
		init();
		setComponent();
		arrangeComponent();
		positioningComponent();
		setTable();
		addTableData();
		setEvent();
		return mainBorderPane;
	}
	
}
