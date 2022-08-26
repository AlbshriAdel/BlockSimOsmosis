package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;

/**
 * 
 * @author adelalbshri
 *
 */

public class Event {

	private String type;
	private Node  minerNode;
	private double time;
	private Block block;
	
	
	public Event(String type, Node minerNode, double time, Block block) {
		super();
		this.type = type;
		this.minerNode = minerNode;
		this.time = time;
		this.block = block;
	}	


	/**
	 * @return the block
	 */
	public Block getBlock() {
		return block;
	}
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	

	/**
	 * @return the node
	 */
	public Node  getMiner() {
		return minerNode;
	}


	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}



}
