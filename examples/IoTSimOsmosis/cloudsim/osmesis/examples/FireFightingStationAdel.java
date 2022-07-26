package IoTSimOsmosis.cloudsim.osmesis.examples;

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
import blockchainNetwork.Block;
import blockchainNetwork.BlockchainController;
import blockchainNetwork.ExcelWriter;
import blockchainNetwork.InputConfig;
import blockchainNetwork.Miner;
import blockchainNetwork.Node;
import blockchainNetwork.Statistics;
import blockchainNetwork.Transaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import IoTSimOsmosis.
import blockchainNetwork.BlockCmmit;

public class FireFightingStationAdel {

	public static final String configurationFile = "inputFiles/Example3_Configuration_Blockchain.json";
	public static final String osmesisAppFile = "inputFiles/Example3_Worload_Blockchain.csv";
	OsmosisBuilder topologyBuilder;
	OsmesisBroker osmesisBroker;
	List<OsmesisDatacenter> datacenters;
	List<MEL> melList;
	EdgeSDNController edgeSDNController;
	List<Vm> vmList;
	static BlockchainController blockchainController;

	public static void main(String[] args) {
		
		new FireFightingStationAdel().run();

	}
	public void run() {
		int clock=0; //set clock to 0 at the start of the simulation
		blockchainController = new BlockchainController();
		
		blockchainController.generateNodes(); // Create blockchain nodes 
		blockchainController.generateMiner(); // Create blockchain miner
		Node.generateGenesisBlock();
		BlockCmmit.generateInitialEvents();
		
		
		
}
}
