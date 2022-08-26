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
	public static int totalNumberOfBlock = 0;
	public static int totalNumberOfTx = 0;
	public static double blockPropagationTime = 0;
	public static double averageLatency = 0;
	public static double transactionsThroughput = 0;
	public static double firstCreationTime = 0;
	public static double lastConfirmiationTime = 0;
	public static double totalTransactionsTime = 0;
	public static double blockIncludeTx=0;
	public static double blockWithoutTx=0;
	public static double blockMinedby1=0;
	public static double blockMinedTime1=0;
	public static double blockMinedby2=0;
	public static double blockMinedTime2=0;
//	public static double blockMinedby3=0;
//	public static double blockMinedby4=0;
	public static double TxPerBlock=0;
	public static double TxInclusionTime=0;
	public static double TxUsedGas=0;
	public static double TxSize=0;
	public static double pendingTx=0;
	public static double blockSize=0;
	public static int runNumber = 0;
	

	

//	
//
	public static void calculate(int simulationRunNumber) {
		// result();
		blockchainLedger();
		globalBlockchain();
		transaction();
		transactionLatency();
		calculateLatency();
		overallResults(); 
		blockCreatedByMiner();
		//transactionsPool();
		// rest();

	}

//	public static void result() {
//
//		getResult().add(new Object[] { Statistics.getRunNumber(), Node.getNodes().size(), Statistics.TotalNumberOfBlock,
//				Statistics.TotalNumberOfTx, Statistics.BlockPropagationTime, Statistics.averageLatency,
//				Statistics.transactionsThroughput, Statistics.totalTransactionsTime });
//
//	}

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
			if (miner.getBlockchainLedger().get(0).getTransactions().size() > 0) {
				firstCreationTime = miner.getBlockchainLedger().get(1).getTransactions().get(0).getCreationTime();
			}
			if (miner.getBlockchainLedger().get(blockchainSize - 1).getTransactions().size() > 0) {
				lastConfirmiationTime = miner.getBlockchainLedger().get(blockchainSize - 1).getTransactions()
						.get(transactionListSize - 1).getConfirmationTime();
			}
			totalTransactionsTime = lastConfirmiationTime - firstCreationTime;
		}

		if (totalTransactionsTime > 0) {
			transactionsThroughput = totalNumberOfTx / totalTransactionsTime;
		}
	}

	private static void blockchainLedger() {

		Node node = Consensus.getAassignLeader();
		double BlockPropagationTimeCount = 0;

		Iterator<Block> iterator = node.getBlockchainLedger().iterator();
		while (iterator.hasNext()) {

			Block b = iterator.next();
			if (b.getBlockID() == 0) {
				Object[] info = { getRunNumber(), b.getBlockID(), b.getPreviousBlockID(), b.getBlockDepth(),
						b.getBlockTimestamp(), b.getBlockSize(), b.getTransactions().size(),0,0};
				getChains().add(info);
			} else {
				Object[] info = { getRunNumber(), b.getBlockID(), b.getPreviousBlockID(), b.getBlockDepth(),
						b.getBlockTimestamp(), b.getBlockSize(), b.getTransactions().size(), b.getMiner().getNodeId(),
						b.getMiner().getHashPower() };
				getChains().add(info);

			}
		}

		for (Block block : node.getBlockchainLedger()) {
			BlockPropagationTimeCount += block.getBlockTimestamp();
		}
		totalNumberOfBlock = node.getBlockchainLedger().size();
		blockPropagationTime = BlockPropagationTimeCount / totalNumberOfBlock;
	}

	private static void globalBlockchain() {

		Node miner = Consensus.getAassignLeader();
		Iterator<Block> iterator = miner.getBlockchainLedger().iterator();
		while (iterator.hasNext()) {

			Block b = iterator.next();
			
			if (b.getBlockID() == 0) {
				Object[] info = { getRunNumber(), b.getBlockID(), b.getPreviousBlockID(), b.getBlockDepth(),
						b.getBlockTimestamp(), b.getBlockSize(), b.getTransactions().size(),0,0};
				getGlobalBlockchain().add(info);
			} else {
				Object[] info = { getRunNumber(), b.getBlockID(), b.getPreviousBlockID(), b.getBlockDepth(),
						b.getBlockTimestamp(), b.getBlockSize(), b.getTransactions().size(), b.getMiner().getNodeId(),
						b.getMiner().getHashPower() };
				getGlobalBlockchain().add(info);

			}
		}
	}
	

	private static void transaction() {

		Node miner = Consensus.getAassignLeader();

		for (Block b : miner.getBlockchainLedger()) {
			for (Transaction transaction : b.getTransactions()) {
				Object[] info = { getRunNumber(), transaction.getTransactionID(), transaction.getCreationTime(),
						transaction.getConfirmationTime(), transaction.getTransactionSize(), transaction.getUsedGas(),
						b.getBlockID()};
				getTransactions().add(info);
			}
		}

		totalNumberOfTx = getTransactions().size();
	}

	private static void transactionLatency() {
		double TransactionLatency;
		double totalTxLatency = 0;

		Node Miner = Consensus.getAassignLeader();

		for (Block b : Miner.getBlockchainLedger()) {
			for (Transaction transaction : b.getTransactions()) {
				Object[] info = { getRunNumber(), transaction.getTransactionID(), transaction.getCreationTime(),
						transaction.getConfirmationTime(),
						TransactionLatency = transaction.getConfirmationTime() - transaction.getCreationTime() };
				getTransactionLatencies().add(info);
				totalTxLatency += TransactionLatency;
			}
		}

		averageLatency = totalTxLatency / totalNumberOfTx;

	}

	private static void overallResults() {
		Node Miner = Consensus.getAassignLeader();
		for (Block b : Miner.getBlockchainLedger()) {
			blockSize+=b.getBlockSize();
				if (b.getHasTx() == false) {
					blockWithoutTx+=1;
					
					
				} else if (b.getHasTx() == true ) {
					blockIncludeTx+=1;
					TxPerBlock+=b.getTransactions().size();
				}
				
			for (Transaction t : b.getTransactions()) {
				TxInclusionTime+=t.getConfirmationTime();
				TxUsedGas+=t.getUsedGas();
				TxSize+=t.getTransactionSize();
				
			}
		}
		
		blockSize=blockSize/totalNumberOfBlock;
		TxPerBlock=TxPerBlock/totalNumberOfBlock;
		TxInclusionTime=TxInclusionTime/totalNumberOfTx;
		TxUsedGas=TxUsedGas/totalNumberOfTx;
		TxSize=TxSize/totalNumberOfTx;
		
	}
	
	private static void blockCreatedByMiner() {
		
		Node miner1 = Consensus.getAassignLeader();
		Node miner2 = Consensus.getAassignLeader();
		
			
		
		
			
				for(Block block : miner1.getBlockchainLedger()){
					//if (block.getMiner().getNodeId() == miner1.getNodeId()) {
						blockMinedby1+=1;
						blockMinedTime1+=block.getBlockTimestamp();
					}
				for(Block block : miner2.getBlockchainLedger()){
				
					blockMinedby2+=1;
					blockMinedTime2+=block.getBlockTimestamp();
				} 
					
				
			
		
		blockMinedTime1 = blockMinedTime1 /blockMinedby1;
		blockMinedTime2 = blockMinedTime2 /blockMinedby1;
//		blockMinedTime3 = blockMinedTime3 /blockMinedby1;
//		blockMinedTime4 = blockMinedTime4 /blockMinedby1;
	}
	

	private static void transactionsPool() {
				Node node=null;
				for (Node miner : Node.getNodes()) {
					//if (node.getNodeType().equals("miner")|| node.getNodeType().equals("leader")) {
						node=miner;
					//}
				}
				
				for (Transaction transaction : node.getTransactionsPool()) {
					node.getTransactionsPool().sort((t1, t2) -> Double.compare(t2.getUsedGas(), t1.getUsedGas()));
					pendingTx+=1;
					Object[] info = { getRunNumber(), transaction.getTransactionID(),
							transaction.getCreationTime(),transaction.getUsedGas(), "Pending" };
					getTransactionsPool().add(info);
				}
				
				
	}

	public static void rest() {
		totalNumberOfBlock = 0;
		totalNumberOfTx = 0;
		blockPropagationTime = 0;
		averageLatency = 0;
		transactionsThroughput = 0;
		firstCreationTime = 0;
		lastConfirmiationTime = 0;
		totalTransactionsTime = 0;
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
