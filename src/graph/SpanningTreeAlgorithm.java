package graph;

/**
 * Interface that provides a service to compute a spanning tree, based on a specific algorithm.
 */
public interface SpanningTreeAlgorithm<V> {

	/**
	 * Computes and returns a Spanning Tree based on the algorithm implemented
	 * @return a spanning tree
	 */
	SpanningTree<V> getSpanningTree();

}