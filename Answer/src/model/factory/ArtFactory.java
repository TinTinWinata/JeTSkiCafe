package model.factory;

import java.sql.Date;
import java.sql.SQLException;

import model.entity.Art;
import model.entity.User;
import model.repository.UserRepository;

public class ArtFactory {
	public static Art createArt(String id, String artistId, String url, String title, String description, Date publishedDate,
			Integer price) throws ClassNotFoundException, SQLException {
		Art art = new Art(id, artistId, url, title, description, publishedDate, price);
		User artist = UserRepository.getUserById(artistId);
		art.setArtistUsername(artist.getUsername());
		return art;
	}
}
