package Project4;
//instantiable once for each checkout lane in the store
//each checker has a queue associated with it
//and an index to refer to a particular Checker
//it is a good idea to create an array of Checker to hold each instance
//queue holds waiting shoppers 
//a boolean indicates whether the checker is busy or not

public class Checker {
	private boolean busy = false;
	private Q1 line = new Q1();
	private double idleTime;
	private double busyTime;
	private double lastUpdateTime = 0;
	private double lastQUptdateTime = 0;
	private double averageQLength; 
	private int oldQLength;
    private double lastQUpdateTime;
    private static int maxQLength;
    //private double totalTime;
	
	public Checker(){
	}
	
	//getters and setters
	public boolean isBusy(){
		return busy;
	}
	public void setBusy(){
		busy = true;
	}
	public void setIdle(){
		busy = false;
	}
	public Q1 getLine(){
		return line;
	}
	public double getBusyTime(){
		return busyTime;
	}
	public double getIdleTime(){
		return idleTime;
	}
	//public double getTotalTime(){
		//return totalTime;
	//}
	public int getMaxQLength(){
		return maxQLength;
	}
	public double getAverageQLength(){
		return averageQLength;
	}

    public void updateBusyTimeStats(double time) {
        busyTime += time - lastUpdateTime;
        lastUpdateTime = time;
    }        

    public void updateIdleTimeStats(double time) {
        idleTime += time - lastUpdateTime;
        lastUpdateTime = time;
    }
    
    public void updateQueueStats(double time, int qlen) {
        if (qlen > maxQLength)
          maxQLength = qlen;
        averageQLength += (double)((time - lastQUpdateTime)*oldQLength);
        lastQUpdateTime = time;
        oldQLength = qlen;
        System.out.println("avg q length:" + averageQLength);
    }
}
