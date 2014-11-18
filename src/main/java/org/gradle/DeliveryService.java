package org.gradle;

import static spark.Spark.*;

public class DeliveryService {

	public static void serviceupdate(AgentServie agentservice) {

		// GET /agents

		get("/get/agents", (req, res) -> {
			return agentService.getAllAgents();
		});

		// POST /agent

		post("/post/agents", (req, res) -> agentService.createAgent());

		// GET /agent/{id}

		get("/get/agents/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Agent agent = agentService.getAgent(id);
		});

		// PUT /agent/{id}/name

		put("/put/agents/:id/:name", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String name = req.params(":name");
			Agent agent = agentService.getAgent(id);
			agent.setName(name);
		});

		// PUT /agent/{id}/location

		put("/put/agent/:id/:lat/:lng", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Double lat = Double.parseDouble(req.params(":lat"));
			Double lng = Double.parseDouble(req.params(":lng"));
			Agent agent = agentService.getAgent(id);
			agent.setLat(lat);
			agent.setLng(lng);
		});

		// GET /agent/{id}/orders

		get("/get/agent/:id/orders", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Agent agent = agentService.getAgent(id);
			return agent.getOrders();
		});

		// GET /orders
		// POST /order
		// GET /order/{id}
		// GET /order/{id}/agent
		// PUT /order/{id}/status

	}

}
