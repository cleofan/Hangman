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
	 * Represent the word to be guess
	 */
	String wordToBeGuessed;
	
	//Constructor
	
	/**
	 * Construct a hangmanTraditional Class with a word to be guessed
	 * @param wordToBeGuessed
	 */
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
	 * Determines whether or not the letter entered by user "hits" one or more positions. 
	 * It will also updates the number of trials (trialCount), and if hit one or more position, update the hitCount.
	 * Updates wordPrint and Incorrect Guesses
	 * @param String userGuess the letter entered by user
	 */
	void compareAndUpdate(String userGuess) {
		
		//If user has not guessed the same letter before.
		if (!this.guessedOrNot(userGuess)) {
		
			//Updates the trial count
			this.incrementTrialCount();
		
			//Compared the input letter against the word to be guessed
			if (this.getWordToBeGuessed().contains(userGuess)) {
				
				//parse over every position of the word to be guessed
				for (int i = 0; i < this.getWordLength(); i++) {
					
					//If the char at the position matches the input character
					if (this.getWordToBeGuessed().charAt(i) == userGuess.charAt(0)) {
						
						//increment hitCount by 1
						this.incrementHitCount();
						
						//Update the matching index of the wordPrint array
						this.getWordPrint().set(i, userGuess);
					}
				}
				
			//when the word to be guessed does not contain the input letter
			}else {
				
				//update the incorrect guess ArrayList
				this.getIncorrectGuesses().add(userGuess);
			}
		} 
	}
}
