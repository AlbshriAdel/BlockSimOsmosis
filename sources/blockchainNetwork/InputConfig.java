package blockchainNetwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InputConfig {
	
	/****************To Create Node Parameters *****************/
	private static final int numberOfNodes = 5;
	private static final int numberOfMiner = 2;
	
	/*************To Create Transaction Parameters ************/
	private static final int transactionNumber  = 9; // Maximum number of transactions created during running simulator
	private static final long minTXsize = 100; // Minimum Transaction size in KB
	private static final long maxTXsize = 1000; // Maximum Transaction size in KB
	private static final int Binterval = 7;
	
	private static final long maxBlockSize = 100;//4194304;//4194304; // Maximum Block Size in KB equivalent to 4 GB
	
	private static final long minGasUsed = 1; // Minimum gas used 
	private static final long maxGasUsed = 3; // Maximum gas used
	
	private static final String consensusAlgorithm = "raft"; 
	private static final int simTime = 1000;
	
	


	private static final double blockGasLimit = 1000;//30000000;//30000000;
	
	
	private static final Statistics STATISTICS = new Statistics();
	
	private static final double propTxDelay = 0.000690847927; 
	
	public static double getProptxdelay() {
		return propTxDelay;
	}

	private static ArrayList<Node> NODES = new ArrayList<>();
	private static ArrayList<Miner> Miners = new ArrayList<>();
	
	public static int getBinterval() {
		return Binterval;
	}

/**
 * Return to get transaction number
 * @return transactionNumber
 */
	public static int getTransactionNumber() {
		return transactionNumber;
	}

	

	/**
	 * Return the Number of nodes that created
	 * @return numberOfNodes
	 */
	public static int getNumberOfNodes() {
		return numberOfNodes;
	}

	
	
	
	/**
	 * Return an arrayList of node
	 * @return
	 */
	public static ArrayList<Node> getNodes() {
		return NODES;
	}


	
	
/**
 *  Return an arrayList of Miner
 * @return Miners
 */
	public static ArrayList<Miner> getMiners() {
		return Miners;
	}



	/**
	 * Return the number of Miner is created
	 * @return
	 */
	public static int getNumberOfMiner() {
		return numberOfMiner;
	}



/**
 * Return the maximum block gas limit
 * @return
 */
	public static double getBlockGasLimit() {
		return blockGasLimit;
	}


	public static Statistics getStatistics() {
		return STATISTICS;
	}


	/**
	 * Return the maximum of block size
	 * @return maxBlockSize
	 */
	public static long getMaxblocksize() {
		return maxBlockSize;
	}

/**
 * Return the minimum of transaction size
 * @return minTXsize
 */
	public static long getMinTransactionSize() {
		return minTXsize;
	}

	
	/**
	 * Return the maximum of transaction size
	 * @return maxTXsize
	 */
	public static long getMaxTransactionSize() {
		return maxTXsize;
	}




/**
 * Return the simulation time that is configured 
 * @return simTime
 */
	public static int getSimTime() {
		return simTime;
	}

	/**
	 * Return the consensus algorithm that used in the simulator
	 * @return consensusAlgorithm
	 */
public static String getConsensusalgorithm() {
	return consensusAlgorithm;
}
	
	
	


}
