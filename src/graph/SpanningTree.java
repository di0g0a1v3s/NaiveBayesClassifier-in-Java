package graph;

/** 
 * Implements a tree data structure.
 */
public class SpanningTree<V> extends AbstractUndirectedWeightedGraph<V> {

	
	/** Creates a new tree data structure
	 * @param max_numb_vertices Maximum number of vertices tree can have
	 */
	public SpanningTree(int max_numb_vertices) {
		super(max_numb_vertices);
	}

	
	/** 
	 * This method connects two vertices and checks if the 
	 * connection creates a cycle in the tree, if it is created
	 * the connection creation is aborted
	 * @param v1 Vertex 1 to connect
	 * @param v2 Vertex 2 to connect
	 * @return true if edge was added, false otherwise
	 * @throws GraphIsCyclicException if a cycle is created in the tree
	 */
    @Override
	public boolean addEdge(V v1, V v2) throws GraphIsCyclicException {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		double temp=this.weight_matrix[index1][index2];
		
		if(index1!=-1 && index2!=-1&&index1!=index2) {//vertices exist
			this.weight_matrix[index1][index2]=1;
			this.weight_matrix[index2][index1]=1;
			if(!this.isGraphCyclic()&&this.currSize>2)//revert changes
			{	
				this.weight_matrix[index1][index2]=temp;
				this.weight_matrix[index2][index1]=temp;
				return false;
			}
			return true;
		}
		else 
			throw new GraphIsCyclicException();
	}
    
    /** 
     * This method connects two vertices and checks if the 
	 * connection creates a cycle in the tree, if it is created
	 * the connection creation is aborted
	 * @param v1 Vertex1 to connect
	 * @param v2 Vertex2 to connect
	 * @param weight weight of the edge
	 * @return true if edge was added, false otherwise
	 * @throws GraphIsCyclicException if a cycle is created in the tree
	 */
	@Override
	public boolean setEdgeWeight(V v1, V v2, double weight) throws GraphIsCyclicException {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		double temp=this.weight_matrix[index1][index2];
		
		if(index1!=-1 && index2!=-1&&index1!=index2) {//vertices exist
			this.weight_matrix[index1][index2]=weight;
			this.weight_matrix[index2][index1]=weight;
			if(!this.isGraphCyclic()&&this.currSize>2)//revert changes
			{	
				this.weight_matrix[index1][index2]=temp;
				this.weight_matrix[index2][index1]=temp;
				return false;
			}
			return true;
		}
		else 
			throw new GraphIsCyclicException();
	}	
	
	/** 
	 * Recursive method that finds the maximum height
	 * that given element generates 
	 * @param i element to iterate
	 * @param previous index of the i variable
	 * @return height of the element
	 */
    private int findDepth(int i, int previous) {
    	int aux =i;
		int res=0;
		int res1=0;
		

		for (int j=0; j < currSize; j++)
		{
			if (weight_matrix[aux][j]!=-Double.MAX_VALUE && j!=previous)
			{
				res1=findDepth(j, aux);
				res1++;
				if (res1>res)res=res1;	
			}
		}
		
		return res;
	}
    
    /** 
	 * Finds the index of the adjacency matrix
	 * that has the shortest height
	 * @return the index of the shortest height
	 */
    public int findHeight() { 
    	int i;

        int depth[] = new int[currSize]; 
        for (i = 0; i < currSize; i++) { 
            depth[i] = 0; 
        } 
  
        for (i=0; i < currSize; i++)
        {
        	depth[i]=findDepth(i,i); 
        }
        int index=0;
        int ht = depth[0]; 
        for (i = 1; i < currSize; i++) { 
            if (ht > depth[i]) { 
                ht = depth[i]; 
                index=i;
                
            } 
        } 
        return index; 
    }

}