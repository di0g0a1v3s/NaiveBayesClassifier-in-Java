package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is a Weighted graph abstraction.
 */
public abstract class AbstractUndirectedWeightedGraph<V> implements WeightedGraph<V> {

	protected double[][] weight_matrix;
	protected List<V> vertices;
	
	protected int currSize;
	protected int max_numb_vertices;
	
	/**
	 * Create a graph object with a maximum of max_numb_vertices vertices
	 * @param max_numb_vertices maximum number of vertices
	 */
	public AbstractUndirectedWeightedGraph(int max_numb_vertices) {
		super();
		this.weight_matrix=new double[max_numb_vertices][max_numb_vertices];
		for(int i=0; i < max_numb_vertices; i++)
		{
			for(int j=0; j < max_numb_vertices; j++)
			{
				weight_matrix[i][j]=-Double.MAX_VALUE;
			}
		}
		this.vertices= new LinkedList<V>();
		this.max_numb_vertices=max_numb_vertices;
		this.currSize=0;
	}
	
	/**	 
	 * A recursive function that uses visited[] and parent 
	 * to detect cycle in subgraph reachable from vertex v. 
	 * @return true if the graph is cyclic, false if acyclic
	 */
    protected boolean isCyclic(int v, Boolean visited[], int parent) 
    { 	        
        // Mark the current node as visited 
        visited[v] = true; 
        Integer i; 
  
        // Recur for all the vertices adjacent to this vertex 
        for(i=0;i<this.max_numb_vertices;i++) {
        	if(v!=i && this.weight_matrix[v][i]>0) {
        		// If an adjacent is not visited, then recur for that adjacent 
        		if(!visited[i]) {
        			if(isCyclic(i,visited,v))
        				return true;
            	}
        		// If an adjacent is visited and not parent of  
                // current vertex, then there is a cycle. 
                else if (i != parent) 
                   return true; 
        	}
        }
        return false; 
    }
    
    /**
     * Determine if the graph is cyclic
	 * @return true if the graph is cyclic, false if acyclic
	 */
    public boolean isGraphCyclic() 
    { 
        // Mark all the vertices as not visited and not part 
        // of recursion stack 
        Boolean visited[] = new Boolean[this.max_numb_vertices]; 
        for (int i = 0; i <this.max_numb_vertices; i++) 
            visited[i] = false; 
  
        // The call to isCyclicUtil serves multiple purposes 
        // It returns true if graph reachable from vertex 0 
        // is cyclic. It also marks all vertices reachable 
        // from 0. 
        if (isCyclic(0, visited, -1))
            return false; 
  
        return true; 
    } 
    
    /**
     * Determine if the graph is acyclic and all the vertices have a connection
	 * @return true if the graph is acyclic and all the vertices have a connection,
	 * false otherwise
	 */
    public boolean isTree()
    { 
        // Mark all the vertices as not visited and not part 
        // of recursion stack 
        Boolean visited[] = new Boolean[this.max_numb_vertices]; 
        for (int i = 0; i <this.max_numb_vertices; i++) 
            visited[i] = false; 

        // The call to isCyclicUtil serves multiple purposes 
        // It returns true if graph reachable from vertex 0 
        // is cyclic. It also marks all vertices reachable 
        // from 0. 
        if (isCyclic(0, visited, -1))
            return false; 

        // If we find a vertex which is not reachable from 0 
        // (not marked by isCyclicUtil(), then we return false 
        for (int u = 0; u <this.max_numb_vertices; u++) 
            if (!visited[u]) 
                return false; 

        return true; 
    } 
	
    /**
     * Add a vertex to the graph
	 * @param vertex New vertex to add to the graph
	 * @return true if vertex was added , false otherwise
	 * @throws MaxCapacityExceededException if the graph capacity is exceeded
	 */
	@Override
	public boolean addVertex (V vertex) throws MaxCapacityExceededException {

		if(vertex == null)
			throw new NullPointerException();
			
		if(this.vertices.contains(vertex))
			return false;
		
		if(currSize==this.max_numb_vertices)
			throw new MaxCapacityExceededException();
		
		this.vertices.add(vertex);
		this.currSize++;
		return true;
	}
	
	/**
	 * Remove an edge from the graph
	 * @param v1 Vertex 1 to disconnect
	 * @param v2 Vertex 2 to disconnect
	 * @return true if vertex was removed, false otherwise
	 */
	@Override
	public boolean removeEdge(V v1, V v2) {
		
		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1) {
			this.weight_matrix[index1][index2]=-Double.MAX_VALUE;
			this.weight_matrix[index2][index1]=-Double.MAX_VALUE;
			return true;
		}
		return false;
	}
	
	
	/**
	 * Get the vertices from the graph
	 * @return a list with the vertices from the graph
	 */
	@Override
	public List<V> getVertices() {
		
		return new LinkedList<V>(this.vertices);
	}
	
	/**
	 * Get the weight of an edge from the graph
	 * @param v1 vertex 1 
	 * @param v2 vertex 2
	 * @return the value of the edge between the two vertices
	 */
	@Override
	public double getEdgeWeight(V v1, V v2) {
		
		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1)
			return (this.weight_matrix[index1][index2]);
		
		return -1;
	}
	
	@Override
	public String toString() {
		
		String to_str= new String();
		for(int i=0;i<this.currSize;i++) {
			for(int j=0;j<this.currSize;j++)
			{
				to_str+=(this.getWeightsMatrix()[i][j]+ " ");
			}
			to_str+="\n";
		}
		
		to_str+=("Vertices:"+this.getVertices()+"\n");
		return to_str;
	}
	
	/**
	 * Get the number of vertices in the graph
	 * @return number of vertices in the graph
	 */
	public int getNumbVertices() {
		return this.currSize;
	}
	
	/**
	 * Get a specific vertex from the graph
	 * @param index index of the vertex
	 * @return the vertex in position index
	 */
	public V getVertexFromIndex(int index) {
		return this.vertices.get(index);
	}
	
	/**
	 * Get a matrix with the weights between vertices
	 * @return matrix of weights
	 */
	public double [][] getWeightsMatrix(){
		
		double[][] copy = new double[currSize][currSize];
		for(int i=0; i<currSize; i++)
			  for(int j=0; j<currSize; j++)
				  copy[i][j]=weight_matrix[i][j];
		
		return copy;
	}

}