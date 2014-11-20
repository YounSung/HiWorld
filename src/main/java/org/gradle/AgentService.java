package org.gradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentService {

	// 여러 Agent를 갖는 AgentService 클래스

	// Agent목록, 특정 ID를 갖는 Agent 호출 등의 함수

	private Map<Integer, Agent> agents = new HashMap<>();

	// public Map<Integer, Agent> getMap() {
	// return agents;
	// }
	//
	// public void setMap(Map<Integer, Agent> map){
	// agents = map;
	// }

	public List<Agent> getAllAgents() {
		return new ArrayList<>(agents.values());
	}

	public Agent getAgent(int id) {
		return agents.get(id);
	}

	public Agent createAgent(int id,String name, Double lat, Double lng) {

		Agent agent = new Agent(id);
		agent.setName(name);
		agent.setLocation(lat, lng);
		agents.put(agent.getId(), agent);
		return agent;

	}

}
