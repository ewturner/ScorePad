package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */

import java.util.Date;

public class GameSession {
    private long id;
    private Date startTime;
    private Date endTime;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Date getStartTime(){
        return startTime;
    }

    public void setStartTime(Date time){
        startTime = time;
    }

    public Date getEndTime(){
        return  endTime;
    }

    public void setEndTime(Date time){
        endTime = time;
    }

    public String toString(){
        return "GameSession " + id + "-- Started: " + startTime
                + ", Ended: " + endTime;
    }


}
