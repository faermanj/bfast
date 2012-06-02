package jfaerman.bfast.cfg;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import jfaerman.bfast.cache.OrderLoader;

import org.infinispan.cdi.ConfigureCache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.loaders.CacheLoader;

import static org.infinispan.eviction.EvictionStrategy.*;

public class InfinispanProducers {
  
  @Inject OrderLoader orderLoader;
  
	@OrdersCache
	@ConfigureCache("ordersCache")
	@Produces
	public Configuration configureCache() {
		return new ConfigurationBuilder().clustering()
				.cacheMode(CacheMode.LOCAL)
				/*
				.eviction()
					//Low Inter-reference Recency Set = recent + frequent
					.strategy(LIRS)
					.maxEntries(5)
					*/
				.jmxStatistics().enable()
				.build();
	}
}
