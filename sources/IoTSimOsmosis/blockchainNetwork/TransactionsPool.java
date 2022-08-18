package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TransactionsPool {

	// a transactionsPool from transaction class
	private static List<Transaction> transactionsPool = Collections.synchronizedList(new ArrayList<Transaction>());

	//
	private static TransactionsPool INSTANCE;

	/**
	 * Return instance of transactions pool
	 * 
	 * @return instance of TransactionsPool
	 */
	public static TransactionsPool getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TransactionsPool();
			transactionsPool = new ArrayList<Transaction>();
		}

		return INSTANCE;
	}

	/**
	 * Return Transactions Pool
	 * 
	 * @return List<Transaction>
	 */
	public List<Transaction> getTransactionsPool() {

		return transactionsPool;
	}

	/**
	 * to sort TransactionsPool
	 */
	public void sortTransactionsPool() {
		// System.out.println("Transactions Pool before descending order"+
		// this.transactionsPool);

		// Collections.sort(this.transactionsPool, Collections.reverseOrder());
		// transactionsPool.sort((t1, t2) -> Double.compare(t1.getCreationTime(),
		// t2.getCreationTime()));
		transactionsPool.sort((t1, t2) -> Double.compare(t2.getUsedGas(), t1.getUsedGas()));
	}
}
