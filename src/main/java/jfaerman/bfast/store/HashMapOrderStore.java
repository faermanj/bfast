package jfaerman.bfast.store;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Singleton;

import jfaerman.bfast.model.Order;

@Alternative
@Singleton
public class HashMapOrderStore implements OrderStore {
  @Inject
  Logger log;
  private Map<String, Order> orders = new ConcurrentHashMap<String, Order>();

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
    throw new UnsupportedOperationException();
  }

  @Override
  public long count() {
    return orders.size();
  }

  @Inject
  public void init() {
    log.info("\n* Initializing HashMap Order Store *\n");
  }

}
