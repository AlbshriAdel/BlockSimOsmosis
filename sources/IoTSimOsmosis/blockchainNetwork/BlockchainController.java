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
				InputConfig.getNodes().add(new Node(i, "follower"));
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

//
//	/**
//	 * create transactions
//	 * 
//	 * @param timestamp   time of transaction reception to the blockchain network
//	 * @param fromAddress sender address
//	 * @param toAddress   receiver address
//	 */
//	public static void createTransactions(double timestamp, String fromAddress, String toAddress) {
//
//		// double creationTime = ThreadLocalRandom.current().nextDouble(timestamp,
//		// timestamp + 1);
//		for (int i = 0; i < 10; i++) {
//			double creationTime = timestamp;
//			double txSize = ThreadLocalRandom.current().nextDouble(InputConfig.getMintxsize(),
//					InputConfig.getMaxtxsize());
//			double gasPrice = ThreadLocalRandom.current().nextDouble(InputConfig.getMingasfee(),
//					InputConfig.getMaxgasfee());
//
//			// create transaction
//			Transaction tx = new Transaction(creationTime, txSize, gasPrice, toAddress, toAddress);
//
//			// propagate to all miners
//			// for (int i = 0; i < InputConfig.getMiners().size(); i++) {
//
//			InputConfig.getMiners().get(0).getTransactionsPool().getTransactionsPool().add(tx);
//
//			// System.out.println(InputConfig.getMiners().get(0).getTransactionsPool().getTransactionsPool().size());
//
//		}
//
//	}
//
//	public void executeTransactions() {
//
//		// System.out.println("current time: "+ currentTime);
//		// System.out.println("Abu hanooda "+
//		// -Math.log(1-Math.random())/(1/InputConfig.getProptxdelay()));
//
//		// currentTime += /** (miner.getHashRate()/100)
//		// Math.log(InputConfig.getBinterval());
//
//		System.out.println("TEST CURRENT TIME: " + currentTime);
//		Consensus consensus = new Consensus(InputConfig.getConcensusalgorithm());
//		Miner miner = consensus.assignMiner();
//		System.out
//				.println("############################# Execute Transactions #######################################");
//		System.out.println("current time at Miner ID (" + miner.getNodeId() + "): " + currentTime);
//
//		// miner.getTransactionsPool().sortTransactionsPool();
//		int remainingTransactions = miner.getTransactionsPool().getTransactionsPool().size();
//
//		while (miner.getTransactionsPool().getTransactionsPool().size() > 0) {
//			Block block = new Block();
//			currentTime += ThreadLocalRandom.current().nextDouble(0, InputConfig.getBinterval());
//			Iterator<Transaction> iterator = miner.getTransactionsPool().getTransactionsPool().iterator();
//			while (iterator.hasNext()) {
//				Transaction transaction = iterator.next();
//				// System.out.println("Transaction Pool size: " +
//				miner.getTransactionsPool().getTransactionsPool().size();
//				block.setDepth(miner.getBlockchain().size());
//				block.setTimestamp(currentTime);
//				System.out.println("Miner ID(" + miner.getId() + ") Blockchain Size: " + miner.getBlockchain().size());
//				System.out.println("Miner ID(" + miner.getId() + ") Current Block Depth: " + block.getDepth());
//				if (block.getBlockLimit() >= transaction.getGasPrice() && block.getSize() >= transaction.getSize()
//						&& transaction.getCreationTime() <= currentTime) {
//					System.out.println("Current Time Zifffft !!" + currentTime);
//					// System.out.println("Miner ID("+miner.getId()+") include transaction");
//					block.setBlockLimit(block.getBlockLimit() - transaction.getGasPrice());
//					block.setSize(block.getSize() - transaction.getSize());
//					transaction.setConfirmationTime(currentTime);
//					// System.out.println("test: " + transaction.getConfirmationTime());
//					InputConfig.getStatistics().calculateLatency(transaction);
//					// System.out.println("latency : "+
//					// InputConfig.getStatistics().getTransactionsLatency().get(transaction));
//					// System.out.println("Before adding transactions to the block: "+
//					// block.getTransactions().size());
//					block.getTransactions().add(transaction);
//					// System.out.println("After adding transactions to the block: "+
//					// block.getTransactions().size());
//					System.out.println("Before removing transaction from the pool: ["
//							+ miner.getTransactionsPool().getTransactionsPool().size() + "]");
//					iterator.remove();
//					System.out.println("After removing transaction from the pool: ["
//							+ miner.getTransactionsPool().getTransactionsPool().size() + "]");
//					System.out.println();
//
//				}
//
//			}
//			if (block.getTransactions().size() > 0) {
//				System.out.println("Before adding the new block: [" + miner.getBlockchain().size() + "]");
//				propagateBlock(block);
//				// miner.getBlockchain().add(block);
//				System.out.println("After adding the new block: [" + miner.getBlockchain().size() + "]");
//			}
//		}
//
//	}
//
//	private void propagateBlock(Block block) {
//		for (Miner miner : InputConfig.getMiners()) {
//			miner.getBlockchain().add(block);
//		}
//
//		for (Node node : InputConfig.getNODES()) {
//			node.getBlockchain().add(block);
//		}
//
//	}
//
//	static boolean transactionsProcessed() {
//
//		boolean processed = true;
//		for (int i = 0; i < InputConfig.getMiners().size(); i++) {
//			if (InputConfig.getMiners().get(0).getTransactionsPool().getTransactionsPool().size() > 0) {
//				processed = false;
//				break;
//			}
//		}
//		System.out.println("is transactions Processed : " + processed + "["
//				+ InputConfig.getMiners().get(0).getTransactionsPool().getTransactionsPool().size() + "]");
//		return processed;
//
//	}
//
	

}
