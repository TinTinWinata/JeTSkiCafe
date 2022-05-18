package model.entity;

public class Cart {
	private String ownerId = null;
	private String artId = null;
	
	private Integer id = null;
	private String url = null;
	private String title = null;
	private String artist = null;
	private Integer copies = null;
	private Integer pricePerCopy = null;
	private Integer totalPrice = null;
	
	
	public Cart() {
	}

	public Cart(Integer id, String ownerId, String artId, Integer copies) {
		this.id = id;
		this.ownerId = ownerId;
		this.artId = artId;
		this.copies = copies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getArtId() {
		return artId;
	}

	public void setArtId(String artId) {
		this.artId = artId;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getPricePerCopy() {
		return pricePerCopy;
	}

	public void setPricePerCopy(Integer pricePerCopy) {
		this.pricePerCopy = pricePerCopy;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
