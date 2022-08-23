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
	static void toGenerateBlock(Event event) {
		Node miner = event.getBlock().getMiner();
		double eventTime = event.getTime();
		long blockPrevious = event.getBlock().getPreviousBlockID();
		
		if (blockPrevious == miner.getLastBlock().getBlockID()) {
			event.getBlock().setTransactions(Transaction.executeTranscationsB(miner, eventTime));
			for (int i=0 ; i<event.getBlock().getTransactions().size(); i++) {
				System.out.println("Node ID: " + event.getBlock().getTransactions().get(i).getTransactionID());
			}
			event.getBlock().setBlockSize(Transaction.getBlockSizeLimit());
			if (event.getBlock().getTransactions().size() > 0) {
				event.getBlock().setBlockUsedGas(Transaction.getLimit());
				event.getBlock().setBlockReceivedTime(eventTime);
				miner.getBlockchainLedger().add(event.getBlock());
				event.getBlock().setBlockReceivedTime(eventTime);
				propagateBlock(event.getBlock());
				//updateTransactionsPool(node, event.getBlock());
			}
			
			generateNextBlock(miner, eventTime);
		}
	}

	/**
	 * 
	 * @param newBlock
	 */
	private static void propagateBlock(Block newBlock) {
		double blockDealy = BlockchainController.blockPropagatingDelay();
		for (Node miner : Node.getNodes()) {
			if (newBlock.getMiner().getNodeId() != miner.getNodeId()) {
	
				Scheduler.receiveBlockEvent(miner, newBlock, blockDealy);
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
			event.getBlock().setBlockReceivedTime(eventTime);
			updateTransactionsPool(miner, event.getBlock());
			generateNextBlock(node, eventTime);
		} else {
			int depth = event.getBlock().getBlockDepth() + 1;
			if (depth > node.getBlockchainLedger().size()) {
				updateLocalBlockchainLedger(node, miner, depth, event);
				generateNextBlock(node, eventTime);
			} else {
				updateTransactionsPool(miner, event.getBlock());
			}
		}
	}

	/**
	 * 
	 * @param node
	 * @param miner
	 * @param depth
	 */
	private static void updateLocalBlockchainLedger(Node node, Node miner, int depth, Event eventTime) {
		for (int i = 0; i < depth; i++) {
			if (i < node.getBlockchainLedger().size()) {
				if (node.getBlockchainLedger().get(i).getBlockID() != miner.getBlockchainLedger().get(i).getBlockID()) {
					Block newBlock = miner.getBlockchainLedger().get(i);
						node.getBlockchainLedger().add(newBlock);
						updateTransactionsPool(miner, newBlock);
					
				} else {
					Block block = miner.getBlockchainLedger().get(i);
					node.getBlockchainLedger().add(block);
					block.setBlockReceivedTime(eventTime.getTime());
					updateTransactionsPool(miner, block);
				}
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
					block.getTransactions().get(i).setConfirmationTime(block.getBlockTimestamp());
					System.out.println("Tx size :"+node.getTransactionsPool().size());
					node.getTransactionsPool().remove(count);
					System.out.println("here");
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
			if (miner.getNodeType().equals("leader")) {
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
			double blockTime = currentTime + Consensus.generateNextBlockDelay();// 0.555; // time when miner x generate
			Scheduler.createBlockEvent(miner, blockTime);
		}
	}

	

	/**
	 * 
	 * 
	 */
	public static void handleEvent(Event event) {
		if (event.getType() == "create_block") {
			toGenerateBlock(event);

		} else if (event.getType() == "receive_block") {
			receiveBlock(event);
		}
	}

}
