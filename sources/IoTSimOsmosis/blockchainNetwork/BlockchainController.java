package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author pca
 *
 */
public class BlockchainController {


	/**
	 * This method is responsible for generate light node
	 */
	public static void generateNodes() {
		if (InputConfig.getNumberOfNodes() >= 3 && InputConfig.getConsensusalgorithm().equals("raft")) {

			for (int i = 0; i < InputConfig.getNumberOfNodes(); i++) {
				Node.getNodes().add(new Node(i, "follower"));
			}
			Consensus consensus = new Consensus(InputConfig.getConsensusalgorithm());
		} else {
			System.out.println(
					"[Error] the number of node must be a larger than three nodes and make sure the spelling of consensus algorithm 'raft'");
		}
	}


	/**
	 * This method is responsible for generate transactions without an integrated
	 */

	public static void creatTransactionsWithoutIntegrated() {

		for (int i = 0; i < InputConfig.getTransactionNumber(); i++) {
			double creationTime = ThreadLocalRandom.current().nextDouble(i, i+1);
			double transactionSize = ThreadLocalRandom.current().nextDouble(InputConfig.getMinTransactionSize(),
					InputConfig.getMaxTransactionSize());

			// create transaction
			// Transaction(double creationTime, double txSize,String fromAddress,String
			// toAddress)
			Transaction tx = new Transaction(creationTime, transactionSize, 1, 2);
			Consensus.getAassignLeader().getTransactionsPool().getTransactionsPool().add(tx);
			Statistics.noTransactionsConfig+=1;
			
		}

	}
	
	/**
	 * This method is responsible for generate transactions with IoT simulator
	 */

	public static void creatTransactionsWithIntegrated(double transactionTime, int fromAddress, int toAddress) {

			double creationTime =transactionTime;
			double transactionSize = ThreadLocalRandom.current().nextDouble(InputConfig.getMinTransactionSize(),
					InputConfig.getMaxTransactionSize());

			// create transaction
			// Transaction(double creationTime, double txSize,String fromAddress,String
			// toAddress)
			Transaction tx = new Transaction(creationTime, transactionSize, fromAddress, toAddress);
			Consensus.getAassignLeader().getTransactionsPool().getTransactionsPool().add(tx);
			Statistics.noTransactionsConfig+=1;
			
		

	}


	/**
	 * Delay for propagating blocks in the network
	 * 
	 * @return double 
	 */

	public static double blockPropagatingDelay(){
		Random rand = new Random();
		double double_random=rand.nextDouble();
		return -Math.log(1-double_random)/(InputConfig.getBlockDelay());
	}

}
