package org.gradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentService {

	// 여러 Agent를 갖는 AgentService 클래스

	// Agent목록, 특정 ID를 갖는 Agent 호출 등의 함수

	private int id = 1;

	private Map<Integer, Agent> agents = new HashMap<>();

	public List<Agent> getAllAgents() {
		return new ArrayList<>(agents.values());
	}

	public Agent getAgent(int id) {
		return agents.get(id);
	}

	public Agent createAgent() {

		Agent agent = new Agent(this.id);
		agents.put(agent.getId(), agent);
		this.id++;
		return agent;

	}

}
