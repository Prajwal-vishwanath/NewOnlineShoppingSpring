package com.lti.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Product;
import com.lti.service.CartService;
import com.lti.service.ProductService;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class ProductResource {
	
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	

@GetMapping(value="/displayProductDetails/{productId}" )
 public Product displayProductDetailsById(@PathVariable("productId") long productId) {
	 return productService.findProductById(productId);
 }

@PostMapping(value="/addProduct")
public Product addProduct(@RequestBody Product product) {
	Product prod = productService.addOrUpdateProduct(product);
	return prod;
	
}

@GetMapping(value="/viewAllProducts")
public List<Product> viewAllProducts(){
	List<Product> prods = productService.viewAllProducts();
	return prods;
}

}
