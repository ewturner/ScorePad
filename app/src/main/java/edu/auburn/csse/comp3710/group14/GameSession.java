package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */

public class GameSession {
    int id;
    String startTime;
    String endTime;

    public GameSession(){
    }

    public GameSession(int id){
        this.id = id;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    //getters
    public long getId(){
        return this.id;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }


}
