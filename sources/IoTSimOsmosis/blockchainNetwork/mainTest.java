package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import IoTSimOsmosis.blockchainNetwork.BlockCommit;
import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.blockchainNetwork.Statistics;
import IoTSimOsmosis.cloudsim.Vm;
import IoTSimOsmosis.cloudsim.edge.core.edge.MEL;
import IoTSimOsmosis.osmosis.core.EdgeSDNController;
import IoTSimOsmosis.osmosis.core.OsmesisBroker;
import IoTSimOsmosis.osmosis.core.OsmesisDatacenter;
import IoTSimOsmosis.osmosis.core.OsmosisBuilder;

public class mainTest {

	public static void main(String[] args) {

		double clock = 0; // set clock to 0 at the start of the simulation
		// blockchainController = new BlockchainController(); // //

		BlockchainController.generateNodes(); // Create blockchain nodes
		BlockchainController.creatTransactionsWithoutIntegrated(); // Create pending transactions without integrated IoT
																	// simulator
		Node.generateGenesisBlock(); // Create the gensis block for all miners
		BlockCommit.generateInitialEvents();

//			BlockCmmit.createBlock();
//			BlockCmmit.testNewArray();
//			BlockCmmit.testexploreBlock();

		// System.out.println(InputConfig.getMiners().size()); // need to delete
		// System.out.println(InputConfig.getNODES().size());// need to delete
		// InputConfig.getSimtime();
		// && (clock <= InputConfig.getSimTime()) !Queue.isEmpty()

		while (!Queue.isEmpty() && (clock <= InputConfig.getSimTime())) {
			Event nextEvent = Queue.getNextEvent();


			// Move clock to the time of the event
			clock = nextEvent.getTime();
			// System.out.println (nextEvent.getTime());

			BlockCommit.handleEvent(nextEvent);
			Queue.removeEvent(nextEvent);

		}

		// for (int i=0; i<InputConfig.getMiners().size(); i++) {

//		System.out.println("The number of blocks : " + InputConfig.getMiners().get(0).getBlockchainLedger().size());
//		System.out.println("Pool size : " + InputConfig.getMiners().get(0).getTransactionsPool().getTransactionsPool().size());
//		for (int i = 0; i < InputConfig.getNodes().size(); i++) {
//			System.out.println("Node id : " + InputConfig.getNodes().get(i).getNodeId() +"\n"+
//					"The number of blockchain ledger : " + InputConfig.getNodes().get(i).getBlockchainLedger().size()  );
//		}

//		for (int i = 0; i < InputConfig.getMiners().get(0).getBlockchainLedger().size(); i++) {
//			System.out.println("===============block " + i + "==================");
//			System.out.println("Block ID : " + InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getBlockID()
//					+ "\n" + "Block Per ID : "
//					+ InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getPreviousBlocKID() + "\n"
//					+ "Block time : " + InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getBlockTimestamp()
//					+ "\n" + "Block depth : "
//					+ InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getBlockDepth() + "\n"
//					+ "Block size used : " + InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getBlockSize()
//					+ "\n" + "Block gas used : "
//					+ InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getUsedGas());
//
//			if (InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getHasTx() == true) {
//				
//				System.out.println("Block includes : "
//						+ InputConfig.getMiners().get(0).getBlockchainLedger().get(i).getNumberTx()
//						+ " transactions");
//
//				Iterator<Transaction> iterator = InputConfig.getMiners().get(0).getBlockchainLedger().get(i)
//						.getTransactions().iterator();
//				while (iterator.hasNext()) {
//
//					Transaction transaction = iterator.next();
//					System.out.println("Transaction ID : " + transaction.getTransactionID() + "\n"
//							+ "Transaction time : " + transaction.getCreationTime() + "\n"
//							+ "Transaction confrimation time: " + transaction.getConfirmationTime() + "\n"
//							+ "Transaction gas used: " + transaction.getUsedGas());
//					// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(1).getTransactions().get(0).getTransactionID());
//					// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(2).getTransactions().get(0).getTransactionID());
//
//				}
//
//			}
//		}
//
		
		Statistics.calculate();
		ExcelWriter.printToExcel();
	}

	

}
