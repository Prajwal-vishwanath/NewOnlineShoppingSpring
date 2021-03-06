package com.lti.service;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

public interface UserService {

	// ----------- Admin --------------------
	public Admin addAnAdmin(Admin admin);

	public Admin authenticateWithEmailAndPasswordAdmin(String email, String password);

	// ----------- Customer --------------------

	public Customer addACustomer(Customer customer);

	public Customer updateACustomer(Customer customer);

	public Customer findCustomerById(long customerId);

	public List<Customer> viewAllCustomers();

	public Customer authenticateWithEmailAndPasswordCustomer(String email, String password);

	public boolean updatePasswordwithEmail(String email, String pwd);
	
	//public String findCustomerNameByEmailId (String emailId);
	public boolean checkEmail(String emailId);
	

	// ----------- retailer --------------------

	public Retailer authenticateWithEmailAndPasswordRetailer(String email, String password);

	public Retailer addOrUpdateRetailer(Retailer retailer);

	public Retailer findRetailerById(long retailerId);

	public List<Retailer> viewAllRetailers();

	public double revenueGeneratedByRetailer(long retailerId);

	public List<Product> displayRetailerProducts(long retailerId);

	public Product addProductByRetailer(Retailer retailer, Product product);
}
