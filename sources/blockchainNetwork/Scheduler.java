package blockchainNetwork;

import java.util.concurrent.ThreadLocalRandom;
import blockchainNetwork.InputConfig;

public class Scheduler {
	
	public static void createBlockEvent(Miner miner, double eventTime) { 
		String eventType = "create_block";
		if (eventTime <= InputConfig.getSimTime()) {
			// Populate event attributes
			Block block = new Block();
			block.setBlockID(ThreadLocalRandom.current().nextLong(100000000000L));
			block.setDepth(miner.getBlockchainLedger().size());
			block.setBlockTimestamp(eventTime);
			block.setMiner(miner);
			block.setPreviousBlockID(miner.getLastBlock().getBlockID());
			
			
			
			// Create the event
			Event event = new Event(eventType, miner.getNodeId(), eventTime, block);
			// Append the event to the event list
			Queue.addEvent(event);
			
			System.out.println("Queue size is :["+ Queue.size() + "] ,event type is : [" + event.getType() + "] ,event time is : [" + event.getTime() +"] and block ID is [" + block.getBlockID()+ "]."+
			"Block time ["+block.getBlockTimestamp() +"]" + "Block depth ["+block.getBlockDepth() +"]"+"Block Previous ["+ block.getPreviousBlocKID() +"].");
			
			
			//System.out.println("Miner id :"+event.getNodeId()+",event time :"+ event.getTime()+",event block id :"+event.getBlock().getId() + ", node type : " + miner.getNodeType());
			//System.out.println("Block :"+ miner.getBlockchain().size());
		}
		System.out.println("Final Queue size is : ["+Queue.size() +"].");
	}
	

	
// Schedule a block receiving event for a node and append to event list
	public static void receiveBlockEvent(Miner recipient, Block block, double blockDelay) {
		String eventType = "receive_block";
		double receiveBlockTime = block.getBlockTimestamp() + blockDelay;
		if (receiveBlockTime <= InputConfig.getSimTime()) {
			Event event = new Event(eventType, recipient.getNodeId(), receiveBlockTime, block);
			Queue.addEvent(event);
		}
	}


	

}
