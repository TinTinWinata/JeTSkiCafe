package model.handler;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Art;
import model.entity.Cart;
import model.entity.Transaction;
import model.exception.ArtNotSelectedException;
import model.exception.CartIsEmptyException;
import model.exception.CartNotSelectedException;
import model.exception.InsiderTradingException;
import model.exception.InvalidCopiesException;
import model.repository.CartRepository;
import model.repository.TransactionRepository;

public class TransactionHandler {
	private static ObservableList<Cart> observableCartList = null;
	private static ObservableList<Transaction> observableTransactionList = null;
	private static Art selectedArt = null;
	private static Cart selectedCart = null;
	
	public static ObservableList<Cart> getObservableCartListInstance() {
		if(observableCartList == null)
			observableCartList =  FXCollections.observableArrayList();
		return observableCartList;
	}
	
	public static ObservableList<Transaction> getObservableTransactionListInstance() {
		if(observableTransactionList == null)
			observableTransactionList =  FXCollections.observableArrayList();
		return observableTransactionList;
	}
	
	public static void updateObservableCartListInstance() throws ClassNotFoundException, SQLException {
		if(observableCartList != null) {
			getObservableCartListInstance().removeAll(getObservableCartListInstance());
			ArrayList<Cart> cartList = null;
			String ownerId = AuthHandler.getCurrentUserId();
			cartList = CartRepository.getCartsByOwnerId(ownerId);
			for(Cart cart : cartList) {
				getObservableCartListInstance().add(cart);
			}
		}
	}
	
	public static void updateObservableTransactionListInstance() throws ClassNotFoundException, SQLException {
		if(observableTransactionList != null) {
			getObservableTransactionListInstance().removeAll(getObservableTransactionListInstance());
			ArrayList<Transaction> txList = null;
			String userRole = AuthHandler.getCurrentUserRole();
			switch(userRole) {
			case AuthHandler.ROLE_ADMINISTRATOR:
				txList = TransactionRepository.getTransactions();
				break;
			case AuthHandler.ROLE_USER:
				String userId = AuthHandler.getCurrentUserId();
				txList = TransactionRepository.getTransactionsByAffiliator(userId);
				break;
			}
			for(Transaction tx : txList) {
				getObservableTransactionListInstance().add(tx);
			}
		}
	}
	
	public static void addToCart(Integer copies) throws ArtNotSelectedException, ClassNotFoundException, SQLException, InvalidCopiesException, InsiderTradingException {
		if(selectedArt == null)
			throw new ArtNotSelectedException();
		String ownerId = AuthHandler.getCurrentUserId();
		if(selectedArt.getArtistId().equals(ownerId))
			throw new InsiderTradingException();
		if(copies <= 0) 
			throw new InvalidCopiesException();
		String artId = selectedArt.getId();
		Cart cartInstance = CartRepository.getCartByOwnerAndArt(ownerId, artId);
		if(cartInstance == null)
			CartRepository.insertCart(ownerId, artId, copies);
		else {
			Integer newCopies = cartInstance.getCopies() + copies;
			CartRepository.updateCartCopies(cartInstance.getId(), newCopies);			
		}
		nullifySelections();
	}
	
	public static void removeFromCart(Integer copies) throws CartNotSelectedException, InvalidCopiesException, ClassNotFoundException, SQLException {
		if(selectedCart == null)
			throw new CartNotSelectedException();
		if(copies <= 0) 
			throw new InvalidCopiesException();
		String ownerId = AuthHandler.getCurrentUserId();
		String artId = selectedCart.getArtId();
		Integer cartId = selectedCart.getId();
		Cart cartInstance = CartRepository.getCartByOwnerAndArt(ownerId, artId);
		Integer newCopies = cartInstance.getCopies() - copies;
		
		CartRepository.updateCartCopies(cartId, newCopies);
		cartInstance = CartRepository.getCartById(cartId);
		if(cartInstance.getCopies() <= 0)
			CartRepository.deleteCart(cartId);
		nullifySelections();
	}
	
	public static void checkout() throws ClassNotFoundException, SQLException, CartIsEmptyException {
		String ownerId = AuthHandler.getCurrentUserId();
		ArrayList<Cart> cartList = CartRepository.getCartsByOwnerId(ownerId);
		if(cartList.isEmpty())
			throw new CartIsEmptyException();
		Date date = new Date((new java.util.Date()).getTime());
		for(Cart cart : cartList) {
			String artId = cart.getArtId();
			Integer copies = cart.getCopies();
			TransactionRepository.insertTransaction(artId, ownerId, date, copies);
			
			Integer cartId = cart.getId();
			CartRepository.deleteCart(cartId);
		}
		nullifySelections();
	}
	
	public static void setSelectedArt(Art art) {
		selectedArt = art;
	}
	
	public static void setSelectedCart(Cart cart) {
		selectedCart = cart;
	}
	
	private static void nullifySelections() {
		setSelectedArt(null);
		setSelectedCart(null);
	}
}
