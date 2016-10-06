package Project4;
//CheckoutSim.java is the driver routine for the Grocery Store Checkout Simulation
//If you are running simulations, you can manipulate the type of bagging, load, simulation time,
//and number of checkout/express lanes via the SIMULATION VARIABLES assignment operations
//at the top of the main() method.
//CheckoutSim.java uses PQ.java, ShopperMaker.java, ShopperEvent.java, CheckerEvent.java, CheckoutSim.java,
//Checker.java, Shopper.java, Q1.java, N.java, Q.java, Segment.java, and Statistics.java.
//The priority queue, node, and queue classes were taken from Professor Dovolis' lecture examples.

public class CheckoutSim {
	
	public static void main(String args[]) {
		//***SIMULATION VARIABLES***// 
		employeeBagging = true; 	//employee or shopper bagging
    	int interArrivalTime = 45;	//load
    	int simulationTime = 30000;	//target simulation time
    	int numCheckoutLanes = 5;	//number of checkout lanes
    	int numExpressLanes = 0;	//number of express lanes
		
    	
    	//create and initialize checkout and express lanes
    	checkers = new Checker[numCheckoutLanes];
    	for(int i = 0; i < checkers.length; i++){
    		checkers[i] = new Checker();
    	}
    	expressLanes = new Checker[numExpressLanes];
    	for(int i = 0; i < expressLanes.length; i++){
    		expressLanes[i] = new Checker();
    	}
    	
    	
        agenda.add(new ShopperMaker(interArrivalTime),10); //add the first ShopperMaker event to agenda
        //run the simulation for the designated simulation time
        //NOTE: actual simulation time may be longer due to the completion of a
        //previously scheduled event
        while (agenda.getCurrentTime() <= simulationTime)
            agenda.remove().run();
        
        //after the simulation is complete, update the busyTime and idleTime statistics of the queues
        //that still have shoppers in their lines
        for(int i = 0; i < checkers.length; i++){
        	if(checkers[i].isBusy())
        		CheckoutSim.checkers[i].updateBusyTimeStats(CheckoutSim.agenda.getCurrentTime());
        	else
        		CheckoutSim.checkers[i].updateIdleTimeStats(CheckoutSim.agenda.getCurrentTime());
        }
        for(int i = 0; i < expressLanes.length; i++){
        	if(expressLanes[i].isBusy())
        		CheckoutSim.expressLanes[i].updateBusyTimeStats(CheckoutSim.agenda.getCurrentTime());
        	else
        		CheckoutSim.expressLanes[i].updateIdleTimeStats(CheckoutSim.agenda.getCurrentTime());
        }
        
        //Send statistics to the statistics class.
        Statistics.setTotalShoppers(ShopperMaker.shopperNumber);
       	Statistics.setNumExpressLanes(expressLanes.length);
        Statistics.setNumCheckoutLanes(checkers.length);
        Statistics.displayStats();

    }//main method   

	//finds the shortest Checker line and returns its index
    public static int findShortestLine(){
    	int index = 0;
    	for(int i = 1; i < checkers.length; i++){
    		if(checkers[index].getLine().length() == 0)
    			return index;
    		if(checkers[i].getLine().length() < checkers[index].getLine().length())
    			index = i;
    	}
    	return index;
    }//findShortestLine() method
    
    //finds the shortest express lane and returns its index
    public static int shortestExpressLane(){
    	int index = 0;
    	for(int i = 1; i < expressLanes.length; i++){
    		if(expressLanes[index].getLine().length() == 0)
    			return index;
    		if(expressLanes[i].getLine().length() < expressLanes[index].getLine().length())
    			index = i;
    	}
    	return index;
    }//shortestExpressLane() method
    
    //variables
    static PQ agenda = new PQ();
    static Checker[] checkers;
    static boolean employeeBagging;
    static Checker[] expressLanes;
    
}// CheckoutSim class
