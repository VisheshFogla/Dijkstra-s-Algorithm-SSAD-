import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 
 * @author Vishesh
 * @version 4 May 2022
 * 
 *          The class builds a table for graph and implements the algorithm for
 *          Dijkstra using Priority Queues
 * 
 */
public class GraphTable {
	private WeightList weightList;
	private ArrayList<ArrayList<Vertex>> vertexList;
	private ArrayList<Integer>[] path;
	private int totalVertex;
	private int begin;

	/**
	 * The constructor takes the arguments that consists of graph file and output
	 * file
	 * 
	 * @param args - graph file and output file
	 */
	public GraphTable(String[] args) {
		weightList = new WeightList(args);
		vertexList = weightList.getVertexList();
		begin = weightList.getBeginVertex();
		totalVertex = weightList.getTotalVertices();
	}

	/**
	 * Method that implements the algorithm for getting the shortest path and the
	 * minimum weight
	 * 
	 * @param args - graph file and output log file
	 */
	@SuppressWarnings("unchecked")
	public void shortestPathAlgo(String args[]) {

		path = new ArrayList[totalVertex];

		// initializing
		for (int i = 0; i < totalVertex; i++) {
			path[i] = new ArrayList<Integer>();
		}

		begin = weightList.getBeginVertex();

		int dist[] = new int[weightList.getTotalVertices()];
		for (int i = 0; i < totalVertex; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[begin] = 0;

		PriorityQueue<Vertex> pq = new PriorityQueue<>((v1, v2) -> v1.getCost() - v2.getCost());

		pq.add(new Vertex(begin, 0));

		while (pq.size() > 0) {
			Vertex current = pq.poll();

			for (int i = 0; i < vertexList.get(current.getIdx()).size(); i++) {
				if (dist[current.getIdx()] + vertexList.get(current.getIdx()).get(i).getCost() < dist[vertexList
						.get(current.getIdx()).get(i).getIdx()]) {
					dist[vertexList.get(current.getIdx()).get(i).getIdx()] = vertexList.get(current.getIdx()).get(i)
							.getCost() + dist[current.getIdx()];

					path[vertexList.get(current.getIdx()).get(i).getIdx()].clear();

					path[vertexList.get(current.getIdx()).get(i).getIdx()]
							.add(vertexList.get(current.getIdx()).get(i).getIdx());
					path[vertexList.get(current.getIdx()).get(i).getIdx()].addAll(0, getPath(current.getIdx(), path));

					pq.add(new Vertex(vertexList.get(current.getIdx()).get(i).getIdx(),
							dist[vertexList.get(current.getIdx()).get(i).getIdx()]));
				}
			}
		}

		weightList.displayWeights(args, dist, path);
	}

	/*
	 * Private helper recursive function that helps to get the path of the vertex
	 * from the starting vertex.
	 */
	private ArrayList<Integer> getPath(int index, ArrayList<Integer>[] path) {
		ArrayList<Integer> temp = new ArrayList<Integer>();

		temp.add(0, index);

		if (path[index].size() > 1) {
			temp.addAll(0, getPath(path[index].get(path[index].size() - 2), path));
		} else {
			return temp;
		}

		return temp;
	}
}
