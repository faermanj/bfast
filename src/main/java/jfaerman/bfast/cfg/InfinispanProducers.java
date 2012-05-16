package jfaerman.bfast.cfg;

import javax.enterprise.inject.Produces;

import org.infinispan.cdi.ConfigureCache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import static org.infinispan.eviction.EvictionStrategy.*;

public class InfinispanProducers {
	@OrdersCache
	@ConfigureCache("ordersCache")
	@Produces
	public Configuration configureCache() {
		return new ConfigurationBuilder().clustering()
				.cacheMode(CacheMode.LOCAL)
				.eviction()
					/*Low Inter-reference Recency Set = recent + frequent */
					.strategy(LIRS)
					.maxEntries(4)
				.jmxStatistics().enable()
				.build();
	}
}
