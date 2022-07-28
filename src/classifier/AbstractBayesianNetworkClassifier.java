package classifier;

import dataset.*;


import directedTree.DefaultDirectedTree;
import directedTree.DirectedTree;
import graph.AbstractUndirectedWeightedGraph;
import graph.DenseUndirectedWeightedGraph;
import graph.GraphIsCyclicException;
import graph.MaxCapacityExceededException;
import graph.PrimMaxSpanningTree;
import graph.SpanningTree;
import graph.SpanningTreeAlgorithm;

/**
 * This class implements a Tree Augmented Naive Bayes Classifier,
 * with a N_prime value of 0.5. This type of classifier uses a tree
 * whose nodes are the attributes of the dataset to make the 
 * classifications.
 * All the methods necessary are already implemented, except the method
 * to compute the weights between two attributes. This method
 * must be implemented in a concrete class that extends this abstract
 * class.
 *
 */
public abstract class AbstractBayesianNetworkClassifier implements Classifier {

	protected Dataset trainSet;
	protected DirectedTree<Attribute> directedTree;
	protected static final double N_prime = (double) 0.5;

	
	/**
	 * Computes the weight between two attributes
	 * @param i first attribute
	 * @param j second attribute
	 * @return weight
	 */
	protected abstract double computeWeight(Attribute i, Attribute j);

	/**
	 * Computes the observed frequency estimates (theta_ijkc) for attribute i, whose parent is i_parent,
	 * where i takes the value k, i_parent takes the value j and the class takes the value c
	 * @param i attribute
	 * @param i_parent parent of i
	 * @param j value of i_parent
	 * @param k value of i
	 * @param c value of the class
	 * @return the OFE value
	 */
	protected double computeOFE(Attribute i, Attribute i_parent, int j, int k, int c) {
		int r_i = trainSet.getMaxAttributeValue(i) + 1;
		double OFE = (computeNijkc(i,i_parent,j,k,c)+N_prime)/(computeNikc(i_parent,j,c) + r_i * N_prime);
		return OFE;
	}

	/**
	 * Computes the observed frequency estimates for the class (theta_c) where
	 * the class takes the value c
	 * @param c value of the class
	 * @return the class OFE value
	 */
	protected double computeClassOFE(int c) {
		int s = trainSet.getMaxClassValue() + 1;
		
		double class_OFE = (computeNc(c) + N_prime)/(computeN()+s*N_prime);
		
		return class_OFE;
	}

	/**
	 * Computes the joint probability distribution of a given instance i, for 
	 * a class value of c
	 * @param i instance
	 * @param c value of the class
	 * @return the class OFE value
	 */
	protected double computeJointProbabilityD(Instance i, int c) {
		double joint_prob = computeClassOFE(c);
		Attribute[] atts = trainSet.getAttributes();
		for(Attribute a:atts)
		{
			Attribute a_parent = directedTree.getParent(a);

			joint_prob*=computeOFE(a,a_parent,i.getAttValue(a_parent),i.getAttValue(a),c);
			
		}
		return joint_prob;
	}

	/**
	 * Compute number of instances in the train set where the attribute i takes the value k, the attribute i_prime
	 * takes the value j and the class takes the value c. If i or i_prime are null, those attributes are ignored
	 * @param i first attribute
	 * @param i_prime second attribute
	 * @param j value of i_prime
	 * @param k value of i
	 * @param c value of the class
	 * @return count of Nijkc
	 */
	protected int computeNijkc(Attribute i, Attribute i_prime, int j, int k, int c) {
	
		int count = 0;
		if(i == null && i_prime == null)
			return computeNc(c);
		if(i == null)
			return computeNikc(i_prime,j,c);
		if(i_prime == null)
			return computeNikc(i,k,c);
			
		for(Instance inst:trainSet)
		{
			if(inst.getAttValue(i) == k && inst.getAttValue(i_prime) == j && inst.getClassValue() == c)
			{
				count++;	
			}
		}
		
		return count;
	}
	
		
	/**
	 * Compute number of instances in the train set where the attribute i takes the value k 
	 * and the class takes the value c. If i is null, the attribute is ignored
	 * @param i attribute
	 * @param k value of i
	 * @param c value of the class
	 * @return count of Nikc
	 */
	protected int computeNikc(Attribute i, int k, int c) {
		int count = 0;
		if(i == null)
			return computeNc(c);
		for(Instance inst:trainSet)
		{
			if(inst.getAttValue(i) == k && inst.getClassValue() == c)
			{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Compute number of instances in the train set where the class takes the value c 
	 * @param c value of the class
	 * @return count of Nc
	 */
	protected int computeNc(int c) {
		int count = 0;
		
		for(Instance inst:trainSet)
		{
			if(inst.getClassValue() == c)
			{
				count++;
			}
					
		}
		return count;
	}
	
	
	/**
	 * Compute number of instances in the train set
	 * @return number of instances
	 */
	protected int computeN() {
		return trainSet.getNumberOfInstances();
	}
	
	
	/**
	 * Build the classifier from the given training dataset
	 * @param data training dataset
	 */
	@Override
	public void buildClassifier(Dataset data) {
		trainSet = data;
		Attribute[] atts = data.getAttributes();
		AbstractUndirectedWeightedGraph<Attribute> g = new DenseUndirectedWeightedGraph<Attribute>(atts.length);
		//add all vertices to graph
		for(int i = 0; i < atts.length; i++)
		{
			try {
				g.addVertex(atts[i]);
			} catch (MaxCapacityExceededException e) {
				e.printStackTrace();
			}
		}
		//set all edge weights
		for(int i = 0; i < atts.length; i++)
		{
			for(int j = i+1; j < atts.length; j++)
			{
				double weight = computeWeight(atts[i], atts[j]);
				try {
					g.setEdgeWeight(atts[i], atts[j], weight);
					g.setEdgeWeight(atts[j], atts[i], weight);
				} catch (GraphIsCyclicException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
		//get the spanning tree from the graph
		SpanningTreeAlgorithm<Attribute> sta = new PrimMaxSpanningTree<Attribute>(g); 
		SpanningTree<Attribute> st = sta.getSpanningTree();
		//get the directed tree from the spanning tree
		DirectedTree<Attribute> dt = new DefaultDirectedTree<Attribute>();
		dt.loadFromSpanningTree(st);
		
		
		directedTree = dt;	
	}

	
	/**Classify an instance with the classifier
	 * @param i instance to classify
	 * @return classification 
	 */
	@Override
	public int classify(Instance i) {
		int class_max = 0;
		double maximum_prob = Double.NEGATIVE_INFINITY;
		for(int j = 0; j <= trainSet.getMaxClassValue(); j++)
		{
			double prob = computeJointProbabilityD(i,j);
			if(prob > maximum_prob)
			{
				maximum_prob = prob;
				class_max = j;
			}
		}
		return class_max;
	}
	
	/**Classify a whole test dataset with the classifier
	 * @param d dataset to classify
	 * @return array with classifications of each instance of the dataset 
	 */
	@Override
	public int[] classify(Dataset d) {
		int[] classification = new int[d.getNumberOfInstances()];
		int i = 0;
		for(Instance inst:d)
		{
			classification[i] = classify(inst);
			i++;
		}
		return classification;
		
	}

	@Override
	public String toString() {
		return directedTree.toString();
	}

}