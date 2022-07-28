package dataset;

/**
 * The Dataset is a set of Instances. The values of the Attributes
 * and the values of the class range between 0 and the maximum value
 * for that attribute/class. The Dataset interface also extends the
 * Iterable interface, to more easily iterate through the Instaces
 * of the Dataset
 */
public interface Dataset extends Iterable<Instance> {

	/**
	 * Adds an instance to the dataset if the instance has the same attributes as the dataset
	 * @param i instance to add
	 * @return true if successful, false if unsuccessful
	 */
	boolean add(Instance i);

	/**
	 * Removes an instance from the dataset
	 * @param i - instance to remove
	 * @return true if successful, false if unsuccessful
	 */
	boolean remove(Instance i);

	/**
	 * Returns an instance belonging to the dataset, in position index
	 * @param index position of the instance
	 * @return Instance at position index, or null if it doesn't exist
	 */
	Instance getInstance(int index);

	/**
	 * Return the maximum value that the class takes in all the instances
	 * @return the maximum value the class takes, or -1 in case of error
	 */
	int getMaxClassValue();
	
	/**
	 * Get the number of instances in the dataset
	 * @return number of instances
	 */
	int getNumberOfInstances();

	/**
	 * Return the maximum value an attribute takes in all the instances
	 * @param a - attribute
	 * @return maximum attribute value, or -1 in case of error
	 */
	int getMaxAttributeValue(Attribute a);

	/**
	 * Get an array with all the attributes of the dataset
	 * @return array with all of the attributes of the dataset
	 */
	 Attribute[] getAttributes();
}