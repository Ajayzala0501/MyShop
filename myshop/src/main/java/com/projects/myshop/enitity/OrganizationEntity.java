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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_organization_master")
public class OrganizationEntity {

	@Id
	@SequenceGenerator(name = "organization_sequence", sequenceName = "organization_sequence", allocationSize = 1)
	@GeneratedValue(generator = "organization_sequence",strategy = GenerationType.SEQUENCE)
	private long Id;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNumber;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String emailId;
	
	@OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "obj_ref_id",referencedColumnName = "orgid")
	private Registration registration;

	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	private Date createdDate;
	
	private Date updatedDate;
	
	public OrganizationEntity() {
		// TODO Auto-generated constructor stub
		
		Date d = new Date();
		setCreatedDate(d);
		setUpdatedDate(d);
		
	}
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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

	public void setUpdatedDate(Date updatedDate){
		this.updatedDate = updatedDate;
	}
}
