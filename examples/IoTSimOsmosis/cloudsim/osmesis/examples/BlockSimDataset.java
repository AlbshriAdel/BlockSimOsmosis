package IoTSimOsmosis.cloudsim.osmesis.examples;

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

public class BlockSimDataset {

	public static void main(String[] args) {

		new BlockSimDataset().run();
	}

	public void run() {
		
		for (int runCount = 0; runCount < InputConfig.getSimulatorRun(); runCount++) {

			
			
			
			
			// Create blockchain nodes
			BlockchainController.generateNodes();
			
			// Create pending transactions without integrated
			//BlockchainController.creatTransactionsWithoutIntegrated(); 
			BlockchainController.creatTransactions();
			
			// Create the gensis block for all miners
			Node.generateGenesisBlock();
			
			// Create Initial events
			BlockCommit.generateInitialEvents();
			
//			for (Node node : Node.getNodes()) {
//				System.out.println("Node ID :" + node.getNodeId() +"\n"+
//									"Node type : " + node.getNodeType() +"\n"+
//									"hash power : " + node.getHashPower());
//				//System.out.println("protocal PoW : " + Consensus.protocalPoW(node));
//				//}
//			}
			

			double clock = 0; // set clock to 0 at the start of the simulation
			//&& (clock <= InputConfig.getSimTime())
			while (!Queue.isEmpty()&& (clock <= InputConfig.getSimulationTime())) {
				Event nextEvent = Queue.getNextEvent();

				// Move clock to the time of the event
				clock = nextEvent.getTime();

				BlockCommit.handleEvent(nextEvent);
				Queue.removeEvent(nextEvent);
			}
			
			Consensus.fork();
			
//			System.out.println("Global Blockchain : " + Consensus.getGlobalBlockchain().size());
//			for (Block block :Consensus.getGlobalBlockchain()) {
//				System.out.println( "Block ID : "  + block.getBlockID() +"\n"+
//						"Block Pre ID : " + block.getPreviousBlockID());
//				
//			}
			// Calculate the simulation results (e.g block statistics)
			Statistics.calculate(runCount);

			System.out.println("run complete");
			System.out.println("");
			
			//BlockchainController.restState();
			
			
			
		}
		ExcelWriter.printToExcel();
		
	}




	
}
