package Model;

import Database.Connect;

public class Cart {

	private Integer userId;
	private Integer menuId;
	private Integer quantity;
	
	public Cart(Integer userId, Integer menuId, Integer quantity) {
		super();
		this.userId = userId;
		this.menuId = menuId;
		this.quantity = quantity;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	public void save()
	{
		Connect con = Connect.getConnection();
		String query = String.format("INSERT INTO cart VALUES (%d, %d,%d)", userId, menuId, quantity);
		con.executeUpdate(query);
	}
	
	public void remove()
	{
		Connect c = Connect.getConnection();
		String query = String.format("DELETE FROM cart WHERE userId = %d AND menuId = %d", userId, menuId);
		c.executeUpdate(query);
	}
	
	
}
