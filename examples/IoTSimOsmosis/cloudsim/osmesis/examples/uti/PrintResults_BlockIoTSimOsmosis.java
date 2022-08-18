package IoTSimOsmosis.cloudsim.osmesis.examples.uti;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import IoTSimOsmosis.blockchainNetwork.BlockchainController;
import IoTSimOsmosis.cloudsim.edge.core.edge.EdgeLet;
import IoTSimOsmosis.osmosis.core.Flow;
import IoTSimOsmosis.osmosis.core.OsmesisAppDescription;
import IoTSimOsmosis.osmosis.core.OsmesisAppsParser;
import IoTSimOsmosis.osmosis.core.OsmesisBroker;
import IoTSimOsmosis.osmosis.core.WorkflowInfo;

public class PrintResults_BlockIoTSimOsmosis {
	public void Adel() {
		List<WorkflowInfo> tags = new ArrayList<>();
		for(OsmesisAppDescription app : OsmesisAppsParser.appList){
			for(WorkflowInfo workflowTag : OsmesisBroker.workflowTag){
				workflowTag.getAppId();
				if(app.getAppID() == workflowTag.getAppId()){
					tags.add(workflowTag);
				}
			}
			//printOsmesisApp(tags);		
			//tags.clear();
			for(WorkflowInfo workflowTag : tags){
//            	double sendTime = ThreadLocalRandom.current().nextDouble(workflowTag.getSartTime(), workflowTag.getFinishTime());
//            	BlockchainController.creatTransactionsWithIntegrated(sendTime);
				
			}
		} 
	}
}
		
        
