package blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

//
//
//
//
//import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;
//import Simulator.Queue;
//import Simulator.Event;
//import Simulator.InputConfig;
import blockchainNetwork.Scheduler;

//import Simulator.Statistics;
//
public class BlockCmmit {

	// done
	public static void generateInitialEvents() {
		System.out.println("####Generate Initial Events####");
		int currentTime = 0;

		generateNextBlock(Consensus.getAassignLeader(), currentTime);

	}

	private static void generateNextBlock(Miner miner, double currentTime) {
		double blockTime = currentTime + 0.555; // time when miner x generate the next block
		Scheduler.createBlockEvent(miner, blockTime);

	}

		public static void handleEvent(Event event) {
			if (event.getType() == "create_block") {
				toGenerateBlock(event);
				
			}else if (event.getType() =="receive_block") {
				BlockCmmit.receiveBlock(event);
		}
			}
		

		static void toGenerateBlock(Event event) {
			
			Miner miner= Consensus.getAassignLeader();
			int minerID= miner.getNodeId();
			double eventTime=event.getTime();
			long blockPrevious=event.getBlock().getPreviousBlocKID();
			
			Transaction.executeTranscationsB(miner,eventTime);
			System.out.println("Test get executeTranscationsB " + Transaction.getTransactions().size());
			//event.getBlock().getTransactions().addAll(Transaction.getTransactions());
			event.getBlock().setTransactions(Transaction.getTransactions());
			event.getBlock().setUsedGas(Transaction.getLimit());
			System.out.println("Test get block " + event.getBlock().getTransactions().size());
			System.out.println("Test block ID " + event.getBlock().getBlockID());
			System.out.println("Test blockPreviou  " + event.getBlock().getPreviousBlocKID());
			System.out.println("Test block limit " + event.getBlock().getBlockGasLimit());
			
//			System.out.println("Test tx ID " + event.getBlock().getTransactions().get(0).transactionID());
//			System.out.println("Test tx usedGas " + event.getBlock().getTransactions().get(0).getUsedGas());
//			System.out.println("Test tx ID " + event.getBlock().getTransactions().get(1).transactionID());
//			System.out.println("Test tx usedGas " + event.getBlock().getTransactions().get(1).getUsedGas());
//			System.out.println("Test tx ID " + event.getBlock().getTransactions().get(2).transactionID());
//			System.out.println("Test tx usedGas " + event.getBlock().getTransactions().get(2).getUsedGas());
//			System.out.println("Block number " + miner.getBlockchainLedger().size());
		     miner.getBlockchainLedger().add(event.getBlock());
		     System.out.println("Block number " + miner.getBlockchainLedger().size());

		}
		
		private static void receiveBlock(Event event){
			

		 }

//		
//		static void testNewArray() {
//			for(int i=0 ; i< transactions.size(); i++) {
//				System.out.println("[test tx new 1] transcation ID : " + transactions.get(i).getId() );
//				System.out.println("[test tx new 2] transcation time : " + transactions.get(i).getCreationTime() );
//				System.out.println("[test tx new 3] transcation gas price : " + transactions.get(i).getGasPrice());
//				
//			}
//		}
//			
//		
//	//	 Miner miner = InputConfig.getMiners().get(0);
//	//	 for(int i=0 ; i< miner.getTransactionsPool().getTransactionsPool().size(); i++) {
//	//		System.out.println("[test tx] transcation ID : " + miner.getTransactionsPool().getTransactionsPool().get(i).getId() );
//	//		System.out.println("[test tx] transcation time : " + miner.getTransactionsPool().getTransactionsPool().get(i).getCreationTime() );
//	//		System.out.println("[test tx] transcation gas price : " + miner.getTransactionsPool().getTransactionsPool().get(i).getGasPrice());
//		
//			
//	
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		

//		
//

}
