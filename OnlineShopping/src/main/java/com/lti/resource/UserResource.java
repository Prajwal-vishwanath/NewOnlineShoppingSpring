package com.lti.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.entity.Customer;
import com.lti.entity.Retailer;
import com.lti.service.CartService;
import com.lti.service.ProductService;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class UserResource {

	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public Customer addACustomer(@RequestBody Customer customer) {
		Customer cust = userService.addACustomer(customer);
		// Customer customer1 = userService.findCustomerById(customerId);
		return cust;
	}

	@GetMapping(value = "/findCustomerById")
	public Customer findCustomerById(@RequestParam("customerId") long customerId) {
		return userService.findCustomerById(customerId);
	}

	@RequestMapping("/viewAllCustomers")
	public List<Customer> viewAllCustomers() {
		return userService.viewAllCustomers();
	}
	
	@RequestMapping(value = "/addRetailer", method =RequestMethod.POST  )
	public Retailer addARetailer(@RequestBody Retailer retailer) {
		Retailer retail = userService.addOrUpdateRetailer(retailer);
		return retail;
	}
	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
	public Customer updateACustomer(@RequestBody Customer customer) {
		Customer cust = userService.updateACustomer(customer);
		// Customer customer1 = userService.findCustomerById(customerId);
		return cust;
	}
	
	@RequestMapping(value = "/loginCustomer", method = RequestMethod.POST)
	public String authenticateWithEmailAndPasswordCustomer(@RequestBody Login login) {
		boolean success=userService.authenticateWithEmailAndPasswordCustomer(login.getEmailId(),login.getPassword());
		if(success) {
			return "Success";
		}
		return "Failed";
	}

	
	
	
}
