package com.lti.dto;

import java.util.List;

import com.lti.entity.Product;

public class Cart {
	long customerId;
	List<Product> products;
	List<Integer> quantity;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Integer> getQuantity() {
		return quantity;
	}
	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}
	
	

}
