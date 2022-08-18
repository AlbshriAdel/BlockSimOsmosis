package IoTSimOsmosis.blockchainNetwork;

public class Event {

	private String type;
	private Miner minerId;
	private Node nodeId;
	private double time;
	private Block block;

	public Event(String type, Miner minerId, double time, Block block) {
		super();
		this.type = type;
		this.minerId = minerId;
		this.time = time;
		this.block = block;
		
	}
	

	public Event(String type, Node nodeId, double time, Block block) {
		super();
		this.type = type;
		this.time = time;
		this.block = block;
		this.nodeId=nodeId;
	}

	/**
	 * @return the block
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(Block block) {
		this.block = block;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the node
	 */
	public Miner getMinerId() {
		return minerId;
	}

	/**
	 * @param node the node to set
	 */
	public void setNodeId(Miner minerId) {
		this.minerId = minerId;
	}
	
	/**
	 * @return the node
	 */
	public Node getNodeId() {
		return nodeId;
	}

	/**
	 * @param node the node to set
	 */
	public void setNodeId(Node nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
