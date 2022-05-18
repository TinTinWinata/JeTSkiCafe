package model.entity;

import java.sql.Date;

public class Transaction {
	
	private String artId = null;
	private String buyerId = null;
	
	private Integer id = null;
	private Date date = null;
	private String title = null;
	private String seller = null;
	private String buyer = null;
	private Integer copies = null;
	private Integer pricePerCopy = null;
	private Integer totalPrice = null;
	
	
	public Transaction() {
	}

	public Transaction(Integer id, String artId, String buyerId, Date date, Integer copies) {
		this.id = id;
		this.artId = artId;
		this.buyerId = buyerId;
		this.date = date;
		this.copies = copies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArtId() {
		return artId;
	}

	public void setArtId(String artId) {
		this.artId = artId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
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
