package com.springboot.repository;

import com.springboot.domain.Order;

public interface OrderRepository {
	 void saveOrder(Order order);
}
