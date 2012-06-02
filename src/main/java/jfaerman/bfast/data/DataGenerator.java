package jfaerman.bfast.data;

import java.util.UUID;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import jfaerman.bfast.model.Order;
import jfaerman.bfast.store.OrderStore;

@Startup
@Singleton
public class DataGenerator {
	@Inject OrderStore orderStore;
	@Inject Logger log; 
	
	@PostConstruct
	public void init(){
		System.out.println("*** Its Alive !!!!!!");
		log.info("Populating  OrderStore");
		orderStore.put(new Order("0c738668-079f-4f76-b9df-f7b416eb4188","David Gilmour","Tea & Toast"));
		orderStore.put(new Order("e31a3c8e-64ff-4dd2-93df-bc5eb5f8a0b6","Lemmy","Jack & Coke"));
		orderStore.put(new Order("59861dea-0c3d-4c8a-8333-51e69707e088","Scott Gorham","Whiskey in the Jar"));
		orderStore.put(new Order("e3f0a762-e89b-45cc-a674-d74fdbf2f0be","Mark Farner","Bacon & Eggs"));
		
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
}
