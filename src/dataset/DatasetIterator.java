package dataset;

import java.util.Iterator;

/**
 * Iterator for the Dataset
 */
public class DatasetIterator implements Iterator<Instance> {
	
	private int current;
	private Dataset d;
	
	/**
	 * Constructor
	 * @param d Dataset to iterate through
	 */
	public DatasetIterator(Dataset d) {
		super();
		this.d = d;
		current = 0;
	}

	@Override
	public boolean hasNext() {
		return d.getInstance(current) != null; 
	}

	@Override
	public Instance next() {
		Instance i = d.getInstance(current);
		current++;
		return i;
	}

}
