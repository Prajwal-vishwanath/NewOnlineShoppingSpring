package com.lti.repository;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

public interface UserRepository {

	// ----------- Admin --------------------
	public Admin addAnAdmin(Admin admin);
	
	public boolean authenticateWithEmailAndPasswordAdmin(String email, String password);


	// ----------- Customer --------------------
	
	public Customer addACustomer(Customer customer);

	public Customer updateACustomer(Customer customer);

	public Customer findCustomerById(long customerId);

	public List<Customer> viewAllCustomers();
	public boolean authenticateWithEmailAndPasswordCustomer(String email, String password);
	public boolean updatePasswordwithEmail(String email, String pwd);
	
	// ----------- retailer --------------------


	public boolean authenticateWithEmailAndPasswordRetailer(String email, String password);


	public Retailer addOrUpdateRetailer(Retailer retailer);

	public Retailer findRetailerById(long retailerId);

	public List<Retailer> viewAllRetailers();

	public double revenueGeneratedByRetailer(long retailerId);

	public List<Product> displayRetailerProducts(long retailerId);

	public Product addProductByRetailer(Retailer retailer, Product product);
	
	
}
