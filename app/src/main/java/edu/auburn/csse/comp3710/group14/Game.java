package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Game {
    int id;
    String name;

    public Game(){}

    public Game(String name){
        this.name = name;
    }

    public Game(int id, String name){
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
    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String toString() { return this.name; }


}
