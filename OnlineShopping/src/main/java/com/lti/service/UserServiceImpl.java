package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {
	

	@Autowired
	UserRepository userRepository;

	@Override
	public Admin addAnAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return userRepository.addAnAdmin(admin);
	}

	@Override
	public boolean authenticateWithEmailAndPasswordAdmin(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.authenticateWithEmailAndPasswordAdmin(email, password);
	}

	@Override
	public Customer addACustomer(Customer customer) {
		// TODO Auto-generated method stub
		return userRepository.addACustomer(customer);
	}

	@Override
	public Customer updateACustomer(Customer customer) {
		// TODO Auto-generated method stub
		return userRepository.updateACustomer(customer);
	}

	@Override
	public Customer findCustomerById(long customerId) {
		// TODO Auto-generated method stub
		return userRepository.findCustomerById(customerId);
	}

	@Override
	public List<Customer> viewAllCustomers() {
		// TODO Auto-generated method stub
		return userRepository.viewAllCustomers();
	}

	@Override
	public boolean authenticateWithEmailAndPasswordCustomer(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.authenticateWithEmailAndPasswordCustomer(email, password);
	}

	@Override
	public boolean updatePasswordwithEmail(String email, String pwd) {
		// TODO Auto-generated method stub
		return userRepository.updatePasswordwithEmail(email, pwd);
	}

	@Override
	public boolean authenticateWithEmailAndPasswordRetailer(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.authenticateWithEmailAndPasswordRetailer(email, password);
	}

	@Override
	public Retailer addOrUpdateRetailer(Retailer retailer) {
		// TODO Auto-generated method stub
		return userRepository.addOrUpdateRetailer(retailer);
	}

	@Override
	public Retailer findRetailerById(long retailerId) {
		// TODO Auto-generated method stub
		return userRepository.findRetailerById(retailerId);
	}

	@Override
	public List<Retailer> viewAllRetailers() {
		// TODO Auto-generated method stub
		return userRepository.viewAllRetailers();
	}

	@Override
	public double revenueGeneratedByRetailer(long retailerId) {
		// TODO Auto-generated method stub
		return userRepository.revenueGeneratedByRetailer(retailerId);
	}

	@Override
	public List<Product> displayRetailerProducts(long retailerId) {
		// TODO Auto-generated method stub
		return userRepository.displayRetailerProducts(retailerId);
	}

	@Override
	public Product addProductByRetailer(Retailer retailer, Product product) {
		// TODO Auto-generated method stub
		return userRepository.addProductByRetailer(retailer, product);
	}

}
