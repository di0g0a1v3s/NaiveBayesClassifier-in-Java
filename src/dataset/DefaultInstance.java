package dataset;

import java.util.*;

/**
 * This class implements an Instance where the
 * association between the Attributes and its values
 * are stored in a HashMap
 */
public class DefaultInstance implements Instance {

	protected Map<Attribute, Integer> attributeValues; //key-value map of attributes and respective values
	protected int classValue;
	

	/**
	 * Constructor - the class value is initialized with the default 
	 * value of -1
	 */
	public DefaultInstance() {
		attributeValues = new HashMap<Attribute,Integer>();
		classValue = -1;
	}

	/**
	 * Set the value of the class of this instance (greater or equal to 0)
	 * @param c class value
	 * @return true if successful, else false
	 */
	@Override
	public boolean setClassValue(int c) {
		if(c >= 0)
		{
			this.classValue = c;
			return true;
		}
		else
			return false;
		
	}

	/**
	 * Return the class value of this instance
	 * @return class value or -1 if it has no class value
	 */
	@Override
	public int getClassValue() {
		return classValue;
	}

	/**
	 * Set a value for an attribute (greater or equal to 0)
	 * @param a Attribute
	 * @param v Attribute value
	 * @return true if successful, else false
	 */
	@Override
	public boolean setAttValue(Attribute a, int v) {
		if(v >= 0)
		{
			attributeValues.put(a, Integer.valueOf(v));
			return true;
		}
		else
			return false;
		
	}

	/**
	 * Return the value of an attribute in the instance
	 * @param a Attribute to return the value of
	 * @return the attribute value, or -1 if unsuccessful
	 */
	@Override
	public int getAttValue(Attribute a) {
		Integer i = attributeValues.get(a);
		if(i == null)
			return -1;
		else return i.intValue();
	}

	/**
	 * Remove an attribute from the instance
	 * @param a Attribute to remove
	 */
	@Override
	public void removeAttribute(Attribute a) {
		attributeValues.remove(a);
		
	}

	/**
	 * Get an array with all the attributes of the instance
	 * @return array with all of the attributes of the instance
	 */
	@Override
	public Attribute[] getAttributes() {
		return attributeValues.keySet().toArray(new Attribute[0]);
	}

	/**
	 * Check if the Instance has a set of attributes
	 * @param attributes Array of attributes to check
	 * @return true if it has all the attributes, else false
	 */
	@Override
	public boolean hasAttributes(Attribute[] attributes) {

		for(Attribute a:attributes)
		{
			if(!attributeValues.containsKey(a))
				return false;
		}
		return true;
	}

		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeValues == null) ? 0 : attributeValues.hashCode());
		result = prime * result + classValue;
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
		DefaultInstance other = (DefaultInstance) obj;
		if (attributeValues == null) {
			if (other.attributeValues != null)
				return false;
		} else if (!attributeValues.equals(other.attributeValues))
			return false;
		if (classValue != other.classValue)
			return false;
		return true;
	}

}