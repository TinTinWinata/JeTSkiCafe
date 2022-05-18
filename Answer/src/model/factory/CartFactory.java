package model.factory;

import java.sql.SQLException;

import model.entity.Art;
import model.entity.Cart;
import model.repository.ArtRepository;

public class CartFactory {
	public static Cart createCart(Integer id, String ownerId, String artId, Integer copies) throws ClassNotFoundException, SQLException {
		Cart cart = new Cart(id, ownerId, artId, copies);
		Art art = ArtRepository.getArtById(artId);
		cart.setUrl(art.getUrl());
		cart.setTitle(art.getTitle());
		cart.setArtist(art.getArtistUsername());
		cart.setPricePerCopy(art.getPrice());
		Integer totalPrice = copies * art.getPrice();
		cart.setTotalPrice(totalPrice);
		return cart;
	}
}
