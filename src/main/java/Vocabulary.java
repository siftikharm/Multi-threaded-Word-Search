import java.io.*;

public class Vocabulary implements Runnable {

	private String filepath;
    private BST Tree;
	
	public Vocabulary(String file , BST tree) {
		this.filepath = file;
		Tree = tree;
	}
	
	private BST getBST() {
		return Tree;
	}
	
	public void printInorder() {
		Tree.printInOrder();
	}

	public void run() {
		
		try {
	        FileReader file = new FileReader(filepath);
	        BufferedReader buffer = new BufferedReader(file);
	        String line = null;
	        
	        while ((line = buffer.readLine()) != null) {
	            String[] tokens = line.split(" ");
	            for(int i = 0 ; i < tokens.length ; i++) {
	            	Tree.insert(tokens[i]);
	            }
	        }
	        
	        buffer.close();
	        
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
