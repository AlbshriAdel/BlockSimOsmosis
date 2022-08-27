package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author adelalbshri
 *
 */
public class Transaction {
	static Random rand = new Random();
	// transaction id
	private long transactionID = 0;
	// timestamp of transaction send from the IoT side
	private double creationTime;
	// timestamp of transaction inclusion in a confirmed block
	private double confirmationTime;
	// transaction sender address
	private int fromAddress;
	// transaction receiver address
	private int toAddress;
	// transaction size
	private double transactionSize;
	// the amount of gas used by the transaction
	private double usedGas;
	// the maximum amount of gas units the transaction can use
	private double transactionGasLimit;
	// A variable to calculate (count) the remaining limit of Block gas used
	static double blockGaslimit = 0;
	// A variable to calculate (count) the remaining limit of Block size used
	static double blockSizelimit = 0;
	
	

	/**
	 * A constructor method for transaction class
	 * 
	 * @param creationTime
	 * @param txSize
	 * @param gasLimit
	 * @param usedGas
	 * @param fromAddress
	 * @param toAddress
	 */
	public Transaction(double creationTime) {
		this.transactionID = ThreadLocalRandom.current().nextLong(10000000000L);
		this.creationTime = creationTime;
		this.transactionGasLimit = InputConfig.getTransactionGaslimit();// 100;//50; //8000000;
		this.usedGas = getRandomNumber(0, (int) transactionGasLimit);
		this.transactionSize = InputConfig.getMinTransactionSize() + rand.nextDouble() * (InputConfig.getMaxTransactionSize()-InputConfig.getMinTransactionSize());;

	}

	/**
	 * Return the transaction ID
	 * 
	 * @return transactionID
	 */
	public long getTransactionID() {
		return transactionID;
	}

	/**
	 * Return creation time for each transaction
	 * 
	 * @return creationTime
	 */
	public double getCreationTime() {
		return creationTime;
	}

	// check if we need it
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	/**
	 * Return transaction size for each transaction
	 * 
	 * @return transactionSize
	 */
	public double getTransactionSize() {
		return transactionSize;
	}

	/**
	 * Return transaction confirmation Time when adding to a block
	 * 
	 * @return transactionSize
	 */
	public double getConfirmationTime() {
		return confirmationTime;
	}

	/**
	 * Return the amount of gas used by the transaction after its execution on the
	 * EVM
	 * 
	 * @return usedGas
	 */
	public double getUsedGas() {
		return usedGas;
	}

	/**
	 * Return the maximum amount of gas units the transaction can use.
	 * 
	 * @return gasLimit
	 */
	public double getTransactionGasLimit() {
		return transactionGasLimit;
	}

	public int getFromAddress() {
		return fromAddress;
	}

	public int getToAddress() {
		return toAddress;
	}

	/**
	 * To set creation time for each transaction
	 * 
	 * @param creationTime
	 */
	public void setCreationTime(double creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * To set the amount of gas units that can use.
	 * 
	 * @param usedGas
	 */
	public void setUsedGas(double usedGas) {
		this.usedGas = usedGas;
	}

	/**
	 * To set confirmation Time of transaction.
	 * 
	 * @param confirmationTime
	 */
	public void setConfirmationTime(double confirmationTime) {
		this.confirmationTime = confirmationTime;
	}

	/*
	 * Remaining limit of Block gas used
	 */

	public static double getLimit() {
		return blockGaslimit;
	}

	/*
	 * Remaining limit of Block gas used
	 */

	public static double getBlockSizeLimit() {
		return blockSizelimit;
	}

/**
 *  1- blockGaslimit subtract transaction used gas.
 *  2- Block size subtract transaction size
 * @param miner
 * @param eventTime
 * @return
 */
	public static ArrayList<Transaction> executeTranscationsPoW(Node miner,Block block, double eventTime) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		
		double blockGas= block.getBlockGas();
		blockGaslimit = 0;
		int count = 0;


		miner.getTransactionsPool().sort((t1, t2) -> Double.compare(t2.getUsedGas(), t1.getUsedGas()));

		while (count < miner.getTransactionsPool().size()) {
			
			if (blockGas >= miner.getTransactionsPool().get(count).getUsedGas()
					&& miner.getTransactionsPool().get(count).getCreationTime() <= eventTime) {
				blockGas  -= miner.getTransactionsPool().get(count).getUsedGas();
				transactions.add(miner.getTransactionsPool().get(count));
				miner.getTransactionsPool().get(count).setConfirmationTime(eventTime);
				blockGaslimit += miner.getTransactionsPool().get(count).getUsedGas();
				
			}
			count += 1;
		}

		return transactions;

	}
	
	public static ArrayList<Transaction> executeTranscationsRaft(Node miner,Block block, double eventTime) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		
	
		double blockSize= block.getBlockSize();
		blockSizelimit = 0;
		int count = 0;


		miner.getTransactionsPool().sort((t1, t2) -> Double.compare(t1.getCreationTime(), t2.getCreationTime()));

		while (count < miner.getTransactionsPool().size()) {
			
			if (blockSize >= miner.getTransactionsPool().get(count).getTransactionSize()
					&& miner.getTransactionsPool().get(count).getCreationTime() <= eventTime) {
				blockSize  -= miner.getTransactionsPool().get(count).getTransactionSize();
				transactions.add(miner.getTransactionsPool().get(count));
				miner.getTransactionsPool().get(count).setConfirmationTime(eventTime);
				blockSizelimit += miner.getTransactionsPool().get(count).getTransactionSize();
			}
			count += 1;
		}

		return transactions;

	}

}
