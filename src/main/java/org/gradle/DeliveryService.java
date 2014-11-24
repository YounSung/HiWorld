package org.gradle;

import static spark.Spark.*;

import static org.gradle.JsonUtil.*;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.*;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

public class DeliveryService {

	public DeliveryService(AgentService agentservice, OrderService orderservice) {

		// Create DB

		Firebase myFire = new Firebase(
				"https://younsungdeliver.firebaseio.com/");
		Firebase myAgentFire = myFire.child("agents");
		Firebase myOrderFire = myFire.child("orders");

//		myAgentFire.setValue(agentservice);
//		myOrderFire.setValue(orderservice);
		
		// GET /agents

		get("/agents", (req, res) -> {
			myAgentFire.addValueEventListener(new ValueEventListener() {
			    @Override
			    public void onDataChange(DataSnapshot snapshot) {			        
			        AgentService agentservice2 = new AgentService();
					Map<Integer, Agent> value = (Map<Integer, Agent>)snapshot.getValue();
					agentservice2.setMap(value);
			        System.out.println(agentservice2.getAgent(1));
			    }
			    @Override
			    public void onCancelled(FirebaseError firebaseError) {
			        System.out.println("The read failed: " + firebaseError.getMessage());
			    }
			    
			});
			
//			AgentService ag = new AgentService();
//			DataSnapshot snapshot = new DataSnapshot(myAgentFire, null, null);
//			return snapshot.child("1").getValue();
//			Map<String, Agent> value = (Map<String, Agent>)snapshot.getValue();
//			return value.get(Integer.toString(1));
//			System.out.println(ag.getAllAgents());
//			return ag.getAllAgents();
			return agentservice.getAllAgents();
		}, json());

		// POST /agent

		post("/agent",
				(req, res) -> {

					int id = Integer.parseInt(req.queryParams("id"));
					String name = req.queryParams("name");
					Double lat = Double.parseDouble(req.queryParams("lat"));
					Double lng = Double.parseDouble(req.queryParams("lng"));
					agentservice.createAgent(id, name, lat, lng);
					myAgentFire.child(Integer.toString(id)).setValue(
							agentservice.getAgent(id));
					return agentservice.getAllAgents();
				}, json());

		// GET /agent/{id}

		get("/agents/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Agent agent = agentservice.getAgent(id);
			return agent;
		}, json());

		// PUT /agent/{id}/name

		put("/agents/:id/name", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String name = req.queryParams("name");
			Agent agent = agentservice.getAgent(id);
			agent.setName(name);
			myAgentFire.child(Integer.toString(id)).setValue(agent);
			return agent;
		}, json());

		// PUT /agent/{id}/location

		put("/agents/:id/location",
				(req, res) -> {
					int id = Integer.parseInt(req.params(":id"));
					Double lat = Double.parseDouble(req.queryParams("lat"));
					Double lng = Double.parseDouble(req.queryParams("lng"));
					Agent agent = agentservice.getAgent(id);
					agent.setLocation(lat, lng);
					myAgentFire.child(Integer.toString(id)).setValue(
							agentservice.getAgent(id));
					return agent;
				}, json());

		// GET /agent/{id}/orders

		get("/agents/:id/orders", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			return orderservice.getAssigendOrders(id);
		}, json());

		// GET /orders

		get("/orders", (req, res) -> {
			return orderservice.getAllOrders();
		}, json());

		// POST /order

		post("/order",
				(req, res) -> {
					int id = Integer.parseInt(req.queryParams("id"));
					String address = req.queryParams("address");
					Double lat = Double.parseDouble(req.queryParams("lat"));
					Double lng = Double.parseDouble(req.queryParams("lng"));
					orderservice.createOrder(id, address, lat, lng);
					myOrderFire.child(Integer.toString(id)).setValue(
							orderservice.getOrder(id));
					return orderservice.getAllOrders();
				}, json());

		// GET /order/{id}

		get("/orders/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Order order = orderservice.getOrder(id);
			return order;
		}, json());

		// GET /order/{id}/agent

		get("/orders/:id/agents", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Order order = orderservice.getOrder(id);
			return order.getAssignedAgent();
		}, json());

		// Set order's destination and assign agent

		put("/orders/:id/location",
				(req, res) -> {
					int id = Integer.parseInt(req.params(":id"));
					Double lat = Double.parseDouble(req.queryParams("lat"));
					Double lng = Double.parseDouble(req.queryParams("lng"));
					Order order = orderservice.getOrder(id);
					order.setDestLocation(lat, lng);
					orderservice.assignAgent(id, agentservice);
					return "Set order " + order.getId() + " Lat :"
							+ order.getLat() + " Lng :" + order.getLng()
							+ " Assigned agent : " + order.getAssignedAgent();
				}, json());

		// PUT /order/{id}/status

		put("/orders/:id/status", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			String stat = req.queryParams("status");
			Order order = orderservice.getOrder(id);
			if (stat.equals("complete")) {
				order.setComplete();
			} else if (stat.equals("cancel")) {
				order.setCancel();
			} else {
				return "Not appropriate status";
			}
			return order.getStatus();
		}, json());
	}
}
