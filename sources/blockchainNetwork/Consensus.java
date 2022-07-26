package blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Consensus {

	private String ConcensusAlgorithm;
	public final ArrayList<Integer> vote = new ArrayList<>();// array list to collect vote
	static Random rand = new Random();
	  //Iterator<Integer> iter = vote.iterator();

	public Consensus(String ConcensusAlgorithm) {

		if (ConcensusAlgorithm.equals("raft")) {
			// this.ConcensusAlgorithm = ConcensusAlgorithm;
			becomeCandidateNode();
			//	Raft();
			 assignLeader();
			printVoting();
		}

	}

	private void Raft() {
		for (int i = 0; i < InputConfig.getNodes().size(); i++) {
			System.out.println("[test Raft ] : Node id : " + InputConfig.getNodes().get(i).getNodeId());
			System.out.println("[test Raft ] : Node type : " + InputConfig.getNodes().get(i).getNodeType());
			System.out.println("..........................................................................");
		}

	}

	/**
	 * Choice random node to become candidate
	 * 
	 * @return int node index
	 */
	public static void becomeCandidateNode() {
		
		int NodeID = 0;
		for (int i = 0; i < InputConfig.getNodes().size(); i++) {
			if (InputConfig.getNodes().get(i).getNodeType() == "follower") {
				NodeID = rand.nextInt(InputConfig.getNumberOfNodes());
			}
			InputConfig.getNodes().get(NodeID).setNodeType("candidate");
			
		}
		System.out.println("[Test become candidate node ] : The node [" + InputConfig.getNodes().get(NodeID).getNodeId()
				+ "] has been became candidate, and type has been changed [ "
				+ InputConfig.getNodes().get(NodeID).getNodeType() + " ]");
		
	}

	/**
	 * this to method to vote to candidate node
	 * @param nodeID
	 * 
	 */
	public void voting(Node node) {
		//Prevent multiple voting from the same server
		//int nodeIDIndx= node.getNodeId(); 
		if(!vote.contains(node.getNodeId()) ) {
			vote.add(node.getNodeId());
			
			System.out.println("[Test voting ]: The node [" + node.getNodeId()
			+ "] has been votted.");
		}
    }
	
	
	/**
	 * this to method to assign new leader
	 * @param nodeID
	 * 
	 */
	public void assignLeader() {
		int candidateIndex=0;
		for(int i = 0;i<InputConfig.getNodes().size();i++) {
			voting(InputConfig.getNodes().get(i));
		if(InputConfig.getNodes().get(i).getNodeType()=="candidate") {
			candidateIndex=i;
			System.out.println("hhhhhhhhh"+ InputConfig.getNodes().get(i).getNodeType());
				
			}
		//System.out.println("hhhhhhhhh"+ InputConfig.getNodes().get(candidateIndex).getNodeId());
		}
		
		if(vote.size()>=InputConfig.getNodes().size()) {
			 
			//InputConfig.getNodes().get(candidateIndex).setNodeType("leader");
			InputConfig.getMiners().add(new Miner(candidateIndex, "leader"));
			System.out.println("[Test remove a node ]: The node [" + InputConfig.getNodes().get(candidateIndex).getNodeId()
					+ "]");
			InputConfig.getNodes().remove(candidateIndex);
			
			
			 }
			
		
	}
	
	public void printVoting() {
//		System.out.println("[Test become candidate node ] : The node [" + becomeCandidateNode().getNodeId()
//				+ "] has been became candidate, and type has been changed [ "
//				+ becomeCandidateNode().getNodeType() + " ]");
//		
		for (int i = 0; i < InputConfig.getNodes().size(); i++) {
			System.out.println("The node has been created successfully for node id [ "
					+ InputConfig.getNodes().get(i).getNodeId() + " ]" + " and node type is [ "
					+ InputConfig.getNodes().get(i).getNodeType() + " ]");
			
		}
		
		for (int i = 0; i < InputConfig.getMiners().size(); i++) {
			System.out.println("The node has been created successfully for node id [ "
					+ InputConfig.getMiners().get(i).getNodeId() + " ]" + " and node type is [ "
					+ InputConfig.getMiners().get(i).getNodeType() + " ]");
//			System.out.println("####################################################################");
//			System.out.println("[test assign Leader before adding leader ] : Node id : " + InputConfig.getMiners().get(i).getNodeId());
//			System.out.println("[test assign Leader before adding leader  ] : Node type : " + InputConfig.getMiners().get(i).getNodeType());
//			
		}
		
		
		
		
		
	}

	/**
	 * Return an array list of vote
	 * 
	 * @return vote
	 */
	public ArrayList<Integer> getVote() {
		return vote;
	}

}
