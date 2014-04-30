package edu.auburn.eng.csse.comp3710.team14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Game implements Comparable<Game>{
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

    @Override
    public int compareTo(Game other){
        return this.getName().compareTo(other.getName());
    }


}
