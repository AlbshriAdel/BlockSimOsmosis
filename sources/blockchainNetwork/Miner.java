package blockchainNetwork;

public class Miner extends Node {

	// 
	private TransactionsPool transactionsPool;

	
	/**
	 * A constructor method for miner which it extends from node class
	 * @param nodeID
	 * @param nodeType
	 * @param hashRate2
	 */
	public Miner(int nodeID, String nodeType) {
		super(nodeID, nodeType);
		transactionsPool = TransactionsPool.getInstance();

	}
	
	public TransactionsPool getTransactionsPool() {
		return transactionsPool;
	}
	


}
