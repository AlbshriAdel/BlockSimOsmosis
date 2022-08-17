package blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;

public class Block {
	// Block ID for each block created
	private long blockID;
	// The previous block ID
	private long previousBlocKID;
	// the index of block in the local BCL
	private int depth;
	// the time when the block is created
	private double blockTimestamp;
	// Miner node who mined (created) the block
	private Miner miner;
	// list of transaction that included in the block
	private ArrayList<Transaction> transactions;
	// The block size
	private double blockSize;
	// The block gas limit
	private double blockGasLimit;
	// The block used gas limit
	private double usedGas;
	//
	private int numberTX;
	
	private boolean hasTX;

	/**
	 * A constructor method for block class
	 */
	public Block() {
		this.blockID = 0;
		this.previousBlocKID = -1;
		this.depth = 0;
		this.blockTimestamp = 0.0;
		this.miner = null;
		this.transactions = new ArrayList<>();
		this.blockSize = InputConfig.getMaxblocksize(); // 1 MB
		this.blockGasLimit = InputConfig.getBlockGasLimit(); // 8000000
		this.usedGas = 0;
		this.hasTX=false;
		this.numberTX=0;

	}

	
	public boolean getHasTx() {
		return this.hasTX;
	}
	
	public void setHasTx(boolean hasTX) {
		this.hasTX = hasTX;
	}
	
	public int getNumberTx() {
		return this.numberTX;
	}
	
	public void setNumberTx(int numberTX) {
		this.numberTX = numberTX;
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
	public long getPreviousBlocKID() {
		return previousBlocKID;
	}

	/**
	 * Return block depth which pointer the index of block in the local BCL
	 * 
	 * @return depth
	 */
	public int getBlockDepth() {
		return depth;
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
	 * Return miner who created the block
	 * 
	 * @return miner
	 */
	public Miner getMiner() {
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
	public double getUsedGas() {
		return usedGas;
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
	public void setPreviousBlockID(long previous) {
		this.previousBlocKID = previous;
	}

	/**
	 * Set block depth
	 * 
	 * @param depth
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * set the miner who created the block
	 * 
	 * @param miner
	 */
	public void setMiner(Miner miner) {
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
	public void setUsedGas(double usedGas) {
		this.usedGas = usedGas;
	}

	/**
	 * set block Timestamp
	 * @param timestamp
	 */
	public void setBlockTimestamp(double timestamp) {
		this.blockTimestamp = timestamp;
	}

}
