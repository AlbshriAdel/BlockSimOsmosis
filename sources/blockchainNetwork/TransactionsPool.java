package blockchainNetwork;

import java.util.ArrayList;
import java.util.Collections;
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
		Collections.sort(this.transactionsPool, Collections.reverseOrder());
		// System.out.println("Transactions Pool before descending order"+
		// this.transactionsPool);

	}

}
