package jfaerman.bfast.store;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import jfaerman.bfast.model.Order;

@Alternative
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class JPAOrderStore implements OrderStore {

  @Inject EntityManager em;
  @Inject Logger log;

  
  @SuppressWarnings("unchecked")
  @Override
  public Collection<Order> findAll() {
    return em.createNamedQuery("order.all").getResultList();
  }

  @Override
  //TODO: Not the same semantics of put(), but hey, this is only a demo...
  public Order put(Order order) {
    return em.merge(order);
  }

  @Override
  public Order put(Order order, Integer maxIdle) {
    //TODO: No maxIdle in RDBMS, will have to code around this
    return em.merge(order);
  }

  @Override
  public Order get(String uuid) {
    return em.find(Order.class, uuid);
  }

  @Override
  public long count() {
    return (Long) em.createNamedQuery("order.count").getSingleResult();
  }
  
  @Inject
  public void init() {
    log.info("\n* Initializing JPA Order Store *\n");
  }

}
