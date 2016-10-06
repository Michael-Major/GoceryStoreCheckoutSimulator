package Project4;
//Statistics.java class used to collect and display data from CheckoutSim.java
//Some data is collected and stored within the shopper and checker classes
//and then accessed by Statistics.java when the displayStatistics() method is called. 
//Some of the methods in Statistics.java were taken from Professor Dovolis' Stat.java.
public class Statistics {
	
	//methods
	public static void updateTime(double time){
		totalTime += time - lastUpdateTime;
		lastUpdateTime = time;
	}
	public static void updateTotalCheckoutTime(double processingTime){
		totalCheckoutTime += processingTime;
	}
    public static void setTotalShoppers(int num){
    	totalShoppers = num;
    }
    public static void setNumCheckoutLanes(int num){
    	numCheckoutLanes = num;
    }
    public static void setNumExpressLanes(int num){
    	numExpressLanes = num;
    }
    public static void updateWaitTime(double waitTime){
    	if(waitTime > maxWait)
    		maxWait = waitTime;
    	totalWaitTime += waitTime;
    }
    public static double calculateAverageQLength(){
    	double sum = 0;
    	for(int i = 0; i < CheckoutSim.checkers.length; i ++){
			sum += CheckoutSim.checkers[i].getAverageQLength();
			if(CheckoutSim.checkers[i].getMaxQLength() > maxQLength)
				maxQLength = CheckoutSim.checkers[i].getMaxQLength();
		}
		for(int i = 0; i < CheckoutSim.expressLanes.length; i ++){
			sum += CheckoutSim.expressLanes[i].getAverageQLength();
			if(CheckoutSim.expressLanes[i].getMaxQLength() > maxQLength)
				maxQLength = CheckoutSim.expressLanes[i].getMaxQLength();
		}
		return (double)sum/(CheckoutSim.checkers.length+CheckoutSim.expressLanes.length);
    }
    
	public static void displayStats() {
		for(int i = 0; i < CheckoutSim.checkers.length; i ++){
			totalBusyTime += CheckoutSim.checkers[i].getBusyTime();
			totalIdleTime += CheckoutSim.checkers[i].getIdleTime();
		}
		for(int i = 0; i < CheckoutSim.expressLanes.length; i ++){
			totalBusyTime += CheckoutSim.expressLanes[i].getBusyTime();
			totalIdleTime += CheckoutSim.expressLanes[i].getIdleTime();
		}
        System.out.println("\n** Checkout Simulation Results **\n"); 
        System.out.println("Checkout & Express Lane Stats");
        System.out.println(" Number of Checkout Lanes: " + 
        		numCheckoutLanes);
        System.out.println(" Number of Express Lanes: " + 
        		numExpressLanes);
        System.out.println(" Avg. Line Length: " + 
        		calculateAverageQLength()/totalTime);
        System.out.println(" Maximum Line Length: " + 
        		maxQLength);
        
        System.out.println("\nShopper Information");
        System.out.println(" Number of shoppers in system: " + 
        		totalShoppers);
        System.out.println(" Number of shoppers that completed checkout: " + 
        		shoppersExited);
        System.out.println(" Number of shoppers 'stranded' in line when simulation terminated: " + 
        		(totalShoppers-shoppersExited));
        if(CheckoutSim.employeeBagging)
        	System.out.println(" Bagging method: employee bagging");
        else
        	System.out.println(" Bagging method: shopper bagging");

        System.out.println("\nTime Statistics");
        System.out.println(" Total Simulation Time: " + totalTime);
        System.out.println(" Avg. Idle Time (all checkout stations): " + 
        		totalIdleTime/(numExpressLanes+numCheckoutLanes));
        System.out.println(" Avg. Busy Time (all checkout stations): " + 
        		totalBusyTime/(numExpressLanes+numCheckoutLanes));
        System.out.println(" Avg. Checkout Time: " + 
        		totalCheckoutTime/totalShoppers);
        System.out.println(" Maximum Waiting Time: " + 
        		maxWait);
        System.out.println(" Avg. Waiting Time for Shoppers: " + 
        		totalWaitTime/shoppersExited);
    }  // displayStats method
    
	//Statistics class Variables
    private static int totalShoppers;
    private static double totalBusyTime;
    private static double totalIdleTime;
    private static double maxWait;
    private static double totalWaitTime;
    private static int maxQLength;
    private static double totalCheckoutTime;
    private static int numCheckoutLanes;
    private static int numExpressLanes;
    static int shoppersExited = 0;
    private static double totalTime;
    private static double lastUpdateTime;
    
}// Statistics.java
