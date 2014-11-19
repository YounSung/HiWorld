package org.gradle;

import static spark.Spark.*;

public class HiWorld {
	public static void main(String[] args) {
		setPort(5000);
		new DeliveryService(new AgentService(), new OrderService());
		get("/", (req, res) -> "Hi World");
	}
}
