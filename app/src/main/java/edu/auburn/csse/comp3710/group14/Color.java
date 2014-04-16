package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Color {
    int id;
    int color;

    public Color(){}

    public Color(int color){
        this.color = color;
    }

    public Color(int id, int color){
        this.id = id;
        this.color = color;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setColor(int color){
        this.color = color;
    }

    //getters
    public int getId(){
        return this.id;
    }

    public int getColor(){
        return this.color;
    }
}
