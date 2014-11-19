package org.gradle;

import static spark.Spark.*;

public class DeliveryService {

	public DeliveryService(AgentService agentservice, OrderService orderservice) {

		// GET /agents

		get("/get/agents", (req, res) -> {
			return agentservice.getAllAgents();
		});

		// POST /agent

		post("/post/agents", (req, res) -> {
			return agentservice.createAgent();
		});

		// GET /agent/{id}

		get("/get/agents/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Agent agent = agentservice.getAgent(id);
			return agent.getId();
		});

		// PUT /agent/{id}/name

		put("/put/agents/:id/:name", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String name = req.params(":name");
			Agent agent = agentservice.getAgent(id);
			agent.setName(name);
			return "Set Agent " + agent.getId() + " Name :" + agent.getName();
		});

		// PUT /agent/{id}/location

		put("/put/agent/:id/:lat/:lng", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Double lat = Double.parseDouble(req.params(":lat"));
			Double lng = Double.parseDouble(req.params(":lng"));
			Agent agent = agentservice.getAgent(id);
			agent.setLat(lat);
			agent.setLng(lng);
			return "Set Agent " + agent.getId() + " Lat :" + agent.getLat()
					+ " Lng :" + agent.getLng();
		});

		// GET /agent/{id}/orders

		get("/get/agent/:id/orders", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Agent agent = agentservice.getAgent(id);
			return agent.getId();
			// Order Class 생성 후 해당 agent가 할당된 order들 반환
			});

		// GET /orders
		// POST /order
		// GET /order/{id}
		// GET /order/{id}/agent
		// PUT /order/{id}/status

	}
}
