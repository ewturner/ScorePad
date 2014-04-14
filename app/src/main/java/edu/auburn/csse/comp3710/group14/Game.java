package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Game {
    private String gameName;

    public Game(String name){
        gameName = name;
    }

    public void setName(String name){
        gameName = name;
    }

    public String getName(){
        return gameName;
    }
}
