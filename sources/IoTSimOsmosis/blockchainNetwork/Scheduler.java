package IoTSimOsmosis.blockchainNetwork;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author adelalbshri
 *
 */
public class Scheduler {
	
	/**
	 * 
	 * @param miner
	 * @param eventTime
	 */
	public static void createBlockEvent(Node miner, double eventTime) { 
		String eventType = "create_block";
		if (eventTime <= InputConfig.getSimTime()) {
			Block block = new Block();
			block.setBlockID(ThreadLocalRandom.current().nextLong(100000000000L));
			block.setBlockDepth(miner.getBlockchainLedger().size());
			block.setBlockTimestamp(eventTime);
			block.setMiner(miner);
			block.setPreviousBlockID(miner.getLastBlock().getBlockID());
			Event event = new Event(eventType, block.getMiner(), eventTime, block);
			Queue.addEvent(event);
		}
	
		}
	


/**
 * 
 * @param node
 * @param newBlock
 * @param blockDelay
 */
	public static void receiveBlockEvent(Node node, Block newBlock, double blockDelay) {
		String eventType = "receive_block";
		double receiveBlockTime = newBlock.getBlockTimestamp();
		if (receiveBlockTime <= InputConfig.getSimTime()) {
			updateTx(newBlock);
			//System.out.println ("====Event.getxList() be fore =======" + Event.getxList().size()); 
			Event event = new Event(eventType, node, receiveBlockTime, newBlock);
			Queue.addEvent(event);
		}
	}
	
	public static void updateTx(Block block) {
		
		int count=0;
		for (count=0 ;count <block.getTransactions().size(); count ++) {
			for (Node node :Node.getNodes()) {
				if(node.getNodeType().equals("leader") && node != block.getMiner()) {
					for (int i=0 ; i<node.getTransactionsPool().size();i++) {
						if(block.getTransactions().get(count)== node.getTransactionsPool().get(i)) {
							node.getTransactionsPool().remove(i);
						}
					}
				}
			}
		}
		
		
	}



	

}
