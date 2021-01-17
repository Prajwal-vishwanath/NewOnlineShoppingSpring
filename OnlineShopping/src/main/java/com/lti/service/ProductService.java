package com.lti.service;

import java.util.List;

import com.lti.dto.CategoryDto;
import com.lti.entity.Product;

public interface ProductService {
	public Product addOrUpdateProduct(Product product);

	public Product findProductById(long productId);

	public List<Product> viewAllProducts();

	public Product updateStockOfProduct(long ProductId, int stock);

	public void removeProduct(Product product);

	public List<Product> viewAllProductByCategoryName(String category);

	public List<Product> viewProductsByProductName(String productName);

	public List<Product> filterByProductName(String productName);

	public List<Product> filterByPriceAndCategoryName(double minPrice, double maxPrice, String categoryName);

	public List<Product> filterByBrand(String brand);

	public List<Product> filterByProductPrice(double minPrice, double maxPrice);
	public List<Product> viewAllProductsToBeApproved();
	
	public List<Product> viewAllApprovedProducts();
	
	public CategoryDto listAllCategories ( );
}

