package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import IoTSimOsmosis.blockchainNetwork.Scheduler;

//import Simulator.Statistics;
//
public class BlockCommit {

	// done
	public static void generateInitialEvents() {
		// System.out.println("####Generate Initial Events####");
		int currentTime = 0;

		generateNextBlock(Consensus.getAassignLeader(), currentTime);

	}

	private static void generateNextBlock(Miner miner, double currentTime) {
		double blockTime = currentTime + Consensus.generateNextBlockDelay();//0.555; // time when miner x generate the next block
		//System.out.println("Block time " + blockTime + "current time : " + currentTime);
		Scheduler.createBlockEvent(miner, blockTime);

	}

	public static void handleEvent(Event event) {
		if (event.getType() == "create_block"
				&& event.getMinerId().getTransactionsPool().getTransactionsPool().size() > 0) {
			toGenerateBlock(event);

		} else if (event.getType() == "receive_block") {
			receiveBlock(event);
		}
	}

	static void toGenerateBlock(Event event) {

		Miner miner = Consensus.getAassignLeader();
		int minerID = miner.getNodeId();
		double eventTime = event.getTime();
		long blockPrevious = event.getBlock().getPreviousBlocKID();
		if (blockPrevious == miner.getLastBlock().getBlockID()) {
			event.getBlock().setTransactions(Transaction.executeTranscationsB(miner, eventTime));
			event.getBlock().setNumberTx(event.getBlock().getTransactions().size());
			if (event.getBlock().getTransactions().size() > 0) {
				event.getBlock().setHasTx(true);
				event.getBlock().setBlockSize(Transaction.getBlockSizeLimit());
				event.getBlock().setUsedGas(Transaction.getLimit());
				miner.getBlockchainLedger().add(event.getBlock());
				updateTransactionsPool(miner, event.getBlock());
				propagate_block(event.getBlock());
				
				
			}
			
			
			
			
			generateNextBlock(miner, eventTime);

		}
	}

	private static void propagate_block(Block newBlock) {
		for (int i = 0; i < Node.getNodes().size(); i++) {
			double blockDealy = BlockchainController.blockPropagatingDelay();
			System.out.println(blockDealy);
			Scheduler.receiveBlockEvent(Node.getNodes().get(i), newBlock, blockDealy);
		}

	}

	private static void receiveBlock(Event event) {

		Miner miner = event.getBlock().getMiner();
		double eventTime = event.getTime();
		long blockPrevious = event.getBlock().getPreviousBlocKID();

		Node node = event.getNodeId();
		

		long lastBlockID = node.getLastBlock().getBlockID();

		if (blockPrevious == lastBlockID) {
			node.getBlockchainLedger().add(event.getBlock());
			updateTransactionsPool(miner, event.getBlock());
			generateNextBlock(miner, eventTime);
		} else {
			int depth = event.getBlock().getBlockDepth() + 1;
			if (depth > node.getBlockchainLedger().size()) {
				updateLocalBlockchainLedger(node, miner, depth);
				generateNextBlock(miner, eventTime);


			}

		}

	}

	private static void updateLocalBlockchainLedger(Node node, Miner miner, int depth) {
		int i = 0;
		while (i < depth) {
			if (i < node.getBlockchainLedger().size()) {
				
				if (node.getBlockchainLedger().get(i).getBlockID() != node.getBlockchainLedger().get(i).getBlockID()) {
					System.out.println("updateLocalBlockchainLedger");
					Block newBlock = miner.getBlockchainLedger().get(i);
					node.getBlockchainLedger().add(newBlock);

				}

			}

		}
		i+=1;

	}

	private static void updateTransactionsPool(Miner miner, Block block) {
		int i = 0;
		while (i < (block.getTransactions().size())) {
			for (int count = 0; count < miner.getTransactionsPool().getTransactionsPool().size(); count++) {

				if (block.getTransactions().get(i).getTransactionID() == miner.getTransactionsPool()
						.getTransactionsPool().get(count).getTransactionID()) {
					block.getTransactions().get(i).setConfirmationTime(block.getBlockTimestamp());
					miner.getTransactionsPool().getTransactionsPool().remove(count);
//					System.out.println("=> Transacation ID : [" + block.getTransactions().get(i).getTransactionID()
//							+ "] has been deleted from Transactions Pool ");
				}
			}
			i += 1;
		}

	}

}
