package Model;

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
	
	
	
}
