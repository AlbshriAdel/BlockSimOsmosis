package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

	static ArrayList<Object[]> test = new ArrayList<>();
	public static void test() {
		test.add(new Object[] {"Adel", "Mohammed", "Albshri"});
	}

	
	@Override
	public String toString() {
		return "Verification [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public static void main(String[] args) {
		test();
		
		for (Object obj :test) {
			if (obj.getClass()== String.class) {
			System.out.println(obj);
			}
		}
		System.out.println ( ThreadLocalRandom.current().nextDouble(0,0.05));    
		
	}
		
//		Random rand = new Random();
//		
//		for (int i=0; i<50 ; i++) {
//			double double_random = rand.nextDouble();
//			
//			//System.out.println (-Math.l(0,3333*1) / (InputConfig.getBinterval()));
//			System.out.println ( ThreadLocalRandom.current().nextDouble(0.333,InputConfig.getBinterval()));    
//		}
////		for (Object[] a : Verification.getChains()
//		}
		
	}
	
	
	
	


