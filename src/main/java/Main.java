import java.util.*;

public class Main {
	
	public static void displayMenu() {
		System.out.println("\n1. Display BST Build\n2. Display Vector Builds\n3. View Matched words\n4. Enter Query\n5. Exit ");
	}
	
	public static void main(String[] args) {

		System.out.print("Number of File : " + args.length + "\nFiles : ");
		for(int i = 0 ; i < args.length ; i++) {
			System.out.print(args[i] + " ");
		}
		
		// Vocabulary Thread
		BST Tree = new BST();
		Vocabulary vocab = new Vocabulary(args[0] , Tree);
		Thread vocabThread = new Thread(vocab);
		vocabThread.setName(args[0]);
		vocabThread.start();
		
		// Input threads
		Thread inputThreads[] = new Thread[args.length - 1];
		Input input[] = new Input[args.length - 1];
		for(int i = 1 ; i < args.length  ; i++ ) {
			input[i-1] = new Input(args[i]);
			inputThreads[i-1] = new Thread(input[i-1]);
			inputThreads[i-1].setName(args[i]);
			inputThreads[i-1].start();
		}
		
				
		displayMenu();
		System.out.print("\nChoice : ");
		Scanner in = new Scanner(System.in);
		byte choice = in.nextByte();
		
		switch(choice) {
		case 1 : {
			try {
				vocabThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Tree.printInOrder();
			break;
		}
		
		case 2 : {
			for(int i = 0 ; i < inputThreads.length ; i++) {
				try {
					inputThreads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				input[i].printWords();
			}
			break;
		}
		
		case 3 : {
			LinkedList<Word> matchedWords = new LinkedList<Word>();	
			for(int i = 0 ; i < args.length - 1 ; i++ ) {
				for(int j =0 ; j < input[i].getSize() ; j++) {
					try {
						String found = Tree.search(input[i].getWordAtIndex(j));
						Word word = new Word(found);
						if(!(found.equals("")) && !(matchedWords.contains(word))) {
							matchedWords.add(word);
						}
						
					   if(matchedWords.contains(word)) {
					        Iterator<Word> itr = matchedWords.iterator();
					        for(int index = 0 ; itr.hasNext() ; index++) {
					        	if(itr.next().getWord().equals(found)) {
					        		matchedWords.get(index).incrementCounter();
					        		break;
					        	}
					        }
						}
						
					} catch (DataNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			
	        Iterator<Word> itr = matchedWords.iterator();
	        while(itr.hasNext()) {
	        	itr.next().print();
	        }

			break;
		}
		
		case 4 : {
			System.out.print("\nEnter Query : ");
			Scanner inp = new Scanner(System.in);
			String query = inp.nextLine();
			LinkedList<String> queryWords = new LinkedList<String>();
        	StringTokenizer Tokens = new StringTokenizer(query , "():,.- ");
        	while (Tokens.hasMoreTokens()) {
        	    queryWords.add(Tokens.nextToken());
        	}
        	
	        Iterator<String> itr = queryWords.iterator();
	        while(itr.hasNext()) {
	        	String temp = itr.next();
	        	try {
					String found = Tree.search(temp);
					if(found.equals(temp)) {
						System.out.println("Query Word matched in " + args[0] + ": " + found);
					}
					
			        for(int i = 0 ; i < args.length - 1 ; i++) {
			        	for(int j = 0 ; j < input[i].getSize() ; j++) {
			        		if(input[i].getWordAtIndex(j).equals(temp)) {
			        			System.out.println("Query Word matched in " + args[i] + ": " + found);
			        		}
			        	}
			        }
				} catch (DataNotFoundException e) {
					e.printStackTrace();
				}
	        }
	        
			break;
		}
		
		case 5 : {
			System.exit(0);
			break;
		}
		
		default: {
			System.out.println("Invalid Input");
		}
		}
				

	}

}
