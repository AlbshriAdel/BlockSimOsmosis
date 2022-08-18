package IoTSimOsmosis.cloudsim.osmesis.examples;

import IoTSimOsmosis.
import blockchainNetwork.Block;
import blockchainNetwork.BlockCmmit;
import blockchainNetwork.BlockchainController;
import blockchainNetwork.Event;
import blockchainNetwork.ExcelWriter;
import blockchainNetwork.InputConfig;
import blockchainNetwork.Miner;
import blockchainNetwork.Node;
import blockchainNetwork.Queue;
import blockchainNetwork.Statistics;
import blockchainNetwork.Transaction;

public class BlockchainSim {

	double clock = 0; // set clock to 0 at the start of the simulation
	// blockchainController = new BlockchainController(); // //

	BlockchainController.generateNodes(); // Create blockchain nodes
	BlockchainController.creatTransactionsWithoutIntegrated(); // Create pending transactions without integrated IoT
																// simulator
	Node.generateGenesisBlock(); // Create the gensis block for all miners
	BlockCmmit.generateInitialEvents();


	while (!Queue.isEmpty() && (clock <= InputConfig.getSimTime())) {
		Event nextEvent = Queue.getNextEvent();


		// Move clock to the time of the event
		clock = nextEvent.getTime();
		// System.out.println (nextEvent.getTime());

		BlockCmmit.handleEvent(nextEvent);
		Queue.removeEvent(nextEvent);

	}




	
	Statistics.calculate();
	ExcelWriter.printToExcel();
}

}
