package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_product1")
public class Product {
	
	@Id
	@SequenceGenerator(name = "seq_product", initialValue = 501, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")	
	long productId;
	String productName;
	int stock;
	boolean approved;
	String categoryName;
	double productPrice;
	String productImg;
	String brand;
	String descriptionText;		
	
	
	@ManyToOne
	@JoinColumn(name="retailer_Id")
	Retailer retailer;
	
    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    List<OrderItem> orderitems;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	//@JsonIgnore
	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}
	@JsonIgnore
	public List<OrderItem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
    
	
	
	

}
