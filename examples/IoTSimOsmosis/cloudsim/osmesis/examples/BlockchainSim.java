package IoTSimOsmosis.cloudsim.osmesis.examples;

import blockchainNetwork.Block;
import blockchainNetwork.BlockchainController;
import blockchainNetwork.ExcelWriter;
import blockchainNetwork.InputConfig;
import blockchainNetwork.Miner;
import blockchainNetwork.Node;
import blockchainNetwork.Statistics;
import blockchainNetwork.Transaction;

public class BlockchainSim {

	private static String senderAddress = "0x36ef5482e8a74d0104fdd5434930849504b3cc39";
	private static String receiverAddress = "0xabea9132b05a70803a4e85094fd0e1800777fbef";

	public static void main(String[] args) {

		BlockchainController blockchainController = new BlockchainController();
		blockchainController.generateNodes();
		blockchainController.generateMiner();
		Node.generateGenesisBlock();

		for (int i = 0; i < InputConfig.getTxnumber(); i++) {
			blockchainController.createTransactions(i, senderAddress, receiverAddress);
		}

		blockchainController.executeTransactions();

		Miner miner = InputConfig.getMiners().get(0);

		for (int i = 0; i < InputConfig.getNODES().size(); i++) {

			System.out.println("############################# Nodes #######################################");
			System.out.println("The nodes ID : " + InputConfig.getNODES().get(i).getId());
			System.out.println("The nodes node type: " + InputConfig.getNODES().get(i).getNodeType());
			System.out.println("The nodes block : " + InputConfig.getNODES().get(i).getBlockchain().size());
			// System.out.println("..............................................................................");
		}

		for (int i = 0; i < InputConfig.getMiners().size(); i++) {
			System.out.println("############################# Miners #######################################");
			System.out.println("The Miner ID : " + InputConfig.getMiners().get(i).getId());
			System.out.println("Type: " + InputConfig.getMiners().get(i).getNodeType());
			System.out.println("The Miner hashRate : " + InputConfig.getMiners().get(i).getHashRate());
			System.out.println("The Miner pool : "
					+ InputConfig.getMiners().get(i).getTransactionsPool().getTransactionsPool().size());
			System.out.println("The Miner block : " + InputConfig.getMiners().get(i).getBlockchain().size());
			// System.out.println("..............................................................................");

		}

		int blockCount = 0;
		for (Block block : miner.getBlockchain()) {

			System.out.println("############################# chain of blocks #######################################");
			System.out.println("The Number of blocks : " + blockCount++);
			System.out.println("The Block ID : " + block.getDepth());
			System.out.println("The Block depth : " + block.getDepth());
			System.out.println("The Block time : " + block.getTimestamp());

			System.out.println("Block (" + block.getDepth() + ")contains the following Transactions: ");

			for (Transaction transaction : block.getTransactions()) {
				System.out.println(" Transaction ID (" + transaction.getId() + ") CreationTime: "
						+ transaction.getCreationTime() + ") Gas fees: " + transaction.getGasPrice());
			}

		}
		Statistics.calculate();
		ExcelWriter.printToExcel();
	}

}
