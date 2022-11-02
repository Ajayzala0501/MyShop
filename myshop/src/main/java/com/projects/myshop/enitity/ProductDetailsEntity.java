package com.projects.myshop.enitity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity	
public class ProductDetailsEntity {

	@Id
	@SequenceGenerator(name = "ProductDetails_Sequence", sequenceName = "ProductDetails_Sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductDetails_Sequence")
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "prod_ref_id",referencedColumnName = "prodId")
	private ProductsEntity productsEntity;
	
	private String typeId;
	
	private String productCompany;
	
	private String productModel;
	
	private String productColour;
	
	private String size;
	
	private int ram;
	
	private int rom;
	
	private int productQuantity;
	
	private int productPrice;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	
	public ProductDetailsEntity() {
		// TODO Auto-generated constructor stub
		Date d = new Date();
		setCreatedDate(d);
		setUpdatedDate(d);
	
	}


	public ProductsEntity getProductsEntity() {
		return productsEntity;
	}


	public void setProductsEntity(ProductsEntity productsEntity) {
		this.productsEntity = productsEntity;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


	public String getProductCompany() {
		return productCompany;
	}


	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
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


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public int getRam() {
		return ram;
	}


	public void setRam(int ram) {
		this.ram = ram;
	}


	public int getRom() {
		return rom;
	}


	public void setRom(int rom) {
		this.rom = rom;
	}


	public int getProductQuantity() {
		return productQuantity;
	}


	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
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
