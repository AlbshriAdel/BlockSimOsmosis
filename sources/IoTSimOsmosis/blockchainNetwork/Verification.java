package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.Random;

public class Verification {

	private static ArrayList<Object[]> verificationStatus = new ArrayList<>();

	private static ArrayList<Object> status(boolean checked) {
		ArrayList<Object> checkedStatus = new ArrayList<>();

		
		if (checked == true) {
			checkedStatus .add("PASSED");
		} else {
			checkedStatus .add("FAILED");
		}

		
		return checkedStatus;

	}
	
	private static void checkTotoalNodes() {
		ArrayList<Object> checkedInfo = new ArrayList<Object>();
		ArrayList<Object[]> checkedResult = new ArrayList<>();
		boolean checkPassed = true; 
		int expectedNodes = InputConfig.getNumberOfNodes();
		checkedInfo.add("Node found [" + Integer.valueOf(expectedNodes) + "] and expected nodes [" + Integer.valueOf(expectedNodes) +"].");
		int nodesFound = 3;//Node.getNodes().size();
		
		if (nodesFound != expectedNodes) {
			checkPassed  = false;
			checkedInfo.add("Expecting ["+ Integer.valueOf(expectedNodes) + "] nodes and found [" + Integer.valueOf(nodesFound) +"].");
		}
		
		verificationStatus.add( new  Object[]{"Check Total Nodes", status(checkPassed), checkedInfo });
		//verificationStatus .add(checkedResult);
		System.out.println(checkedResult.size());
		
		
		
	}

//	@Override
//    public String toString(){
//		String index;
//		for (int i=0; i<verificationStatus.size() ;i++) {
//			index+= verificationStatus.get(i) +"\n";
//			
//		}
//		
//		index;
//	}


	
	public static void main(String[] args) {
		Random rand = new Random();
		
		for (int i=0; i<50 ; i++) {
			double double_random = rand.nextDouble();
			Random rd = new Random();
		      byte[] arr = new byte[7];
		      rd.nextBytes(arr);
		      
		System.out.println(InputConfig.getMinTransactionSize() + rd.nextDouble() * (InputConfig.getMaxTransactionSize()-InputConfig.getMinTransactionSize()));
	}
//		for (Object[] a : Verification.getChains()
		}
		
	}
	
	
	
	


