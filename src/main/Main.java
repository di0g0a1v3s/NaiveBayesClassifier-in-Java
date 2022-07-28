package main;

import classifier.Classifier;
import classifier.ClassifierMetrics;
import classifier.LLBayesianNetworkClassifier;
import classifier.MDLBayesianNetworkClassifier;
import dataset.Dataset;
import dataset.DatasetLoader;

public class Main {
	public static void main(String[] args) {
		
		//input parameters check
		if(args.length != 3)
		{
			System.out.println("Invalid number of arguments");
			return;
		}
		if(!args[2].equals("LL") && !args[2].equals("MDL"))
		{
			System.out.println("Third argument must be either LL or MDL");
			return;
		}
		
		Dataset train = DatasetLoader.loadDatasetFromCsv(args[0]);
		Dataset test = DatasetLoader.loadDatasetFromCsv(args[1]);
		if(train == null)
		{
			System.out.println("Error reading train file (1st argument)");
			return;
		}
		if(test == null)
		{
			System.out.println("Error reading test file (2nd argument)");
			return;
		}
			
		Classifier c;
		if(args[2].equals("MDL"))
			c = new MDLBayesianNetworkClassifier();
		else
			c = new LLBayesianNetworkClassifier();
		ClassifierMetrics metrics = new ClassifierMetrics(c, train,test);
		System.out.println(metrics);
		
		c.classify(test.getInstance(10));
		
	
	}
	
}
