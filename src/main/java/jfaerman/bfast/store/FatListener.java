package jfaerman.bfast.store;

import jfaerman.bfast.model.Order;

import org.infinispan.Cache;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;

@Listener
public class FatListener {
	
	@CacheEntryModified
	public void onCacheEntryModified(CacheEntryModifiedEvent<String, Order> event){
		Cache<String, Order> cache = event.getCache();
		Order value = cache.get(event.getKey());
		System.out.println("CacheEntryModified size["+cache.size()+"] " +
				"pre["+event.isPre()+"] " +
				"key["+event.getKey()+"] " +
				"value["+value+"]");
			
		
	}
}
