package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_retailer1")
public class Retailer {
	
	@Id
	@SequenceGenerator(name = "seq_retailer", initialValue = 601, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_retailer")
	long retailerId;
	String retailerName;
	String mobileNumber;
	String emailId;
	String password;
	boolean approved;
	
	
	@OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Product> product;


	public long getRetailerId() {
		return retailerId;
	}


	public void setRetailerId(long retailerId) {
		this.retailerId = retailerId;
	}


	public String getRetailerName() {
		return retailerName;
	}


	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isApproved() {
		return approved;
	}


	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	public List<Product> getProduct() {
		return product;
	}


	public void setProduct(List<Product> product) {
		this.product = product;
	}


	
}
