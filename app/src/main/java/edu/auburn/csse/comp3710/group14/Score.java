package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Score {
    private int score;
    private int rank;

    public Score(){
        score = 0;
        rank = 0;
    }

    public int resetScore(){
        score = 0;
        return score;
    }

    public int updateScore(int amount){
        score += amount;
        updateRank();
        return score;
    }

    public void updateRank(){
        //scan database for other score objects associated with
        //this game session


        //update the rank of each to match the updated score

        //reorder list

    }

    public int getRank(){
        return rank;
    }



}
