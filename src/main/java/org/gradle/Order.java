package org.gradle;

import com.sun.jmx.snmp.Timestamp;

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

	private enum Status {
		PENDING, COMPLETED, CANCELLED
	};

	private int agent_id;
	private Timestamp created_at = new Timestamp(System.currentTimeMillis());
	private Timestamp completed_at;
	private Timestamp cancelled_at;

	private Status status;

	public Order(int id) {
		this.id = id;
		status = Status.PENDING;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDestLocation(Double lat, Double lng) {
		this.dest_lat = lat;
		this.dest_lng = lng;
	}

	public void setComplete() {
		status = Status.COMPLETED;
		completed_at = new Timestamp(System.currentTimeMillis());
	}

	public void setCancel() {
		status = Status.CANCELLED;
		cancelled_at = new Timestamp(System.currentTimeMillis());
	}

	public void assignAgent(int id) {
		this.agent_id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getAddress() {
		return this.address;
	}

	public double getLat() {
		return this.dest_lat;
	}

	public double getLng() {
		return this.dest_lng;
	}

	public Timestamp getCreateTime() {
		return this.created_at;
	}

	public Timestamp getEndTime() {
		if (this.status == Status.COMPLETED) {
			return this.completed_at;
		} else if (this.status == Status.CANCELLED) {
			return this.cancelled_at;
		} else {
			return this.created_at;
		}

	}

	public int getAssignedAgent() {
		return this.agent_id;
	}

	public Status getStatus() {
		return this.status;
	}
}
