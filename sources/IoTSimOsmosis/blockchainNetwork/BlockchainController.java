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
	static Random rand = new Random();
	static Calendar now = Calendar.getInstance();
	static DateFormat df = new SimpleDateFormat("dd-MM-yyy-HH:mm");


	/**
	 * This method is responsible for generate light node
	 */
	public static void generateNodes() {

		if (InputConfig.getConsensusalgorithm().equals("raft")) {
		if (InputConfig.getNumberOfNodes() >= 3 && InputConfig.getNumberOfNodes()>InputConfig.getNumberOfMiner()) {
		   
			for (int i = 0; i < InputConfig.getNumberOfNodes(); i++) {
				String time = df.format(now.getTime());
				Node.getNodes().add(new Node(i, "follower",time));
				}
			}
		}
		//&& InputConfig.getNumberOfNodes()>InputConfig.getNumberOfMiner()
		 else if(InputConfig.getConsensusalgorithm().equals("PoW") ) {
			 for (int i = 0; i < InputConfig.getNumberOfNodes(); i++) {
					String time = df.format(now.getTime());
					Node.getNodes().add(new Node(i, "node",time));
					}
		 		}
		 
		
		Consensus.consensus(InputConfig.getConsensusalgorithm());
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
				//double creationTime = osmosisTransactionTime;
				//double transactionSize =ThreadLocalRandom.current().nextDouble(100,1000);
				Transaction tx = new Transaction(osmosisTransactionTime);
//				Node.getNodes().get(0).getTransactionsPool().add(tx);
				
				
				for (Node n :Node.getNodes()){
					if(n.getNodeType().equals("leader") || n.getNodeType().equals("miner")) {
						n.getTransactionsPool().add(tx);
						
					
					
					}	
				}
			//Statistics.noTransactionsConfig += 1;
			
			}
			//System.out.println("Tx pool size:" + Transaction.getPool());
			

	public static void creatTransactions() {
		int countTransaction=0; // to count number of transaction per second
		double maxTxTime =1;
		double minTxTime =0;
		int Psize= InputConfig.getSimulationTime()*InputConfig.getTransactionNumber();
		int i=0;
		while (i < Psize) {
			if (countTransaction<InputConfig.getTransactionNumber()) {
				double transactionCreatingTime= ThreadLocalRandom.current().nextDouble(minTxTime,maxTxTime);
				Transaction tx = new Transaction(transactionCreatingTime);
				for (Node n :Node.getNodes()){
					if(n.getNodeType().equals("leader") || n.getNodeType().equals("miner")) {
						n.getTransactionsPool().add(tx);
						}	
					}
				countTransaction+=1;
				i+=1;
				System.out.println("count tx: " + countTransaction);
			} else {
				countTransaction=0;
				maxTxTime+=1;
				minTxTime+=1;
				
				
			}
			
					
	
			
			

		
		
		}
		
		
		}

	 	
	

	/**
 	 * This method is responsible for generate transactions without an integrated
	 */

	public static void creatTransactionsWithoutIntegrated() {

			for (int i = 0; i < InputConfig.getTransactionNumber(); i++) {
				double creationTime = ThreadLocalRandom.current().nextDouble(0,31);
				//double transactionSize = 
				//ThreadLocalRandom.current().nextDouble(InputConfig.getMaxTransactionSize(),InputConfig.getMinTransactionSize());
						Transaction tx = new Transaction(creationTime);
		
				
				
				for (Node n :Node.getNodes()){
					if(n.getNodeType().equals("leader") || n.getNodeType().equals("miner")) {
						n.getTransactionsPool().add(tx);
						
						
					
					}	
				}
			
			
			}
			
			
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
		Transaction tx = new Transaction(creationTime);
		//Consensus.getAassignLeader().getTransactionsPool().add(tx);
		

	}

	
	


	/**
	 * 
	 */
	public static void restState() {
		Node.getNodes().clear();
		
		
	}

}
