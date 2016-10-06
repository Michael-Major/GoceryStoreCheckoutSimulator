package Project4;
//created for each completion of a customer's checkout
//causes the checker to look at its queue and see if there is a Shopper waiting
//if there is a Shopper queued, the Checker will create a new CheckerEvent and schedule it (via the agenda).
//for the future at time depending on the number of items in the queued Shopper's cart
//and whether customer or employee bagging will be done.
//if there is NOT a shopper in the waiting in the queue, the checker event sets the checker to idle.
//NOTE: shoppers are not removed from checkout queues (lines) until their checker event 
//is executed by the agenda (line 34 in run() method). 
public class CheckerEvent implements Event{
	
	//constructor
	public CheckerEvent(Shopper s){
		shopper = s;
	}
	
	//run() method that gets called by agenda
	public void run() {
		Statistics.shoppersExited++;
		Checker c;
		if(shopper.expressLane()){ //find the right checker lane
			c = CheckoutSim.expressLanes[shopper.getIndex()];	
			System.out.println("CHECKER EVENT in express lane: " + shopper.getIndex()+" time: "+ 
					CheckoutSim.agenda.getCurrentTime()+ " shopper number: " + shopper.getShopperNumber());
		}
		else if(!shopper.expressLane()){ //find the right checker lane
			c = CheckoutSim.checkers[shopper.getIndex()]; 
			System.out.println("CHECKER EVENT in line: " + shopper.getIndex()+" time: "+ 
					CheckoutSim.agenda.getCurrentTime()+ " shopper number: " + shopper.getShopperNumber());
		}
		else{ //lets user know that an error occurred
			c = CheckoutSim.checkers[shopper.getIndex()];
			System.out.println("this should not happen in CheckerEvent run() method");
		}
		c.getLine().remove();//remove the shopper associated with this CheckerEvent from the checker queue (line)
		c.updateQueueStats(CheckoutSim.agenda.getCurrentTime(), c.getLine().length());
                if(c.getLine().length() == 0){//if the queue is now empty, set the appropriate checker to idle
        		c.setIdle();
        	//update statistics
        	c.updateBusyTimeStats(CheckoutSim.agenda.getCurrentTime());
        	Statistics.updateTime(CheckoutSim.agenda.getCurrentTime());
        }
        else{//the queue is not empty
        	shopper = (Shopper) c.getLine().getFront().getData();//add the next shopper to a CheckerEvent, but 
        														//do not remove it from the queue (line)
        	CheckoutSim.agenda.add(new CheckerEvent(shopper),shopper.getProcessingTime());//add the CheckerEvent 
        																				 //to the agenda 
        	//update statistics
        	Statistics.updateWaitTime(CheckoutSim.agenda.getCurrentTime() - shopper.getTimeEnteredQueue());
        	Statistics.updateTime(CheckoutSim.agenda.getCurrentTime());
        }
    }  // run method
	
	//variables
	private Shopper shopper;
	
} //CheckerEvent class
