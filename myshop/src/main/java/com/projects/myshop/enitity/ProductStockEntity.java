package com.projects.myshop.enitity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class ProductStockEntity {

	@Id
	private String stockId;
	
	private int stockQuantity;
	
	private int remainingQuantity;
	
	private Date createdDate;
	
	private Date updatedDate;

	private String userId;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "prodRefId")
	private ProductDetailsEntity detailsEntity;
	
	public ProductStockEntity() {
		super();
		setStockId(generateStockCode());
		setCreatedDate(new Date());
		setUpdatedDate(getCreatedDate());
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public String generateStockCode() {
		int min = 1000;  
		int max = 9999;
		int b = (int)(Math.random()*(max-min+1)+min);  
		return "STOCKID"+String.valueOf(b);
		
	}

	public ProductDetailsEntity getDetailsEntity() {
		return detailsEntity;
	}

	public void setDetailsEntity(ProductDetailsEntity detailsEntity) {
		this.detailsEntity = detailsEntity;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
