package com.lti.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Cart;
import com.lti.dto.Login;
import com.lti.dto.ProductDto;
import com.lti.dto.UserDto;
import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
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
	
	
	// changed 
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
	public Customer updateACustomer(@RequestBody Customer customer) {
		Customer c = userService.findCustomerById(customer.getCustomerId());
		c.setAddress(customer.getAddress());
		
		Customer cust = userService.updateACustomer(c);
		// Customer customer1 = userService.findCustomerById(customerId);
		return cust;
	}
	
	
	@RequestMapping(value = "/loginCustomer", method = RequestMethod.POST)
	public Customer authenticateWithEmailAndPasswordCustomer(@RequestBody Login login) {
		Customer customer=userService.authenticateWithEmailAndPasswordCustomer(login.getEmailId(),login.getPassword());
		
		return customer;
	}

	@GetMapping(value = "/findRetailerById")
	public Retailer findRetailerById(@RequestParam("retailerId") long retailerId) {
		return userService.findRetailerById(retailerId);
	}
	
	@RequestMapping(value = "/loginRetailer", method = RequestMethod.POST)
    public Retailer authenticateWithEmailAndPasswordRetailer(@RequestBody Login login) {
        Retailer retailer =userService.authenticateWithEmailAndPasswordRetailer(login.getEmailId(), login.getPassword());
        return retailer;
    }
   
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public Admin authenticateWithEmailAndPasswordAdmin(@RequestBody Login login) {
        Admin admin =userService.authenticateWithEmailAndPasswordAdmin(login.getEmailId(), login.getPassword());
        return admin;
    }
   
    @RequestMapping(value = "/addAdmin", method =RequestMethod.POST  )
    public  Admin addAnAdmin(@RequestBody Admin admin) {
        Admin ad = userService.addAnAdmin(admin);
        return ad;
    }
    
    @RequestMapping(value = "/addToOrderAndOrderItem", method =RequestMethod.POST  )
    public Cart addtoOrderAndOrderItem(@RequestBody Cart cart ) {
    	System.out.println(cart.getCustomerId()+" ");
    	
    	cartService.addIntoOrderAndOrderItemByCart(cart);
    	
    	System.out.println("workin2...");
    	return cart;
    	
    }
    
    @RequestMapping("/viewAllOrders")
	public List<Order> viewAllOrders() {
		return cartService.viewAllOrders();
	}
	
    
    
    @RequestMapping("/viewAllOrderItems")
	public List<OrderItem> viewAllOrderItems() {
		return cartService.viewAllOrderItems();
	}
    
    @RequestMapping("/viewAllRetailers")
    public List<Retailer> viewAllRetailers() {
    return userService.viewAllRetailers();
    }
    
    @GetMapping(value = "/displayRetailerProducts/{retailerId}")
    public List<Product> displayRetailerProducts(@PathVariable("retailerId") long retailerId) {
    List<Product> prods = userService.displayRetailerProducts(retailerId);
    return prods;
    }
    
    @PostMapping(value="/addProductByRetailer/{retailerId}")
    public Product addProductByRetailer(@PathVariable("retailerId") long retailerId,@RequestBody Product product) {
    Retailer retailer=userService.findRetailerById(retailerId);
    Product prod = userService.addProductByRetailer(retailer,product);
    return prod;
    }
    
    @GetMapping(value = "/revenue/{retailerId}")
    public UserDto revenueGeneratedByRetailer(@PathVariable("retailerId") long retailerId) {
    double totalsum=userService.revenueGeneratedByRetailer(retailerId);
    UserDto userDto=new UserDto();
    userDto.setRevenue(totalsum);
    return userDto;
    }
	
    @GetMapping(value="/orderHistory/{customerId}")
    public List<ProductDto> viewOrderHistoryByCustomer(@PathVariable("customerId") long customerId) {
    return cartService.viewOrderHistoryByCustomer(customerId);
    }
    
    
    @PostMapping(value="/approveProducts")
    public List<Product> approveProducts(@RequestBody List<Product> products){
    List<Product> items= products;
    for(Product p: items ) {
    	p.setApproved(true);
    productService.addOrUpdateProduct(p);
    }
    return items;
    }
    
    @PostMapping(value="/deleteProducts")
    public List<Product> deleteProducts(@RequestBody List<Product> products){
    List<Product> items= products;
    for(Product p: items ) {
    productService.removeProduct(p);
    }
    return items;
    }
    
    @RequestMapping(value="/findEmail/{emailId}")
    public boolean checkEmail(@PathVariable("emailId") String emailId) {
    	return userService.checkEmail(emailId);
    }
    
    @PostMapping(value="/updatePassword")
    public UserDto  updatePasswordwithEmail(@RequestBody Login login) {
    	UserDto userDto = new UserDto();
    		userDto.setUpdate(userService.updatePasswordwithEmail(login.getEmailId(), login.getPassword()));	
    			return userDto;
    }
}
