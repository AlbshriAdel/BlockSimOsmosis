package IoTSimOsmosis.blockchainNetwork;

/**
 * 
 * @author adelalbshri
 *
 */

public class BlockCommit {

	/**
	 * 
	 * @param event
	 */
	static void generateNewBlock(Event event) {
		Node miner = event.getBlock().getMiner();
		double eventTime = event.getTime();
		long blockPrevious = event.getBlock().getPreviousBlockID();

		if (blockPrevious == miner.getLastBlock().getBlockID()) {
			if (InputConfig.getConsensusalgorithm() == "PoW") {
				event.getBlock().setTransactions(Transaction.executeTranscationsPoW(miner, event.getBlock(), eventTime));
				event.getBlock().setBlockGas(Transaction.blockGaslimit);
			} else if (InputConfig.getConsensusalgorithm() == "raft") {
				event.getBlock().setTransactions(Transaction.executeTranscationsRaft(miner,event.getBlock(), eventTime));
				event.getBlock().setBlockSize(Transaction.getBlockSizeLimit());
			}

			if (event.getBlock().getTransactions().size() > 0) {
				event.getBlock().setHasTx(true);
			//}

			miner.getBlockchainLedger().add(event.getBlock());
			propagateBlock(event.getBlock());
			
		}}
		generateNextBlock(miner, eventTime);

		

	}

	/**
	 * 
	 * @param newBlock
	 */
	private static void propagateBlock(Block newBlock) {
		for (Node miner : Node.getNodes()) {
			if (newBlock.getMiner().getNodeId() != miner.getNodeId()) {

				Scheduler.receiveBlockEvent(miner, newBlock);
			}
		}
	}

	/**
	 * 
	 * @param event
	 */
	private static void receiveBlock(Event event) {
		Node miner = event.getBlock().getMiner();
		double eventTime = event.getTime();
		long blockPrevious = event.getBlock().getPreviousBlockID();
		Node node = event.getMiner();
		long lastBlockID = node.getLastBlock().getBlockID();
		if (blockPrevious == lastBlockID) {
			node.getBlockchainLedger().add(event.getBlock());
			updateTransactionsPool(miner, event.getBlock()); // *************
			generateNextBlock(node, eventTime);
		} else {
			int depth = event.getBlock().getBlockDepth() + 1;
			if (depth > node.getBlockchainLedger().size()) {
				updateLocalBlockchainLedger(node, miner, depth);
				generateNextBlock(node, eventTime);
			} 
				//updateTransactionsPool(miner, event.getBlock()); // ***********
			
		}
	}

	/**
	 * 
	 * @param node
	 * @param miner
	 * @param depth
	 */
	private static void updateLocalBlockchainLedger(Node node, Node miner, int depth) {
		
		for (int i = 0; i < depth; i++) {

			if (i < node.getBlockchainLedger().size()) {
				if (node.getBlockchainLedger().get(i).getBlockID() != miner.getBlockchainLedger().get(i).getBlockID()) {
					Block newBlock = miner.getBlockchainLedger().get(i);
					node.getBlockchainLedger().add(newBlock);
					updateTransactionsPool(miner, newBlock); // ********

				}
			} else {
				Block block = miner.getBlockchainLedger().get(i);
				node.getBlockchainLedger().add(block);
				updateTransactionsPool(miner, block); // **********
			}
			
		}
	}

	/**
	 * 
	 * @param node
	 * @param block
	 */
	private static void updateTransactionsPool(Node node, Block block) {
		int i = 0;
		while (i < (block.getTransactions().size())) {
			for (int count = 0; count < node.getTransactionsPool().size(); count++) {
				if (block.getTransactions().get(i).getTransactionID() == node.getTransactionsPool().get(count)
						.getTransactionID()) {
					node.getTransactionsPool().remove(count);

				}
			}
			i += 1;
		}
	}

	/**
	 * 
	 * 
	 */
	public static void generateInitialEvents() {
		int currentTime = 0;

		for (Node miner : Node.getNodes()) {
			if (miner.getNodeType().equals("leader") || miner.getNodeType().equals("miner")) {
				generateNextBlock(miner, currentTime);
			}

		}
	}

	/**
	 * 
	 * 
	 */
	private static void generateNextBlock(Node miner, double currentTime) {
		if (miner.getNodeType().equals("leader")) {
			double blockTime = currentTime + Consensus.protocal();// 0.555; // time when miner x generate
			Scheduler.createBlockEvent(miner, blockTime);
		} else if (miner.getNodeType().equals("miner") /* && miner.getHashPower()>0 */) {
			double blockTime = currentTime + Consensus.protocalPoW(miner);// 0.555; // time when miner x generate
			Scheduler.createBlockEvent(miner, blockTime);
		}
	}

	/**
	 * 
	 * 
	 */
	public static void handleEvent(Event event) {
		if (event.getType() == "create_block") {
			generateNewBlock(event);

		} else if (event.getType() == "receive_block") {
			receiveBlock(event);
		}
	}

}
