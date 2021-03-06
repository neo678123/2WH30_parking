package tue.parking2WH30;

import java.io.IOException;
import java.io.FileWriter;



public class Main {
	public SimulationHandler sim;
	public final int N = 100000;
	
	public final int strategy = 1;
	
	
	public double simulation(double occupancyRate) {
		sim = new SimulationHandler(occupancyRate, strategy);
		
		
		long start = System.nanoTime();
		// TODO: data vis
		double time = 0;
		for(int i = 0; i < N; i++) {
			//sim.printParkingLot();
			double t = sim.updateReturnTime();
			time += t;
			//System.out.println(d);
		}
		//sim.printParkingLot();
		time /= (double)N;
		long end = System.nanoTime();
		System.out.println(N + " iterations in " + (end-start)/1000000d + " ms");
		
		return time;
	}
	
	public void multipleRuns(int nrRuns, Double... occupancyRates) {
		try {
			// CSV file location
			String csvFile = "simulationOutput.csv";
			// FileWriter object to output location
			FileWriter writer = new FileWriter(csvFile);
			
			for (int j=0; j < occupancyRates.length; j++) {
				writer.write(occupancyRates[j].toString() + ", ");
				for (int i = 0; i < nrRuns; i++) {
					writer.write(((Double) this.simulation(occupancyRates[j])).toString());	
					if (i != nrRuns - 1) {
						writer.write(", ");
					}
				}
				writer.write("\n");
			}
			// close the writer
			writer.flush();
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();	
		}
		System.out.println("Done.");
	}
	
	public static void main(String... args) {
		//System.out.println((new Main()).simulation());
		Double[] occupancy = {0., 0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1, 0.11,
				0.12, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18, 0.19, 0.2, 0.21, 0.22,
				0.23, 0.24, 0.25, 0.26, 0.27, 0.28, 0.29, 0.3, 0.31, 0.32, 0.33,
				0.34, 0.35, 0.36, 0.37, 0.38, 0.39, 0.4, 0.41, 0.42, 0.43, 0.44,
				0.45, 0.46, 0.47, 0.48, 0.49, 0.5, 0.51, 0.52, 0.53, 0.54, 0.55,
				0.56, 0.57, 0.58, 0.59, 0.6, 0.61, 0.62, 0.63, 0.64, 0.65, 0.66,
				0.67, 0.68, 0.69, 0.7, 0.71, 0.72, 0.73, 0.74, 0.75, 0.76, 0.77,
				0.78, 0.79, 0.8, 0.81, 0.82, 0.83, 0.84, 0.85, 0.86, 0.87, 0.88,
				0.89, 0.9, 0.91, 0.92, 0.93, 0.94, 0.95, 0.96, 0.97, 0.98, 0.99};
		(new Main()).multipleRuns(2,occupancy);
	}
}