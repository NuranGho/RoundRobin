//Nuran Ghoneim

package cpu_scheduler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] arg) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter filename: ");
		String fileName = sc.nextLine();
		System.out.print("Enter Time Quantum: ");
		int timequantum = sc.nextInt();
		Scanner input = new Scanner(new File(fileName));
		input.useDelimiter("(,|\\n)");  // seperating each output into another line so each process prints out on its own line
		input.nextLine();
		ArrayList<Process> csvFile = new ArrayList<>();
		while (input.hasNext()) {
			Process process = new Process(input.nextInt(), input.nextInt(), input.nextInt());
			csvFile.add(process);
		}

		Scheduler myScheduler = new Scheduler(csvFile, timequantum);
		myScheduler.rrfuntions();
		sc.close();
		input.close();
	}
}

