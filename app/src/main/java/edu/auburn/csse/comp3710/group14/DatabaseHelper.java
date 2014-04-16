package edu.auburn.csse.comp3710.group14;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BenKnowles on 4/16/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    //Logcat tag
    private static final String LOGCAT_TAG = "DatabaseHelper";

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "scorepadGameManager";

    //Table Names
    private static final String TABLE_GAMESESSION = "gamesessions";
    private static final String TABLE_GAME = "games";
    private static final String TABLE_GAMESESSION_GAME = "gamesession_games";
    private static final String TABLE_PLAYER = "players";
    private static final String TABLE_GAMESESSION_PLAYER = "gamesession_players";
    private static final String TABLE_SCORE = "scores";
    private static final String TABLE_PLAYER_SCORE = "player_scores";
    private static final String TABLE_COLOR = "colors";
    private static final String TABLE_PLAYER_COLOR = "player_colors";

    //Common column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    //GAMESESSIONS table column names
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_END_TIME = "end_time";

    //GAMESESSION_GAMES table column names
    private static final String COLUMN_GAMESESSION_ID = "gamesession_id";
    private static final String COLUMN_GAME_ID = "game_id";

    //GAMESESSION_PLAYERS table column names
    private static final String COLUMN_PLAYER_ID = "player_id";

    //SCORES table column names
    private static final String COLUMN_SCORE = "score";

    //PLAYER_SCORES table column names
    private static final String COLUMN_SCORE_ID = "score_id";

    //COLORS table column names
    private static final String COLUMN_COLOR = "color";

    //PLAYER_COLORS table column names
    private static final String COLUMN_COLOR_ID = "color_id";


    //Table creation
    //gamesessions table create statement
    private static final String CREATE_TABLE_GAMESESSION = "CREATE TABLE " +
            TABLE_GAMESESSION + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_START_TIME + " DATETIME," + COLUMN_END_TIME + " DATETIME)";

    //games table create statement
    private static final String CREATE_TABLE_GAME = "CREATE TABLE " +
            TABLE_GAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT)";

    //gamesession_games table create statement


}
