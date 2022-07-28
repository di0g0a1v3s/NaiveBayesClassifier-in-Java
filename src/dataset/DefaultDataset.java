package dataset;

import java.util.*;

/**
 * This class implements a Dataset in which the set
 * of instances is represented by a List and the set
 * of Attributes by an array
 */
public class DefaultDataset implements Dataset {

	protected List<Instance> instacesList;
	
	protected Attribute[] attributes;
	
	/**
	 * Constructor
	 * @param attributes array of Attributes that compose the dataset 
	 */
	public DefaultDataset(Attribute[] attributes) {
		instacesList = new LinkedList<Instance>();
		//save a copy of the array of attributes, so it can't be changed outside this class
		this.attributes = Arrays.copyOf(attributes, attributes.length); 
	}

	/**
	 * Adds an instance to the dataset if the instance has the same attributes as the dataset
	 * @param i instance to add
	 * @return true if successful, false if unsuccessful
	 */
	@Override
	public boolean add(Instance i) {
		if(i.hasAttributes(this.attributes)) //check if the instance has all the attributes the dataset has
			return instacesList.add(i);
		else
			return false;
		
	}

	/**
	 * Removes an instance from the dataset
	 * @param i - instance to remove
	 * @return true if successful, false if unsuccessful
	 */
	@Override
	public boolean remove(Instance i) {
		return instacesList.remove(i);   
		
	}

	/**
	 * Returns an instance belonging to the dataset, in position index
	 * @param index position of the instance
	 * @return Instance at position index, or null if it doesn't exist
	 */
	@Override
	public Instance getInstance(int index) {
		if(index < getNumberOfInstances())
			return instacesList.get(index);  
		else
			return null;
	}

	/**
	 * Return the maximum value that the class takes in all the instances
	 * @return the maximum value the class takes
	 */
	@Override
	public int getMaxClassValue() {
		
		int max_value = -1;
		for(Instance i:instacesList)
		{
			if(i.getClassValue() > max_value)
				max_value = i.getClassValue();
		}
		return max_value;
	}

	/**
	 * Return the maximum value an attribute takes in all the instances
	 * @param a - attribute
	 * @return maximum attribute value, or -1 in case of error
	 */
	@Override
	public int getMaxAttributeValue(Attribute a) {
		int max_value = -1;
		for(Instance i:instacesList)
		{
			if(i.getAttValue(a) > max_value)
				max_value = i.getAttValue(a);
		}
		return max_value;
	}
	
	/**
	 * Get an array with all the attributes of the dataset
	 * @return array with all of the attributes of the dataset
	 */
	@Override
	public Attribute[] getAttributes() {
		return Arrays.copyOf(attributes, attributes.length); //return a copy so it can't be changed outside this class
	}
	
	/**
	 * Get the number of instances in the dataset
	 * @return number of instances
	 */
	@Override
	public int getNumberOfInstances() {
		return instacesList.size();
	}
	
	@Override
	public String toString() {
		Attribute[] atts = getAttributes();
		String s = "\t\t";
		for(Attribute a:atts)
		{
			s+= a.toString() + "\t";
		}
		s += "\tClass\n";
		int i = 1;
		for(Instance inst:this)
		{
			s+="Instance" + i + ":\t";
			i++;
			for(Attribute a:atts)
			{
				s += inst.getAttValue(a) + "\t";
			}
			s+="\t"+ inst.getClassValue()+"\n";
		}
		return s;
	}


	@Override
	public Iterator<Instance> iterator() {
		return new DatasetIterator(this);
	}



}