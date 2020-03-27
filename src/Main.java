/**
 * Description: This class runs the whole game
 * Author: Justin Flores
 * Last Updated: 06/07/2016
 * Note: Fix internal documentation
 */

//Utilities
import java.util.Scanner;
import java.util.Arrays;

public class Main {
	public static WordList w = new WordList();
	public static HighscoreManager hm = new HighscoreManager();
	public static Scanner keyb = new Scanner(System.in);
	public static String answer;
	public static int chances;
	public static int playerScore;
	public static long startTime, endTime;

	/**
	 * Description: Calls out displayMenu to run the 
	 * whole game
	 */
	public static void main(String[] arg) {
		displayMenu();
	}
		
	/**
	 * Description: Prints out the menu screen and prompts
	 * player for input
	 * Note: Fixed error
	 */
	public static void displayMenu(){
			 System.out.println("!!!!!4!!!!!");
			 System.out.println("Hall of Fame: ");
			 System.out.println(hm.getHighscoreString());
			 System.out.println("1] Play");
			 System.out.println("2] Quit");
			 String userInput;
			 boolean quitMenu = false;
			 do{
				 System.out.println("Enter your choice:");
				 userInput = keyb.nextLine();
				 if (userInput.equalsIgnoreCase("1")) {
					runGame();
					quitMenu = true;
					
				}else if(userInput.equalsIgnoreCase("2")){
					System.out.println("Goodbye Pleblerd (T-T)");
					quitMenu = true;
					
				}else{
					System.out.println("Invalid Choice [-_-]");
					quitMenu = false;
				}
			 }while(!quitMenu);
	}
	
	/**
	 * Description:Starts the game and processes what occurs after the game is over.
	 * Prompt the user name entering and the restart menu. Restarts the game if player chooses
	 * to.
	 */
	public static  void runGame(){
		 w.readFile();
		 playerScore = 0;
		 chances = 3;
		 boolean runGame = false;
		 boolean quitGame = false;
		 String userInput,word;
		 do {
			 word = w.getWord();
			 setUpScreen(w.shuffleWord(word));
			 if (!processAnswer(word)) {
					System.out.println("Possible Answer: " + word);
					System.out.println("Your Final Score: " + playerScore);
					System.out.println("Enter username: ");
					String userName = keyb.nextLine();
					hm.addScore(userName, playerScore);
					do{
						System.out.println("Would you like to play again? (y/n)");
						userInput = keyb.nextLine();
						if (userInput.equalsIgnoreCase("y")) {
							playerScore = 0;
							runGame = true;
							quitGame = true;
						}else if(userInput.equalsIgnoreCase("n")){
							quitGame = true;
							runGame = false;
							System.out.println("Goodbye Pleblord (ToT)");
						}else{
							System.out.println("Invalid Choice d[-_-]b");
							quitGame = false;
						}
					}while(!quitGame);
			 }else{
				 runGame = true;
			 }
		 } while (runGame);
//		game over
	}

	
	/**
	 * Description: Prints out the game screen
	 * @param word - the scrambled word generated randomly from
	 * the WordList class
	 */
	public static void setUpScreen(String word) {
		System.out.println("!!!!!!!!4!!!!!!!!");
		System.out.println("+---+---+---+---+");
		System.out.println("| " + word.charAt(0) + " | " + word.charAt(1)
				+ " | " + word.charAt(2) + " | " + word.charAt(3) + " | ");
		System.out.println("+---+---+---+---+");
		System.out.println("Score: " + playerScore);
		System.out.println("Pleb Chances: " + chances);
		startTime = System.currentTimeMillis();
		
	}

	/**
	 * Description: Sets up the answering interface and get the answer from the
	 * player.
	 * @return answer - users answer for the level which is needed to be processed
	 * in another method
	 */
	public static String getAnswer() {
		System.out.println("Answer:");
		answer = keyb.nextLine().toLowerCase();
		return answer;
	}

	/**
	 * Description: Processes if the user's answer is correct and move to the next level,
	 *  and determines when the game is over
	 * @param word - the scrambled word generated randomly from
	 * the WordList class
	 * @return boolean - returns true if the players answer is correct,
	 * and returns false if the player has no more tries and its game over
	 * 
	 */
	public static boolean processAnswer(String word) {
		int tries = 0;
		do {
			getAnswer();
			char[] userAnswer = answer.toCharArray();
			char[] givenWord = word.toCharArray();
			Arrays.sort(userAnswer);
			Arrays.sort(givenWord);
			if ((Arrays.toString(userAnswer).equals(Arrays.toString(givenWord)))
					&& (w.words.contains(answer))) {
				endTime = System.currentTimeMillis();
				getScore();
				return true;
			}else if(answer.equals("pleb")){
				chances--;
				if(chances == 0){
					System.out.println("GG Newb! U SUCK AT THIS VIDEO GAYME! PLES UNINSTALL t( -.-t)");
					return false;
				}else{
					System.out.println("Possible Answer: " + word);
					return true;
				}
			}else {
				if (tries == 0) {
					System.out.println("Wrong Answer d[-_-]b");
					playerScore -= 2;
					System.out.println("Score: " + playerScore);
					tries++;
				} else if (tries == 1) {
					System.out.println("Are you serious? Bruh ¯\\(°_o)/¯");
					playerScore -= 2;
					System.out.println("Score: " + playerScore);
					tries++;
				} else if (tries == 2) {
					System.out
							.println("GG Newb! U SUCK AT THIS VIDEO GAYME! PLES UNINSTALL t( -.-t)");
					tries++;
				}
			}

		} while (tries < 3);
		return false;
	}

	/**
	 * Description: Determines how much point the player gets for a
	 * level, which depends how fast they completed it.
	 * level
	 * @return - the player's score after the point(s) is added.
	 * Note: subtract 2 points from player score for every 5 sec. after 10 sec.
	 */
	public static int getScore(){
		long totalTime = endTime - startTime;
		 if(totalTime <= 10000 && totalTime >= 8000){
			playerScore++; 
		 }
		 else if(totalTime < 8000 && totalTime >= 5000){
			 playerScore += 2;
		 }
		 else if(totalTime < 5000 && totalTime >= 3000){
			 playerScore += 3;
		 }
		 else if(totalTime < 3000){
			 playerScore += 5;
		 }else if(totalTime > 10000){
			 playerScore -= 5;		 
		 }else{
			 playerScore += 0;
		 }
		 return playerScore;
	}

}
