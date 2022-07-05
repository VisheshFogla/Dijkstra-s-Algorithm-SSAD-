/**
 * 
 * @author Vishesh Fogla (visheshfogla)
 * @version 4 May 2022
 * 
 *          Class that defines a vertex entity that has an index and cost to
 *          reach that vertex. The attributes are vertex and cost to reach that
 *          vertex. 
 */
public class Vertex {
	private int idx;
	private int cost;

	public Vertex(int index, int weight) {
		idx = index;
		cost = weight;
	}

	public int getCost() {
		return cost;
	}

	public int getIdx() {
		return idx;
	}
}
