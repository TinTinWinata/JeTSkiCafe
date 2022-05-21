package Model;

import java.sql.ResultSet;
import java.util.Vector;

import Database.Connect;
import Page.AlertWindow;
import javafx.scene.control.Alert.AlertType;

public class Menu {

	private Integer menuId;
	private String menuName;
	private String menuType;
	private Integer menuPrice;
	private Integer menuStock;
	
	public Menu(Integer menuId, String menuName, String menuType, Integer menuPrice, Integer menuStock) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuPrice = menuPrice;
		this.menuStock = menuStock;
	}
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public Integer getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(Integer menuPrice) {
		this.menuPrice = menuPrice;
	}
	public Integer getMenuStock() {
		return menuStock;
	}
	public void setMenuStock(Integer menuStock) {
		this.menuStock = menuStock;
	}
	
	public void update(String menuName, String menuType, int menuPrice, int menuStock)
	{
		Connect c = Connect.getConnection();
		String q = String.format("UPDATE menu SET menuName = '%s', menuType = '%s', menuPrice = %d, menuStock = %d WHERE menuId = %d", 
				menuName, menuType, menuPrice, menuStock, this.menuId);
		c.executeUpdate(q);
	}
	
	public void remove()
	{
		Connect c = Connect.getConnection();
		String q = String.format("DELETE FROM menu WHERE menuId = %s", menuId);
		c.executeUpdate(q);
	}
	
	public void save()
	{
		Connect c = Connect.getConnection();
		String q = String.format("INSERT INTO menu VALUES (%d, '%s', '%s', %d, %d)", menuId, menuName, menuType, menuPrice, menuStock);
		c.executeUpdate(q);
	}
	
	public static Vector<Menu> getMenuFromDB()
	{	
		Vector<Menu> menuList = new Vector<Menu>();
		Connect c = Connect.getConnection();
		String q = "SELECT * FROM menu";
		ResultSet rs = c.executeQuery(q);
		try {
			while(rs.next())
			{
				int menuId = rs.getInt("menuId");
				String menuName = rs.getString("menuName");
				String menuType = rs.getString("menuType");
				int menuPrice = rs.getInt("menuPrice");
				int menuStock = rs.getInt("menuStock");
				
				Menu m = new Menu(menuId, menuName, menuType, menuPrice, menuStock);
				menuList.add(m);
			}
		} catch (Exception e) {
			new AlertWindow(AlertType.ERROR, "Failed to get menu from Database");
		}
		return menuList;
	}
	
}
