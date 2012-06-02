package jfaerman.bfast.store;

import java.util.Collection;

import jfaerman.bfast.model.Order;

public interface OrderStore {

	Collection<Order> findAll();

	Order put(Order order);
	
	Order put(Order order, Integer maxIdle);

	Order get(String uuid);
	
	long count();
	

}
