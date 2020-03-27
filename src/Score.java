/**
 * Description: Makes an array list of the type Score to hold the name and score of a player.
 * Serializable is used  to be able to sort this type.
 * Author: Justin Flores
 * Last Updated:06/06/2016
 */
import java.io.Serializable;

public class Score implements Serializable {
	private static final long serialVersionUID = 1L;
	private int score;
	private String name;
	
	/**
	 * Description: returns score of the player
	 * @return - score that the player got in
	 * one run
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Description: returns the username of the player
	 * @return - the username of the player 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Description: Takes the username of a player to be used in
	 * score comparator
	 * @param name - username of the player
	 * @param score - score the the player
	 */
	public Score(String name, int score){
		this.score = score;
		this.name = name;
	}

}
