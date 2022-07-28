package directedTree;

import java.util.List;

import graph.*;

/**
 * Interface to Directed Trees.
 * A Directed Tree is is a directed graph with a designated node,
 * the root, such that for each node, there is exactly one path from  
 * the root to that node
 */
public interface DirectedTree<V> {

	
	/**
	 * Adds a child to a given parent of a directedTree
	 * @param parent value of parent
	 * @param child value of child
	 */
	void addChild(V parent, V child);

	/**
	 * Returns the list of children from a given parent 
	 * @param parent value of parent
	 * @return list of children
	 */
	List<V> getChildren(V parent);
	
	/**
	 * Returns the value of the root
	 * @return value of the root
	 */
	V getRoot();

	/**
	 * Returns the value of the parent from a given child
	 * @param child value of child
	 * @return value of the parent
	 */
	V getParent(V child);


	/**
	 * Creates a directed spanning Tree from a SpanningTree
	 * @param s SpanningTree
	 */
	void loadFromSpanningTree(SpanningTree<V> s);

}