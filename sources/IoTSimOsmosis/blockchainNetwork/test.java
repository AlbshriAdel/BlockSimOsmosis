package IoTSimOsmosis.blockchainNetwork;

import java.util.Random;

public class test {

	public static void main(String[] args) {
		
			Random rand = new Random();
			double double_random=rand.nextDouble();
			System.out.println( -Math.log(1-double_random)/(InputConfig.getBlockDelay()));
		}

	

}
