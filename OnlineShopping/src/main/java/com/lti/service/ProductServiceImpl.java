package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Product;
import com.lti.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product addOrUpdateProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.addOrUpdateProduct(product);
	}

	@Override
	public Product findProductById(long productId) {
		// TODO Auto-generated method stub
		return productRepository.findProductById(productId);
	}

	@Override
	public List<Product> viewAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.viewAllProducts();
	}

	@Override
	public Product updateStockOfProduct(long ProductId, int stock) {
		// TODO Auto-generated method stub
		return productRepository.updateStockOfProduct(ProductId, stock);
	}

	@Override
	public void removeProduct(long ProductId) {
		// TODO Auto-generated method stub
		productRepository.removeProduct(ProductId);
	}

	@Override
	public List<Product> viewAllProductByCategoryName(String category) {
		// TODO Auto-generated method stub
		return productRepository.viewAllProductByCategoryName(category);
	}

	@Override
	public List<Product> viewProductsByProductName(String productName) {
		// TODO Auto-generated method stub
		return productRepository.viewAllProductByCategoryName(productName);
	}

	@Override
	public List<Product> filterByProductName(String productName) {
		// TODO Auto-generated method stub
		return productRepository.filterByProductName(productName);
	}

	@Override
	public List<Product> filterByPriceAndCategoryName(double minPrice, double maxPrice, String categoryName) {
		// TODO Auto-generated method stub
		return productRepository.filterByPriceAndCategoryName(minPrice, maxPrice, categoryName);
	}

	@Override
	public List<Product> filterByBrand(String brand) {
		// TODO Auto-generated method stub
		return productRepository.filterByBrand(brand);
	}

	@Override
	public List<Product> filterByProductPrice(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return productRepository.filterByProductPrice(minPrice,maxPrice);
	}

}
