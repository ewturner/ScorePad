package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Player {
    private String playerName;
    private Score playerScore;
    private Color playerColor;

    public Player(String name){
        playerName = name;
        playerScore = new Score();
        playerColor = new Color();
    }

    public void setName(String name){
        playerName = name;
    }

    public String getName(){
        return playerName;
    }

    public Score getPlayerScore(){
        return playerScore;
    }

    public Color getPlayerColor(){
        return playerColor;
    }
}
