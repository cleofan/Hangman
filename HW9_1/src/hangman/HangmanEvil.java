package hangman;

import java.util.ArrayList;
import java.util.HashMap;

public class HangmanEvil extends Hangman {

	//instance variables
	
	/**
	 * This ArrayList contains all the words for player to guess in next turn, 
	 * It's the words group that contains most words among the all words group from last turn.
	 */
	ArrayList<String> wordSource = new ArrayList<String>();
	
	/**
	 * This is a HashMap sorting all words based whether or not each position contains a specific letter.
	 * The words with the same contain/not contain letter pattern are grouped under the same key.
	 * The key is an ArrayList of booleans, where each element records whether or not the corresponding position in a word contains a specified letter.
	 * The value is an ArrayList of strings, where each string is a word with the corresponding contain/not contain letter pattern as specified by the key.
	 */
	HashMap<ArrayList<Boolean>, ArrayList<String>> sortingDictionary = new HashMap<ArrayList<Boolean>, ArrayList<String>>() ;
	
	//constructor
	
	public HangmanEvil(String wordToBeGuessed) {
		
		//Call the constructor of the super class
		super(wordToBeGuessed.length());
		
		//initiate next word source
		this.setFirstWordSource(wordToBeGuessed.length());
	}
	
	//getters and setters
	    
	/**
	 * @return nextSource
	 */
	public ArrayList<String> getWordSource() {
		return wordSource;
	}
	
	/**
	 * @return sortingDictionary
	 */
	public HashMap<ArrayList<Boolean>, ArrayList<String>> getSortingDictionary(){
		return this.sortingDictionary;
	}
	
	/**
	 * This method filters through the QUALIFIED_WORDS list generated by the FileProcessing class, and store all words of the given
	 * length into the nextSource list of this class.
	 * @param wordLength the length used to select words
	 */
	void setFirstWordSource(int wordLength) {
		
		//Parse through every word in the qualified words list.
		for (String word : FileProcessing.getQUALIFIED_WORDS()) {
			
			//add the word to the wordsOfLength ArrayList if the length is the same as wordLength
			if (word.length() == wordLength) this.getWordSource().add(word);
		}
	}
	
	/**
	 * This method takes in an ArrayList of String, and set the nextSource list to be a copy of the input wordList
	 * @param wordList
	 */
	void updateLastWordSource(ArrayList<String> wordsList) {
		
		//clear the current list
		this.getWordSource().clear();
		
		//add words from given list to the wordSource for next turn
		this.getWordSource().addAll(wordsList);
	}

	//methods
	
	/**
	 * This method takes in a word, and a letter, and analyze if each position of the word matches the letter
	 * Record the boolean value in an ArrayList, and return this ArrayList for further use
	 * @param word to be analyzed
	 * @param letter used to compare each position of the word with
	 * @return letterPattern ArrayList recording the match-or-not pattern of the word
	 */
	private ArrayList<Boolean> letterPattern(String word, String letter) {
		
		//create an ArrayList of Boolean recording whether or not each position of the word contains the letter input.
		ArrayList<Boolean> letterPattern = new ArrayList<Boolean>();
		
		//Parse through every position of the word
		for (int i = 0; i < word.length(); i++) {
			
			//If the position of the word contains the letter
			if (word.charAt(i) == letter.charAt(0)) {
				
				//Set the corresponding element in the letterPattern List to true
				letterPattern.add(true);
				
			//if the position of the word doesn't match the letter
			}else {
				
				//Set the corresponding element in the letterPattern List to false
				letterPattern.add(false);
			}
		}
		return letterPattern;
	}
	
	/**
	 * Takes in an ArrayList of word Strings, and group word strings based on their letterPattern as defined by the letterPattern method.
	 * This method will wipe the current sortingDictionary and directly modify it.
	 * @param wordList the ArrayList of words used to create sortingDictionary
	 * @param letter the letter used to define letterPattern of each String in the ArrayList
	 */
	private void updateSortingDictionary(ArrayList<String> wordList, String letter) {
		
		//clear the current sortingDictionary
		this.getSortingDictionary().clear();
		
		//parse through the whole wordList
		for (String word: wordList) {
			
			//Get the key of the word (The letterPattern)
			ArrayList<Boolean> key = this.letterPattern(word, letter);
			
			//if the HashMap doesn't contain the key
			if (!this.getSortingDictionary().containsKey(key)) {
				
				//Initiate a new ArrayList of Strings as the value of the key
				ArrayList<String> value = new ArrayList<String>();
				
				//Add the current word to the newly created list
				value.add(word);
				
				//Add the key:val pair to the sortingDictionary
				this.getSortingDictionary().put(key, value);
				
			//if the SortingDictionary contains the key already.
			}else {
				
				//Add the word directly to the value list of the key
				this.getSortingDictionary().get(key).add(word);
			}
		}
	}

	@Override
	/**
	 * This abstract method determines whether or not the letter entered by user "hits" one or more positions. 
	 * It will also updates the number of trials (trialCount), and if hit one or more position, update the hitCount.
	 * Updates wordPrint and incorrectGuesses
	 * @param String of letter entered by user
	 */
	void compareAndUpdate(String userGuess) {
		
		//If user has not guessed the same letter before.
		if (!this.guessedOrNot(userGuess)) {
				
			//Increment trialCount
			this.incrementTrialCount();
			
			//Update the sortingDictionary using the userGuesses and the wordSource list.
			this.updateSortingDictionary(this.getWordSource(), userGuess);
			
			//Create an arrayList of boolean recording the key with the biggest ArrayList of words
			ArrayList<Boolean> patternWithMostWords = new ArrayList<Boolean>();
			
			//Create a variable documenting the length of the biggest value list
			int maxWordsCount = 0;
			
			//parse over the sortingDictionary
			for(ArrayList<Boolean> pattern : this.getSortingDictionary().keySet()) {
				
				//if the size of the value list of the key is bigger than the existing record
				if (this.getSortingDictionary().get(pattern).size() > maxWordsCount) {
					
					//Clear the keyWithMostValue
					patternWithMostWords.clear();
					
					//Update the keyWithMostValue
					patternWithMostWords.addAll(pattern);
					
					//update the biggestSize
					maxWordsCount = this.getSortingDictionary().get(pattern).size();
				}
			}
			
			//If the patternWithMostWords doesn't have true at any position, the hit missed.
			if (!patternWithMostWords.contains(true)) {
				
				//update the incorrect guess list
				this.getIncorrectGuesses().add(userGuess);
				
			//If the keyWithMostValue contains one or more true value
			}else {
				
				//parse through every position of the ArrayList
				for (int i = 0; i < patternWithMostWords.size(); i++) {
					
					//if the element is true
					if (patternWithMostWords.get(i)) {
						
						//increment hit count by 1
						this.incrementHitCount();
						
						//update the wordPrint List
						this.getWordPrint().set(i, userGuess);
					}
				}
			}
			//Update the nextSource as the words with the patternWithMostWords
			this.updateLastWordSource(this.getSortingDictionary().get(patternWithMostWords));
		}
	}
}
