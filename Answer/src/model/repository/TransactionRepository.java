package model.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.database.MOTADBHelper;
import model.entity.Transaction;
import model.factory.TransactionFactory;


public class TransactionRepository {
	private static final String MYSQL_TABLE = "transaction";
	private static final String MYSQL_COLUMN_ID = "id";
	private static final String MYSQL_COLUMN_ART_ID = "art_id";
	private static final String MYSQL_COLUMN_BUYER_ID = "buyer_id";
	private static final String MYSQL_COLUMN_DATE = "date";
	private static final String MYSQL_COLUMN_COPIES = "copies";
	
	private static Transaction createInstanceFromSet(ResultSet set) throws SQLException, ClassNotFoundException {
		Integer id = set.getInt(MYSQL_COLUMN_ID);
		String artId = set.getString(MYSQL_COLUMN_ART_ID);
		String buyerId = set.getString(MYSQL_COLUMN_BUYER_ID);
		Date date = set.getDate(MYSQL_COLUMN_DATE);
		Integer copies = set.getInt(MYSQL_COLUMN_COPIES);
		return TransactionFactory.createTransaction(id, artId, buyerId, date, copies);
	}
	
	public static ArrayList<Transaction> getTransactions() throws ClassNotFoundException, SQLException {
		ArrayList<Transaction> txList = new ArrayList<>();
		String query = String.format("SELECT * FROM %s", MYSQL_TABLE);
		ResultSet set = MOTADBHelper.executeQuery(query);
		while(set.next()) {
			txList.add(createInstanceFromSet(set));
		}
		return txList;
	}
	
	public static ArrayList<Transaction> getTransactionsByAffiliator(String userId) throws ClassNotFoundException, SQLException {
		ArrayList<Transaction> txList = new ArrayList<>();
		String query = String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.%s WHERE %s = '%s' OR %s = '%s'", 
				MYSQL_TABLE,
				ArtRepository.getMysqlTable(),
				MYSQL_TABLE, MYSQL_COLUMN_ART_ID,
				ArtRepository.getMysqlTable(), ArtRepository.getMysqlColumnId(),
				MYSQL_COLUMN_BUYER_ID, userId,
				ArtRepository.getMysqlColumnArtistId(), userId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		while(set.next()) {
			txList.add(createInstanceFromSet(set));
		}
		return txList;
	}
	
	public static Transaction getTransactionById(Integer txId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = %d", MYSQL_TABLE, MYSQL_COLUMN_ID, txId);
		ResultSet set = MOTADBHelper.executeQuery(query);
		if(set.next()) {
			return createInstanceFromSet(set);
		}
		return null;
	}
	
	
	public static void insertTransaction(String artId, String buyerId, Date date, Integer copies) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', '%s', '%s', %d)", 	
				MYSQL_TABLE, 
				MYSQL_COLUMN_ART_ID,
				MYSQL_COLUMN_BUYER_ID,
				MYSQL_COLUMN_DATE,
				MYSQL_COLUMN_COPIES,
				artId, buyerId, date, copies);
		MOTADBHelper.executeUpdate(query);
	}
}
