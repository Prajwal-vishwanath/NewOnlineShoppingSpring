package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.dto.Cart;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;

@Component
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartService cartService;

	@Override
	public Order addorUpdateOrder(Order order) {
		// TODO Auto-generated method stub
		return cartService.addorUpdateOrder(order);
	}

	@Override
	public Order findOrderById(long orderId) {
		// TODO Auto-generated method stub
		return cartService.findOrderById(orderId);
	}

	@Override
	public List<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		return cartService.viewAllOrders();
	}

	@Override
	public OrderItem addOrUpdateOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return cartService.addOrUpdateOrderItem(orderItem);
	}

	@Override
	public OrderItem findOrderItemById(long orderItemId) {
		// TODO Auto-generated method stub
		return cartService.findOrderItemById(orderItemId);
	}

	@Override
	public List<OrderItem> viewAllOrderItems() {
		// TODO Auto-generated method stub
		return cartService.viewAllOrderItems();
	}

	@Override
	public Cart getCart() {
		// TODO Auto-generated method stub
		return cartService.getCart();
	}

	@Override
	public void setCart(Cart cart) {
		// TODO Auto-generated method stub
		cartService.setCart(cart);
	}

	@Override
	public void createCart(long customerId) {
		// TODO Auto-generated method stub
		cartService.createCart(customerId);

	}

	@Override
	public void addToCart(long productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int searchProductinCart(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void increaseProductQuantityinCart(Product product, int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public double calculateProductPriceWithQuantityinCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateTotalPrice(List<Product> products, List<Integer> quantity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addIntoOrderAndOrderItemByCart(Cart cart) {
		// TODO Auto-generated method stub

	}

}
