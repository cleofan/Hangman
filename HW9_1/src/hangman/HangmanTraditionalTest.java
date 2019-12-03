package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HangmanTraditionalTest {
	private String word1;	
	Hangman traditional1;

	private String word2;
	Hangman traditional2;	
	
	private String word3;
	Hangman traditional3;

	@BeforeEach
	void setUp() throws Exception {
		FileProcessing.readAndStore("words_clean.txt");
		FileProcessing.parseAndClean();
		this.word1 = "";
		this.traditional1 = new HangmanTraditional(word1);
		
		
		this.word2 = "aaa";
		this.traditional2 = new HangmanTraditional(word2);
		
		this.word3 = "yogurt";
		this.traditional3 = new HangmanTraditional(word3);
		
	}

	@Test
	void testCompareAndUpdate() {
		traditional1.compareAndUpdate("a");
		assertTrue(traditional1.getIncorrectGuesses().get(0).equals("a"));
		
		traditional2.compareAndUpdate("a");
		assertTrue(traditional2.isGameOver());
		
		traditional3.compareAndUpdate("y");
		traditional3.compareAndUpdate("c");
		assertTrue(traditional3.getIncorrectGuesses().size() == 1);
	}

}
