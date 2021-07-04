//Nuran Ghoneim
package cpu_scheduler;

import java.util.*;
import java.util.*;

public class Scheduler {
	int timer;
	ArrayList<Process> listOfJobs;
	ArrayList<Process> readyQ;
	ArrayList<Process> endOfProcesses;
	int timeQuantum;
	int contextSwitch; 
						
	Process cpu;
	int counter;

	public Scheduler(ArrayList<Process> listOfJobs, int timeQuantum) {
		this.timeQuantum = timeQuantum;
		this.listOfJobs = listOfJobs;
	}

	public void rrfuntions() {
		timer = 0;
		contextSwitch = 0;
		cpu = null;
		readyQ = new ArrayList<>();
		endOfProcesses = new ArrayList<>();

		while (!readyQ.isEmpty() || !listOfJobs.isEmpty() || cpu != null) {
			
			for (int i = 0; i < listOfJobs.size(); i++) {   // adding to readyQueue
				if (listOfJobs.get(i).arrivalTime == timer) {
					readyQ.add(listOfJobs.remove(i));
				}
			}
			if (cpu == null) {    // adding to CPU
				cpu = readyQ.remove(0);
			}
			counter++;
			cpu.serviceTime++;

			if (cpu.burstTime == cpu.serviceTime) { // process is complete
				cpu.completionTime = timer; // completion time is set
				endOfProcesses.add(cpu); 
				cpu = null;
				contextSwitch++;
				counter = 0;
			} else if (counter == timeQuantum) { // if it exceeds the timeQuantum
				readyQ.add(cpu);
				cpu = null;
				contextSwitch++;
				counter = 0;
			}
			timer++; 
		}

		double turnAroundTimeTotal = 0.0;
		double waitingTimeTotal = 0.0;
		double utilizationTotal = 0.0;
		
	//calculating the functions below
		for (int i = 0; i < endOfProcesses.size(); i++) {
			turnAroundTimeTotal += endOfProcesses.get(i).completionTime - endOfProcesses.get(i).arrivalTime;
			waitingTimeTotal += (endOfProcesses.get(i).completionTime - endOfProcesses.get(i).arrivalTime)
					- endOfProcesses.get(i).burstTime;
			utilizationTotal += endOfProcesses.get(i).burstTime;
		}
		double avgTurnAroundTime = turnAroundTimeTotal / endOfProcesses.size();
		double avgWaitingTime = waitingTimeTotal / endOfProcesses.size();
		double cpuUtilization = (utilizationTotal - (contextSwitch * .01)) / timer;
		double throughput = (double) endOfProcesses.size() / timer;

		System.out.println("");
		System.out.println("CPU Utilization: " + cpuUtilization);
		System.out.println("Throughput: " + throughput);
		System.out.println("Average Waiting Time: " + avgWaitingTime);
		System.out.println("Average Turnaround Time: " + avgTurnAroundTime); 

	}

}
