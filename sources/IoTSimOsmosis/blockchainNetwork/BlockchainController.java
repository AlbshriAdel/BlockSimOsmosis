package IoTSimOsmosis.blockchainNetwork;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import IoTSimOsmosis.cloudsim.core.SimEvent;
import IoTSimOsmosis.osmosis.core.OsmosisBuilder;
import IoTSimOsmosis.osmosis.core.Infrastructure.EdgeDatacenters;


/**
 * 
 * @author adelalbshri
 *
 */
public class BlockchainController {


	/**
	 * This method is responsible for generate light node
	 */
	public static void generateNodes() {
		if (InputConfig.getNumberOfNodes() >= 3 && InputConfig.getNumberOfNodes()>InputConfig.getNumberOfMiner() && InputConfig.getConsensusalgorithm().equals("raft")) {
		    Calendar now = Calendar.getInstance();
		    DateFormat df = new SimpleDateFormat("dd-MM-yyy-HH:mm");

			for (int i = 0; i < InputConfig.getNumberOfNodes(); i++) {
				String time = df.format(now.getTime());
				Node.getNodes().add(new Node(i, "follower",time));

			}
			Consensus.consensus(InputConfig.getConsensusalgorithm());

		} else {
			System.out.println(
					"[Error] the number of node must be a larger than three nodes and make sure the spelling of consensus algorithm 'raft'");
		}
}
	
	
	/**
	 * This method is responsible for generate light node
	 */
	public static void generateOsmosisNodes(OsmosisBuilder topologyBuilder) {
		
		int dgeNodes = topologyBuilder.getEdgeDatacentres().size();
		//int dgeNodes = a.getEdgeDevices().size();
		    Calendar now = Calendar.getInstance();
		    DateFormat df = new SimpleDateFormat("dd-MM-yyy-HH:mm");
		   // String time1 = df.format(now.getTime());

			for (int i = 0; i < dgeNodes; i++) {
				String time = df.format(now.getTime());
				Node.getNodes().add(new Node(i, "follower",time));

			}
			//Node.getNodes().add(new Node(5, "leader",time1));
			Consensus.consensus(InputConfig.getConsensusalgorithm());
			

		} 

	/**
 	 * This method is responsible for generate transactions without an integrated
	 */

	public static void createOsmosisTransaction (double osmosisTransactionTime) {
		
//		int appID = ev.getDestination();
//		int node = ev.getSource();
//		//Transaction tx =ev.getData();

//			for (int i = 0; i < InputConfig.getTransactionNumber(); i++) {
				double creationTime = osmosisTransactionTime;
				double transactionSize =ThreadLocalRandom.current().nextDouble(100,1000);
				Transaction tx = new Transaction(creationTime, transactionSize, 1, 2);
//				Node.getNodes().get(0).getTransactionsPool().add(tx);
				
				
				for (Node n :Node.getNodes()){
					if(n.getNodeType().equals("leader")) {
						n.getTransactionsPool().add(tx);
						System.out.println("TX pool : " + n.getTransactionsPool().size());
					
					
					}	
				}
			//Statistics.noTransactionsConfig += 1;
			
			}
			//System.out.println("Tx pool size:" + Transaction.getPool());
			
			
	
	

	/**
 	 * This method is responsible for generate transactions without an integrated
	 */

	public static void creatTransactionsWithoutIntegrated() {

			for (int i = 0; i < InputConfig.getTransactionNumber(); i++) {
				double creationTime = ThreadLocalRandom.current().nextDouble(i,i+1);
				double transactionSize =ThreadLocalRandom.current().nextDouble(100,1000);
				Transaction tx = new Transaction(creationTime, transactionSize, 1, 2);
		
				
				
				for (Node n :Node.getNodes()){
					if(n.getNodeType().equals("leader")) {
						n.getTransactionsPool().add(tx);
						
						Statistics.noTransactionsConfig += 1;
					
					}	
				}
			
			
			}
			System.out.println("Tx pool size:" + Transaction.getPool());
			
			}

		 	
	

	/**
	 * This method is responsible for generate transactions with IoT simulator
	 */

	public static void creatTransactionsWithIntegrated(double transactionTime, int fromAddress, int toAddress) {

		double creationTime = transactionTime;
		double transactionSize = ThreadLocalRandom.current().nextDouble(InputConfig.getMinTransactionSize(),
				InputConfig.getMaxTransactionSize());

		// create transaction
		// Transaction(double creationTime, double txSize,String fromAddress,String
		// toAddress)
		Transaction tx = new Transaction(creationTime, transactionSize, fromAddress, toAddress);
		//Consensus.getAassignLeader().getTransactionsPool().add(tx);
		Statistics.noTransactionsConfig += 1;

	}

	/**
	 * Delay for propagating blocks in the network
	 * 
	 * @return double
	 */

	public static double blockPropagatingDelay() {
		Random rand = new Random();
		double double_random = rand.nextDouble();
		return -Math.log(1 - double_random) / (1/InputConfig.getBlockDelay());
	}
	
	


	/**
	 * 
	 */
	public static void restState() {
		Node.getNodes().clear();
		
		
	}

}
