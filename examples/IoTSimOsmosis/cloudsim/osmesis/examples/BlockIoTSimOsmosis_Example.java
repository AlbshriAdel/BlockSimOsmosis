package IoTSimOsmosis.cloudsim.osmesis.examples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import IoTSimOsmosis.blockchainNetwork.BlockCommit;
import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.blockchainNetwork.Event;
import IoTSimOsmosis.blockchainNetwork.ExcelWriter;
import IoTSimOsmosis.blockchainNetwork.InputConfig;
import IoTSimOsmosis.blockchainNetwork.Node;
import IoTSimOsmosis.blockchainNetwork.Queue;
import IoTSimOsmosis.blockchainNetwork.Statistics;
import IoTSimOsmosis.cloudsim.Log;
import IoTSimOsmosis.cloudsim.Vm;
import IoTSimOsmosis.cloudsim.core.CloudSim;
import IoTSimOsmosis.cloudsim.edge.core.edge.ConfiguationEntity;
import IoTSimOsmosis.cloudsim.edge.core.edge.MEL;
import IoTSimOsmosis.cloudsim.edge.utils.LogUtil;
import IoTSimOsmosis.cloudsim.osmesis.examples.uti.LogPrinter;
import IoTSimOsmosis.cloudsim.osmesis.examples.uti.PrintResults_Example_3;
import IoTSimOsmosis.cloudsim.osmesis.examples.uti.PrintResults_BlockIoTSimOsmosis;
import IoTSimOsmosis.cloudsim.sdn.Switch;
import IoTSimOsmosis.osmosis.core.EdgeSDNController;
import IoTSimOsmosis.osmosis.core.OsmesisAppsParser;
import IoTSimOsmosis.osmosis.core.OsmesisBroker;
import IoTSimOsmosis.osmosis.core.OsmesisDatacenter;
import IoTSimOsmosis.osmosis.core.OsmosisBuilder;
import IoTSimOsmosis.osmosis.core.OsmosisOrchestrator;
import IoTSimOsmosis.osmosis.core.SDNController;

public class BlockIoTSimOsmosis_Example {
	
	public static final String configurationFile = "inputFiles/Example3_Configuration_Blockchain.json";
	public static final String osmesisAppFile = "inputFiles/Example3_Worload_Blockchain.csv";
	OsmosisBuilder topologyBuilder;
	OsmesisBroker osmesisBroker;
	List<OsmesisDatacenter> datacenters;
	List<MEL> melList;
	EdgeSDNController edgeSDNController;
	List<Vm> vmList;
	
	public static void main(String[] args) throws Exception {
		BlockIoTSimOsmosis_Example osmesis = new BlockIoTSimOsmosis_Example();
		
		BlockchainController.generateNodes(); // Create blockchain nodes
		//BlockchainController.creatTransactionsWithoutIntegrated(); // Create pending transactions without integrated IoT simulator
		Node.generateGenesisBlock(); // Create the gensis block for all miners
		BlockCommit.generateInitialEvents();
		osmesis.start();
		

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

		CloudSim.startSimulation();

		//LogUtil.simulationFinished();
		PrintResults_BlockIoTSimOsmosis pr = new PrintResults_BlockIoTSimOsmosis();
		//PrintResults_Example_3 pr = new PrintResults_Example_3();
		//pr.Adel();
		
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

