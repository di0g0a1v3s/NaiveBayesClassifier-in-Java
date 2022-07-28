package directedTree;

import java.util.LinkedList;
import java.util.List;

/**
 * This class implements nodes that can be used on directed trees
 * and several methods to operate over them.
 * All the methods necessary to the project are implemented.
 */
public class TreeNode<V> {

	TreeNode<V> parent;
	List<TreeNode<V>> children;
	protected V vertex;
	
	/**
	 * Creates a node
	 * @param vertex value of the vertex of the node
	 */
	public TreeNode(V vertex) {
		this.vertex = vertex;
		this.children = new LinkedList<TreeNode<V>>();
	}

	/**
	 * Adds a child to node 
	 * @param vertex value of the vertex child
	 * @return node of the new child
	 */
	public TreeNode<V> addChild(V vertex) {
		TreeNode<V> childNode = new TreeNode<V>(vertex);
		childNode.parent = this;
		this.children.add(childNode);
		
		return childNode;
	}
	

	@Override
	public String toString() {
		return "(parent=" + parent + ", vertex=" + vertex +")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode<?> other = (TreeNode<?>) obj;
		if (vertex == null) {
			if (other.vertex != null)
				return false;
		} else if (!vertex.equals(other.vertex))
			return false;
		return true;
	}

}