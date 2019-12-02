package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parse through the given file, and return an ArrayList of words qualified
 * for playing the Hangman game.
 * @author mengyaofan
 *
 */

public class FileProcessing {
	
	//static variable

	/**
	 * An ArrayList of uncleaned words
	 */
	static ArrayList<String> UNCLEANED_WORDS = new ArrayList<String>();
	
	/**
	 * An ArrayList of String storing all qualified words
	 */
	static ArrayList<String> QUALIFIED_WORDS = new ArrayList<String>();
	
	//Getter

	/**
	 * @return uncleanedWords
	 */
	public static ArrayList<String> getUNCLEANED_WORDS() {
		return UNCLEANED_WORDS;
	}

	/**
	 * @return QUALIFIED_WORDS
	 */
	public static ArrayList<String> getQUALIFIED_WORDS() {
		return QUALIFIED_WORDS;
	}
	
	//methods

	/**
	 * This method reads a file with the given name, and store every word in the UNCLEANED_WORDS ArrayList.
	 * @param fileName
	 */
	public static void readAndStore(String fileName) {
		
		//Create a file object with the name
		File myFile = new File(fileName);
		
		//Create a scanner class.
		Scanner sc;
		
		try {
			sc = new Scanner(myFile);
			while (sc.hasNextLine()) {
				
				//Store the scanning result as a word.
				String word = sc.nextLine().trim();
				
				//add the word to the ArrayList if it's not empty
				if (!word.equals("")) FileProcessing.getUNCLEANED_WORDS().add(word);
			}
		}catch(FileNotFoundException e) {
			
			//when the file doesn't exist, print the msg to users
			e.printStackTrace();
			System.out.println("The filename entered does not exist.");
		}
	}

	/**
	 * This method parse through every word stored in the QUALIFIED_WORDS ArrayList, and retain only words with all lower case letters
	 */
	static void parseAndClean (){
		 
		//Create an iterator
		Iterator<String> iterator = FileProcessing.getUNCLEANED_WORDS().iterator();

		//Create a String Regex. Only words with all lower case letters and no space can be selected.
		String regex = "[^a-z]+";
		
		//Parse through the current ArrayList
		while (iterator.hasNext()) {
			
			//Store the iterator result as a word
			String word = iterator.next().trim();
			
			//create pattern and matcher
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(word);
			
			//add the word to the qualified array if 
			if (!matcher.find()) FileProcessing.getQUALIFIED_WORDS().add(word);	
		}	
	}
	
	public static void main(String[] args) {
		
		String fileName = "words.txt";
		FileProcessing.readAndStore(fileName);
		FileProcessing.parseAndClean();
		System.out.println(FileProcessing.getQUALIFIED_WORDS());
	}

}
