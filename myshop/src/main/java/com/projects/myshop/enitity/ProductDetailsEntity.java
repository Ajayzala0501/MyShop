package com.projects.myshop.enitity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

@Entity	
public class ProductDetailsEntity {

	@Id
	@SequenceGenerator(name = "ProductDetails_Sequence", sequenceName = "ProductDetails_Sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductDetails_Sequence")
	private long id;

	private String prodId;
	
	private String userId;
	
	private String typeId;
	
	private String companyId;
	
	private String productModel;
	
	private String productColour;

	@Lob 
	@Column(name="productSpecification", length=2000)
	private String productSpecification;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "detailsEntity",fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private ProductStockEntity stockEntity;
	
	private int productPrice;
	
	private Date createdDate;
	
	public ProductStockEntity getStockEntity() {
		return stockEntity;
	}


	private Date updatedDate;
	
	public ProductDetailsEntity() {
		// TODO Auto-generated constructor stub
		Date d = new Date();
		setCreatedDate(d);
		setUpdatedDate(d);
		setProdId(generateProductCode());
	}
	public String generateProductCode(){
		int min = 1000;  
		int max = 9999;
		int b = (int)(Math.random()*(max-min+1)+min);  
		return "PRODID"+String.valueOf(b);		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getProductSpecification() {
		return productSpecification;
	}

	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}

	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getProductModel() {
		return productModel;
	}


	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}


	public String getProductColour() {
		return productColour;
	}


	public void setProductColour(String productColour) {
		this.productColour = productColour;
	}

	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
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
	
	
}
