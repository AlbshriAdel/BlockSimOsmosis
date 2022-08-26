package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Statistics {

	private static ArrayList<Object[]> chains = new ArrayList<>();
	private static ArrayList<Object[]> globalBlockchain = new ArrayList<>();
	private static ArrayList<Object[]> transactions = new ArrayList<>();
	private static ArrayList<Object[]> transactionLatencies = new ArrayList<>();
	private static ArrayList<Object[]> transactionsPool = new ArrayList<>();
	private static ArrayList<Object[]> Result = new ArrayList<>();
//
	public static int TotalNumberOfBlock = 0;
	public static int TotalNumberOfTx = 0;
	public static double BlockPropagationTime = 0;
	public static double averageLatency = 0;
	public static double transactionsThroughput = 0;
	public static double firstCreationTime = 0;
	public static double lastConfirmiationTime = 0;
	public static double totalTransactionsTime = 0;
	public static int noTransactionsConfig = 0;
	public static int runNumber = 0;

//	
//
	public static void calculate(int simulationRunNumber) {
		result();
		blockchainLedger();
		globalBlockchain();
		transaction();
		transactionLatency();
		calculateLatency();
		transactionsPool();
		rest();

	}

	public static void result() {

		getResult().add(new Object[] { Statistics.getRunNumber(), Node.getNodes().size(), Statistics.TotalNumberOfBlock,
				Statistics.TotalNumberOfTx, Statistics.BlockPropagationTime, Statistics.averageLatency,
				Statistics.transactionsThroughput, Statistics.totalTransactionsTime });

	}

	public static void calculateLatency() {

		Node miner = null;
		for (Node m : Node.getNodes()) {
			if (m.getNodeType().equals("leader") || m.getNodeType().equals("miner")) {
				miner = m;
			}
		}
		int blockchainSize = miner.getBlockchainLedger().size();
		int transactionListSize = miner.getBlockchainLedger().get(blockchainSize - 1).getTransactions().size();
		if (blockchainSize > 0) {
			if (miner.getBlockchainLedger().get(0).getHasTx() == true) {
				firstCreationTime = miner.getBlockchainLedger().get(1).getTransactions().get(0).getCreationTime();
			}
			if (miner.getBlockchainLedger().get(blockchainSize - 1).getHasTx() == true) {
				lastConfirmiationTime = miner.getBlockchainLedger().get(blockchainSize - 1).getTransactions()
						.get(transactionListSize - 1).getConfirmationTime();
			}
			totalTransactionsTime = lastConfirmiationTime - firstCreationTime;
		}

		if (totalTransactionsTime > 0) {
			transactionsThroughput = TotalNumberOfTx / totalTransactionsTime;
		}
	}

	private static void blockchainLedger() {

		Node node = Node.getNodes().get(0);
		double BlockPropagationTimeCount = 0;
		for (int i = 0; i < Node.getNodes().size(); i++) {
			Iterator<Block> iterator = Node.getNodes().get(i).getBlockchainLedger().iterator();
			while (iterator.hasNext()) {

				Block b = iterator.next();
				Object[] info = { getRunNumber(), b.getBlockID(), b.getPreviousBlockID(), b.getBlockDepth(),
						b.getBlockTimestamp(), b.getBlockReceivedTime(), b.getBlockSize(), b.getTransactions().size(),
						b.getMiner().getNodeId(), b.getMiner().getHashPower() };
				getChains().add(info);
			}
		}

		for (Block block : node.getBlockchainLedger()) {
			BlockPropagationTimeCount += block.getBlockTimestamp();
		}
		TotalNumberOfBlock = node.getBlockchainLedger().size();
		BlockPropagationTime = BlockPropagationTimeCount / TotalNumberOfBlock;
	}
	
	
	private static void globalBlockchain() {

		
		double BlockPropagationTimeCount = 0;
		for (int i = 0; i < Node.getNodes().size(); i++) {
			Iterator<Block> iterator = Node.getNodes().get(i).getBlockchainLedger().iterator();
			while (iterator.hasNext()) {

				Block b = iterator.next();
				Object[] info = { getRunNumber(), b.getBlockID(), b.getPreviousBlockID(), b.getBlockDepth(),
						b.getBlockTimestamp(), b.getBlockReceivedTime(), b.getBlockSize(), b.getTransactions().size(),
						b.getMiner().getNodeId(), b.getMiner().getHashPower() };
				getGlobalBlockchain().add(info);
			}
		}

	
	}

	private static void transaction() {

		Node miner = Node.getNodes().get(0);

		for (Block b : miner.getBlockchainLedger()) {
			for (Transaction transaction : b.getTransactions()) {
				Object[] info = { getRunNumber(), miner.getNodeId(), transaction.getTransactionID(),
						transaction.getCreationTime(), transaction.getConfirmationTime(),
						transaction.getTransactionSize(), transaction.getUsedGas(), b.getBlockID(),
						transaction.getFromAddress(), transaction.getToAddress() };
				getTransactions().add(info);
			}
		}

		TotalNumberOfTx = getTransactions().size();
	}

	private static void transactionLatency() {
		double TransactionLatency;
		double totalTxLatency = 0;


				Node Miner = Consensus.getAassignLeader();

				for (Block b : Miner.getBlockchainLedger()) {
					for (Transaction transaction : b.getTransactions()) {
						Object[] info = { getRunNumber(), transaction.getTransactionID(), transaction.getCreationTime(),
								transaction.getConfirmationTime(),
								TransactionLatency = transaction.getConfirmationTime()
										- transaction.getCreationTime() };
						getTransactionLatencies().add(info);
						totalTxLatency += TransactionLatency;
					}
				}
			
		

		averageLatency = totalTxLatency / TotalNumberOfTx;

	}

	private static void transactionsPool() {

//		Node miner =Consensus.getAassignLeader();
//		
//
//		Iterator<Transaction> iterator = miner.getTransactionsPool().iterator();
//        while (iterator.hasNext()) {
//        	Transaction transaction = iterator.next();
		for (Node node : Node.getNodes()) {
			if (node.getNodeType().equals("leader") || node.getNodeType().equals("miner")) {
				for (Transaction transaction : node.getTransactionsPool()) {

					Object[] info = { getRunNumber(), node.getNodeId(), transaction.getTransactionID(),
							transaction.getCreationTime(), transaction.getConfirmationTime(), "Pending" };
					getTransactionsPool().add(info);
				}
			}
		}

	}

	public static void rest() {
		TotalNumberOfBlock = 0;
		TotalNumberOfTx = 0;
		BlockPropagationTime = 0;
		averageLatency = 0;
		transactionsThroughput = 0;
		firstCreationTime = 0;
		lastConfirmiationTime = 0;
		totalTransactionsTime = 0;
		noTransactionsConfig = 0;
//		getChains().clear();
//		getTransactions().clear();
//		getTransactionLatencies().clear();
//		getTransactionsPool().clear();

	}

	public static ArrayList<Object[]> getChains() {
		return chains;
	}

	public static ArrayList<Object[]> getTransactions() {
		return transactions;
	}

	public static ArrayList<Object[]> getTransactionLatencies() {
		return transactionLatencies;
	}

	public static ArrayList<Object[]> getTransactionsPool() {
		return transactionsPool;
	}

	public static ArrayList<Object[]> getResult() {
		return Result;
	}
	public static ArrayList<Object[]> getGlobalBlockchain() {
		return globalBlockchain;
	}

	public static int getRunNumber() {
		return runNumber;
	}

}
