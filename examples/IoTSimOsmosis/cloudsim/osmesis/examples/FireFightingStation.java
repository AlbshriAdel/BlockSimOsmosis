package IoTSimOsmosis.cloudsim.osmesis.examples;

import IoTSimOsmosis.blockchainNetwork.Block;
import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.blockchainNetwork.ExcelWriter;
import IoTSimOsmosis.blockchainNetwork.InputConfig;
import IoTSimOsmosis.blockchainNetwork.Miner;
import IoTSimOsmosis.blockchainNetwork.Node;
import IoTSimOsmosis.blockchainNetwork.Statistics;
import IoTSimOsmosis.blockchainNetwork.Transaction;
import IoTSimOsmosis.cloudsim.Log;
import IoTSimOsmosis.cloudsim.Vm;
import IoTSimOsmosis.cloudsim.core.CloudSim;
import IoTSimOsmosis.cloudsim.edge.core.edge.ConfiguationEntity;
import IoTSimOsmosis.cloudsim.edge.core.edge.MEL;
import IoTSimOsmosis.cloudsim.edge.utils.LogUtil;
import IoTSimOsmosis.cloudsim.osmesis.examples.uti.LogPrinter;
import IoTSimOsmosis.cloudsim.osmesis.examples.uti.PrintResults_Example_3;
import IoTSimOsmosis.cloudsim.sdn.Switch;
import IoTSimOsmosis.osmosis.core.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class FireFightingStation {

	public static final String configurationFile = "inputFiles/Example3_Configuration_Blockchain.json";
	public static final String osmesisAppFile = "inputFiles/Example3_Worload_Blockchain.csv";
	OsmosisBuilder topologyBuilder;
	OsmesisBroker osmesisBroker;
	List<OsmesisDatacenter> datacenters;
	List<MEL> melList;
	EdgeSDNController edgeSDNController;
	List<Vm> vmList;
	static BlockchainController blockchainController;

	public static void main(String[] args) throws Exception {
		blockchainController = new BlockchainController();
		blockchainController.generateNodes();
		blockchainController.generateMiner();
		Node.generateGenesisBlock();

		FireFightingStation osmesis = new FireFightingStation();
		osmesis.start();
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

	public void start() throws Exception {

		int num_user = 1; // number of users
		Calendar calendar = Calendar.getInstance();
		boolean trace_flag = false; // mean trace events

		// Initialize the CloudSim library
		CloudSim.init(num_user, calendar, trace_flag);
		osmesisBroker = new OsmesisBroker("OsmesisBroker");
		topologyBuilder = new OsmosisBuilder(osmesisBroker);
		ConfiguationEntity config = buildTopologyFromFile(configurationFile);
		if (config != null) {
			topologyBuilder.buildTopology(config);
		}

		OsmosisOrchestrator maestro = new OsmosisOrchestrator();

		OsmesisAppsParser.startParsingExcelAppFile(osmesisAppFile);
		List<SDNController> controllers = new ArrayList<>();
		for (OsmesisDatacenter osmesisDC : topologyBuilder.getOsmesisDatacentres()) {
			osmesisBroker.submitVmList(osmesisDC.getVmList(), osmesisDC.getId());
			controllers.add(osmesisDC.getSdnController());
			osmesisDC.getSdnController().setWanOorchestrator(maestro);
		}
		controllers.add(topologyBuilder.getSdWanController());
		maestro.setSdnControllers(controllers);
		osmesisBroker.submitOsmesisApps(OsmesisAppsParser.appList);
		osmesisBroker.setDatacenters(topologyBuilder.getOsmesisDatacentres());

		double startTime = CloudSim.startSimulation();

		LogUtil.simulationFinished();
		PrintResults_Example_3 pr = new PrintResults_Example_3();
		pr.printOsmesisNetwork(blockchainController);
		Log.printLine();

		for (OsmesisDatacenter osmesisDC : topologyBuilder.getOsmesisDatacentres()) {
			List<Switch> switchList = osmesisDC.getSdnController().getSwitchList();
			LogPrinter.printEnergyConsumption(osmesisDC.getName(), osmesisDC.getSdnhosts(), switchList, startTime);
			Log.printLine();
		}

		Log.printLine();
		LogPrinter.printEnergyConsumption(topologyBuilder.getSdWanController().getName(), null,
				topologyBuilder.getSdWanController().getSwitchList(), startTime);
		Log.printLine();
		Log.printLine("Simulation Finished!");

	}

	private ConfiguationEntity buildTopologyFromFile(String filePath) throws Exception {
		System.out.println("Creating topology from file " + filePath);
		ConfiguationEntity conf = null;
		try (FileReader jsonFileReader = new FileReader(filePath)) {
			conf = topologyBuilder.parseTopology(jsonFileReader);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: input configuration file not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Topology built:");
		return conf;
	}

	public void setEdgeSDNController(EdgeSDNController edc) {
		this.edgeSDNController = edc;
	}

}
