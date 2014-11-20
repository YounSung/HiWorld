package org.gradle;

import static spark.Spark.*;
import static org.gradle.JsonUtil.*;



public class DeliveryService {

	public DeliveryService(AgentService agentservice, OrderService orderservice) {

		// GET /agents

		get("/agents", (req, res) -> {
			return agentservice.getAllAgents();
		},json());

		// POST /agent

		post("/agents", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			return agentservice.createAgent(id);
		},json());

		// GET /agent/{id}

		get("/agents/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Agent agent = agentservice.getAgent(id);
			return agent;
		},json());

		// PUT /agent/{id}/name

		put("/agents/:id/name", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String name = req.queryParams("name");
			Agent agent = agentservice.getAgent(id);
			agent.setName(name);
			return "Set Agent " + agent.getId() + " Name :" + agent.getName();
		},json());

		// PUT /agent/{id}/location

		put("/agents/:id/location", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Double lat = Double.parseDouble(req.queryParams("lat"));
			Double lng = Double.parseDouble(req.queryParams("lng"));
			Agent agent = agentservice.getAgent(id);
			agent.setLocation(lat, lng);
			return "Set Agent " + agent.getId() + " Lat :" + agent.getLat()
					+ " Lng :" + agent.getLng();
		},json());

		// GET /agent/{id}/orders

		get("/agents/:id/orders", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			return orderservice.getAssigendOrders(id);
		},json());

		// GET /orders

		get("/orders", (req, res) -> {
			return orderservice.getAllOrders();
		},json());

		// POST /order

		post("/orders", (req, res) -> {
			int id = Integer.parseInt(req.queryParams("id"));
			return orderservice.createOrder(id);
		},json());

		// GET /order/{id}

		get("/orders/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Order order = orderservice.getOrder(id);
			return order;
		},json());

		// GET /order/{id}/agent

		get("/orders/:id/agents", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Order order = orderservice.getOrder(id);
			return order.getAssignedAgent();
		},json());
		
//		Set order's destination and assign agent
		put("/orders/:id/location", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Double lat = Double.parseDouble(req.queryParams("lat"));
			Double lng = Double.parseDouble(req.queryParams("lng"));
			Order order = orderservice.getOrder(id);
			order.setDestLocation(lat, lng);
			orderservice.assignAgent(id, agentservice);
			return "Set order " + order.getId() + " Lat :" + order.getLat()
					+ " Lng :" + order.getLng() + " Assigned agent : " + order.getAssignedAgent();
		},json());

		// PUT /order/{id}/status
		put("/orders/:id/status", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String stat = req.queryParams("status");
			Order order = orderservice.getOrder(id);
			if (stat.equals("complete")) {
				order.setComplete();
			} else if (stat.equals("cancel")) {
				order.setCancel();
			} else{
				return "Not appropriate status";
			}
			return order.getStatus();
		},json());
	}
}
