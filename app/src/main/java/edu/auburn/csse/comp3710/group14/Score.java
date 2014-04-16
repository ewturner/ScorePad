package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Score {
    int id;
    int score;

    public Score(){}

    public Score(int score){
        this.score = score;
    }

    public Score(int id, int score){
        this.id = id;
        this.score = score;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setScore(int score){
        this.score = score;
    }

    //getters
    public int getId(){
        return this.id;
    }

    public int getScore(){
        return this.score;
    }
}
