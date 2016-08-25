
public class Run {
	public static void main(String[] args){
		long start = System.nanoTime();
		
		int n;
		
		Topology to = new Topology();
		to.setTopology("atlanta.xml");
		n = to.n;
		Network network = new Network(n); // create network
		
		
		int[] switches = {0,1,5,6,7,8,3,9,10};
		network.setSwitches(switches);	
		network.setMatrixByTopology(to);
		
		
		
	//	AlgorithmAngle aa = new AlgorithmAngle();
	//	System.out.println(aa.best(network,0.4));
		
		Traffic traffic = new Traffic(n);  //randomly generate flow
//		traffic.generateTraffic();
		traffic.setTrafficByTo(to);

//		traffic.printTrafficFlow();
//		traffic.printTrafficFlowForSetting();
		
		SPRouting a = new SPRouting();
		RoutingTable rt = new RoutingTable(n);
		for(int i=0;i<n;i++)
			rt.routing[i] = a.routing(network,i,n);
		
//		rt.printPath(n);
		
		
		//rt.linkUtilization(traffic, network, n);
		rt.linkUtilizationSwitch(traffic, network, n);
		
//		network.printLinkFlow();

//		network.printSDNFlow();		
		
		
		
		
		Lambda l = new Lambda();
		System.out.println(l.getLambda(0.1, network, rt));
		
		long end = System.nanoTime();
		System.out.println("Running "+(end - start) / 1000000000);

	}
	
	public void ms(boolean a){
		if(a)
			return;
		System.out.println("assertation failed");
		System.exit(0);
	}
}
