package model.handler;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Art;
import model.exception.InvalidDescriptionException;
import model.exception.InvalidIDException;
import model.exception.InvalidPriceException;
import model.exception.InvalidTitleException;
import model.exception.InvalidURLException;
import model.repository.ArtRepository;

public class ArtHandler {
	
	private static ObservableList<Art> observableArtManagerList = null;
	private static ObservableList<Art> observableArtMarketList = null;
	
	public static ObservableList<Art> getObservableArtManagerListInstance() {
		if(observableArtManagerList == null)
			observableArtManagerList =  FXCollections.observableArrayList();
		return observableArtManagerList;
	}
	
	public static ObservableList<Art> getObservableArtMarketListInstance() {
		if(observableArtMarketList == null)
			observableArtMarketList =  FXCollections.observableArrayList();
		return observableArtMarketList;
	}
	
	public static void updateObservableArtManagerListInstance() throws ClassNotFoundException, SQLException {
		if(observableArtManagerList != null) {
			getObservableArtManagerListInstance().removeAll(getObservableArtManagerListInstance());
			ArrayList<Art> artList = null;
			String userRole = AuthHandler.getCurrentUserRole();
			switch(userRole) {
			case AuthHandler.ROLE_ADMINISTRATOR:
				artList = ArtRepository.getArts();
				break;
			case AuthHandler.ROLE_USER:
				String userId = AuthHandler.getCurrentUserId();
				artList = ArtRepository.getArtsByArtistId(userId);
				break;
			}
			for(Art art : artList) {
				getObservableArtManagerListInstance().add(art);
			}
		}
	}
	
	public static void updateObservableArtMarketListInstance() throws ClassNotFoundException, SQLException {
		if(observableArtMarketList != null) {
			getObservableArtMarketListInstance().removeAll(getObservableArtMarketListInstance());
			ArrayList<Art> artList = ArtRepository.getArts();
			for(Art art : artList) {
				getObservableArtMarketListInstance().add(art);
			}
		}
	}
	
	public static String generateRandomUniqueArtId() throws ClassNotFoundException, SQLException {
		Random random = new Random();
		String id = null;
		
		do {
			id = "ART";
			id += Math.abs(random.nextInt() % 10);
			id += Math.abs(random.nextInt() % 10);
			id += Math.abs(random.nextInt() % 10);
		}while(ArtRepository.getArtById(id) != null);
		
		return id;
	}
	
	public static String insertArt(String title, String url, Integer price, String description) throws ClassNotFoundException, SQLException, InvalidTitleException, InvalidURLException, InvalidPriceException, InvalidDescriptionException {
		String id = ArtHandler.generateRandomUniqueArtId();
		String artistId = AuthHandler.getCurrentUserId();
		Date publishedDate = new Date((new java.util.Date()).getTime());
		if(title.length() <= 0) 
			throw new InvalidTitleException();
		if(!url.startsWith("http"))
			throw new InvalidURLException();
		if(price <= 0)
			throw new InvalidPriceException();
		if(description.length() <= 0)
			throw new InvalidDescriptionException();
		
		ArtRepository.insertArt(id, artistId, url, title, description, publishedDate, price);
		return id;
	}
	
	public static void updateArt(String id, String title, String url, Integer price, String description) throws InvalidTitleException, InvalidURLException, InvalidPriceException, InvalidDescriptionException, ClassNotFoundException, SQLException, InvalidIDException {
		if(ArtRepository.getArtById(id) == null)
			throw new InvalidIDException();
		if(title.length() <= 0) 
			throw new InvalidTitleException();
		if(!url.startsWith("http"))
			throw new InvalidURLException();
		if(price <= 0)
			throw new InvalidPriceException();
		if(description.length() <= 0)
			throw new InvalidDescriptionException();
		
		ArtRepository.updateArt(id, url, title, description, price);
	}
	
	public static void deleteArt(String id) throws InvalidIDException, ClassNotFoundException, SQLException {
		if(ArtRepository.getArtById(id) == null)
			throw new InvalidIDException();
		ArtRepository.deleteArt(id);
	}
}
