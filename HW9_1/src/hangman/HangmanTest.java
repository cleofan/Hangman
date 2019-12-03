package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HangmanTest {
	private String word1;	
	Hangman traditional1;
	Hangman evil1;
	
	private String word2;
	Hangman traditional2;
	Hangman evil2;
	
	private String word3;
	Hangman traditional3;
	Hangman evil3;
	
	

	@BeforeEach
	void SetUp() throws Exception{
		FileProcessing.readAndClean("words_clean.txt");
		this.word1 = "";
		this.traditional1 = new HangmanTraditional(word1);
		this.evil1 = new HangmanEvil(word1);
		
		this.word2 = "aaa";
		this.evil2 = new HangmanEvil(word2);
		this.traditional2 = new HangmanTraditional(word2);
		
		this.word3 = "yogurt";
		this.evil3 = new HangmanEvil(word3);
		this.traditional3 = new HangmanTraditional(word3);
		
	}

	@Test
	void testHangman() {
		
		
		assertTrue(traditional1.getWordLength() == 0);
		assertTrue(evil1.getWordPrint().size() == 0);
		
		
		assertEquals(0, traditional2.getHitCount());		
		assertEquals(3, evil2.getWordLength());
		
		
		
		assertEquals(0, traditional3.getIncorrectGuesses().size());
		assertEquals(6, evil3.getWordLength());
		
	}

	@Test
	void testUpdateIncorrectGuesses() {
		
		
		traditional1.updateIncorrectGuesses(traditional1.getIncorrectGuesses(), "a");
		evil1.updateIncorrectGuesses(evil1.getIncorrectGuesses(), "c");
		
		assertEquals("a", traditional1.getIncorrectGuesses().get(0));
		assertTrue(evil1.getIncorrectGuesses().size() == 1);
		
		traditional2.compareAndUpdate("b");
		traditional2.updateIncorrectGuesses(traditional2.getIncorrectGuesses(), "c");
		assertEquals("c", traditional2.getIncorrectGuesses().get(1));
		evil2.compareAndUpdate("a");
		evil2.updateIncorrectGuesses(evil2.getIncorrectGuesses(), "b");
		assertTrue(evil2.getIncorrectGuesses().get(1).contentEquals("b"));
		
		traditional3.compareAndUpdate("g");
		traditional3.updateIncorrectGuesses(traditional3.getIncorrectGuesses(), "a");
		assertTrue(traditional3.getIncorrectGuesses().size() == 1);
		evil3.compareAndUpdate("y");
		evil3.updateIncorrectGuesses(evil3.getIncorrectGuesses(), "c");
		assertEquals("y", evil3.getIncorrectGuesses().get(0));
		
	}

	@Test
	void testIsGameOver() {
		
		assertTrue(traditional1.isGameOver());
		assertTrue(evil1.isGameOver());
		
		
		evil2.compareAndUpdate("a");
        traditional2.compareAndUpdate("a");
		assertTrue(traditional2.isGameOver());
		assertFalse(evil2.isGameOver());
	
		
	
		traditional3.compareAndUpdate("y");
		evil3.compareAndUpdate("y");
		assertFalse(traditional3.isGameOver());
		assertFalse(evil3.isGameOver());
	
	
		
	}

	@Test
	void testGuessedOrNot() {
		
		assertFalse(traditional1.guessedOrNot("a"));
		assertFalse(evil1.guessedOrNot("b"));
		
		
	    traditional2.compareAndUpdate("a");
	    evil2.compareAndUpdate("a");
	    
	   assertTrue(traditional2.guessedOrNot("a"));
	   assertTrue(evil2.guessedOrNot("a"));
	   
	   traditional3.compareAndUpdate("y");
	   assertTrue(traditional3.guessedOrNot("y"));
	   evil3.compareAndUpdate("y");
	   assertFalse(evil3.guessedOrNot("o"));
	   
	    
	    
	 
	}

	
}
