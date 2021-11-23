
public class BST {
	
	private class Node {
		String data;
		Node left;
		Node right;
		
		Node(){
			this.data = "";
			left = null;
			right = null;
		}	
		
		Node(String data){
			this.data = data;
			left = null;
			right = null;
		}		
	}
		
	private Node root;

	public BST() {
		root = null;
	}

	public void insert(String data) {
		root = insertNode(data , root);
	}
	
	public Node insertNode(String data , Node node) {
		try {
			if(node == null ) {
				node = new Node(data);
			}
			else {
				if(node.data.compareTo(data) < 0 ) {
					node.left = insertNode(data , node.left);
				}
				else if(node.data.compareTo(data) > 0  ) {
					node.right = insertNode(data , node.right);
				}
//				else if(node.data.compareTo(data) == 0) {
//					throw new NodeAlreadyExistsException("This word already exists");
//				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return node;
		
	}
	
	public String search(String data) throws DataNotFoundException {
		Node found = new Node();
		found = searchNode(root , data);
		
		if(found == null) {
			return "";
			//throw new DataNotFoundException("Data not found");
		}
		return found.data;
	}
	
	public Node searchNode(Node root, String data) {
	    if ( root == null || root.data.equals(data) )
	        return root;

	    if (root.data.compareTo(data) > 0)
	       return searchNode(root.right, data);
	    
	    return searchNode(root.left, data );
	}
	
	public void printInOrder() {
		printInorder(root);
	}

	public void printInorder(Node node) {
		if(node == null) {
			return;
		}
		printInorder(node.left);
		System.out.println(node.data + " ");
		printInorder(node.right);
	}

}
