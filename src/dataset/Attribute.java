package dataset;

/**
 * An Attribute (or feature), which is represented by a 
 * label (or name) (String) is a class meant to represent 
 * features belonging to an Instance
 *
 */
public class Attribute {

	protected String label;

	/**
	 * Create an attribute Object
	 * @param label attribute label (name)
	 */
	public Attribute(String label) {
		this.label = label;
	}
	
	
	/**
	 * Get the label of an attribute
	 * @return label of the attribute
	 */
	public String getLabel() {
		return label;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		Attribute other = (Attribute) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return label;
	}
	
	

}