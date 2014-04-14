package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class Color {
    private String hexColor;

    public Color(){
        hexColor = "#fff";
    }

    public void setHexColor(String color){
        hexColor = color;
    }

    public String getHexColor(){
        return hexColor;
    }

}
