package blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import IoTSimOsmosis.cloudsim.Vm;
import IoTSimOsmosis.cloudsim.edge.core.edge.MEL;
import IoTSimOsmosis.cloudsim.osmesis.examples.FireFightingStationAdel;
import IoTSimOsmosis.osmosis.core.EdgeSDNController;
import IoTSimOsmosis.osmosis.core.OsmesisBroker;
import IoTSimOsmosis.osmosis.core.OsmesisDatacenter;
import IoTSimOsmosis.osmosis.core.OsmosisBuilder;
import blockchainNetwork.BlockCmmit;
import blockchainNetwork.BlockchainController;
import blockchainNetwork.Statistics;
import blockchainNetwork.BlockCmmit;


public class mainTest {

	public static void main(String[] args) {
		
		double clock=0; //set clock to 0 at the start of the simulation
			//blockchainController = new BlockchainController();
			
			
			BlockchainController.generateNodes(); // Create blockchain nodes 
			//Consensus consensus = new Consensus(InputConfig.getConsensusalgorithm());


			//BlockchainController.generateMiner(); // Create blockchain miner
			//BlockchainController.creatTransactionsWithoutIntegrated(); // Create pending transactions without integrated IoT simulator   
			//Node.generateGenesisBlock(); // Create the gensis block for all miners
			//BlockCmmit.generateInitialEvents();
			
//			BlockCmmit.createBlock();
//			BlockCmmit.testNewArray();
//			BlockCmmit.testexploreBlock();
			
			
			//System.out.println(InputConfig.getMiners().size()); // need to delete 
			//System.out.println(InputConfig.getNODES().size());// need to delete 
			//InputConfig.getSimtime();
			
		//	while (!Queue.isEmpty() && (clock <=InputConfig.getSimtime() )) {
		//	Event nextEvent = Queue.getNextEvent(); 
				
				// Move clock to the time of the event
		//	clock = nextEvent.getTime();
		//	System.out.println (nextEvent.getTime());
		//		
				
		//	BlockCmmit.handleEvent(nextEvent);
			//Queue.removeEvent(nextEvent);

		//	}
			
			
			
			
			//System.out.println("here :"+Queue.getEventList().size());// need to delete 
			//System.out.println("here :"+InputConfig.getMiners().get(0).getBlockchain().get(0).getTransactions().get(0).getId());// need to delete
			//System.out.println("here :"+InputConfig.getMiners().get(0).getBlockchain().size());
			//System.out.println("here :"+InputConfig.getMiners().get(0).getTransactionsPool().getTransactionsPool().size());
			}// need to delete 
			
}
	
	

