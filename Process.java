//Nuran Ghoneim
package cpu_scheduler;

public class Process {
	public int processId;
	public int arrivalTime;
	public int serviceTime;
	public int burstTime;
	public int completionTime;

	public Process(int processId, int arrivalTime, int burstTime) {
		this.processId = processId; 
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.serviceTime = 0;
	}

}
