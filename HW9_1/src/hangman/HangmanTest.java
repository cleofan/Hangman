package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HangmanTest {

	@BeforeEach
	void setUp() throws Exception {
		String word1 = "";
		Hangman traditional1 = new HangmanTraditional(word1);
		Hangman evil1 = new HangmanEvil(word1);
	}

	@Test
	void testHangman() {
		
		//Case 1
		//When the word is an empty string
		String word1 = "";
		//Initiate the two versions.
		Hangman traditional1 = new HangmanTraditional(word1);
		Hangman evil1 = new HangmanEvil(word1);
		//Word length and the size of the wordPrint list should be 0
		assertTrue(traditional1.getWordLength() == 0);
		assertTrue(evil1.getWordPrint().size() == 0);
		
		//Case2
		//When the word is "a"
		String word2 = "a";
		//Initiate the two versions.
		Hangman traditional2 = new HangmanTraditional(word2);
		Hangman evil2 = new HangmanEvil(word2);
		//The initial hitCount shoud be 0
		assertEquals(0, traditional2.getHitCount());
		//The word length is 1
		assertEquals(1, evil2.getWordLength());
		
		//Case3
		//When the word is yogurt
		String word3 = "yogurt";
		//Initiate the two versions.
		Hangman traditional3 = new HangmanTraditional(word3);
		Hangman evil3 = new HangmanEvil(word3);
		//The initial size of te incorrectGuesses will be 0.
		assertEquals(0, traditional3.getIncorrectGuesses().size());
		//The word length is 6
		assertEquals(6, evil3.getWordLength());
		
	}

	@Test
	void testUpdateIncorrectGuesses() {
		//When the word to be guessed is an empty string. All entered letters should be in the IncorrectGuesses.
		
	}

	@Test
	void testIsGameOver() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGuessedOrNot() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testPrintWord() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testPrintFinalResult() {
		fail("Not yet implemented"); // TODO
	}

}
