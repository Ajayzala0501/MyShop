package com.projects.myshop.enitity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class ProductTypesEntity {

	@Id
	@SequenceGenerator(name = "productTypes_Sequence", sequenceName = "productTypes_Sequence", allocationSize = 1)
	@GeneratedValue(generator = "productTypes_Sequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String typeId;
	
	private String typeName;
	
	private Date createdDate;
	
	private Date updtaedDate;

	private String orgRefId;
	
	public ProductTypesEntity() {
		Date d = new Date();
		setCreatedDate(d);
		setUpdtaedDate(d);
		setTypeId(generateTypeCode());
	}
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}
	

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public String getOrgRefId() {
		return orgRefId;
	}
	public void setOrgRefId(String orgRefId) {
		this.orgRefId = orgRefId;
	}
	
	
}
