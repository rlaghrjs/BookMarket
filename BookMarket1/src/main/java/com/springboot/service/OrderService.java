package com.springboot.service;

import com.springboot.domain.Order;

public interface  OrderService {

	 void confirmOrder(String  bookId, long quantity);
	 void saveOrder(Order order);

}
