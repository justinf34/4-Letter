/**
 * Description: This class manages how the high scores are handled/sorted and where they should
 * be stored.
 * Author: Justin Flores
 * Last Updated:06/06/2016
 */
import java.util.*;
import java.io.*;

public class HighscoreManager {
    // An arraylist of the type "score" to work with the scores inside the class
    private ArrayList<Score> scores;

    // The name of the file where the highscores will be saved
    private final String HIGHSCORE_FILE = "scores.dat";

    //Initializing an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    
    /**
     * Description: Initializes the scores-arraylist
     */
    public HighscoreManager() {
        scores = new ArrayList<Score>();
    }
    
    /**
     * Descriptions: This methods loads the high score file, sort it and return it as an
     * array list. 
     * @return scores - an array list that contains the scores.
     */
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
    
    /**
     * Description: It creates a new object from the ScoreComparator class
     * and sorts the arraylist with the help if comparator. 
     */
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }
    
    /**
     * Description: This method loads the score file and adds the score and the username
     * of the player to the score file
     * @param name - username of the player
     * @param score - the score the player got
     */
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }
    
    /**
     * Description: Loads the arraylist  in the high-score file and puts it 
     * in "scores"-arraylist 
     */
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    } 
    
    /**
     * Description:This method writes the "score"-arraylist to the file
     */
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Description: This method displays the top score in a specific format
     * @return highScoreString - the format on how should each score is displayed
     */
    public String getHighscoreString() {
        String highscoreString = "";
        int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + "]" + " " + scores.get(i).getName() + " ------ " + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }   
    
}