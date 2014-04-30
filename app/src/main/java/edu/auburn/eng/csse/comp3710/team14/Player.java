package edu.auburn.eng.csse.comp3710.team14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Player implements Comparable<Player>{
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

    public String toString() { return this.name; }

    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(!(obj instanceof Player))
            return false;

        return (this.id == ((Player) obj).getId());
    }

    @Override
    public int compareTo(Player other){
        return this.getName().compareTo(other.getName());
    }
}
