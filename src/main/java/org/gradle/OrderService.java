package org.gradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

	private int id = 1;
	private Map<Integer, Order> orders = new HashMap<>();

	public List<Order> getAllOrders() {
		return new ArrayList<>(orders.values());
	}

	public Order getOrder(int id) {
		return orders.get(id);
	}

	public Order createOrder() {
		Order order = new Order(this.id);
		this.id++;
		orders.put(order.getId(), order);
		return order;
	}
	
	public List<Order> getAssigendOrders(int id){
		List<Order> array = new ArrayList<>();
		for (int i=0; i<this.id;i++){
			if(orders.get(i+1).getAssignedAgent()==id){
				array.add(orders.get(i+1));
			}
		}
		return array;
	}
}
