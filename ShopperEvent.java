package Project4;
//ShopperEvent.java simulates the shopper entering the checker or expressLine that it was assigned
//to by ShopperMaker.java. The ShopperEvent run() method also 'wakes up' the checker if it was 
//previously idle. 
public class ShopperEvent implements Event{
	
	//constructor
	public ShopperEvent(Shopper s){
		shopper = s;
		System.out.println("Line chosen: " + shopper.getIndex() + " Arrival Time " + shopper.getArrivalTime() +
				" Number of Items in Cart: " + shopper.getNumItems() + " shopper number: " + shopper.getShopperNumber());
	}
	
	//run() method that gets called by the agenda
	public void run(){
		if(shopper.expressLane()){
			if(!CheckoutSim.expressLanes[shopper.getIndex()].isBusy()){	//if the line isn't busy
	        	CheckoutSim.agenda.add(new CheckerEvent(shopper),shopper.getProcessingTime()); //add checker event to agenda
	        	CheckoutSim.expressLanes[shopper.getIndex()].setBusy(); //indicate that the checker is busy
	        	
	        	CheckoutSim.expressLanes[shopper.getIndex()].updateIdleTimeStats(CheckoutSim.agenda.getCurrentTime());
			}
		}
		else
			if(!CheckoutSim.checkers[shopper.getIndex()].isBusy()){	//if the line isn't busy
	        	CheckoutSim.agenda.add(new CheckerEvent(shopper),shopper.getProcessingTime()); //add checker event to agenda
	        	CheckoutSim.checkers[shopper.getIndex()].setBusy(); //indicate that the checker is busy
	        	
	        	CheckoutSim.checkers[shopper.getIndex()].updateIdleTimeStats(CheckoutSim.agenda.getCurrentTime());
			}
		Statistics.updateTotalCheckoutTime(shopper.getProcessingTime());
		Statistics.updateWaitTime(CheckoutSim.agenda.getCurrentTime() - shopper.getTimeEnteredQueue());
	}//run() method
	
	//variables
	private Shopper shopper;
	
}//ShopperEvent class
