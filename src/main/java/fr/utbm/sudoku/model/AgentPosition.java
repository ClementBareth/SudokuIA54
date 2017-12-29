/** 
 * 
 */
package fr.utbm.sudoku.model;

import java.util.UUID;

/** 
 * @author Clément Bareth-Desgranges <clement.bareth@gmail.com>
 * 
 */
public class AgentPosition {

	private int x;
	private int y;
	private UUID id;

	public AgentPosition(int x,int y, UUID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public AgentPosition (UUID id){
		this.id = id;
	}

	public UUID getId(){
		return this.id;
	}

	public int getY(){
		return this.y;
	}

	public int getX(){
		return this.x;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
		AgentPosition other = (AgentPosition) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "("+this.x+","+this.y+")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
