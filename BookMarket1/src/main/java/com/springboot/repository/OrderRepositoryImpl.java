package com.springboot.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springboot.domain.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository{
	private Map<Long, Order> listOfOrders;

	public OrderRepositoryImpl() {
		listOfOrders = new HashMap<>();
	}

	@Override
	public void saveOrder(Order order) {
	    listOfOrders.put(order.getOrderId(), order);
	}
}
