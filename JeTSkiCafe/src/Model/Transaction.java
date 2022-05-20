package Model;

import java.util.Vector;

public class Transaction {

	private Integer transactionId;
	private Integer userId;
	private String transactionDate;
	private Integer totalPrice;
	private Vector<DetailTransaction> transactionList = new Vector<>();
	
	public Transaction(Integer transactionId, Integer userId, String transactionDate) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.transactionDate = transactionDate;
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
	
	
	
}
