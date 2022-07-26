package blockchainNetwork;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Transaction implements Comparable<Transaction> {

	// transaction id
	private long transactionID = 0;
	// timestamp of transaction send from the IoT side
	private double creationTime;
	// timestamp of transaction inclusion in a confirmed block
	private double confirmationTime;
	// the node that generates the block.
	private Node miner;
	// transaction sender address
	private String fromAddress;
	// transaction receiver address
	private String toAddress;
	// transaction size
	private double transactionSize;
	// the amount of gas used by the transaction
	private double usedGas;
	// the maximum amount of gas units the transaction can use
	private double gasLimit;

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
	public Transaction(double creationTime, double txSize,String fromAddress,
			String toAddress) {
		this.transactionID = ThreadLocalRandom.current().nextLong(10000000000L);
		this.creationTime = creationTime;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.gasLimit = 8000000;
		this.usedGas = 0;
		this.transactionSize = txSize;
	}

	/**
	 * Return the transaction ID
	 * 
	 * @return transactionID
	 */
	public long transactionID() {
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
	public double getGasLimit() {
		return gasLimit;
	}

	/**
	 * To set creation time for each transaction
	 * @param creationTime
	 */
	public void setCreationTime(double creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * To set the amount of gas units that can use.
	 * @param usedGas
	 */
	public void setUsedGas(double usedGas) {
		this.usedGas = usedGas;
	}

	/**
	 * To set confirmation Time of transaction.
	 * @param confirmationTime
	 */
	public void setConfirmationTime(double confirmationTime) {
		this.confirmationTime = confirmationTime;
	}

	// check if we need it
	public static int ChoiceRandomSnderNode() {
		int NodeID = 0;
//	for( int i = 0;i<InputConfig.getNODES().size();i++)
//	{
//		if (InputConfig.getNODES().get(i).getNodeType() == "lightNode") {
		NodeID = getRandomNumber(0, InputConfig.getNumberOfNodes());

//		}
//	}
		return NodeID;
	}

	// check if we need it
	public static int ChoiceRandomReceiverNode() {
		int NodeID = 0;
//	for( int i = 0;i<InputConfig.getNODES().size();i++)
//	{
//		if (InputConfig.getNODES().get(i).getNodeType() == "lightNode") {
		NodeID = getRandomNumber(0, InputConfig.getNumberOfNodes());

//		}
//	}
		return NodeID;
	}

	@Override
	public int compareTo(Transaction transaction) {

		// Get the two double values
		// to be compared
		Double d1 = this.usedGas;
		Double d2 = transaction.usedGas;
		int result = 0;

		// function call to compare two double values
		if (Double.compare(d1, d2) == 0) {

			result = 0;
			// System.out.println("d1=d2");
		} else if (Double.compare(d1, d2) < 0) {

			result = -1;
			// System.out.println("d1<d2");
		} else {

			result = 1;
			// System.out.println("d1>d2");
		}
		return result;
	}

}
