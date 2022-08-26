package IoTSimOsmosis.blockchainNetwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InputConfig {
	
	/****************To Create Node Parameters *****************/
	private static final int numberOfNodes = 4;
	private static final int numberOfMiner = 4; // number of is part of total number of Nodes
	
	/*************To Create Transaction Parameters ************/
	private static final int transactionNumber  = 100; // Maximum number of transactions created during running simulator
	private static final double minTXsize = 0.1; // Minimum Transaction size in KB
	private static final double maxTXsize = 1; // Maximum Transaction size in KB
	private static final double Binterval = 12.42;
	private static final double blockDelay= 2.6; //average block propogation delay in seconds
	private static final long maxBlockSize = 1;//4194304;//4194304; // Maximum Block Size in KB equivalent to 4 GB
	
	
	private static final double TransactionGasLimit = 100;
	
	
	private static final String consensusAlgorithm = "PoW"; // raft or PoW
	private static final int simTime = 100;
	private static final int simulatorRun= 1;
	
	


	private static final double blockGasLimit = 1000000;//30000000;//30000000;
	
	
	
	
	

	public static int getSimulatorRun() {
		return simulatorRun;
	}

	public static double getTransactionGaslimit() {
		
		return TransactionGasLimit;
	}

	


	
	public static double getBinterval() {
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
	public static double getMinTransactionSize() {
		return minTXsize;
	}

	
	/**
	 * Return the maximum of transaction size
	 * @return maxTXsize
	 */
	public static double getMaxTransactionSize() {
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

	public static double getBlockDelay() {
		return blockDelay;
	}




	
	
	


}
