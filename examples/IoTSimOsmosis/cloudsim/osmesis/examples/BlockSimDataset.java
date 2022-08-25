package IoTSimOsmosis.cloudsim.osmesis.examples;

import IoTSimOsmosis.blockchainNetwork.BlockCommit;
import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.blockchainNetwork.Consensus;
import IoTSimOsmosis.blockchainNetwork.Event;
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
			BlockchainController.creatTransactionsWithoutIntegrated(); 
			
			// Create the gensis block for all miners
			Node.generateGenesisBlock();
			
			// Create Initial events
			BlockCommit.generateInitialEvents();

			double clock = 0; // set clock to 0 at the start of the simulation
			//&& (clock <= InputConfig.getSimTime())
			while (!Queue.isEmpty()) {
				Event nextEvent = Queue.getNextEvent();

				// Move clock to the time of the event
				clock = nextEvent.getTime();

				BlockCommit.handleEvent(nextEvent);
				Queue.removeEvent(nextEvent);
			}

			// Calculate the simulation results (e.g block statistics)
			Statistics.calculate(runCount);

			System.out.println("run complete");
			System.out.println("");
			BlockchainController.restState();
			
			
			
		}
		ExcelWriter.printToExcel();
	}




	
}
