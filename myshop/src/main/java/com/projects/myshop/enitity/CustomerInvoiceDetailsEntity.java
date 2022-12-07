package com.projects.myshop.enitity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
public class CustomerInvoiceDetailsEntity {

	@Id
	@SequenceGenerator(name = "customerInvoiceDetails_Sequence", sequenceName = "customerInvoiceDetails_Sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerInvoiceDetails_Sequence")
	private long id;
	
	private String customerName;
	
	private String customerAddess;
	
	private String customerMobileNumber;
	
	private String invoiceID;
	@Lob 
	@Column(name="productDetails", length=2000)
	private String customerInvoiceProductDetails;
	
	private int totalQuantity;
	
	private int totalAmount;
	
	private String userID;
	
	private Date invoiceDate;
	
	private Date createdDate;
	
	private Date updatedDate;

	public CustomerInvoiceDetailsEntity() {
		super();
		Date d = new Date();
		setCreatedDate(d);
		setUpdatedDate(getCreatedDate());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddess() {
		return customerAddess;
	}

	public void setCustomerAddess(String customerAddess) {
		this.customerAddess = customerAddess;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getCustomerInvoiceProductDetails() {
		return customerInvoiceProductDetails;
	}

	public void setCustomerInvoiceProductDetails(String customerInvoiceProductDetails) {
		this.customerInvoiceProductDetails = customerInvoiceProductDetails;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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
