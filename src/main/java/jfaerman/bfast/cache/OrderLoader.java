package jfaerman.bfast.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import jfaerman.bfast.model.Order;

import org.infinispan.container.entries.ImmortalCacheEntry;
import org.infinispan.container.entries.ImmortalCacheValue;
import org.infinispan.container.entries.InternalCacheEntry;
import org.infinispan.loaders.AbstractCacheLoader;
import org.infinispan.loaders.CacheLoaderConfig;
import org.infinispan.loaders.CacheLoaderException;


public class OrderLoader extends AbstractCacheLoader{

  @Inject EntityManager em;
  @Inject Logger log;
  
  public OrderLoader() {
    System.out.println("**# OrderLoader created");
  }
 
  @Override
  public Class<? extends CacheLoaderConfig> getConfigurationClass() {
    return OrderLoaderConfig.class;
  }
  
  @Override
  public InternalCacheEntry load(Object key) throws CacheLoaderException {
    log.info("**# load("+key+")");
    Order value = em.find(Order.class, key);
    return new ImmortalCacheEntry(key, value);
  }
  
  @Override
  public Set<InternalCacheEntry> load(int arg0) throws CacheLoaderException {
    log.info("**# load(int)");
    return new HashSet<InternalCacheEntry>();
  }
  
  @Override
  public Set<InternalCacheEntry> loadAll() throws CacheLoaderException {
    log.info("**# loadAll()");
    List<Order> orders = em.createNamedQuery("order.all").getResultList();
    Set<InternalCacheEntry> entries = new HashSet<InternalCacheEntry>();
    for(Order order:orders){
      InternalCacheEntry entry = new ImmortalCacheEntry(order.getUuid(),order);
      entries.add(entry);
    }
    return entries;
  }
  
  @Override
  public Set<Object> loadAllKeys(Set<Object> arg0) throws CacheLoaderException {
    log.info("**# loadAllKeys()");
    List<Order> orders = em.createNamedQuery("order.all").getResultList();
    Set keys = new HashSet();
    for(Order order:orders){
     keys.add(order.getUuid());
    }
    return null;
  }
  
  @Override
  public void start() throws CacheLoaderException {
    log.info("**# start()");  
  }
  
  @Override
  public void stop() throws CacheLoaderException {
    // TODO Auto-generated method stub
    log.info("**# stop()");
  }
  
}
