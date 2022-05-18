package model.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.database.MOTADBHelper;
import model.entity.Art;
import model.factory.ArtFactory;


public class ArtRepository {
	private static final String MYSQL_TABLE = "art";
	private static final String MYSQL_COLUMN_ID = "id";
	private static final String MYSQL_COLUMN_ARTIST_ID = "artist_id"; 
	private static final String MYSQL_COLUMN_URL = "url";
	private static final String MYSQL_COLUMN_TITLE = "title";
	private static final String MYSQL_COLUMN_DESCRIPTION = "description";
	private static final String MYSQL_COLUMN_PUBLISHED_DATE = "published_date";
	private static final String MYSQL_COLUMN_PRICE = "price";
	
	private static Art createInstanceFromSet(ResultSet set) throws SQLException, ClassNotFoundException {
		String id = set.getString(MYSQL_COLUMN_ID);
		String artistId = set.getString(MYSQL_COLUMN_ARTIST_ID);
		String url = set.getString(MYSQL_COLUMN_URL);
		String title = set.getString(MYSQL_COLUMN_TITLE);
		String description = set.getString(MYSQL_COLUMN_DESCRIPTION);
		Date publishedDate = set.getDate(MYSQL_COLUMN_PUBLISHED_DATE);
		Integer price = set.getInt(MYSQL_COLUMN_PRICE);
		return ArtFactory.createArt(id, artistId, url, title, description, publishedDate, price);
	}
	
	public static ArrayList<Art> getArts() throws ClassNotFoundException, SQLException {
		ArrayList<Art> artList = new ArrayList<>();
		String query = String.format("SELECT * FROM %s", MYSQL_TABLE);
		ResultSet set = MOTADBHelper.executeQuery(query);
		while(set.next()) {
			artList.add(createInstanceFromSet(set));
		}
		return artList;
	}
	
	public static ArrayList<Art> getArtsByArtistId(String artistId) throws ClassNotFoundException, SQLException { 
		ArrayList<Art> artList = new ArrayList<>();
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", MYSQL_TABLE, MYSQL_COLUMN_ARTIST_ID, artistId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		while(set.next()) {
			artList.add(createInstanceFromSet(set));
		}
		return artList;
	}
	
	public static Art getArtById(String artId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", MYSQL_TABLE, MYSQL_COLUMN_ID, artId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		if(set.next()) {
			return createInstanceFromSet(set);
		}
		return null;
	}
	
	public static void insertArt(String id, String artistId, String url, String title, String description, Date publishedDate, Integer price) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %d)", 	
				MYSQL_TABLE, 
				MYSQL_COLUMN_ID, 
				MYSQL_COLUMN_ARTIST_ID,
				MYSQL_COLUMN_URL, 
				MYSQL_COLUMN_TITLE, 
				MYSQL_COLUMN_DESCRIPTION,
				MYSQL_COLUMN_PUBLISHED_DATE,
				MYSQL_COLUMN_PRICE,
				id, artistId, url, title, description, publishedDate.toString(), price);
		MOTADBHelper.executeUpdate(query);
	}
	
	public static void updateArt(String id, String url, String title, String description, Integer price) throws ClassNotFoundException, SQLException {
		String query = String.format("UPDATE %s SET %s = '%s', %s = '%s', %s = '%s', %s = %d WHERE %s = '%s'",
				MYSQL_TABLE, 
				MYSQL_COLUMN_URL, url,
				MYSQL_COLUMN_TITLE, title,
				MYSQL_COLUMN_DESCRIPTION, description,
				MYSQL_COLUMN_PRICE, price,
				MYSQL_COLUMN_ID, id);
		MOTADBHelper.executeUpdate(query);
	}
	
	public static void deleteArt(String id) throws ClassNotFoundException, SQLException {
		String query = String.format("DELETE FROM %s WHERE %s = '%s'", MYSQL_TABLE, MYSQL_COLUMN_ID, id);
		MOTADBHelper.executeUpdate(query);
	}

	public static String getMysqlTable() {
		return MYSQL_TABLE;
	}

	public static String getMysqlColumnId() {
		return MYSQL_COLUMN_ID;
	}

	public static String getMysqlColumnArtistId() {
		return MYSQL_COLUMN_ARTIST_ID;
	}
}
