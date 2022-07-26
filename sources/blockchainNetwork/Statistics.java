package blockchainNetwork;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {

	HashMap<Transaction, Double> TransactionsLatency = new HashMap<Transaction, Double>();
	private static ArrayList<Object[]> validation = new ArrayList<>();
	
//	private static ArrayList<Object[]> chains = new ArrayList<>();
//	private static ArrayList<Object[]> transactions = new ArrayList<>();
//	private static ArrayList<Object[]> transactionLatencies = new ArrayList<>();
//
//	public static int TotalNumberOfBlock = 0;
//	public static int TotalNumberOfTx = 0;
//	public static double averageTransactionLatency = 0;
//	public static double transactionsThroughput = 0;
//	public static double firstCreationTime = 0;
//	public static double lastConfirmiationTime = 0;
//	public static double totalTransactionsTime = 0;
//	
//
	public static void calculate() {
		

	}
//		// getBlockchainLedger();
//		Chains();
//		transaction();
//		transactionLatency();
//		
//		Miner miner = InputConfig.getMiners().get(0);
//		firstCreationTime = miner.getBlockchain().get(1).getTransactions().get(0).getCreationTime();
//		int blockchainSize = miner.getBlockchain().size();
//		int transactionListSize = miner.getBlockchain().get(blockchainSize -1).getTransactions().size();
//		lastConfirmiationTime = miner.getBlockchain().get(blockchainSize -1).getTransactions().get(transactionListSize -1).getConfirmationTime();
//		totalTransactionsTime = lastConfirmiationTime - firstCreationTime;
//		if (totalTransactionsTime > 0)
//		{
//		transactionsThroughput = TotalNumberOfTx / totalTransactionsTime;
//		}
//		
//	}
//
//	public void calculateLatency(Transaction transaction) {
//		double latency = transaction.getConfirmationTime() - transaction.getCreationTime();
//		this.TransactionsLatency.put(transaction, latency);
//
//	}
//
//

//	private static void Chains() {
//
//			Node node = InputConfig.getNODES().get(1);
//			for (Block b :  node.getBlockchain()) {
//				Object[] info = { b.getId(), b.getDepth(), b.getTimestamp(), b.getTransactions().size() };
//				getChains().add(info);
//			
//		}
//		TotalNumberOfBlock=getChains().size();
//	}
//
//	private static void transaction() {
//
//		//for (int i = 0; i < InputConfig.getMiners().size(); i++) {
//			Miner Miner = InputConfig.getMiners().get(0);
//			for (Block b : Miner.getBlockchain()) {
//				for (Transaction transaction : b.getTransactions()) {
//					Object[] info = { transaction.getId(), transaction.getSize(),
//							transaction.getCreationTime(),
//							transaction.getConfirmationTime() };
//					getTransactions().add(info);
//				}
//			}
//		//}
//		TotalNumberOfTx=getTransactions().size();
//	}
//	
//	
//	
//	
//
//	private static void transactionLatency() {
//		double TransactionLatency;
//		double totalTxLatency = 0;
//		for (int i = 0; i < InputConfig.getMiners().size(); i++) {
//			Miner Miner = InputConfig.getMiners().get(i);
//			for (Block b : Miner.getBlockchain()) {
//				for (Transaction transaction : b.getTransactions()) {
//					Object[] info = { transaction.getId(),
//							TransactionLatency = transaction.getConfirmationTime() - transaction.getCreationTime()};
//					getTransactionLatencies().add(info);
//					totalTxLatency+=TransactionLatency;
//				}
//			}
//		}
//		
//		averageTransactionLatency=totalTxLatency/TotalNumberOfTx;
//		
//
//	}
//	
//
//	
//
//	public static ArrayList<Object[]> getChains() {
//		return chains;
//	}
//
//	public HashMap<Transaction, Double> getTransactionsLatency() {
//		return TransactionsLatency;
//	}
//
//	public static ArrayList<Object[]> getTransactions() {
//		return transactions;
//	}
//
//	public static ArrayList<Object[]> getTransactionLatencies() {
//		return transactionLatencies;
//	}
}
