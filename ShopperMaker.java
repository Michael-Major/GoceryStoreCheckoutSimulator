package Project4;
import java.util.Random;

//similar to CarMaker from lecture
//uses an priority queue(called agenda) to create and reschedule itself
//creates one shopper each time it is run, decides how many groceries the Shopper will have,
//and places the Shopper in a queue in front of a Checker
public class ShopperMaker implements Event{
	
	//constructors
	public ShopperMaker(double intv){
		interval = intv;
	}
	
	//methods
	
	//generates random arrival time for next shopperMaker event
	public double setArrivalTime(){	 
		int percentage = r.nextInt(100);
		if(percentage < 10)
			return (interval + (0.75*interval));
		else if(percentage < 25)
			return (interval + (0.50*interval));
		else if(percentage < 45)
			return (interval + (0.20*interval));
		else if(percentage < 55)
			return interval;
		else if(percentage < 75)
			return (interval - (0.20*interval));
		else if(percentage < 90)
			return (interval - (0.50*interval));
		else if(percentage < 100)
			return (interval - (0.75*interval));
		return interval;
	}//setArrivalTime() method
	
	//run method implemented from Event interface
	public void run() {	
		CheckoutSim.agenda.add(new ShopperMaker(interval),setArrivalTime());//schedule the next ShopperMaker event (itself)
		shopperNumber++;
		int index = CheckoutSim.findShortestLine();
		Shopper newShopper = new Shopper(CheckoutSim.agenda.getCurrentTime(),
				itemCountArray[r.nextInt(30)] - r.nextInt(10),index,shopperNumber);
		if(newShopper.getNumItems() < 11 && CheckoutSim.expressLanes.length > 0){//determine if shopper should choose an express lane
			if(CheckoutSim.expressLanes[CheckoutSim.shortestExpressLane()].getLine().length() <= 
					CheckoutSim.checkers[index].getLine().length()){
				newShopper.setIndex(CheckoutSim.shortestExpressLane());
				newShopper.setExpressLane(true);
				CheckoutSim.expressLanes[newShopper.getIndex()].getLine().add(newShopper);
				
				
				//update necessary statistics
				CheckoutSim.expressLanes[newShopper.getIndex()].updateQueueStats(CheckoutSim.agenda.getCurrentTime(),
								CheckoutSim.expressLanes[newShopper.getIndex()].getLine().length());	
			}
		}
		else{//shopper was not added to an express lane
			CheckoutSim.checkers[index].getLine().add(newShopper);	//add shopper to the shortest line
			
			CheckoutSim.checkers[newShopper.getIndex()].updateQueueStats(CheckoutSim.agenda.getCurrentTime(),
					CheckoutSim.checkers[newShopper.getIndex()].getLine().length());
		}
		
		CheckoutSim.agenda.add(new ShopperEvent(newShopper),0);
		Statistics.updateTime(CheckoutSim.agenda.getCurrentTime());
		newShopper.setTimeEntered(CheckoutSim.agenda.getCurrentTime());
	}//run() method
	
	//variables
	static int shopperNumber = 0;
	Random r = new Random();
	private double interval;
	private static int[] itemCountArray = {10,10,10,20,20,20,20,30,30,30,30,30,40,40,40,40,
		50,50,50,50,60,60,60,70,70,70,80,80,90,100};
	
}//ShopperMaker class
