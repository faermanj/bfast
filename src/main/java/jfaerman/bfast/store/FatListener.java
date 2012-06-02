package jfaerman.bfast.store;

import java.util.logging.Logger;

import javax.inject.Inject;

import jfaerman.bfast.model.Order;

import org.infinispan.Cache;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;

@Listener
public class FatListener {
	@Inject Logger log;
	
	@CacheEntryModified
	public void onCacheEntryModified(CacheEntryModifiedEvent<String, Order> event){
		if(event.isPre()) return;
		Order order = event.getValue();
		if(order.isFat()){
			log.warning("** Take care with all that fat! **");
		}
			
		
	}
}
