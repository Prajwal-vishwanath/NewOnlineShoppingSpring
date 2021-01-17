package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

@Component
public class UserRepositoryImpl implements UserRepository {
	
	@PersistenceContext
	EntityManager em;
	

	
	@Transactional
	public Admin addAnAdmin(Admin admin) {
		Admin admin1 = em.merge(admin);
		return admin1;
	}


	public Admin authenticateWithEmailAndPasswordAdmin(String email, String password) {
		String jpql = "Select a from Admin a where a.emailId=:emailId and a.password=:pwd";
		Admin admin;
		try {
			Query query = em.createQuery(jpql);
			query.setParameter("emailId", email);
			query.setParameter("pwd", password);
			admin = (Admin) query.getSingleResult();
		} catch (Exception NoResultException) {
			return null;
		}
		return admin;
	}


	@Transactional
	public Customer addACustomer(Customer customer) {
		Customer customer2 = em.merge(customer);
		return customer2;
	}

	@Transactional
	public Customer updateACustomer(Customer customer) {
		Customer customer2 = em.merge(customer);
		return customer2;
	}

	
	public Customer findCustomerById(long customerId) {
		return em.find(Customer.class, customerId);
	}

	
	public List<Customer> viewAllCustomers() {
		String jpql = "from Customer c";
		Query query = em.createQuery(jpql);
		List<Customer> customers = query.getResultList();
		return customers;
	}

	
	public Customer authenticateWithEmailAndPasswordCustomer(String email, String password) {
		String jpql = "Select c from Customer c where c.emailId=:emailId and c.password=:pwd";
		Customer customer;
		try {
			Query query = em.createQuery(jpql);
			query.setParameter("emailId", email);
			query.setParameter("pwd", password);
			 customer = (Customer) query.getSingleResult();
		} catch (Exception NoResultException) {
			return null;
		}
		return customer;
	}

	
	@Transactional
	public boolean updatePasswordwithEmail(String email, String pwd) {
		String jpql = "Select c from Customer c where c.emailId=:emailId";
		try {
			Query query = em.createQuery(jpql);
			query.setParameter("emailId", email);
			// query.setParameter("pwd", pwd);
			Customer customer = (Customer) query.getSingleResult();
			customer.setPassword(pwd);
			em.merge(customer);
		} catch (Exception NoResultException) {
			return false;
		}
		return true;
	}

	
	public Retailer authenticateWithEmailAndPasswordRetailer(String email, String password) {
		String jpql = "Select r from Retailer r where r.emailId=:emailId and r.password=:pwd";
		
		Retailer retailer;
		try {
			Query query = em.createQuery(jpql);
			query.setParameter("emailId", email);
			query.setParameter("pwd", password);
			retailer = (Retailer) query.getSingleResult();
		} catch (Exception NoResultException) {
			return null;
		}
		return retailer;
	}

	
	@Transactional
	public Retailer addOrUpdateRetailer(Retailer retailer) {
		Retailer retailer2 = em.merge(retailer);
		return retailer2;
	}

	
	public Retailer findRetailerById(long retailerId) {
		return em.find(Retailer.class, retailerId);
	}

	
	public List<Retailer> viewAllRetailers() {
		String jpql = "from Retailer r";
		Query query = em.createQuery(jpql);
		List<Retailer> retailers = query.getResultList();
		return retailers;
	}

	
	public double revenueGeneratedByRetailer(long retailerId) {
		String jpql="Select p.productPrice,oi.quantity from  Product p join OrderItem oi on p.productId=oi.product.productId where p.retailer.retailerId=:retailerId";
		Query query = em.createQuery(jpql);
		query.setParameter("retailerId",retailerId);
		List<Object[]> objects=query.getResultList();
		double revenue=0;
		for(Object[] o:objects) {
		  revenue=revenue+((Double)o[0])*((Integer)o[1]);

		}
		return revenue;
	}

	
	public List<Product> displayRetailerProducts(long retailerId) {
		String jpql = "Select p from Product p where p.retailer.retailerId=:retailerId";
		Query query = em.createQuery(jpql);
		query.setParameter("retailerId", retailerId);
		List<Product> products = query.getResultList();
		return products;
	}

	
	@Transactional
	public Product addProductByRetailer(Retailer retailer, Product product) {
		List<Product> products=retailer.getProduct();
		if(products==null) {
			products=new ArrayList<Product>();
		}
		products.add(product);
		product.setRetailer(retailer);
		retailer.setProduct(products);
		Product product2= em.merge(product);
		return product2;
	}
	

	public long findUserIdByEmailId(String emailId) {
		String jpql = "select u.customerId from Customer u where u.emailId =: userEmail";
		long customerId;
		Query query = em.createQuery(jpql);
		query.setParameter("userEmail", emailId);
	    customerId = (long) query.getSingleResult(); 
		return customerId;
	}
	
	public boolean checkEmail(String emailId) {
		Customer customer;
		try {
			String jpql = "select u from Customer u where u.emailId =: userEmail";
			Query query = em.createQuery(jpql);
			query.setParameter("userEmail", emailId);
			customer= (Customer) query.getSingleResult();
			if(customer !=null)
			return true;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
