package IoTSimOsmosis.cloudsim.osmesis.examples;

import java.util.Iterator;

import IoTSimOsmosis.blockchainNetwork.Block;
import IoTSimOsmosis.blockchainNetwork.BlockCommit;
import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.blockchainNetwork.Consensus;
import IoTSimOsmosis.blockchainNetwork.Event;
import IoTSimOsmosis.blockchainNetwork.Excel;
import IoTSimOsmosis.blockchainNetwork.ExcelWriter;
import IoTSimOsmosis.blockchainNetwork.InputConfig;
import IoTSimOsmosis.blockchainNetwork.Node;
import IoTSimOsmosis.blockchainNetwork.Queue;
import IoTSimOsmosis.blockchainNetwork.Statistics;
import IoTSimOsmosis.blockchainNetwork.Transaction;

public class BlockSim {

	public static void main(String[] args) {
		int i=0;
		for (i=0; i<5 ; i++) {
		BlockchainController.generateNodes(); // Create blockchain nodes
		
		
		System.out.println("===============[round" +  Consensus.protocalPoW() +"]=================");
		System.out.println("===============[round" +  Consensus.protocal() +"]=================");
			for (Node node : Node.getNodes()) {
				Excel.getDf3().add(new Object[] {i,node.getNodeId(),node.getNodeType()});
				//if (node.getNodeType().equals("miner")) {
				System.out.println("Node ID :" + node.getNodeId() +"\n"+
									"Node type : " + node.getNodeType() +"\n"+
									"hash power : " + node.getHashPower());
				//System.out.println("protocal PoW : " + Consensus.protocalPoW(node));
				//}
			}
			
			BlockchainController.restState();
		}
		Excel.printToExcel(i);
			
	}
//		BlockchainController.creatTransactionsWithoutIntegrated(); // Create pending transactions without integrated IoT simulator
//		Node.generateGenesisBlock();
//		BlockCommit.generateInitialEvents();// Create the gensis block for all miners
//
//
//		double clock = 0; // set clock to 0 at the start of the simulation
//
//		while (!Queue.isEmpty()&& (clock <= InputConfig.getSimTime())) {
//			Event nextEvent = Queue.getNextEvent();
//
//			// Move clock to the time of the event
//			clock = nextEvent.getTime();
//  
//			BlockCommit.handleEvent(nextEvent);
//			Queue.removeEvent(nextEvent);
//
//		}
//Statistics.calculate();
//ExcelWriter.printToExcel();


//System.out.println("===============[main]=================");
//for (int a = 0; a < Node.getNodes().size(); a++) {
//for (int i = 0; i < Node.getNodes().get(a).getBlockchainLedger().size(); i++) {
//System.out.println("===============block " + i + "==================");
//System.out.println("Node ID : "+Node.getNodes().get(a).getNodeId()+ "\n"+
//		"Block ID : " + Node.getNodes().get(a).getBlockchainLedger().get(a).getBlockID()
//		+ "\n" + "Block Per ID : "
//		+ Node.getNodes().get(a).getBlockchainLedger().get(i).getPreviousBlockID() + "\n"
//		+ "Block time : " + Node.getNodes().get(a).getBlockchainLedger().get(i).getBlockTimestamp()
//		+ "\n" + "Block depth : "
//		+ Node.getNodes().get(a).getBlockchainLedger().get(i).getBlockDepth() + "\n"
//		+ "Block size used : " + Node.getNodes().get(a).getBlockchainLedger().get(i).getBlockSize()
//		+ "\n" + "Block gas used : "
//		+ Node.getNodes().get(a).getBlockchainLedger().get(i).getBlockUsedGas()
//		+ "\n" + "Mined by : "
//		+Node.getNodes().get(a).getBlockchainLedger().get(i).getMiner().getNodeId());
//
//
//
//	
//	System.out.println("Block includes : "
//			+ Node.getNodes().get(a).getBlockchainLedger().get(i).getNumberTx()
//			+ " transactions");
//
//	Iterator<Transaction> iterator = Node.getNodes().get(a).getBlockchainLedger().get(i)
//			.getTransactions().iterator();
//	while (iterator.hasNext()) {
//
//		Transaction transaction = iterator.next();
//		System.out.println("Transaction ID : " + transaction.getTransactionID() + "\n"
//				+ "Transaction time : " + transaction.getCreationTime() + "\n"
//				+ "Transaction confrimation time: " + transaction.getConfirmationTime() + "\n"
//				+ "Transaction gas used: " + transaction.getUsedGas());
//		// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(1).getTransactions().get(0).getTransactionID());
//		// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(2).getTransactions().get(0).getTransactionID());
//
//	}
//
//}





//		System.out.println("Queue size " + Queue.size());
		//Node.getNodes().get(0).getBlockchainLedger().add(new Block());
//		
//		System.out.println("===============[main]=================");
//		for (int a = 0; a < Node.getNodes().size(); a++) {

			
			

			
			
	
//	for (int a = 0; a < Node.getMiners().size(); a++) {
//	for (int i = 0; i < Node.getMiners().get(a).getBlockchainLedger().size(); i++) {
//	System.out.println("===============block " + i + "==================");
//	System.out.println("Node ID : "+Node.getMiners().get(a).getNodeId()+ "\n"+
//			"Block ID : " + Node.getMiners().get(a).getBlockchainLedger().get(a).getBlockID()
//			+ "\n" + "Block Per ID : "
//			+ Node.getMiners().get(a).getBlockchainLedger().get(i).getPreviousBlockID() + "\n"
//			+ "Block time : " + Node.getMiners().get(a).getBlockchainLedger().get(i).getBlockTimestamp()
//			+ "\n" + "Block depth : "
//			+ Node.getMiners().get(a).getBlockchainLedger().get(i).getBlockDepth() + "\n"
//			+ "Block size used : " + Node.getMiners().get(a).getBlockchainLedger().get(i).getBlockSize()
//			+ "\n" + "Block gas used : "
//			+ Node.getMiners().get(a).getBlockchainLedger().get(i).getBlockUsedGas()
//			+ "\n" + "Mined by : "
//			+Node.getMiners().get(a).getBlockchainLedger().get(i).getMiner().getNodeId());
//	}
//	}
//	if (Node.getMiners().get(a).getBlockchainLedger().get(i).getHasTx() == true) {
//		
//		System.out.println("Block includes : "
//				+ Node.getMiners().get(a).getBlockchainLedger().get(i).getNumberTx()
//				+ " transactions");
//
//		Iterator<Transaction> iterator = Node.getMiners().get(a).getBlockchainLedger().get(i)
//				.getTransactions().iterator();
//		while (iterator.hasNext()) {
//
//			Transaction transaction = iterator.next();
//			System.out.println("Transaction ID : " + transaction.getTransactionID() + "\n"
//					+ "Transaction time : " + transaction.getCreationTime() + "\n"
//					+ "Transaction confrimation time: " + transaction.getConfirmationTime() + "\n"
//					+ "Transaction gas used: " + transaction.getUsedGas());
//			// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(1).getTransactions().get(0).getTransactionID());
//			// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(2).getTransactions().get(0).getTransactionID());
//
//		}
//
//	}
//}
//	}
//}
}
	
