package blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Consensus {

	public final ArrayList<Integer> vote = new ArrayList<>();// array list to collect vote
	static Random rand = new Random();

	public Consensus(String ConcensusAlgorithm) {

		if (ConcensusAlgorithm.equals("raft")) {

			becomeCandidateNode();
			assignLeader();
			getAassignLeader();
		}
	}

	/**
	 * Choice random node to become candidate
	 * 
	 * @return int node index
	 */
	public void becomeCandidateNode() {

		int NodeID = rand.nextInt(InputConfig.getNumberOfNodes());
		InputConfig.getNodes().get(NodeID).setNodeType("candidate");

//		//System.out.println("[Test become candidate node] The node [" + InputConfig.getNodes().get(NodeID).getNodeId()
//				+ "] has been became candidate, and type has been changed [ "
//				+ InputConfig.getNodes().get(NodeID).getNodeType() + " ]");

	}

	/**
	 * this to method to vote to candidate node
	 * 
	 * @param nodeID
	 * 
	 */
	public void voting(Node node) {
		if (!vote.contains(node.getNodeId())) {
			vote.add(node.getNodeId());

		}
	}

	/**
	 * this to method to assign new leader
	 * 
	 * @param nodeID
	 * 
	 */
	public void assignLeader() {
		int candidateIndex = 0;
		for (int i = 0; i < InputConfig.getNodes().size(); i++) {
			voting(InputConfig.getNodes().get(i));
			if (InputConfig.getNodes().get(i).getNodeType().equals("candidate")) {
				candidateIndex = i;

			}

		}

		if (vote.size() >= InputConfig.getNodes().size()) {

			InputConfig.getMiners().add(new Miner(candidateIndex, "leader"));
			InputConfig.getNodes().remove(candidateIndex);

		}

	}

	/**
	 * this to method to return leader Miner
	 * 
	 * @return miner
	 */
	public static Miner getAassignLeader() {
		Miner miner = null;
		for (int i = 0; i < InputConfig.getMiners().size(); i++) {
			if (InputConfig.getMiners().get(i).getNodeType().equals("leader")) {
				miner = InputConfig.getMiners().get(i);
			}
		}
//		System.out.println("[Test getAassignLeader] The leader is  [ " + miner.getNodeId() + " ]" + " and type is [ "
//				+ miner.getNodeType() + " ]");
		return miner;
	}

//	public void TestRaft() {
//		System.out.println("####Initialization to candidate a leader####");
//		for (int i = 0; i < InputConfig.getNodes().size(); i++) {
//			
//			System.out.println(
//					"The node has been created successfully for node id [ " + InputConfig.getNodes().get(i).getNodeId()
//							+ " ]" + " and node type is [ " + InputConfig.getNodes().get(i).getNodeType() + " ]");
//
//		}
//
//		for (int i = 0; i < InputConfig.getMiners().size(); i++) {
//			
//			System.out.println("[leader ]The node has been created successfully for node id [ "
//					+ InputConfig.getMiners().get(i).getNodeId() + " ]" + " and node type is [ "
//					+ InputConfig.getMiners().get(i).getNodeType() + " ]");
//		}
//		
//	}

}
