package org.gradle;

public class Order {

	// id: integer,
	// address: string,
	// dest_lat: double,
	// dest_lng: double,
	// status: enum ['PENDING', 'COMPLETED', 'CANCELLED'],
	// agent_id: integer,
	// created_at: timestamp,
	// completed_at: timestamp,
	// cancelled_at: timestamp
	private int id;
	private String address;
	private double dest_lat;
	private double dest_lng;

	private enum status {
		PENDING, COMPLETED, CANCELLED
	};

}
