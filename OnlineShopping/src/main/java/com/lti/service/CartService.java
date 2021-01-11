package com.lti.service;

import java.util.List;

import com.lti.dto.Cart;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;

public interface CartService {
	
	public Order addorUpdateOrder(Order order);

	public Order findOrderById(long orderId);

	public List<Order> viewAllOrders();

	public OrderItem addOrUpdateOrderItem(OrderItem orderItem);

	public OrderItem findOrderItemById(long orderItemId);

	public List<OrderItem> viewAllOrderItems();

	public Cart getCart();

	public void setCart(Cart cart);

	public void createCart(long customerId);

	public void addToCart(long productId);

	public int searchProductinCart(Product product);

	public void increaseProductQuantityinCart(Product product, int quantity);

	public double calculateProductPriceWithQuantityinCart(Product product, int quantity);

	public double calculateTotalPrice(List<Product> products, List<Integer> quantity);

	public void addIntoOrderAndOrderItemByCart(Cart cart);

}
