package hangman;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Hangman {
	
	//instance variables
	
	/**
	 * The instance variable recording the # of positions hit by user
	 */
	int hitCount;

	/**
	 * The length of the word to be guessed
	 */
	int wordLength;
	
	/**
	 * The number of trials a user has taken
	 */
	int trialCount;
	
	/**
	 * The ArrayList of incorrect guesses
	 */
	ArrayList<String> incorrectGuesses;
	
	/**
	 * The ArrayList recording each position of the answer word.
	 * "_" represents positions that haven't been hit.
	 * Positions already hit will be displayed as letters.
	 */
	ArrayList<String> wordPrint;
	
	//constructor
	
	/**
	 * Set up a new abstract class with the length of the word to be guessed.
	 * @param wordLength the length of the word to be guessed
	 */
	public Hangman(int wordLength) {
		
		//Initiate wordLength
		this.wordLength = wordLength;
		
		//Set the initial trialCount and hitCount to be 0;
		this.trialCount = 0;
		this.hitCount = 0;
		
		//Initiate the incorrectGuesses ArrayList
		this.incorrectGuesses = new ArrayList<String>();
		
		//Initiate the list recording every position of the word
		this.wordPrint = new ArrayList<String>();
		
		//Change every position of the wordList to "_".
		for (int i = 0; i < this.wordLength; i++) {
			this.wordPrint.add("_");
			
		}		
	}
	
	//Getter and setter
	/**
	 * @return hitCount
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * @return wordLength
	 */
	int getWordLength() {
		return this.wordLength;
	}
	
	/**
	 * @return trialCount
	 */
	int getTrialCount() {
		return this.trialCount;
	}
	
	/**
	 * @return incorrectGuesses
	 */
	ArrayList<String> getIncorrectGuesses(){
		return this.incorrectGuesses;
	}
	
	/**
	 * @return wordList
	 */
	ArrayList<String> getWordPrint(){
		return this.wordPrint;
	}
	
	/**
	 * Increment the hitCount by the input amount
	 */
	public void incrementHitCount(int number) {
		this.hitCount += number;
	}

	/**
	 * Increment the trialCount
	 */
	public void incrementTrialCount() {
		this.trialCount ++;
	}

	/**
	 * Update the incorrectGuesses ArrayList by adding the wrong guess word. 
	 * @param incorrectGuesses
	 * @param userGuess
	 */
	public void updateIncorrectGuesses(ArrayList<String> incorrectGuesses, String userGuess) {
		this.incorrectGuesses.add(userGuess);
	}

	//methods
	
	/**
	 * This methods determines whether or not the current game is over.
	 * The game is over if the hitCount is equal to the word length.
	 * @return true if game over
	 */
	boolean isGameOver() {

		//otherwise, returns true when the hitCount equals to the wordLength
		if (this.getHitCount() == this.getWordLength()) return true;
		
		//return false if none of the above happens.
		return false;
	}
	
	/**
	 * This abstract method determines whether or not the letter entered by user "hits" one or more positions. 
	 * It will also updates the number of trials (trialCount), and if hit one or more position, update the hitCount.
	 * Updates wordPrint and Incorrect Guesses
	 * @param String of letter entered by user
	 */
	abstract void compareAndUpdate(String userGuess);
	
	/**
	 * This method prints out the current wordPrint.
	 * If have one or more incorrect guesses, it will also display all the incorrect guesses right.
	 */
	void printWord() {
		
		//Iterate over every char in the wordList, print every element
		//Initiate an iterator
		Iterator<String> iterator = this.getWordPrint().iterator();
		
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
		
		//If there are incorrect guesses, print it out.
		if (this.getIncorrectGuesses().size() > 0) {
			System.out.println("Incorrect guesses: " + this.getIncorrectGuesses());
		}					
	}
	/**
	 * This function prints the final result to the user.
	 */
	void printFinalResult() {
		
		//Print out # of trials taken
		System.out.println("Congratulations! You\'ve guessed the word!");
		System.out.println("You\'ve taken " + this.getTrialCount() + " guesses.");
	}
	
	/**
	  * Check whether or not player has guesses the given letter before.
	  * Return true if player has guesses the letter before, false otherwise.
	  * @param String of letter guessed by user
	  * @return true if player has guesses the letter before, false otherwise.
	  */
	boolean guessedOrNot(String userGuess) {

		  //if user has already guessed this letter, whether the letter in the answer word or not
		  if (this.getIncorrectGuesses().contains(userGuess) || this.getWordPrint().contains(userGuess)) {
		   
		   // print the msg and tell them to enter another one.
		   System.out.println("You have already guessed this letter. Try another one.");
		   //return true
		   return true;
		  
		   //if user has never guessed this letter before
		  } else {
		   
		   //return false
		   return false;
		  } 
		 }

	
}
