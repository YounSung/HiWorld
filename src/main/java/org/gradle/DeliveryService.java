package org.gradle;

import static spark.Spark.*;

public class DeliveryService {

	public DeliveryService(AgentService agentservice, OrderService orderservice) {

		// GET /agents

		get("/agents", (req, res) -> {
			return agentservice.getAllAgents();
		});

		// POST /agent

		post("/agents", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			return agentservice.createAgent(id);
		});

		// GET /agent/{id}

		get("/agents/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Agent agent = agentservice.getAgent(id);
			return agent.getId();
		});

		// PUT /agent/{id}/name

		put("/agents/:id/:name", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String name = req.params(":name");
			Agent agent = agentservice.getAgent(id);
			agent.setName(name);
			return "Set Agent " + agent.getId() + " Name :" + agent.getName();
		});

		// PUT /agent/{id}/location

		put("/agent/:id/:lat/:lng", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Double lat = Double.parseDouble(req.params(":lat"));
			Double lng = Double.parseDouble(req.params(":lng"));
			Agent agent = agentservice.getAgent(id);
			agent.setLocation(lat, lng);
			return "Set Agent " + agent.getId() + " Lat :" + agent.getLat()
					+ " Lng :" + agent.getLng();
		});

		// GET /agent/{id}/orders

		get("/agent/:id/orders", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			return orderservice.getAssigendOrders(id);
		});

		// GET /orders

		get("/orders", (req, res) -> {
			return orderservice.getAllOrders();
		});

		// POST /order

		post("/orders", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			return orderservice.createOrder(id);
		});

		// GET /order/{id}

		get("/orders/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Order order = orderservice.getOrder(id);
			return order;
		});

		// GET /order/{id}/agent

		get("/orders/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Order order = orderservice.getOrder(id);
			return order.getAssignedAgent();
		});

		// PUT /order/{id}/status
		put("/orders/:id/:status", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String stat = req.params(":status");
			Order order = orderservice.getOrder(id);
			if (stat.equals("complete")) {
				order.setComplete();
			} else if (stat.equals("cancel")) {
				order.setCancel();
			}
			return order.getStatus();
		});
	}
}
