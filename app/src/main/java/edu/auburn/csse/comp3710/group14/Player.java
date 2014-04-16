package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Player {
    int id;
    String name;

    public Player(){}

    public Player(String name){
        this.name = name;
    }

    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    //getters
    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
