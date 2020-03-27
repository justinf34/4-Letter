/**
 * Description: This class processes the word to be used in each level
 * Author: Justin Flores
 * Last Updated:13/05/2016
 * Note:Fix documentation
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordList {

	public ArrayList<String> words = new ArrayList<String>();//Creates ArrayList to hold all the words
	
	// Variables that can only be used in this class
	public static Random rand = new Random();
	public static String fileName = "//Users//jk.flores//OneDrive - University of Calgary//4LetterGame//4LetterWords.txt";
	
	/**
	 * Description: gets a shuffled word by calling out the 
	 * getWord and shuffleWord method
	 * @return shuffleWord(getWord()) - the shuffled word
	 * to be used in a level
	 */
	public String getShuffledWord() {
		return shuffleWord(getWord());
	}
	
	/**
	 * Description: Reads the 4LetterWord.txt and adds every word in the text in
	 * an array list. 
	 * @return ArrayList<String> the array list of words.
	 */
	public ArrayList<String> readFile() {
		String line;
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(fileName));
			do {
				
				line = inputStream.readLine();
				words.add(line);// Adds File into ArrayList
			} while (line != null);
			inputStream.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return words;
	}
	
	/**
	 * Description: Gets a random word from the words array list by
	 * generating a random number and using that number as an index.
	 * @return words.get(rand.nextInt(words.size())) - this is the word 
	 * in the index
	 */
	public String getWord() {
		return words.get(rand.nextInt(words.size()));
	}

	/**
	 * Description: Shuffles  the randomly generated word until the 
	 * shuffled word does not exist in the word list array 
	 * @param word - randomly picked word from the getWord method
	 * @return String.valueOf(shuffledArray) - the shuffled word as a string
	 */
	public String shuffleWord(String word) {
		char[] charArray, shuffledArray;
		do {
			charArray = word.toCharArray();
			shuffledArray = "    ".toCharArray();
			for (int i = 0; i < shuffledArray.length; i++) {
				int randNum;
				do {
					randNum = rand.nextInt(shuffledArray.length);
					if (charArray[randNum] != ' ') {
						shuffledArray[i] = charArray[randNum];
						charArray[randNum] = ' ';
						break;
					}
				} while (true);
			}
		} while (words.contains(String.valueOf(shuffledArray)));
		return String.valueOf(shuffledArray);
	}

}