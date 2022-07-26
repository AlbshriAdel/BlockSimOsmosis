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
		int currentTime=0;
			//for (Miner miner : InputConfig.getMiners()) {
		
					Scheduler.createBlockEvent(InputConfig.getMiners().get(0), currentTime);
					//System.out.println("=> An event has been created successfully [" + miner.getId() + "], node type is [" + miner.getNodeType() + "] at [" + currentTime + "]");
			//	}
			}

		
//		public static void handleEvent(Event event) {
//			if (event.getType() == "create_block") {
//				//BlockCmmit.createBlock(event);
//			}else if (event.getType() =="receive_block") {
//				BlockCmmit.receiveBlock(event);
//		}
//			}
//		
//		
//		//private static void createBlock(Event event) {
//		static Event event;
//		static void createBlock() {
//			
//		static void testexploreBlock() {
//			for(int i=0 ; i<InputConfig.getMiners().get(0).getBlockchain().size() ; i++) {
//				System.out.println("[test block new ] block ID : " + InputConfig.getMiners().get(0).getBlockchain().get(i).getId() );
//				System.out.println("[test block new 2] block time : " + InputConfig.getMiners().get(0).getBlockchain().get(i).getSize());
//				System.out.println("[test block new 3] block gas price : " + InputConfig.getMiners().get(0).getBlockchain().get(i).getTransactions().size());
//				
//			}
//		}
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
//		private static void generateNextBlock(Miner miner, double d) {
//			Scheduler.createBlockEvent(miner, d);
//			
//			
//			
//		}
//		
//
//	private static void receiveBlock(Event event){
//		
//
// }
	}
