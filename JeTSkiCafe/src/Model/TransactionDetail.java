package Model;

import java.sql.ResultSet;

import Database.Connect;

public class TransactionDetail {

	private Integer transactionId;
	private Integer transactionDetailId;
	private Integer menuId;
	private Integer quantity;
	
	private Menu m;
	
	public TransactionDetail(Integer transactionId, Integer menuId, Integer quantity) {
		super();
		this.transactionId = transactionId;
		this.menuId = menuId;
		this.quantity = quantity;
		generateId();
	}
	
	public TransactionDetail(Integer transactionDetailId, Integer transactionId, Integer menuId, Integer quantity) {
		super();
		this.transactionId = transactionId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.transactionDetailId = transactionDetailId;
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
	
	
	public Menu getMenu() {
		return m;
	}
	
	
	public void setMenu()
	{
		Connect c = Connect.getConnection();
		String q = String.format("SELECT * FROM menu WHERE menuId = %d" , this.menuId);
		ResultSet rs = c.executeQuery(q);
		try {
			while(rs.next())
			{
				String menuName = rs.getString("menuName");
				String menuType = rs.getString("menuType");
				int menuPrice = rs.getInt("menuPrice");
				int menuStock = rs.getInt("menuStock");
				int menuId = rs.getInt("menuId");
				m = new Menu(menuId, menuName, menuType, menuPrice, menuStock);
			}
		} catch (Exception e) {
		}
	}



	public void generateId()
	{
		int id = 1;
		Connect c = Connect.getConnection();
		String q = "SELECT * FROM transactionDetail";
		ResultSet rs = c.executeQuery(q);
		try {
			while(rs.next())
			{
				if(rs.isLast())
				{
					int lastId = rs.getInt("transactionDetailId");
					id = lastId + 1;
					break;
				}
			}
		} catch (Exception e) {
		}
		transactionDetailId =  id;
	}
	
	public void save()
	{

		Connect c = Connect.getConnection();
		String q = String.format("INSERT INTO transactionDetail VALUES (%d, %d, %d, %s)", transactionDetailId, transactionId, menuId, quantity);
		c.executeUpdate(q);
	}
	
}
