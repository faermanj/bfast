package jfaerman.bfast.store;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Singleton;

import jfaerman.bfast.cfg.OrdersCache;
import jfaerman.bfast.model.Order;

import org.infinispan.Cache;
import static java.util.concurrent.TimeUnit.*;

@Alternative
@Singleton
public class InfinispanOrderStore implements OrderStore {
	private static int UNLIMITED = -1;

	@Inject @OrdersCache Cache<String, Order> orders;
	@Inject Logger log;
	
	@Override
	public Collection<Order> findAll() {
		return orders.values();
	}

	@Override
	public Order put(Order order) {
		return orders.put(order.getUuid(), order);
	}

	@Override
	public Order get(String uuid) {
		return orders.get(uuid);
	}

	@Override
	public Order put(Order order, Integer maxIdle) {
		return orders.put(order.getUuid(), order, UNLIMITED, SECONDS, maxIdle, SECONDS);
	} 

	@PostConstruct
	public void init(){
		log.info("* Initializing Infinispan Order Store *");
		orders.addListener(new FatListener());
	}

}
