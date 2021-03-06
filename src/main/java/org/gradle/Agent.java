package org.gradle;

/**
 * 각 Agent를 나타내는 class
 * @author YounSung
 *
 */
public class Agent {
	// Agent객체 생성

	// ID, Name, LAT, LNG
	private int id;
	private String name;
	private Double lat;
	private Double lng;

	// function set : Set Agent's Variables

	public Agent(int id, String name, Double lat, Double lng) {
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
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
