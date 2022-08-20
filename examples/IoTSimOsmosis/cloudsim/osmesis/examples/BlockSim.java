package IoTSimOsmosis.cloudsim.osmesis.examples;

import java.util.Iterator;
import IoTSimOsmosis.blockchainNetwork.BlockCommit;
import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.blockchainNetwork.Event;
import IoTSimOsmosis.blockchainNetwork.ExcelWriter;
import IoTSimOsmosis.blockchainNetwork.InputConfig;
import IoTSimOsmosis.blockchainNetwork.Node;
import IoTSimOsmosis.blockchainNetwork.Queue;
import IoTSimOsmosis.blockchainNetwork.Statistics;
import IoTSimOsmosis.blockchainNetwork.Transaction;

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

		//Statistics.calculate();
		//ExcelWriter.printToExcel();
	
	
	for (int i = 0; i < Node.getMiners().get(0).getBlockchainLedger().size(); i++) {
	System.out.println("===============block " + i + "==================");
	System.out.println("Block ID : " + Node.getMiners().get(0).getBlockchainLedger().get(i).getBlockID()
			+ "\n" + "Block Per ID : "
			+ Node.getMiners().get(0).getBlockchainLedger().get(i).getPreviousBlocKID() + "\n"
			+ "Block time : " + Node.getMiners().get(0).getBlockchainLedger().get(i).getBlockTimestamp()
			+ "\n" + "Block depth : "
			+ Node.getMiners().get(0).getBlockchainLedger().get(i).getBlockDepth() + "\n"
			+ "Block size used : " + Node.getMiners().get(0).getBlockchainLedger().get(i).getBlockSize()
			+ "\n" + "Block gas used : "
			+ Node.getMiners().get(0).getBlockchainLedger().get(i).getUsedGas());

	if (Node.getMiners().get(0).getBlockchainLedger().get(i).getHasTx() == true) {
		
		System.out.println("Block includes : "
				+ Node.getMiners().get(0).getBlockchainLedger().get(i).getNumberTx()
				+ " transactions");

		Iterator<Transaction> iterator = Node.getMiners().get(0).getBlockchainLedger().get(i)
				.getTransactions().iterator();
		while (iterator.hasNext()) {

			Transaction transaction = iterator.next();
			System.out.println("Transaction ID : " + transaction.getTransactionID() + "\n"
					+ "Transaction time : " + transaction.getCreationTime() + "\n"
					+ "Transaction confrimation time: " + transaction.getConfirmationTime() + "\n"
					+ "Transaction gas used: " + transaction.getUsedGas());
			// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(1).getTransactions().get(0).getTransactionID());
			// System.out.println(InputConfig.getMiners().get(0).getBlockchainLedger().get(2).getTransactions().get(0).getTransactionID());

		}

	}
}


	}
}