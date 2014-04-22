package edu.auburn.csse.comp3710.group14;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
    private static final String TABLE_SCORE = "scores";
    private static final String TABLE_COLOR = "colors";
    private static final String TABLE_GAMESESSION_PLAYER_SCORE_COLOR = "gamesession_player_score_colors";

    //Common column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    //GAMESESSIONS table column names
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_END_TIME = "end_time";

    //GAMESESSION_GAMES table column names
    private static final String COLUMN_GAMESESSION_ID = "gamesession_id";
    private static final String COLUMN_GAME_ID = "game_id";

    //SCORES table column names
    private static final String COLUMN_SCORE = "score";

    //COLORS table column names
    private static final String COLUMN_COLOR = "color";

    //GAMESESSION_PLAYER_SCORE_COLORS table column names
    private static final String COLUMN_PLAYER_ID = "player_id";
    private static final String COLUMN_SCORE_ID = "score_id";
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
    public static final String CREATE_TABLE_GAMESESSION_GAME = "CREATE TABLE " +
            TABLE_GAMESESSION_GAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_GAMESESSION_ID + " INTEGER, " + COLUMN_GAME_ID + " INTEGER)";

    //players table create statement
    public static final String CREATE_TABLE_PLAYER = "CREATE TABLE " +
            TABLE_PLAYER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT)";

    //scores table create statment
    public static final String CREATE_TABLE_SCORE = "CREATE TABLE " +
            TABLE_SCORE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_SCORE + " INTEGER";

    //colors table create statement
    public static final String CREATE_TABLE_COLOR = "CREATE TABLE " +
            TABLE_COLOR + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_COLOR + " INTEGER)";

    //gamesession_player_score_colors table create statement
    public static final String CREATE_TABLE_GAMESESSION_PLAYER_SCORE_COLOR = "CREATE TABLE " +
            TABLE_GAMESESSION_PLAYER_SCORE_COLOR + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_GAMESESSION_ID + " INTEGER," + COLUMN_PLAYER_ID + " INTEGER," +
            COLUMN_SCORE_ID + " INTEGER," + COLUMN_COLOR_ID + " INTEGER)";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //create all tables
        db.execSQL(CREATE_TABLE_GAMESESSION);
        db.execSQL(CREATE_TABLE_GAME);
        db.execSQL(CREATE_TABLE_GAMESESSION_GAME);
        db.execSQL(CREATE_TABLE_PLAYER);
        db.execSQL(CREATE_TABLE_SCORE);
        db.execSQL(CREATE_TABLE_COLOR);
        db.execSQL(CREATE_TABLE_GAMESESSION_PLAYER_SCORE_COLOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        //on upgrade, drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESESSION_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESESSION_PLAYER_SCORE_COLOR);

        //create new tables
        onCreate(db);
    }

    //create games table entry
    public long createGame(Game game){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, game.getName());

        //insert row
        long game_id = db.insert(TABLE_GAME, null, values);

        //return record id
        return game_id;
    }

    //create colors table entry
    public long createColor(Color color){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_COLOR, color.getColor());

        //insert row
        long color_id = db.insert(TABLE_COLOR, null, values);

        //return record id
        return color_id;
    }

    //create scores table entry
    public long createScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getScore());

        //insert row
        long score_id = db.insert(TABLE_SCORE, null, values);

        //return record id
        return score_id;
    }

    //create players table entry
    public long createPlayer(Player player){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, player.getName());

        //insert row
        long player_id = db.insert(TABLE_PLAYER, null, values);

        //return record id
        return player_id;
    }

    //create gamesession_games table entry
    public long createGameSessionGame(long gamesession_id, long game_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_GAMESESSION_ID, gamesession_id);
        values.put(COLUMN_GAME_ID, game_id);

        //insert row
        long gamesession_game_id = db.insert(TABLE_GAMESESSION_GAME, null, values);

        //return record id
        return gamesession_game_id;
    }

    //create gamesession_player_score_colors table entry
    public long createGameSessionPlayerScoreColor(long gamesession_id, long player_id,
                                             long score_id, long color_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_GAMESESSION_ID, gamesession_id);
        values.put(COLUMN_PLAYER_ID, player_id);
        values.put(COLUMN_SCORE_ID, score_id);
        values.put(COLUMN_COLOR_ID, color_id);

        //insert row
        long gamesession_player_score_color_id = db.insert(TABLE_GAMESESSION_PLAYER_SCORE_COLOR, null, values);

        //return record id
        return gamesession_player_score_color_id;
    }

    //create gamesessions table entry -- NOT FINISHED
    public long createGameSession(GameSession gameSession, Game game,
                                  Player[] players){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_START_TIME, gameSession.getStartTime());
        values.put(COLUMN_START_TIME, gameSession.getEndTime());

        //insert row
        long gamesession_id = db.insert(TABLE_GAMESESSION, null, values);

        //return record id
        return gamesession_id;
    }

}
