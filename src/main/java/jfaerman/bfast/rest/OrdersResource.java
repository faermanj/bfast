package jfaerman.bfast.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import jfaerman.bfast.model.Order;
import jfaerman.bfast.store.OrderStore;

@Path("orders")
@Produces(APPLICATION_JSON)
public class OrdersResource {
	
	@Inject OrderStore orderStore;
	
	@GET
	public Collection<Order> findAll(){
		return orderStore.findAll();
	}
	
	@PUT
	public Order put(Order order, @QueryParam("maxIdle") @DefaultValue("3600") Integer maxIdle){
		return orderStore.put(order,maxIdle);
	}
	
	@GET
	@Path("{uuid}")
	public Order get(@PathParam("uuid") String uuid){
		return orderStore.get(uuid);
	}
	
	
}
