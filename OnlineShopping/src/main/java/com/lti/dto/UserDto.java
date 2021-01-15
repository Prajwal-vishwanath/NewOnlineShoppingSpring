package com.lti.dto;

import java.util.List;

import com.lti.entity.Customer;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

public class UserDto {
	List<Product> products;
	List<Customer> customers;
    List<Retailer> retailers;
    double revenue;
    
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<Retailer> getRetailers() {
		return retailers;
	}
	public void setRetailers(List<Retailer> retailers) {
		this.retailers = retailers;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
    
    
}
