package Project4;
//Each instance of Shopper.java represents a shopper that has entered the checkout area. 
//Shoppers are created by the ShopperMaker class and get passed on to the ShopperEvent and CheckerEvent classes.
//Shoppers are passive, so they only hold and calculate their own data. They do not manipulate other classes.
public class Shopper {
	
	//constructor
	public Shopper(double aT, int nI, int indx, int shopperNum){
		arrivalTime = aT;
		numItems = nI;
		index = indx;
		shopperNumber = shopperNum;
	}
	
	//getters and setters
	public void setIndex(int i){
		index = i;
	}
	public int getIndex(){
		return index;
	}
	public double getArrivalTime(){
		return arrivalTime;
	}
	public int getNumItems(){
		return numItems;
	}
	public int getShopperNumber(){
		return shopperNumber;
	}
	public double getProcessingTime(){
		if(CheckoutSim.employeeBagging && !expressLane)
			return numItems*5;
		return numItems*9;
	}
	public void setExpressLane(boolean b){
		expressLane = b;
	}
	public boolean expressLane(){
		return expressLane;
	}
	public void setTimeEntered(double time){
		timeEnteredQueue= time;
	}
	public double getTimeEnteredQueue(){
		return timeEnteredQueue;
	}
	
	//variables
	private double arrivalTime;
	private int numItems;
	private int index;
	private int shopperNumber;
	private boolean expressLane = false;
	public double timeEnteredQueue;
	
}//Shopper class
