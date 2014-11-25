package org.gradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 여러 Agent를 set로 갖는 service class
 * @author YounSung
 *
 */
public class AgentService {

	// 여러 Agent를 갖는 AgentService 클래스

	// Agent목록, 특정 ID를 갖는 Agent 호출 등의 함수

	private Map<Integer, Agent> agents = new HashMap<>();

	/**
	 * 
	 * @return List of All agents
	 */
	public List<Agent> getAllAgents() {
		return new ArrayList<>(agents.values());
	}
	/**
	 * 특정 id의 agent 호출
	 * @param id : 부르고자 하는 agent의 id
	 * @return 
	 */
	public Agent getAgent(int id) {
		return agents.get(id);
	}

	/**
	 * Agent생성후 Agent Map에 할당
	 * 
	 */
	public void createAgent(int id, String name, Double lat, Double lng) {

		Agent agent = new Agent(id, name, lat, lng);
		agents.put(agent.getId(), agent);

	}

}
