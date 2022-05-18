package Model;

public class Transaction {

	private Integer transcationId;
	private Integer userId;
	private String transactionDate;
	
	public Transaction(Integer transcationId, Integer userId, String transactionDate) {
		super();
		this.transcationId = transcationId;
		this.userId = userId;
		this.transactionDate = transactionDate;
	}
	public Integer getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(Integer transcationId) {
		this.transcationId = transcationId;
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
