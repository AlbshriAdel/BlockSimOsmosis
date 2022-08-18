package IoTSimOsmosis.cloudsim.osmesis.examples;

import IoTSimOsmosis.blockchainNetwork.BlockCommit;
import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.blockchainNetwork.Event;
import IoTSimOsmosis.blockchainNetwork.ExcelWriter;
import IoTSimOsmosis.blockchainNetwork.InputConfig;
import IoTSimOsmosis.blockchainNetwork.Node;
import IoTSimOsmosis.blockchainNetwork.Queue;
import IoTSimOsmosis.blockchainNetwork.Statistics;

public class BlockSim {

	public static void main(String[] args) {
		BlockchainController.generateNodes(); // Create blockchain nodes
		BlockchainController.creatTransactionsWithoutIntegrated(); // Create pending transactions without integrated IoT simulator
		Node.generateGenesisBlock(); // Create the gensis block for all miners
		BlockCommit.generateInitialEvents();

		double clock = 0; // set clock to 0 at the start of the simulation
		while (!Queue.isEmpty() && (clock <= InputConfig.getSimTime())) {
			Event nextEvent = Queue.getNextEvent();

			// Move clock to the time of the event
			clock = nextEvent.getTime();

			BlockCommit.handleEvent(nextEvent);
			Queue.removeEvent(nextEvent);

		}

		Statistics.calculate();
		ExcelWriter.printToExcel();
	}

}
