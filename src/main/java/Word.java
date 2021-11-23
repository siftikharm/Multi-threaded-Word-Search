
public class Word {
	
	private String word;
	private int counter;
	
	public Word() {
		this.word = "";
		this.counter =0;
	}

	public Word(String word) {
		this.word = word;
		this.counter = 0;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCounter() {
		return counter;
	}

	public void incrementCounter() {
		this.counter++;
	}
	
	public void print() {
		if(this.getCounter() != 0)
		System.out.println("Word : " + this.getWord() + "\nFrequency : " + this.getCounter());
	}
	

}
