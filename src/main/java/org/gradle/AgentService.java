package org.gradle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���� Agent�� set�� ���� service class
 * @author YounSung
 *
 */
public class AgentService {

	// ���� Agent�� ���� AgentService Ŭ����

	// Agent���, Ư�� ID�� ���� Agent ȣ�� ���� �Լ�

	private Map<Integer, Agent> agents = new HashMap<>();

	/**
	 * 
	 * @return List of All agents
	 */
	public List<Agent> getAllAgents() {
		return new ArrayList<>(agents.values());
	}
	/**
	 * Ư�� id�� agent ȣ��
	 * @param id : �θ����� �ϴ� agent�� id
	 * @return 
	 */
	public Agent getAgent(int id) {
		return agents.get(id);
	}

	/**
	 * Agent������ Agent Map�� �Ҵ�
	 * 
	 */
	public void createAgent(int id, String name, Double lat, Double lng) {

		Agent agent = new Agent(id, name, lat, lng);
		agents.put(agent.getId(), agent);

	}

}
