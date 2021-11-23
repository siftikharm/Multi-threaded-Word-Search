import java.io.*;
import java.util.*;

public class Input implements Runnable {

	private List<String> words; 
	private String filepath;
	
	public Input(String path) {
		this.filepath = path;
		words = new LinkedList<String>();
	}
	
	public void printWords() {
        System.out.println("Printing from file : " + filepath);
        Iterator<String> itr = words.iterator();
        while(itr.hasNext()) {
        	System.out.println(itr.next());
        }
	}
	
	public String getWordAtIndex(int index) {
		return words.get(index);
	}
	
	public int getSize() {
		return words.size();
	}
	
	public void run() {

		try {
			//System.out.println("Reading File : " + filepath);
	        FileReader file = new FileReader(filepath);
	        BufferedReader buffer = new BufferedReader(file);
	        String line = null;
	        
	        while ((line = buffer.readLine()) != null) {
	        	StringTokenizer Tokens = new StringTokenizer(line , "():,.- ");
	        	while (Tokens.hasMoreTokens()) {
	        	    words.add(Tokens.nextToken());
	        	}
	        }
	        
	        buffer.close(); 
	        
	      // printWords();
	        
	        
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
