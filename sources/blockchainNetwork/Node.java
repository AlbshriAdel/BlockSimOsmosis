package blockchainNetwork;

import java.util.ArrayList;

import blockchainNetwork.Block;
import blockchainNetwork.InputConfig;

public class Node {

	// node ID
	private final int nodeID;
	// Node type
	private String nodeType;
	// Blockchain Ledger
	private final ArrayList<Block> blockchainLedger;

	public Node(int nodeID, String nodeType) {

		this.nodeID = nodeID;
		this.nodeType = nodeType;
		this.blockchainLedger = new ArrayList<>();

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
	 * Return the local blockchain ledger
	 * 
	 * @return ArrayList<Block>
	 */
	public ArrayList<Block> getBlockchainLedger() {
		return blockchainLedger;
	}
	
	/**
	 * set node type
	 * @param nodeType
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * a method to generate genesis Block for all miner in the network
	 */
	public static void generateGenesisBlock() {
		System.out.println("####Generate genesis block for all miner in the network####");
			Consensus.getAassignLeader().getBlockchainLedger().add(new Block());
			System.out.println("The genesis block has been created successfully for leader id [ " + Consensus.getAassignLeader().getNodeId() + " ].");


	}
	
	

}
