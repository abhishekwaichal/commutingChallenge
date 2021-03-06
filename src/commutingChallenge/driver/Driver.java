/**
 * 
 */
package commutingChallenge.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Abhishek Waichal
 * 
 * Class 'Driver' :
 *  - Takes an input file and then parses it to get location(latitude and longitude) of all the companies 
 * 	- Creates adjacency matrix from company locations obtained from input file by calculating distances between them.
 *  - Calculates the shortest possible route which visits each coordinate once starting from the point 1.
 * 
 */
public class Driver {

	private int numNodes;
	private Stack<Integer> stack;
	private FileReader in = null;
	private String currentDirectory = System.getProperty("user.dir");
	private String FilePath;
	private BufferedReader br = null;

	public Driver(String args) {

		// FilePath = "//" + args; //windows
		FilePath = "\\" + args; // unix
		// System.out.println(currentDirectory + FilePath);

		try {
			in = new FileReader(currentDirectory + FilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		br = new BufferedReader(in);

		stack = new Stack<Integer>();
	}

	
	/**
	 * @param adjMat Double
	 * Adjacency Matrix to store the distances between companies  
	 * 
	 *  This method is used to calculate the shortest possible route which visits each coordinate once starting from the point 1.
	 * 
	 */
	public void tsp(Double adjMat[][]) {

		int curr, dst = 0, i;
		Double min = Double.MAX_VALUE;
		boolean minFlag = false;

		numNodes = adjMat[1].length - 1;

		int[] visited = new int[numNodes + 1];

		// As start Point is node number 1
		visited[1] = 1;
		stack.push(1);
		System.out.println(1);

		while (!stack.isEmpty()) {
			curr = stack.peek();
			i = 1; 
			min = Double.MAX_VALUE;
			while (i <= numNodes) {
				if (adjMat[curr][i] > 0 && visited[i] == 0) {
					if (min > adjMat[curr][i]) {
						minFlag = true;
						min = adjMat[curr][i];
						dst = i;
					}
				}
				i++;
			}
			if (minFlag) {
				visited[dst] = 1;
				stack.push(dst);
				System.out.println(dst);
				minFlag = false;
				continue;
			}
			stack.pop();
		}
	}
	
	/**
	 * @param lat1 Double
	 * @param lon1 Double
	 * @param lat2 Double
	 * @param lat2 Double
	 * 
	 * Takes input as location of two points (the latitude and longitude) and 
	 * calculates and returns the distance between them. 
	 *
	 */
	public Double distance(Double lat1, Double lon1, Double lat2, Double lon2) {

		Double R = 3958.75; // Radius of earth in miles
		Double dlon, dlat;

		dlon = Math.toRadians(lon2 - lon1);
		dlat = Math.toRadians(lat2 - lat1);

		Double a;
		a = (Math.pow(Math.sin(dlat / 2), 2)) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2))
				* (Math.pow(Math.sin(dlon / 2), 2));

		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		Double d = R * c;// (where R is the radius of the Earth)

		return d;
	}

	/**
	 * 
	 * @param args String[]
	 * Takes input file as its first argument a path to a filename.
	 *  
	 * @throws Throwable
	 * 
	 */
	public static void main(String[] args) throws Throwable {

		int number_of_nodes;
		Driver comm = new Driver(args[0]);// ("inputCoordinates.txt"); First input argument is the file name. 

		number_of_nodes = 10;

		Double adjacency_matrix[][] = new Double[number_of_nodes + 1][number_of_nodes + 1];
		List<Double[][]> loc = new ArrayList<Double[][]>();

		
		// Parse input file to extract locations(Latitude and Longitude) of all the input companies 
		for (int j = 0; j < number_of_nodes; j++) {
		
			String line = comm.br.readLine();

			CharSequence lat = line.subSequence(line.indexOf("(") + 1,
					line.lastIndexOf(","));
			Double lat1 = Double.parseDouble(lat.toString());

			CharSequence lon = line.subSequence(line.lastIndexOf(",") + 1,
					line.lastIndexOf(")"));
			Double lon1 = Double.parseDouble(lon.toString());

			Double set[][] = new Double[1][2];

			set[0][0] = lat1;
			set[0][1] = lon1;
			loc.add(set);

		}

		/*
		  for (int j = 0; j < number_of_nodes; j++) 
		  System.out.println(" j = " + j + " " + loc.get(j)[0][0] + " " + loc.get(j)[0][1]);
		  System.exit(0);
		 */

		// Calculate Distance between all nodes(extracted from coordinates) and
		// store it in the adjacency matrix
		for (int i = 1; i <= number_of_nodes; i++) {
			for (int j = 1; j <= number_of_nodes; j++) {
				adjacency_matrix[i][j] = comm.distance(
						loc.get(i - 1)[0][1], loc.get(i - 1)[0][0],
						loc.get(j - 1)[0][1], loc.get(j - 1)[0][0]);
			}
		}

		/*
		  for (int i = 1; i <= number_of_nodes; i++) {
			  System.out.println();
			  for (int j = 1; j <= number_of_nodes; j++) {
			  	System.out.print("\t\t"+adjacency_matrix[i][j]); 
			  } 
		  }
		  System.exit(0);
		 */
		
		System.out.println("The companys are visited in following order: ");
		// Use traveling salesman - Greedy Algorithm 
		comm.tsp(adjacency_matrix);
	}
}
