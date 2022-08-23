package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.List;

public class Block {
	// Block ID for each block created
	private long blockID;
	// The previous block ID
	private long previousBlockID;
	// the index of block in the local BCL
	private int blockDepth;
	// the time when the block is created
	private double blockTimestamp;
	// the time when the block is add received
	private double blockReceivedTime;
	// Miner node who mined (created) the block
	private Node miner;
	// list of transaction that included in the block
	private ArrayList<Transaction> transactions;
	// The block size
	private double blockSize;
	// The block gas limit
	private double blockGasLimit;
	// The block used gas limit
	private double blockUsedGas;
	// The number of transaction in a block
	private int numberTX;
	// is block include transaction?
	private boolean hasTX;

	/**
	 * A constructor method for block class
	 */
	public Block() {
		this.blockID = 0;
		this.previousBlockID = -1;
		this.blockDepth = 0;
		this.blockTimestamp = 0.0;
		this.blockReceivedTime=0.0;
		this.miner = Node.getNodes().get(0); // need to change just for test
		this.transactions = new ArrayList<>();
		this.blockSize = InputConfig.getMaxblocksize(); // 1 MB
		this.blockGasLimit = InputConfig.getBlockGasLimit(); // 8000000
		this.blockUsedGas = 0;
		this.hasTX = false;
		this.numberTX = 0;

	}

	/**
	 * Return block ID
	 * 
	 * @return depth
	 */
	public long getBlockID() {
		return blockID;
	}

	/**
	 * Return Previous BlocK ID
	 * 
	 * @return previousBlocKID
	 */
	public long getPreviousBlockID() {
		return previousBlockID;
	}

	/**
	 * Return block depth which pointer the index of block in the local BCL
	 * 
	 * @return depth
	 */
	public int getBlockDepth() {
		return blockDepth;
	}

	/**
	 * Return block Timestamp
	 * 
	 * @return blockTimestamp
	 */
	public double getBlockTimestamp() {
		return blockTimestamp;
	}
	

	
	/**
	 * Return block Received Time
	 * 
	 * @return blockTimestamp
	 */
	public double getBlockReceivedTime() {
		return blockReceivedTime;
	}
	
	
	/**
	 * Return miner who created the block
	 * 
	 * @return miner
	 */
	public Node getMiner() {
		return miner;
	}

	/**
	 * Return transactions that included in the block
	 * 
	 * @return transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Return block size
	 * 
	 * @return blockSize
	 */
	public double getBlockSize() {
		return blockSize;
	}

	/**
	 * Return block gas Limit
	 * 
	 * @return blockGasLimit
	 */
	public double getBlockGasLimit() {
		return blockGasLimit;
	}

	/**
	 * Return block used gas
	 * 
	 * @return usedGas
	 */
	public double getBlockUsedGas() {
		return blockUsedGas;
	}

	/**
	 * Return number of transactions that included in the block
	 * 
	 * @return numberTX
	 */
	public int getNumberTx() {
		return this.numberTX;
	}

	/**
	 * Return boolean if block includes transactions
	 * 
	 * @return hasTX
	 */
	public boolean getHasTx() {
		return this.hasTX;
	}

	/**
	 * Set block ID
	 * 
	 * @param id
	 */
	public void setBlockID(long id) {
		this.blockID = id;
	}

	/**
	 * Set Previous Block ID
	 * 
	 * @param previous
	 */
	public void setPreviousBlockID(long previousID) {
		this.previousBlockID = previousID;
	}

	/**
	 * Set block depth
	 * 
	 * @param depth
	 */
	public void setBlockDepth(int depth) {
		this.blockDepth = depth;
	}

	/**
	 * set the miner who created the block
	 * 
	 * @param miner
	 */
	public void setMiner(Node miner) {
		this.miner = miner;
	}

	/**
	 * set the transactions that included to the block
	 * 
	 * @param transactions
	 */
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * set block size
	 * 
	 * @param size
	 */
	public void setBlockSize(double size) {
		this.blockSize = size;
	}

	/**
	 * set block limit
	 * 
	 * @param blockLimit
	 */
	public void setBlockLimit(double blockLimit) {
		this.blockGasLimit = blockLimit;
	}

	/**
	 * set block used gas
	 * 
	 * @param usedGas
	 */
	public void setBlockUsedGas(double usedGas) {
		this.blockUsedGas = usedGas;
	}

	/**
	 * set block Timestamp
	 * 
	 * @param timestamp
	 */
	public void setBlockTimestamp(double timestamp) {
		this.blockTimestamp = timestamp;
	}
	
	
	
	/**
	 * set block received time
	 * 
	 * @param timestamp
	 */
	public void setBlockReceivedTime(double timestamp) {
		this.blockReceivedTime = timestamp;
	}

	/**
	 * set block has Tx
	 * 
	 * @param timestamp
	 */
	public void setHasTx(boolean hasTX) {
		this.hasTX = hasTX;
	}

	/**
	 * set block number of tx that include in a block
	 * 
	 * @param timestamp
	 */
	public void setNumberTx(int numberTX) {
		this.numberTX = numberTX;
	}

}
