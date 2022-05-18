package Model;

public class DetailTransaction {

	private Integer transactionId;
	private Integer transactionDetailId;
	private Integer menuId;
	private Integer quantity;
	
	public DetailTransaction(Integer transactionId, Integer transactionDetailId, Integer menuId, Integer quantity) {
		super();
		this.transactionId = transactionId;
		this.transactionDetailId = transactionDetailId;
		this.menuId = menuId;
		this.quantity = quantity;
	}
	
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getTransactionDetailId() {
		return transactionDetailId;
	}
	public void setTransactionDetailId(Integer transactionDetailId) {
		this.transactionDetailId = transactionDetailId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}
