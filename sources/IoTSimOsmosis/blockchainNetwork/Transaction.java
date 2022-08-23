package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author adelalbshri
 *
 */
public class Transaction {
	static ArrayList<Integer> poolcheck = new ArrayList<>();

	// pending transaction pool
	static ArrayList<Long> pool = new ArrayList<>();
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
	// A local variable to calculate (count) the remaining limit of Block gas used
	static double limit = 0;
	// A local variable to calculate (count) the remaining limit of Block size used
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
	public Transaction(double creationTime, double txSize, int fromAddress, int toAddress) {
		this.transactionID = ThreadLocalRandom.current().nextLong(10000000000L);
		this.creationTime = creationTime;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.transactionGasLimit = InputConfig.getTransactionGaslimit();// 100;//50; //8000000;
		this.usedGas = getRandomNumber(0, (int) transactionGasLimit);
		this.transactionSize = txSize;

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
		return limit;
	}

	/*
	 * Remaining limit of Block gas used
	 */

	public static double getBlockSizeLimit() {
		return blockSizelimit;
	}
	
	

	public static ArrayList<Long> getPool() {
		return pool;
	}
	
	public static ArrayList<Integer> getPoolcheck() {
		return poolcheck;
	}

	

	public static ArrayList<Transaction> executeTranscationsB(Node miner, double eventTime) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		System.out.println("Pool size : " + pool.size());

		

		limit = 0;
		blockSizelimit = 0;
		int count = 0;
		double blockGaslimit = InputConfig.getBlockGasLimit();
		double blocksize = InputConfig.getMaxblocksize();
		System.out.println("pool size : " + pool.size());
		miner.getTransactionsPool().sort((t1, t2) -> Double.compare(t2.getUsedGas(), t1.getUsedGas()));
		//System.out.println("Miner iD : " + miner.getNodeId() + "Miner size : " + miner.getTransactionsPool().size() );
		while (count < miner.getTransactionsPool().size()) {
			
			if (blockGaslimit >= miner.getTransactionsPool().get(count).getTransactionGasLimit()
					&& miner.getTransactionsPool().get(count).getCreationTime() <= eventTime
					&& blocksize >= miner.getTransactionsPool().get(count).getTransactionSize()) {
				blockGaslimit -= miner.getTransactionsPool().get(count).getUsedGas();
				blocksize -= miner.getTransactionsPool().get(count).getTransactionSize();
				//if(!Event.getxList().contains(miner.getTransactionsPool().get(count).getTransactionID())) {
				transactions.add(miner.getTransactionsPool().get(count));
				//pool.add(miner.getTransactionsPool().get(count).transactionID);
				//}
				
				limit += miner.getTransactionsPool().get(count).getUsedGas();
				blockSizelimit += miner.getTransactionsPool().get(count).getTransactionSize();
			}
			count += 1;
		}
		//System.out.println("transactions size" + transactions.size());
		return transactions;
		
	}

}
