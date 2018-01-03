package fr.utbm.sudoku.model;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Configuration {

	private final ConcurrentHashMap<UUID,Integer> agentsValue = new ConcurrentHashMap<>();
	private UUID myId;
	
	public Configuration(UUID id) {
		this.myId = id;
		this.agentsValue.put(this.myId,new Integer(0));
	}
	
	public boolean isInConflict() {
		Integer value = this.agentsValue.get(this.myId);
		if(Collections.frequency(this.agentsValue.values(), value) > 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean containsValue(Integer value) {
		return this.agentsValue.containsValue(value);
	}
	
	public Integer getMyValue() {
		return this.agentsValue.get(this.myId);
	}
	
	private void putAll(Map<UUID,Integer> map) {
		this.agentsValue.putAll(map);
	}
	
	public void update(UUID id, Integer value) {
		this.agentsValue.put(id,value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.agentsValue == null) ? 0 : this.agentsValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (this.agentsValue == null) {
			if (other.agentsValue != null)
				return false;
		} else if (!this.agentsValue.equals(other.agentsValue))
			return false;
		return true;
	}
	
	public Configuration image() {
		Configuration config = new Configuration(this.myId);
		config.putAll(this.agentsValue);
		return config;
	}
}
