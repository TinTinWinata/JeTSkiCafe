package model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.database.MOTADBHelper;
import model.entity.Cart;
import model.factory.CartFactory;


public class CartRepository {
	private static final String MYSQL_TABLE = "cart";
	private static final String MYSQL_COLUMN_ID = "id";
	private static final String MYSQL_COLUMN_OWNER_ID = "owner_id";
	private static final String MYSQL_COLUMN_ART_ID = "art_id";
	private static final String MYSQL_COLUMN_COPIES = "copies";
	
	private static Cart createInstanceFromSet(ResultSet set) throws SQLException, ClassNotFoundException {
		Integer id = set.getInt(MYSQL_COLUMN_ID);
		String ownerId = set.getString(MYSQL_COLUMN_OWNER_ID);
		String artId = set.getString(MYSQL_COLUMN_ART_ID);
		Integer copies = set.getInt(MYSQL_COLUMN_COPIES);
		return CartFactory.createCart(id, ownerId, artId, copies);
	}
	
	public static ArrayList<Cart> getCarts() throws ClassNotFoundException, SQLException {
		ArrayList<Cart> cartList = new ArrayList<>();
		String query = String.format("SELECT * FROM %s", MYSQL_TABLE);
		ResultSet set = MOTADBHelper.executeQuery(query);
		while(set.next()) {
			cartList.add(createInstanceFromSet(set));
		}
		return cartList;
	}
	
	public static ArrayList<Cart> getCartsByOwnerId(String userId) throws ClassNotFoundException, SQLException {
		ArrayList<Cart> cartList = new ArrayList<>();
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", MYSQL_TABLE, MYSQL_COLUMN_OWNER_ID, userId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		while(set.next()) {
			cartList.add(createInstanceFromSet(set));
		}
		return cartList;
	}
	
	public static Cart getCartById(Integer cartId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = %d", MYSQL_TABLE, MYSQL_COLUMN_ID, cartId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		if(set.next()) {
			return createInstanceFromSet(set);
		}
		return null;
	}
	
	public static Cart getCartByOwnerAndArt(String ownerId, String artId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'", 
				MYSQL_TABLE, 
				MYSQL_COLUMN_OWNER_ID, ownerId,
				MYSQL_COLUMN_ART_ID, artId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		if(set.next()) {
			return createInstanceFromSet(set);
		}
		return null;
	}
	
	public static void insertCart(String ownerId, String artId, Integer copies) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES ('%s', '%s', %d)", 	
				MYSQL_TABLE, 
				MYSQL_COLUMN_OWNER_ID,
				MYSQL_COLUMN_ART_ID,
				MYSQL_COLUMN_COPIES,
				ownerId, artId, copies);
		MOTADBHelper.executeUpdate(query);
	}
	
	public static void updateCartCopies(Integer id, Integer copies) throws ClassNotFoundException, SQLException {
		String query = String.format("UPDATE %s SET %s = %d WHERE %s = %d", 	
				MYSQL_TABLE, 
				MYSQL_COLUMN_COPIES , copies,
				MYSQL_COLUMN_ID, id);
		MOTADBHelper.executeUpdate(query);
	}
	
	public static void deleteCart(Integer id) throws ClassNotFoundException, SQLException {
		String query = String.format("DELETE FROM %s WHERE %s = %d", 	
				MYSQL_TABLE, 
				MYSQL_COLUMN_ID, id);
		MOTADBHelper.executeUpdate(query);
	}
}
