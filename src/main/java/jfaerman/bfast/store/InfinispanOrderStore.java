package jfaerman.bfast.store;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Collection;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Singleton;

import jfaerman.bfast.cfg.OrdersCache;
import jfaerman.bfast.model.Order;

import org.infinispan.Cache;

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
	  orders.put(order.getUuid(), order);
	  return order;
	}

	@Override
	public Order get(String uuid) {
		return orders.get(uuid);
	}

	@Override
	public Order put(Order order, Integer maxIdle) {
		return orders.put(order.getUuid(), order, UNLIMITED, SECONDS, maxIdle, SECONDS);
	} 

	@Inject
	public void registerFatListener(@New FatListener fatListener){
		log.info("\n* Initializing Infinispan Order Store *\n");
		//orders.addListener(fatListener);
	}

  @Override
  public long count() {
    return orders.size();
  }

}
