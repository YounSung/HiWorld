package org.gradle;

import static spark.Spark.*;

import static org.gradle.JsonUtil.*;

public class DeliveryService {

	public DeliveryService(AgentService agentservice, OrderService orderservice) {

		// Get / agents
		/**
		 * ���� ��� Agent�� ���� ���
		 */
		get("/agents", (req, res) -> {
			return agentservice.getAllAgents();
		}, json());

		// POST /agent
		
		/**
		 * ���ο� Agent ����
		 */
		post("/agent", (req, res) -> {

			int id = Integer.parseInt(req.queryParams("id"));
			String name = req.queryParams("name");
			Double lat = Double.parseDouble(req.queryParams("lat"));
			Double lng = Double.parseDouble(req.queryParams("lng"));
			agentservice.createAgent(id, name, lat, lng);
			return agentservice.getAllAgents();
		}, json());

		// GET /agent/{id}
		
		/**
		 * Ư�� id�� ���� �ִ� Agent ȣ��
		 */
		get("/agents/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			return agentservice.getAgent(id);
		}, json());

		// PUT /agent/{id}/name
		/**
		 * Ư�� id�� Agent�� ���ο� �̸� �ο�
		 */
		put("/agents/:id/name", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String name = req.queryParams("name");
			agentservice.getAgent(id).setName(name);
			return agentservice.getAgent(id);
		}, json());

		// PUT /agent/{id}/location
		/**
		 * Ư�� id�� Agent�� ���ο� �ּ� �ο�
		 */
		put("/agents/:id/location", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Double lat = Double.parseDouble(req.queryParams("lat"));
			Double lng = Double.parseDouble(req.queryParams("lng"));
			agentservice.getAgent(id).setLocation(lat, lng);
			return agentservice.getAgent(id);
		}, json());

		// GET /agent/{id}/orders
		/**
		 * Ư�� id�� Agent�� �Ҵ�� �ֹ� id�� ȣ��
		 */
		get("/agents/:id/orders", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			return orderservice.getAssigendOrders(id);
		}, json());

		// GET /orders
		/**
		 * ��� Orders ȣ��
		 */
		get("/orders", (req, res) -> {
			return orderservice.getAllOrders();
		}, json());

		// POST /order
		/**
		 * ���ο� order ����
		 */
		post("/order", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			String address = req.queryParams("address");
			Double lat = Double.parseDouble(req.queryParams("lat"));
			Double lng = Double.parseDouble(req.queryParams("lng"));
			orderservice.createOrder(id, address, lat, lng);
			return orderservice.getAllOrders();
		}, json());

		// GET /order/{id}
		/**
		 * Ư�� id�� order ȣ��
		 */
		get("/orders/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			return orderservice.getOrder(id);
		}, json());

		// GET /order/{id}/agent
		/**
		 * Ư�� id�� order�� �Ҵ�� agent id ȣ��
		 */
		get("/orders/:id/agents", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			orderservice.assignAgent(id, agentservice);
			return orderservice.getOrder(id);
		}, json());

		// Set order's destination and assign agent
		/**
		 * Ư�� id�� order�� location �Ҵ�
		 */
		put("/orders/:id/location", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Double lat = Double.parseDouble(req.queryParams("lat"));
			Double lng = Double.parseDouble(req.queryParams("lng"));
			Order order = orderservice.getOrder(id);
			order.setDestLocation(lat, lng);
			orderservice.assignAgent(id, agentservice);
			return orderservice.getOrder(id);
		}, json());

		// PUT /order/{id}/status
		/**
		 * Ư�� id�� order ���º���
		 */
		put("/orders/:id/status", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String stat = req.queryParams("status");
			if (stat.equals("complete")) {
				orderservice.getOrder(id).setComplete();
			} else if (stat.equals("cancel")) {
				orderservice.getOrder(id).setCancel();
			} else {
				return "Not appropriate status";
			}
			return orderservice.getOrder(id);
		}, json());
	}
}
