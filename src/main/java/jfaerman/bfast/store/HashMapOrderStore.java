package jfaerman.bfast.store;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jfaerman.bfast.model.Order;

public class HashMapOrderStore implements OrderStore {
	Map<String, Order> orders = new ConcurrentHashMap<String, Order>();

	@Override
	public Collection<Order> findAll() {
		return orders.values();
	}

	@Override
	public Order put(Order order) {
		return orders.put(order.getUuid(),order);
	}

	@Override
	public Order get(String uuid) {
		return orders.get(uuid);
	}

	@Override
	public Order put(Order order, Integer maxIdle) {
		throw new UnsupportedOperationException();
	}
	
	
	

}
