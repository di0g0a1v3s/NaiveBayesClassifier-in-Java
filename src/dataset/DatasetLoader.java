package dataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The purpose of this abstract class is to create and return an
 * object Dataset that contains the information present
 * in a file
 */
public abstract class DatasetLoader {
	
	/**
	 * Static method that creates a Dataset Object based on the information
	 * of a .csv file
	 * @param filepath path (relative or absolute) of the .csv file in the file system 
	 * @return the Dataset Object
	 */
	public static Dataset loadDatasetFromCsv(String filepath)
	{
		String line = "";
        String csvSplitBy = ",";
        Dataset d = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
        	
        	if((line = br.readLine()) != null)  //read the first line
			{	
	        	String[] header = line.split(csvSplitBy);
	        	
	        	Attribute[] atts = new Attribute[header.length-1];
	        	
	        	for(int i = 0; i < atts.length; i++)
	        	{
	        		atts[i] = new Attribute(header[i]);
	        	}
	        	d = new DefaultDataset(atts);
			}
        	else
        		return null;
        	
        	

            while ((line = br.readLine()) != null) {
            	
            	String[] values = line.split(csvSplitBy);
                //create an instance for each line and add to dataset
            	Instance inst = new DefaultInstance();
            	for(int i = 0; i < values.length - 1; i++)
            	{
            		
        			inst.setAttValue(d.getAttributes()[i], Integer.parseInt(values[i]));
    		
            		
            	}
            	inst.setClassValue(Integer.parseInt(values[values.length-1]));
            	d.add(inst);

            }

        } catch (IOException e) {
        	
            return null;
        }
        catch(NumberFormatException e) { //in case there is an error in the file
			return null;
		}
        
        return d;
	}

}
