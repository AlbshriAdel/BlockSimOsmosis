package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Statistics {

	HashMap<Transaction, Double> TransactionsLatency = new HashMap<Transaction, Double>();
	private static ArrayList<Object[]> validation = new ArrayList<>();

	private static ArrayList<Object[]> chains = new ArrayList<>();
	private static ArrayList<Object[]> transactions = new ArrayList<>();
	private static ArrayList<Object[]> transactionLatencies = new ArrayList<>();
	private static ArrayList<Object[]> transactionsPool = new ArrayList<>();
//
	public static int TotalNumberOfBlock = 0;
	public static int TotalNumberOfTx = 0;
	public static double BlockPropagationTime = 0;
	public static double averageLatency = 0;
	public static double transactionsThroughput = 0;
	public static double firstCreationTime = 0;
	public static double lastConfirmiationTime = 0;
	public static double totalTransactionsTime = 0;

//	
//
	public static void calculate() {
		blockchainLedger();
		transaction();
		transactionLatency();
		calculateLatency();
		transactionsPool();

	}

	public static void calculateLatency() {
		Miner miner = InputConfig.getMiners().get(0);
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

		Node node = InputConfig.getNodes().get(0);
		double BlockPropagationTimeCount = 0;
		for (int i = 0; i < InputConfig.getNodes().size(); i++) {
			Iterator<Block> iterator = InputConfig.getNodes().get(i).getBlockchainLedger().iterator();
			while (iterator.hasNext()) {

				Block b = iterator.next();
				Object[] info = { InputConfig.getNodes().get(i).getNodeId(), b.getBlockID(), b.getPreviousBlocKID(),
						b.getBlockDepth(), b.getBlockTimestamp(), b.getBlockSize(), b.getTransactions().size() };
				getChains().add(info);
			}
		}

		for (Block block : node.getBlockchainLedger()) {
			BlockPropagationTimeCount += block.getBlockTimestamp();
		}
		TotalNumberOfBlock = node.getBlockchainLedger().size();
		BlockPropagationTime = BlockPropagationTimeCount / TotalNumberOfBlock;
	}

	private static void transaction() {

		Miner Miner = InputConfig.getMiners().get(0);
		for (Block b : Miner.getBlockchainLedger()) {
			for (Transaction transaction : b.getTransactions()) {
				Object[] info = { transaction.getTransactionID(), transaction.getCreationTime(),
						transaction.getConfirmationTime(), transaction.getTransactionSize(), transaction.getUsedGas(),
						b.getBlockID(), transaction.getFromAddress(), transaction.getToAddress()};
				getTransactions().add(info);
			}
		}

		TotalNumberOfTx = getTransactions().size();
	}

	private static void transactionLatency() {
		double TransactionLatency;
		double totalTxLatency = 0;
		for (int i = 0; i < InputConfig.getMiners().size(); i++) {
			Miner Miner = InputConfig.getMiners().get(i);
			for (Block b : Miner.getBlockchainLedger()) {
				for (Transaction transaction : b.getTransactions()) {
					Object[] info = { transaction.getTransactionID(), transaction.getCreationTime(),
							transaction.getConfirmationTime(),
							TransactionLatency = transaction.getConfirmationTime() - transaction.getCreationTime() };
					getTransactionLatencies().add(info);
					totalTxLatency += TransactionLatency;
				}
			}
		}

		averageLatency = totalTxLatency / TotalNumberOfTx;

	}
	
	private static void transactionsPool() {

		Miner miner =Consensus.getAassignLeader();
		

		Iterator<Transaction> iterator = miner.getTransactionsPool().getTransactionsPool().iterator();
        while (iterator.hasNext()) {
        	Transaction transaction = iterator.next();

					Object[] info = { transaction.getTransactionID(), transaction.getCreationTime(),
							transaction.getConfirmationTime(), "Pending"};
					getTransactionsPool().add(info);
	
        }

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

	
	
}
