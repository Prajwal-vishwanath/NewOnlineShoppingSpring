package com.lti.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lti.dto.Cart;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;

@Component
public class CartRepositoryImpl implements CartRepository {

	Cart cart;

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Order addorUpdateOrder(Order order) {
		Order order2 = em.merge(order);
		return order2;
	}

	
	public Order findOrderById(long orderId) {
		Order order = em.find(Order.class, orderId);
		return order;
	}

	
	public List<Order> viewAllOrders() {
		String jpql = "from Order o";
		Query query = em.createQuery(jpql);
		List<Order> orders = query.getResultList();
		return orders;
	}

	@Transactional
	public OrderItem addOrUpdateOrderItem(OrderItem orderItem) {
		OrderItem orderItem2 = em.merge(orderItem);
		return orderItem2;
	}

	
	public OrderItem findOrderItemById(long orderItemId) {
		return em.find(OrderItem.class, orderItemId);
	}

	
	public List<OrderItem> viewAllOrderItems() {
		String jpql = "from OrderItem oi";
		Query query = em.createQuery(jpql);
		List<OrderItem> orderItems = query.getResultList();
		return orderItems;
	}

	
	public Cart getCart() {
		// TODO Auto-generated method stub
		return cart;
	}

	
	public void setCart(Cart cart) {
		this.cart = cart;

	}

	
	public void createCart(long customerId) {
		cart = new Cart();
		cart.setCustomerId(customerId);
		cart.setProducts(new ArrayList<Product>());
		cart.setQuantity(new ArrayList<Integer>());

	}

	
	public void addToCart(long productId) {
		Product product = em.find(Product.class, productId);
		cart.getProducts().add(product);
		cart.getQuantity().add(new Integer(1));

	}

	
	public int searchProductinCart(Product product) {
		List<Product> products = cart.getProducts();
		int i = -1;
		for (Product p : products) {
			i = i + 1;
			if (p.getProductId() == product.getProductId()) {
				break;
			}
		}
		return i;
	}

	
	public void increaseProductQuantityinCart(Product product, int quantity) {
		int i = searchProductinCart(product);
		List<Integer> quantity1 = cart.getQuantity();
		quantity1.set(i, quantity);
		cart.setQuantity(quantity1);

	}

	
	public double calculateProductPriceWithQuantityinCart(Product product, int quantity) {
		double price = product.getProductPrice() * quantity;
		return price;
	}

	
	public double calculateTotalPrice(List<Product> products, List<Integer> quantity) {
		double totalPrice = 0;
		int i = 0;
		for (Product p : products) {
			totalPrice = totalPrice + calculateProductPriceWithQuantityinCart(p, (int) quantity.get(i));
//			(p.getProductPrice()*quantity.get(i));
			i = i + 1;
		}
		return totalPrice;
	}

	@Transactional
	public void addIntoOrderAndOrderItemByCart(Cart cart) {
		Order order = new Order();
		order.setCustomer(em.find(Customer.class,cart.getCustomerId()));
		List<OrderItem> oil = new ArrayList<OrderItem>();
		List<Integer> quantity = cart.getQuantity();
		int i = -1;
		for (Product p : cart.getProducts()) {
			i = i + 1;
			OrderItem oi = new OrderItem();
			oi.setProduct(p);
			oi.setQuantity(quantity.get(i));
			oi.setOrder(order);
			oil.add(oi);
		}
		order.setOrderItem(oil);
		order.setOrderDate(LocalDate.now());
		em.merge(order);
	}

}
