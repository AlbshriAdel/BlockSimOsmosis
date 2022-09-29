package IoTSimOsmosis.blockchainNetwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InputConfig {
	
	/**
	 * @param numberOfNodes
	 * @param numberOfMiner
	 * 
	 * Miner is part of total number of nodes 
	 *    e.g. No.nodes 5 and Miner 2 
	 * (total number of nodes and miner is 5)
	 */
	/****************To Create Node Parameters *****************/
	private static final int numberOfNodes = 4;
	private static final int numberOfMiner = 1; 
														
	/**
	 * 
	 * @param transactionNumber : Maximum number of transactions created during running simulator.
	 * @param maxTXsize : Maximum Transaction size in MB.
	 * @param minTXsize : Minimum Transaction size in MB.
	 * @param TransactionGasLimit : maximum amount of gas units the transaction can used.
	 * 
	 */
	/*************To Create Transaction Parameters ************/
	private static final int transactionNumber  = 1650; // 
	private static final double maxTXsize = 0.064; // Maximum Transaction size in MB (64KB quorum)
	private static final double minTXsize = 0.001; // Minimum Transaction size in MB (1 KB)
	private static final double transactionGasLimit = 100; //the maximum amount of gas units the transaction can use
	
	/**
	 * 
	 * @param maxBlockSize
	 * @param blockGasLimit
	 *  @param blockInterval
	 * 
	 */
	/*************To Create Block Parameters ************/
	private static final long maxBlockSize = 1;//4194304;//4194304;
	private static final double blockGasLimit = 1000000;//30000000;//30000000;
	private static final double Binterval = 0.05; //12.41 raft(50ms)
	
	/**
	 * @param consensusAlgorithm : PoW and raft
	 */
	/*************To configure consensus Algorithm   ************/
	private static final String consensusAlgorithm = "raft"; // raft or PoW
	
	/**
	 * 
	 * @param simTime
	 * @param simulatorRun
	 */
	/*************To configure simulator ************/
	private static final int simTime = 500;
	private static final int simulatorRun= 1;
	
	


	
	
	
	
	
	

	public static int getSimulatorRun() {
		return simulatorRun;
	}

	public static double getTransactionGaslimit() {
		
		return transactionGasLimit;
	}

	


	
	public static double getBlockInterval() {
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
	public static long getMaxBlockSize() {
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
	public static int getSimulationTime() {
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
