package model.entity;

import java.sql.Date;

public class Art {
	private String artistId = null;
	
	private String id = null;
	private String artistUsername = null;
	private String url = null;
	private String title = null;
	private String description = null;
	private Date publishedDate = null;
	private Integer price = null;
	
	public Art() {
	}

	public Art(String id, String artistId, String url, String title, String description, Date publishedDate,
			Integer price) {
		this.id = id;
		this.artistId = artistId;
		this.url = url;
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	
	public String getArtistUsername() {
		return artistUsername;
	}

	public void setArtistUsername(String artistName) {
		this.artistUsername = artistName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
