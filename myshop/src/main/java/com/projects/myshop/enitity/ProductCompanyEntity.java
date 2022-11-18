package com.projects.myshop.enitity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ProductCompanyEntity {
	@Id
	@SequenceGenerator(name = "productCompany_Sequence", sequenceName = "productCompany_Sequence", allocationSize = 1)
	@GeneratedValue(generator = "productCompany_Sequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String companyId;
	
	private String companyName;
	
	private Date createdDate;
	
	private Date updtaedDate;
	
	@OneToOne
	@JoinColumn(name = "obj_ref_id",referencedColumnName = "orgid")
	private Registration registration;

	
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	public ProductCompanyEntity() {
		Date d = new Date();
		setCreatedDate(d);
		setUpdtaedDate(d);
		setCompanyId(generateTypeCode());
	}
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdtaedDate() {
		return updtaedDate;
	}

	public void setUpdtaedDate(Date updtaedDate) {
		this.updtaedDate = updtaedDate;
	}
	
	public String generateTypeCode() {
		int min = 1000;  
		int max = 9999;
		int b = (int)(Math.random()*(max-min+1)+min);  
		return "TYPEID"+String.valueOf(b);
		
	}
}
