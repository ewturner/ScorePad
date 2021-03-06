package edu.auburn.eng.csse.comp3710.team14;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Created by BenKnowles on 4/14/14.
 */

public class GameSession {
    int id;
    String start_time;
    String end_time;

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
        this.start_time = startTime;
    }

    public void setEndTime(String endTime){
        this.end_time = endTime;
    }

    //getters
    public long getId(){
        return this.id;
    }

    public String getStartTime(){
        return this.start_time;
    }

    public String getEndTime(){
        return this.end_time;
    }

    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");

        Date date = new Date();

        try {

            date = formatter.parse(this.getStartTime());
            System.out.println(formatter.format(date));


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "Started " + formatter.format(date);
    }


}
