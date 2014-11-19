package org.gradle;

import java.util.ArrayList;

public class Agent {
	// Agent°´Ã¼ »ý¼º

	// ID, Name, LAT, LNG
	private int id;
	private String name;
	private Double lat;
	private Double lng;

	// function set : Set Agent's Variables

	public Agent(int id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(Double lat, Double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	// public void setLat(Double lat) {
	// this.lat = lat;
	// }
	//
	// public void setLng(Double lng) {
	// this.lng = lng;
	// }

	// function get : Call Agent's Variables

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Double getLat() {
		return this.lat;
	}

	public Double getLng() {
		return this.lng;
	}

}
