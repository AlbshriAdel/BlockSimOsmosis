package IoTSimOsmosis.blockchainNetwork;

import java.util.Random;

public class test {

	public static void main(String[] args) {

		for (int i=0; i<100 ;i++) {
		System.out.println(Consensus.generateNextBlockDelay());
		
		}
	}

}