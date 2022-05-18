package model.factory;

import java.sql.Date;
import java.sql.SQLException;

import model.entity.Art;
import model.entity.Transaction;
import model.entity.User;
import model.repository.ArtRepository;
import model.repository.UserRepository;

public class TransactionFactory {
	public static Transaction createTransaction(Integer id, String artId, String buyerId, Date date, Integer copies) throws ClassNotFoundException, SQLException {
		Transaction tx = new Transaction(id, artId, buyerId, date, copies);
		Art art = ArtRepository.getArtById(artId);
		User buyer = UserRepository.getUserById(buyerId);
		User seller = UserRepository.getUserById(art.getArtistId());
		
		tx.setTitle(art.getTitle());
		tx.setSeller(seller.getUsername());
		tx.setBuyer(buyer.getUsername());
		tx.setPricePerCopy(art.getPrice());
		Integer totalPrice = art.getPrice() * copies;
		tx.setTotalPrice(totalPrice);
		return tx;
	}
}
