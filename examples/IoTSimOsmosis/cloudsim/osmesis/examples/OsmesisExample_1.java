/*
 * Title:        IoTSim-Osmosis 1.0
 * Description:  IoTSim-Osmosis enables the testing and validation of osmotic computing applications 
 * 			     over heterogeneous edge-cloud SDN-aware environments.
 * 
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2020, Newcastle University (UK) and Saudi Electronic University (Saudi Arabia) 
 * 
 */

package IoTSimOsmosis.cloudsim.osmesis.examples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import IoTSimOsmosis.cloudsim.core.CloudSim;
import IoTSimOsmosis.cloudsim.edge.core.edge.ConfiguationEntity;
import IoTSimOsmosis.cloudsim.edge.core.edge.MEL;
import IoTSimOsmosis.cloudsim.edge.utils.LogUtil;
import IoTSimOsmosis.cloudsim.osmesis.examples.uti.LogPrinter;
import IoTSimOsmosis.cloudsim.osmesis.examples.uti.PrintResults;
import IoTSimOsmosis.cloudsim.sdn.Switch;
import IoTSimOsmosis.cloudsim.Log;
import IoTSimOsmosis.cloudsim.Vm;
import IoTSimOsmosis.osmosis.core.EdgeSDNController;
import IoTSimOsmosis.osmosis.core.OsmesisBroker;
import IoTSimOsmosis.osmosis.core.OsmesisDatacenter;
import IoTSimOsmosis.osmosis.core.OsmosisBuilder;
import IoTSimOsmosis.osmosis.core.OsmosisOrchestrator;
import IoTSimOsmosis.osmosis.core.SDNController;

import IoTSimOsmosis.osmosis.core.OsmesisAppsParser;

/**
 * 
 * @author Khaled Alwasel
 * @contact kalwasel@gmail.com
 * @since IoTSim-Osmosis 1.0
 * 
**/

public class OsmesisExample_1 {
	public static final String configurationFile = "inputFiles/Example1_configuration.json";
	public static final String osmesisAppFile =  "inputFiles/Example1_Worload.csv";
    OsmosisBuilder topologyBuilder;
	OsmesisBroker osmesisBroker;
	List<OsmesisDatacenter> datacenters;
	List<MEL> melList;
	EdgeSDNController edgeSDNController;
	List<Vm> vmList;

	public static void main(String[] args) throws Exception {
		OsmesisExample_1 osmesis = new OsmesisExample_1();
		osmesis.start();
	}
	
	public void start() throws Exception{

		int num_user = 1; // number of users
		Calendar calendar = Calendar.getInstance();
		boolean trace_flag = false; // mean trace events

		// Initialize the CloudSim library
		CloudSim.init(num_user, calendar, trace_flag);
		osmesisBroker  = new OsmesisBroker("OsmesisBroker");
		topologyBuilder = new OsmosisBuilder(osmesisBroker);
		ConfiguationEntity config = buildTopologyFromFile(configurationFile);
        if(config !=  null) {
        	topologyBuilder.buildTopology(config);
        }
        
        OsmosisOrchestrator maestro = new OsmosisOrchestrator();
        
		OsmesisAppsParser.startParsingExcelAppFile(osmesisAppFile);
		List<SDNController> controllers = new ArrayList<>();
		for(OsmesisDatacenter osmesisDC : topologyBuilder.getOsmesisDatacentres()){
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
		PrintResults pr = new PrintResults();
		pr.printOsmesisNetwork();
			
		Log.printLine();

		for(OsmesisDatacenter osmesisDC : topologyBuilder.getOsmesisDatacentres()){		
			List<Switch> switchList = osmesisDC.getSdnController().getSwitchList();
			LogPrinter.printEnergyConsumption(osmesisDC.getName(), osmesisDC.getSdnhosts(), switchList, startTime);
			Log.printLine();
		}
		
		Log.printLine();		
		LogPrinter.printEnergyConsumption(topologyBuilder.getSdWanController().getName(), null, topologyBuilder.getSdWanController().getSwitchList(), startTime);		
		Log.printLine();
		Log.printLine("Simulation Finished!");

	}
	
    private ConfiguationEntity buildTopologyFromFile(String filePath) throws Exception {
        System.out.println("Creating topology from file " + filePath);
        ConfiguationEntity conf  = null;
        try (FileReader jsonFileReader = new FileReader(filePath)){
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
