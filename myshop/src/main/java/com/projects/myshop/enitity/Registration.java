package com.projects.myshop.enitity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_registration")
public class Registration {
	
	@Id
	@SequenceGenerator(name = "tbl_registration_sequence",sequenceName = "tbl_registration_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_registration_sequence")
	private long id;
	
	private String orgid;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private boolean status;
	
	private Date CreatedDate;
	
	private Date UpdatedDate;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public Date getUpdatedDate() {
		return UpdatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		UpdatedDate = updatedDate;
	}
	
	public Registration() {
		Date d = new Date();
		setCreatedDate(d);
		setUpdatedDate(d);
		setStatus(false);
		setOrgid(generateUsername());
	}
	public String generateUsername() {
		int min = 1000;  
		int max = 9999;
		int b = (int)(Math.random()*(max-min+1)+min);  
		return "USERID"+String.valueOf(b);
	}

	
}


