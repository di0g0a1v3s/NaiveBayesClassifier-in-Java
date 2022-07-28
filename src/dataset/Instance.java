package dataset;

/**
 * An Instance is a collection of values (int), each one 
 * associated to an Attribute, plus a value for the class (int) 
 */
public interface Instance {

	/**
	 * Set the value of the class of this instance (greater or equal to 0)
	 * @param c class value
	 * @return true if successful, else false
	 */
	boolean setClassValue(int c);
	
	
	/**
	 * Return the class value of this instance
	 * @return class value or -1 if it has no class value
	 */
	int getClassValue();

	/**
	 * Set a value for an attribute (greater or equal to 0)
	 * @param a Attribute
	 * @param v Attribute value
	 * @return true if successful, else false
	 */
	boolean setAttValue(Attribute a, int v);

	/**
	 * Return the value of an attribute in the instance
	 * @param a Attribute to return the value of
	 * @return the attribute value, or -1 if unsuccessful
	 */
	int getAttValue(Attribute a);

	/**
	 * Remove an attribute from the instance
	 * @param a Attribute to remove
	 */
	void removeAttribute(Attribute a);

	/**
	 * Get an array with all the attributes of the instance
	 * @return array with all of the attributes of the instance
	 */
	Attribute[] getAttributes();

	/**
	 * Check if the Instance has a set of attributes
	 * @param attributes Array of attributes to check
	 * @return true if it has all the attributes, else false
	 */
	boolean hasAttributes(Attribute[] attributes);

}