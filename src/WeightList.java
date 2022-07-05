import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * 
 * @author Vishesh Fogla (visheshfogla)
 * @version 4 May 2022
 * 
 *          The class creates a list of arraylist that consists of vertices. The
 *          class also reads the graph and parses it to create a weightList.
 *
 */
public class WeightList {
	private int begin;
	private int totalVertices;
	private ArrayList<ArrayList<Vertex>> vertexList;

	/**
	 * @return the total number of vertices in the graph
	 */
	public int getTotalVertices() {
		return totalVertices;
	}

	/**
	 * @return the Weight list that consists of all the vertex and their weights
	 */
	public ArrayList<ArrayList<Vertex>> getVertexList() {
		return vertexList;
	}

	/**
	 * @return the starting vertex of the graph
	 */
	public int getBeginVertex() {
		return begin;
	}

	/**
	 * Constructor that calls parse graph to process the graph
	 * 
	 * @param args - graph file to read and output file to print
	 */
	public WeightList(String[] args) {

		try (RandomAccessFile graph = new RandomAccessFile(args[0], "r")) {
			try (FileWriter fw = new FileWriter(args[1])) {
				parseGraph(graph, fw);
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that reads the graph file and then creates a Weight List that consists
	 * of all the Vertices and their weights.
	 * 
	 * @param graphFile - file to read graph from
	 * @param fw        - fileWriter that prints to output file
	 * @throws IOException
	 */
	public void parseGraph(RandomAccessFile graphFile, FileWriter fw) throws IOException {

		String store_line = graphFile.readLine();

		totalVertices = Integer.parseInt(store_line.split(" ")[3]);

		vertexList = new ArrayList<ArrayList<Vertex>>(totalVertices);

		int m = 0;

		while (m < totalVertices) {
			vertexList.add(new ArrayList<Vertex>());
			m++;
		}

		store_line = graphFile.readLine();

		begin = Integer.parseInt(store_line.split(" ")[8]);

		fw.write("  Node  | Successors\n");
		fw.write("----------------------------------------------------------------------\n");

		graphFile.readLine();
		graphFile.readLine();
		graphFile.readLine();

		store_line = graphFile.readLine();

		int i = 0;

		while (i < totalVertices) {

			String[] idx = store_line.split("\\s+");

			if (i < 10) {
				fw.write(" ");
			}

			fw.write("     " + i + " |");

			int k = 3;

			while (k < totalVertices + 3) {
				if (Integer.parseInt(idx[k]) > 0) {

					fw.write("\t" + (k - 3) + ": " + idx[k]);

					if (idx[k] != null) {

						Vertex v = new Vertex(k - 3, Integer.parseInt(idx[k]));

						if (v != null) {

							vertexList.get(i).add(v);
						}
					}
				}

				k++;
			}
			fw.write("\n");
			store_line = graphFile.readLine();
			i++;
		}

		fw.write("\nStart vertex is: " + begin);

	}

	/**
	 * Method to print weights and shortest path from the starting vertex
	 * 
	 * @param args  - graph file and output file to print to
	 * @param dist  - array containing all the distances from the starting vertex
	 * @param paths - array containing the shortest path from the starting vertex.
	 */
	public void displayWeights(String args[], int[] dist, ArrayList<Integer>[] paths) {
		try {
			try (FileWriter log = new FileWriter(args[1], true)) {
				log.write("\n\n");
				log.write("\t\t   Total\n");
				log.write("\tDest | Weight | Path\n");
				log.write("----------------------------------------------------------------------\n");

				String str = "";

				for (int i = 0; i < totalVertices; i++) {

					if (paths[i].size() > 1) {
						str = paths[i].subList(1, paths[i].size()).toString();
					} else {
						str = paths[i].toString();
					}

					if (dist[i] == 2147483647) {
						log.write("\t" + i + "\t" + " |\t" + "inf" + "\t" + "  |\t"
								+ str.substring(1, str.length() - 1).replace(",", "\t") + "\n");
					} else {
						log.write("\t" + i + "\t" + " |\t" + dist[i] + "\t" + "  |\t"
								+ str.substring(1, str.length() - 1).replace(",", "\t") + "\n");
					}
				}

				log.flush();
				log.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
