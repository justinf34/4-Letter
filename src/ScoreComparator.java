/**
 * /**
 * Description: This class tells java how to compare 2 scores.
 * Author: Justin Flores
 * Last Updated:05/31/2016
 */
import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
	
	/**
	 * Description: -1 means the first score is greater than the 2nd one, +1 
	 * means it's smaller and 0 means it's equal.
	 */
	public int compare(Score score1, Score score2){
		int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
	}
}
