package jfaerman.bfast.web;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jfaerman.bfast.cfg.OrdersCache;
import jfaerman.bfast.model.Order;

import org.infinispan.Cache;



public class ShutdownCacheManager implements ServletContextListener{
	@Inject @OrdersCache Cache<String, Order> orders;
	@Inject Logger log;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("* contextInitialized *");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("* Shutting down cache manager *");
		orders.getCacheManager().stop();
		
	}

}
