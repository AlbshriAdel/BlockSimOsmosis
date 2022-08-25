package IoTSimOsmosis.blockchainNetwork;


import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author adelalbshri
 *
 */
public class Consensus {

	private static ArrayList<Object[]> nodesLog = new ArrayList<>();
	static Random rand = new Random();
	
/**
 * 
 * @param ConcensusAlgorithm
 */
	public static void consensus(String ConcensusAlgorithm) {

		if (ConcensusAlgorithm.equals("raft")) {

			statusNodeLog();
			

		}
	}

	/**
	 * 
	 * @return
	 */
	public static ArrayList<Object[]> getNodesLog() {
		return nodesLog;
	}


	/**
	 * Choice random node to become candidate
	 * 
	 * @return int node index
	 */
	private static void becomeCandidateNode() {
		int countCandidate = 0;
		int i=0;
		while (countCandidate <InputConfig.getNumberOfMiner()) {
			int NodeID = rand.nextInt(Node.getNodes().size());
			if (!Node.getNodes().get(NodeID).getNodeType().equals("candidate")
					&& !Node.getNodes().get(NodeID).getNodeType().equals("leader")) {
				Node.getNodes().get(NodeID).setNodeType("candidate");
				System.out.println(" node type : " + Node.getNodes().get(NodeID).getNodeType() );
				nodesLog.add(new Object[] { "become Candidate", Node.getNodes().get(NodeID).getNodeId(),
						Node.getNodes().get(NodeID).getNodeType(), Node.getNodes().get(NodeID).getJoinTime() });
				votingFor(Node.getNodes().get(NodeID));
				countCandidate += 1;
			}
			

		}

	}

	/**
	 * this to method to vote to candidate node
	 * 
	 * @param nodeID
	 * 
	 */
	public static void votingFor(Node candidate) {
		//String[] randomVoting = { "Yes", "No" };
		boolean nodeVote;
		int countNodeLeader=0;
		int countVotingCandidate = 0;
		int round = 0;
		for (round = 0; round <Node.getNodes().size(); round++) {
			for (int i = 0; i < Node.getNodes().size(); i++) {
				//int select = rand.nextInt(randomVoting.length);
				if (candidate.getNodeId() != Node.getNodes().get(i).getNodeId()) {
					nodeVote = rand.nextBoolean();
					if (nodeVote == true) {
						countVotingCandidate += 1;
					}
				}
			}
			if (countVotingCandidate >= Node.getNodes().size() / 2) {
				candidate.setNodeType("leader");
				round=Node.getNodes().size();
			}
			countVotingCandidate = 0;
		
		}
	}
	
	/**
	 * this to method to return miner node
	 * 
	 * @return miner Node
	 * 
	 */
	public static Node getAassignLeader(){
		Node miner = null;
		
		for (Node node : Node.getNodes()) {
			if (node.getNodeType().equals("leader")) {
				miner= node;
			}
		}
		return miner;
	}

	/**
	 * this to method to keep tracking node status logs
	 * 
	 * 
	 */
	public static void statusNodeLog() {
		

		// initial nodes
		for (Node node : Node.getNodes()) {
			nodesLog.add(new Object[] { "Initial Nodes", node.getNodeId(), node.getNodeType(), node.getJoinTime() });
		}

		// Updating initial nodes to become candidate nodes
		becomeCandidateNode();

		for (Node node : Node.getNodes()) {
			nodesLog.add(new Object[] { "become leader", node.getNodeId(), node.getNodeType(), node.getJoinTime() });
		}

	}


	/**
	 * The time it takes the miner to generate next block
	 * 
	 * @return double
	 */

	public static double generateNextBlockDelay() {
		Random rand = new Random();
		double double_random = rand.nextDouble();
		return -Math.log(1 - double_random) / (InputConfig.getBinterval());
	}

}
