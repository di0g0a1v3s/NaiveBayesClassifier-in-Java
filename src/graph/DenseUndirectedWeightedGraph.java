package graph;

/**
 * Implements a Dense Weighted graph.
 */
public class DenseUndirectedWeightedGraph<V> extends AbstractUndirectedWeightedGraph<V> {
	
	
	
	/** 
	 * Creates a new graph
	 * @param max_numb_vertices Maximum number of vertices in the graph
	 */
	public DenseUndirectedWeightedGraph(int max_numb_vertices) {
		super(max_numb_vertices);
		
	}

	
	/** 
	 * This method connects two vertices with the default weight of 1
	 * @param v1 Vertex1 to connect
	 * @param v2 Vertex2 to connect
	 * @return true if edge was added, false otherwise
	 */
	@Override
	public boolean addEdge(V v1, V v2) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1) {
			this.weight_matrix[index1][index2]=1;
			this.weight_matrix[index2][index1]=1;
			return true;
		}
		return false;
	}
	
	/** 
	 * This method connects two vertices
	 * @param v1 Vertex1 to connect
	 * @param v2 Vertex2 to connect
	 * @param weight weight of the edge
	 * @return true if edge was added, false otherwise
	 */
	@Override
	public boolean setEdgeWeight(V v1, V v2, double weight) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1) {
			this.weight_matrix[index1][index2]=weight;
			this.weight_matrix[index2][index1]=weight;
			return true;
		}
		
		return false;
	}


	
}