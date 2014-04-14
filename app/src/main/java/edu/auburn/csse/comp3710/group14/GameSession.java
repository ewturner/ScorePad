package edu.auburn.csse.comp3710.group14;

/**
 * Created by BenKnowles on 4/14/14.
 */

import java.util.Date;

public class GameSession {
    private Date startTime;
    private Date endTime;

    public GameSession(){
        startTime = new Date();
        endTime = null;
    }

    public void endGame(){
        endTime = new Date();
    }

    public Date getStartTime(){
        return startTime;
    }

    public Date getEndTime(){
        return  endTime;
    }
}
