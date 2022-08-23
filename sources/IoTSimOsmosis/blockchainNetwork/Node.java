package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;




public class Node {

	// node ID
	private final int nodeID;
	// Node type
	private String nodeType;
	
	private String joinTime;
	// Blockchain Ledger
	private final ArrayList<Block> blockchainLedger;
	// transactions Pool
	private final ArrayList<Transaction> transactionsPool;
	// Node list
	private final static ArrayList<Node> NodesList = new ArrayList<>();
	

	/**
	 * 
	 * @param nodeID
	 * @param nodeType
	 * @param joinTime
	 */
	public Node(int nodeID, String nodeType, String joinTime) {

		this.nodeID = nodeID;
		this.nodeType = nodeType;
		this.joinTime = joinTime;
		this.blockchainLedger = new ArrayList<>();
		this.transactionsPool=new ArrayList<>();
		
	
	}
	
	/**
	 * a method to generate genesis Block for all miner in the network
	 */
	public static void generateGenesisBlock() {
		for (Node node: Node.getNodes()) {
			node.getBlockchainLedger().add(new Block());
		}
	}

	/**
	 * Return the node ID
	 * 
	 * @return nodeID
	 */
	public int getNodeId() {
		return nodeID;
	}

	/**
	 * Return the node type (e.g. light node and miner)
	 * 
	 * @return nodeType
	 */
	public String getNodeType() {
		return nodeType;
	}
	
	/**
	 * Return the the time that node join to blockchain network
	 * @return joinTime
	 */
	public String getJoinTime() {
		return joinTime;
	}
	
	

	/**
	 * Return the local blockchain ledger
	 * 
	 * @return ArrayList<Block>
	 */
	public ArrayList<Block> getBlockchainLedger() {
		return blockchainLedger;
	}
	
	
	/**
	 *  Return array of transactions pool
	 *  
	 * @return ArrayList<Transaction>
	 */
	public ArrayList<Transaction> getTransactionsPool() {
		return transactionsPool;

	}
	


	/**
	 * set node type
	 * 
	 * @param nodeType
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	

	/**
	 * Return the last block at the nodes local blockchain
	 * 
	 * @return block
	 */
	public Block getLastBlock() {
		return this.getBlockchainLedger().get(blockchainLedger.size() - 1);
		
		
	}
	
	/**
	 * Return an arrayList of node
	 * @return
	 */
	public static ArrayList<Node> getNodes() {
		return NodesList;
	}

}
