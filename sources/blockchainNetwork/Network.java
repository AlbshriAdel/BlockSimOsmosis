package blockchainNetwork;

public class Network {
	// Delay for propagating transactions in the network
		public static double txPropDelay() {
			return -Math.log(1-Math.random())/(1/InputConfig.getProptxdelay());
		}
		
		
		

}
