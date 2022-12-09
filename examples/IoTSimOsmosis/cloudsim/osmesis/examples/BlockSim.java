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
	  for (int runCount = 1; runCount <= InputConfig.getSimulatorRun(); runCount++) {

	      // Create blockchain nodes
	      BlockchainController.generateNodes();


	      // Create pending transactions
	      BlockchainController.creatTransactions();



	      // Create the gensis block for all miners
	      Node.generateGenesisBlock();



	      // Create Initial events
	      BlockCommit.generateInitialEvents();

	      double clock = 0; // set clock to 0 at the start of the simulation

	      while (!Queue.isEmpty() && (clock <= InputConfig.getSimulationTime())) {
	        Event nextEvent = Queue.getNextEvent();

	        // Move clock to the time of the event
	        clock = nextEvent.getTime();

	        BlockCommit.handleEvent(nextEvent);
	        Queue.removeEvent(nextEvent);
	        
	      }



	      Statistics.calculate(runCount);
	      System.out.println("run complete, round [" + runCount + "].");
	      System.out.println("");
	      BlockchainController.restState();

	    }


	  }
}
	
	
