package org.gradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

	private Map<Integer, Order> orders = new HashMap<>();

	public List<Order> getAllOrders() {
		return new ArrayList<>(orders.values());
	}

	public Order getOrder(int id) {
		return orders.get(id);
	}

	public Order createOrder(int id, String address, Double lat, Double lng) {
		Order order = new Order(id, address, lat, lng);
		orders.put(order.getId(), order);
		return order;
	}

	public void assignAgent(int id, AgentService agentservice) {
		List<Agent> list = agentservice.getAllAgents();
		double dist = distFrom(list.get(0).getLat(), list.get(0).getLng(),
				orders.get(id).getLat(), orders.get(id).getLng());
		orders.get(id).assignAgent(list.get(0).getId());
		for (int i = 1; i < list.size(); i++) {
			if (dist >= distFrom(list.get(i).getLat(), list.get(i).getLng(),
					orders.get(id).getLat(), orders.get(id).getLng())) {
				orders.get(id).assignAgent(list.get(i).getId());
			}
		}
	}

	// Lat, Lng���� Distance ���
	public static double distFrom(double lat1, double lng1, double lat2,
			double lng2) {
		double earthRadius = 6371; // kilometers
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
	}

	// Id�� �����Է��ؼ� ������ get(i+2)�� �ƴ϶� Hashmap���� Order�� �ϳ��� ������ ���ϴ� �޹��ʿ�
	public List<Order> getAssigendOrders(int id) {
		List<Order> array = new ArrayList<>();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i + 1).getAssignedAgent() == id) {
				array.add(orders.get(i + 1));
			}
		}
		return array;
	}
}
