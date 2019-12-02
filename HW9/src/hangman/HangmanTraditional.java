package hangman;

/**
 * This class contains methods and variables needed for the traditional hangman game
 * It extends the Hangman class.
 * @author mengyaofan
 *
 */

public class HangmanTraditional extends Hangman{
	
	//instance variable
	/**
	 * records the word to be guess
	 */
	
	String wordToBeGuessed;
	
	//Constructor
	//Construct a hangmanTraditional Class with a word to be guessed
	public HangmanTraditional(String wordToBeGuessed) {
		//Call the constructor of the super class
		super(wordToBeGuessed.length());
		
		//Set the wordToBeGuessed
		this.wordToBeGuessed = wordToBeGuessed;
	}
	
	/**
	 * @return wordToBeGuessed
	 */
	String getWordToBeGuessed() {
		return this.wordToBeGuessed;
	}
	
	@Override
	/**
	 * method determines whether or not the letter entered by user "hits" one or more positions. 
	 * It will also updates the number of trials (trialCount), and if hit one or more position, update the hitCount.
	 * Updates wordPrint and Incorrect Guesses
	 * @param String userGuess the letter entered by user
	 */
	void compareAndUpdate(String userGuess) {
		
		//If the letter hasn't been guessed yet
		if (!this.guessedOrNot(userGuess)) {
		//Updates the trial count
		this.incrementTrialCount();
		
		//cast the input string to char
		char guessChar = userGuess.charAt(0);
				
		//Compared the input letter against the word to be guessed
		if (this.getWordToBeGuessed().contains(userGuess)) {
			
			//parse over every position of the word to be guessed
			for (int i = 0; i < this.getWordLength(); i++) {
				
				//If the char at the position matches the input character
				if (this.getWordToBeGuessed().charAt(i) == guessChar) {
					
					//increment hitcount by 1
					this.incrementHitCount(1);
					
					//Update the matching index of the wordPrint array
					this.getWordPrint().set(i, userGuess);
					
				}
			}
		}else {
			//when the word to be guessed does not contain the input letter
			
			//update the incorrect guess arraylist
			this.getIncorrectGuesses().add(userGuess);
		}
	}
	}
}
