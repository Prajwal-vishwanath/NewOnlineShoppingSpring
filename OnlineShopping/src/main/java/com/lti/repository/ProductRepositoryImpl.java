package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lti.dto.CategoryDto;
import com.lti.dto.UserDto;
import com.lti.entity.Product;
@Component
public class ProductRepositoryImpl implements ProductRepository {
	
	@PersistenceContext
	EntityManager em;
	

	@Transactional
	public Product addOrUpdateProduct(Product product) {
		Product product2 = em.merge(product);
		return product2;
	}

	
	public Product findProductById(long productId) {
		return em.find(Product.class, productId);
	}

	
	public List<Product> viewAllProducts() {
		String jpql = "from Product p";
		Query query = em.createQuery(jpql);
		List<Product> products = query.getResultList();
		return products;
	}
	
	public List<Product> viewAllApprovedProducts() {
		String jpql = "from Product p where p.approved= true";
		Query query = em.createQuery(jpql);
		List<Product> products = query.getResultList();
		return products;
	}
	
	public List<Product> viewAllProductsToBeApproved() {
		String jpql = "from Product p where p.approved = false";
		Query query = em.createQuery(jpql);
		List<Product> products = query.getResultList();
		return products;
	}


	@Transactional
	public Product updateStockOfProduct(long ProductId, int stock) {
		Product product = em.find(Product.class, ProductId);
		product.setStock(stock);
		product = em.merge(product);
		return product;
	}

	@Transactional
	public void removeProduct(Product product) {
		long productId = product.getProductId();
		String jpql = "delete from Product p where p.productId=: prodId";
		Query query = em.createQuery(jpql);
		query.setParameter("prodId", productId);
		System.out.println("deleted...?");
		query.executeUpdate();
	}

	
	public List<Product> viewAllProductByCategoryName(String category) {
		String jpql = "select p from Product p where p.categoryName=:category and p.approved= true";
		Query query = em.createQuery(jpql);
		query.setParameter("category", category);
		List<Product> products = query.getResultList();
		return products;
	}

	
	public List<Product> viewProductsByProductName(String productName) {
		String jpql = "select p from Product p where p.productName like %:prodname%";
		Query query = em.createQuery(jpql);
		query.setParameter("prodname", productName);
		List<Product> prod =  query.getResultList();
		return prod;
	}

	
	public List<Product> filterByProductName(String productName) {
		String jpql = "select p from Product p where UPPER(p.productName) LIKE :prodname and p.approved= true" ;
		Query query = em.createQuery(jpql);
		query.setParameter("prodname", "%"+productName.toUpperCase()+"%");
		List<Product> prod =  query.getResultList();
		return prod;
	}

	
	public List<Product> filterByPriceAndCategoryName(double minPrice, double maxPrice, String categoryName) {
		String jpql = "select p from Product p where p.categoryName =: categorynm AND p.productPrice> :minPrice AND p.productPrice< :maxPrice ";
		Query query=em.createQuery(jpql);
		query.setParameter("minPrice", minPrice);
		query.setParameter("maxPrice", maxPrice);
		query.setParameter("categorynm", categoryName);
		List<Product> prod = query.getResultList();
		return prod;
	}

	
	public List<Product> filterByBrand(String brand) {
		String jpql = "select p from Product p where p.brand=:brandName";
		Query query = em.createQuery(jpql);
		query.setParameter("brandName", brand);
		List<Product> prod =  query.getResultList();
		return prod;
	}

	
	public List<Product> filterByProductPrice(double minPrice, double maxPrice) {
		String jpql = "select p from Product p where p.productPrice between :minPrice AND :maxPrice ";
		Query query=em.createQuery(jpql);
		query.setParameter("minPrice", minPrice);
		query.setParameter("maxPrice", maxPrice);
		List<Product> prod = query.getResultList();
		return prod;
	}
	
	public CategoryDto listAllCategories (){
		String jpql = "select distinct p.categoryName from Product p where p.approved= true";
		Query query=em.createQuery(jpql);
		CategoryDto categoryDto= new CategoryDto();  
		categoryDto.setCategories(query.getResultList());  
		return categoryDto;
	}
}
