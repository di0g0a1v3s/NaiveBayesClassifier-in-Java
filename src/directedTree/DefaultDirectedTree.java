package directedTree;

import java.util.ArrayList;
import java.util.List;
import graph.*;

/**
 * This class implements a Directed tree through a pointer to its root
 * and several methods to operate over it.
 * All the methods necessary to the project are implemented.
 */

public class DefaultDirectedTree<V> implements DirectedTree<V> {

	TreeNode<V> root;
	
	/**
	 * Creates an DefaultDirectedTree with root vertex 
	 * @param vertex root of the new DefaultDirectedTree
	 */
	public DefaultDirectedTree(V vertex) {
		root = new TreeNode<V>(vertex);
	}
	
	/**
	 * Creates an empty DefaultDirectedTree  
	 *
	 */
	public DefaultDirectedTree() {
		
	}

	/**
	 * Adds a child to a given parent of a directedTree
	 * @param parent value of parent
	 * @param child value of child
	 */
	@Override
	public void addChild(V parent, V child) {
		
		TreeNode<V> parentNode=findTreeNode(root, parent);
		parentNode.addChild(child);
		
	}
	
	/**
	 * Recursive depth search on a tree
	 * @param iter current node of the tree
	 * @param find value of the TreeNode to find
	 * @return Node of the element to find
	 */
	protected TreeNode<V> findTreeNode(TreeNode<V> iter, V find) {
		TreeNode<V> res=iter;
		int nChilds = iter.children.size();
		
		//if find==iter.vertex
		if (find.equals(iter.vertex))
		{
			return iter;
		}
		//else
		for (int j=0; j < nChilds; j++)
		{
			//if child(j)==find
			if (find.equals(iter.children.get(j).vertex))
			{
				return iter.children.get(j);
			}
			else
			{
				if (find.equals(res.vertex))
				{
					return res;
				}
				res=findTreeNode(iter.children.get(j), find);
			}
		}
		
		return res;
	}
	
	/**
	 * Returns the list of children from a given parent 
	 * @param parent value of parent
	 * @return list of children
	 */
	@Override
	public List<V> getChildren(V parent) {
		TreeNode<V> parentNode=null;
		List<V> vertix = new ArrayList<V>();
		
		parentNode=findTreeNode(root, parent);
		int i = parentNode.children.size();
		if (i==0) return null;
		
		
		for (int j=0; j < i; j++)
		{
			vertix.add(parentNode.children.get(j).vertex);
		}
		
		return vertix;
	}
	
	/**
	 * Returns the value of the root
	 * @return value of the root
	 */
	@Override
	public V getRoot() {

		return root.vertex;
		
	}
	
	/**
	 * Returns the value of the parent from a given child
	 * @param child value of child
	 * @return value of the parent
	 */
	@Override
	public V getParent(V child) {
		
		if (child.equals(root.vertex))
		{
			return null;
		}
		
		TreeNode<V> childNode = findTreeNode(root, child);

		
		return childNode.parent.vertex;

	}
	
	/**
	 * Recursive copy from spanningTree 
	 * @param node current node of the tree
	 * @param s spanningTree to copy
	 * @param index spanningTree to copy
	 * @param previous index of parent
	 */
	private void fillnode(TreeNode<V> node, SpanningTree<V> s, int index, int previous) {
			
		int size=s.getNumbVertices();
		double[][] matrix=s.getWeightsMatrix();
		
		for (int j=0; j < size; j++)
		{
			if (matrix[index][j]!=-Double.MAX_VALUE && j!=previous)
			{
				TreeNode<V> childnode=node.addChild(s.getVertexFromIndex(j));
				fillnode(childnode,s, j, index);	
			}
		}
	}
	
	/**
	 * Creates a directed spanning Tree from a SpanningTree
	 * adjacency matrix
	 * @param s SpanningTree
	 */
	@Override
	public void loadFromSpanningTree(SpanningTree<V> s) {

		int index=s.findHeight();
		V vertex= s.getVertexFromIndex(index);
		
		root=new TreeNode<V>(vertex);
		
		fillnode(root, s, index, index);	
	}
	
	/**
	 * Recursive iteration over a tree to put vertex edges on string  
	 * @param iter current node of the tree
	 * @param to_str string to fill
	 * @param previous index of parent
	 * @return string with edges of tree
	 */
	private String getStringTree(TreeNode<V> iter, String to_str) {
		String aux = new String();
		int nChilds = iter.children.size();
		//System.out.println("nChilds:" +nChilds);
		for (int i=0; i < nChilds; i++)
		{
			to_str+=(iter.children.get(i).vertex+ ":" + iter.vertex +"\n");
			to_str+=getStringTree(iter.children.get(i),aux);
		}
		
		return to_str;
	}
	
	
	@Override
	public String toString() {
		
		String to_str= new String();
		
		to_str+=(root.vertex+ ":\n");
		to_str=getStringTree(root,to_str);
		
		return to_str;
	}
		

}

