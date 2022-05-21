package Model;

import java.sql.ResultSet;
import java.util.Vector;

import Database.Connect;

public class Transaction {

	private Integer transactionId;
	private Integer userId;
	private String transactionDate;
	private Integer transactionPrice;
	
	private Vector<TransactionDetail> tdList = new Vector<>();
	
	public Transaction(Integer userId, String transactionDate) {
		super();
		generateTransactionId();
		this.userId = userId;
		this.transactionDate = transactionDate;
		this.transactionPrice = 0;
		
		}
	public Transaction(Integer transactionId, Integer userId, String transactionDate) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.transactionDate = transactionDate;
		this.transactionPrice = 0;
		
		}
	
	public void generateTransactionId() {
		int id = 1;
		Connect c = Connect.getConnection();
		String q = "SELECT * FROM transaction";
		ResultSet rs = c.executeQuery(q);
		try {
			while (rs.next()) {
				if (rs.isLast()) {
					int lastId = rs.getInt("transactionId");
					System.out.println("Last id : " + lastId);
					id = lastId + 1;
					break;
				}
			}
		} catch (Exception e) {
		}
		transactionId=  id;
	}
	
	public Vector<TransactionDetail> getTdList() {
		return tdList;
	}

	public void setTransactionDetail()
	{
		Connect c = Connect.getConnection();
		String q = String.format("SELECT * FROM transactionDetail td WHERE td.transactionId = %d", this.transactionId) ;
		ResultSet rs = c.executeQuery(q);
		try {
			while(rs.next())
			{
				int menuId = rs.getInt("menuId");
				int quantity = rs.getInt("quantity");
				int transactionDetailId = rs.getInt("transactionDetailId");
				TransactionDetail td = new TransactionDetail(transactionDetailId, this.transactionId, menuId, quantity);
				tdList.add(td);
			}
		} catch (Exception e) {
		}
	}

	public void remove()
	{
		Connect c = Connect.getConnection();
		String q = String.format("DELETE FROM transaction WHERE transactionId = %d", this.transactionId);
		c.executeUpdate(q);
	}
	
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}



	public Integer getTransactionPrice() {
		return transactionPrice;
	}


	public void setTransactionPrice(Integer transactionPrice) {
		this.transactionPrice = transactionPrice;
	}


	public void countPrice()
	{
		Connect c = Connect.getConnection();
		String q = String.format( "SELECT * FROM transactionDetail td JOIN menu m ON m.menuId = td.menuId WHERE td.transactionId = %d", transactionId);
		ResultSet rs = c.executeQuery(q);
		try {
			while(rs.next())
			{
				int price = rs.getInt("menuPrice");
				int quantity = rs.getInt("quantity");
				int calculatedPrice = price * quantity;
				transactionPrice += calculatedPrice;
			}
		} catch (Exception e) {
		}
		System.out.println("Total Price : "+ transactionPrice);
	}
	
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTranscationId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public void save()
	{
		Connect c = Connect.getConnection();
		String q = String.format("INSERT INTO transaction VALUES (%d, %d, '%s')", transactionId, userId, transactionDate);
		c.executeUpdate(q);
	}
	

	
}
