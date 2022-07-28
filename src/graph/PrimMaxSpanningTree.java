package graph;

/**
 * Implements the Prim Algorithm for constructing a tree from a graph.
 */
public class PrimMaxSpanningTree<V> implements SpanningTreeAlgorithm<V> {
	
	private int numb_vertices;
	protected AbstractUndirectedWeightedGraph<V> graph;
	
	/** 
	 * Creates a new spanning tree computation from a graph
	 * @param g input graph from which to compute a spanning tree
	 */
	public PrimMaxSpanningTree(AbstractUndirectedWeightedGraph<V> g) {
		this.graph=g;
		this.numb_vertices=this.graph.getNumbVertices();
	}
	
	 /**A utility function to find the vertex with minimum key 
	 * value, from the set of vertices not yet included in MST 
	 * @param key key value
	 * @param mstSet array with true if vertex is visited, false if not visited
	 * @return
	 */
	int minKey(double key[], Boolean mstSet[]) 
	 { 
	     // Initialize min value 
	     double max = -Double.MAX_VALUE;
	     int min_index = -1; 
	     for (int v = 0; v <this.numb_vertices; v++) 
	     {
	    	 
	         if (mstSet[v] == false && key[v] > max) { 
	             max = key[v]; 
	             min_index = v; 
	         } 
	         
	     }

	     return min_index; 
	 } 
		 
	/** 
	 * Method which uses Prim's Spanning tree algorithm to compute
	 * the spanning tree
	 * @return Minimum spanning tree calculated from the graph
	 */
	@Override
	public SpanningTree<V> getSpanningTree() {
		
		 double [][] g=graph.getWeightsMatrix();
	     // Array to store constructed MST 
	     int parent[] = new int[graph.getNumbVertices()]; 

	     // Key values used to pick minimum weight edge in cut 
	     double key[] = new double[graph.getNumbVertices()]; 

	     // To represent set of vertices not yet included in MST 
	     Boolean mstSet[] = new Boolean[graph.getNumbVertices()]; 

	     // Initialize all keys as -INFINITE 
	     for (int i = 0; i < graph.getNumbVertices(); i++) { 
	         key[i] = -Double.MAX_VALUE; 
	         mstSet[i] = false; 
	     } 

	     // Always include first 1st vertex in MST. 
	     key[0] = 0; // Make key 0 so that this vertex is 
	     // picked as first vertex 
	     parent[0] = -1; // First node is always root of MST 

	     // The MST will have getNumbVertecis vertices 
	     for (int count = 0; count < graph.getNumbVertices() - 1; count++) { 
	         // Pick the minimum key vertex from the set of vertices 
	         // not yet included in MST 
	         int u = minKey(key, mstSet); 
	         
	         // Add the picked vertex to the MST Set 
	         mstSet[u] = true; 

	         // Update key value and parent index of the adjacent 
	         // vertices of the picked vertex. Consider only those 
	         // vertices which are not yet included in MST 
	         for (int v = 0; v < graph.getNumbVertices(); v++) 

	             // graph[u][v] is non zero only for adjacent vertices of m 
	             // mstSet[v] is false for vertices not yet included in MST 
	             // Update the key only if graph[u][v] is bigger than key[v] 
	             if (g[u][v] > -Double.MAX_VALUE && mstSet[v] == false && g[u][v] > key[v]) { 
	                 parent[v] = u; 
	                 key[v] = g[u][v]; 
	             } 
	     }
	     
	     
	     SpanningTree<V> tree = new SpanningTree<V>(this.numb_vertices);
	     
	     for(int i=1;i<this.numb_vertices;i++) {
	    	 V vaux1=this.graph.getVertexFromIndex(i);
	    	 V vaux2=this.graph.getVertexFromIndex(parent[i]);
	    	 try {
		    	 tree.addVertex(vaux1);
		    	 tree.addVertex(vaux2);
		    	 tree.setEdgeWeight(vaux1,vaux2,g[i][parent[i]]);
			} catch (GraphIsCyclicException|MaxCapacityExceededException e) {
				e.printStackTrace();
			}
	     }
	     
		return tree;
	}

}