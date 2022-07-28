package graph;
import java.util.List;

/**
 * Root interface of graph. A weighted graph is a collection
 * of vertices and edges, where there is a weight associated
 * with each edge. 
 */
public interface WeightedGraph<V> {

	
	 /**
     * Add a vertex to the graph
	 * @param vertex New vertex to add to the graph
	 * @return true if vertex was added , false otherwise
	 * @throws MaxCapacityExceededException if the graph capacity is exceeded
	 */
	boolean addVertex(V vertex) throws MaxCapacityExceededException;

	/** 
	 * This method connects two vertices with the default weight of 1
	 * @param v1 Vertex1 to connect
	 * @param v2 Vertex2 to connect
	 * @return true if edge was added, false otherwise
	 */
	boolean addEdge(V v1, V v2) throws GraphIsCyclicException;

	
	/** 
	 * This method connects two vertices
	 * @param v1 Vertex1 to connect
	 * @param v2 Vertex2 to connect
	 * @param weight weight of the edge
	 * @return true if edge was added, false otherwise
	 */
	boolean setEdgeWeight(V v1, V v2, double weight) throws GraphIsCyclicException;
	
	
	/**
	 * Remove an edge from the graph
	 * @param v1 Vertex 1 to disconnect
	 * @param v2 Vertex 2 to disconnect
	 * @return true if vertex was removed, false otherwise
	 */
	boolean removeEdge(V v1, V v2);
	
	
	/**
	 * Get the vertices from the graph
	 * @return a list with the vertices from the graph
	 */
	List<V> getVertices();

	/**
	 * Get the weight of an edge from the graph
	 * @param v1 vertex 1 
	 * @param v2 vertex 2
	 * @return the value of the edge between the two vertices
	 */
	double getEdgeWeight(V v1, V v2);
	
	
	/**
     * Determine if the graph is cyclic
	 * @return true if the graph is cyclic, false if acyclic
	 */
	boolean isGraphCyclic();
	
	/**
     * Determine if the graph is acyclic and all the vertices have a connection
	 * @return true if the graph is acyclic and all the vertices have a connection,
	 * false otherwise
	 */
	boolean isTree();
	
	
	/**
	 * Get the number of vertices in the graph
	 * @return number of vertices in the graph
	 */
	int getNumbVertices();
	
}