package hangman;

import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

	//variables
	
	/**
	 * Flag of whether or not the game is on.
	 */
	private boolean play = true;

	/**
	 * The Array of Hangman storing two versions of Hangman game.
	 */
	static Hangman[] GAME_VERSIONS = new Hangman[2];

	/**
	 * The variable storing the letter entered by user.
	 */
	private String enteredChar;

	//methods

	/**
	 * @return gameVersions
	 */
	public Hangman[] getGAME_VERSIONS() {
		return GAME_VERSIONS;
	}

	/**
	 * Set the gameVersions Array with the given word
	 */
	void setGAME_VERSIONS(String wordToBeGuessed) {
		//Initiate both versions of the game and add each version of hangman to the array.
		this.getGAME_VERSIONS()[0] = new HangmanTraditional(wordToBeGuessed);
		this.getGAME_VERSIONS()[1] = new HangmanEvil(wordToBeGuessed);
	}

	/**
	 * Print instructions to the user.
	 */
	void printInstructions() {

		System.out.println("Welcome to the Hangman Game!");
		System.out.println("A random word is drawn from a dictionary.\n" +
				"You need to guess the word letter by letter.");

		System.out.println("The word to be guessed will initially be displayed as \"_\" on all positions.");

		System.out.println("If the letter you entered matches any position of the word, "
				+ "the position(s) will be shown as letters.\n" 
				+ "If the letter your entered doesn't match, it will be shown in the incorrect guesses.");

	}

	/**
	 * @return play
	 */
	public boolean getPlay() {
		return play;
	}

	/**
	 * @return enteredChar
	 */
	public String getEnteredChar() {
		return enteredChar;
	}

	public static void main(String[] args) {

		//Initiate a new random module
		Random random = new Random();

		//Initiate a new scanner
		Scanner sc = new Scanner(System.in);

		//Generate a random boolean determining whether or not to play traditional hangman
		HangmanGame game = new HangmanGame();

		//Import dictionary and get all qualified words.
		FileProcessing.readAndStore("words.txt");
		FileProcessing.parseAndClean();

		//while the game is on
		while (game.getPlay()) {

			//print instructions.
			game.printInstructions();
			System.out.println();

			//Generate a random word to be guessed.
			int DICTIONARY_SIZE = FileProcessing.getQUALIFIED_WORDS().size();
			String wordToBeGuessed = FileProcessing.getQUALIFIED_WORDS().get(random.nextInt(DICTIONARY_SIZE));

			//Set up the GAME_VERSIONS array.
			game.setGAME_VERSIONS(wordToBeGuessed);
            
			
			//System.out.println(a);
			
			//Generate a random integer indicating the index of the game version.
			int a = random.nextInt(2);
			Hangman version = game.getGAME_VERSIONS()[a];
	
			//Print the initial word with all underscores to the user.
			version.printWord();

			//Set a while loop that keeps running until the game is over.
			while(!version.isGameOver()) {

				//Set a variable recording user's guess
				String userGuess = "";

				//Set up a while loop keeps running until a valid input is acquired.
				while(userGuess.length() == 0) {

					System.out.println("Please enter a letter.");

					//Take in user input
					userGuess = sc.nextLine().trim();
					
					//if player didn't enter only one letter
					if ((userGuess.length() != 1) || !(Character.isLetter(userGuess.charAt(0)))) {

						//prompt the error 
						System.out.println("Please only enter a single letter.");
						//reset userGuess to empty.
						userGuess = "";		
					}
				}
				//Take in the letter user entered (converted to lowercase) and see if the input hits.
				version.compareAndUpdate(userGuess.toLowerCase());

				//Print the current word and incorrect guesses.
				version.printWord();
				System.out.println();
			}
			
			//Print final result
			version.printFinalResult();

			//Ask if user wants to play again
			//Initiate a variable storing user's input.
			String userAnswer = "";

			//Set up a while loop that keeps running until user inputs a valid answer.
			while (userAnswer.length() == 0) {
				
				System.out.println("Do you wish to play again? Enter Y or y for yes, N or n for no.");

				//Set the userAnswer to be the result of the scanner.
				userAnswer = sc.nextLine().trim();

				if (userAnswer.startsWith("Y") || userAnswer.startsWith("y")) {
					
					//Set the play variable to true if the user answered yes.
					game.play = true;
					
				}else if(userAnswer.startsWith("N") || userAnswer.startsWith("n")) {
					
					//Set the play variable to false if the user answered no.
					game.play = false;
					
				}else {
					
					//In cases when user input invalid answers, prompt user of the error.
					System.out.println("Sorry, the answer you input was invalid.");

					//Reset the userAnswer to empty String.
					userAnswer = "";
				}
			}
			System.out.println();
		}

		//print the goodbye message
		System.out.println("See you next time!");
        
		//Close the scanner.
		sc.close();
	}
}
