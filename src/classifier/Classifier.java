package classifier;

import dataset.Dataset;
import dataset.Instance;

/**
 * Root interface in the classifier hierarchy. A classifier represents a
 * tool capable of assigning (predicting) a class of a given Instance.
 * The classifier is built from a training Dataset.
 * (Instance and Dataset are interfaces from the package dataset).
 */
public interface Classifier {

	/**Build the classifier from the given training dataset
	 * @param data training dataset
	 */
	void buildClassifier(Dataset data);

	/**Classify an instance with the classifier
	 * @param i instance to classify
	 * @return classification 
	 */
	int classify(Instance i);
	
	/**Classify a whole test dataset with the classifier
	 * @param d dataset to classify
	 * @return array with classifications of each instance of the dataset 
	 */
	int[] classify(Dataset d);

}