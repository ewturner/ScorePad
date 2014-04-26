package edu.auburn.csse.comp3710.group14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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


    //CREATE TABLE ROWS
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


    //GET TABLE ROWS
    //given a color_id, return the color
    public Color getColorFromId(long color_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_COLOR + " WHERE "
                + COLUMN_ID + " = " + color_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Color color = new Color();
        color.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        color.setColor((c.getInt(c.getColumnIndex(COLUMN_COLOR))));

        return color;
    }

    //given a player_id, return the player
    public Player getPlayerFromId(long player_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER + " WHERE "
                + COLUMN_ID + " = " + player_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Player player = new Player();
        player.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        player.setName((c.getString(c.getColumnIndex(COLUMN_NAME))));

        return player;
    }

    //given a score_id, return the score
    public Score getScoreFromId(long score_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SCORE + " WHERE "
                + COLUMN_ID + " = " + score_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Score score = new Score();
        score.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        score.setScore((c.getInt(c.getColumnIndex(COLUMN_SCORE))));

        return score;
    }

    //given a game_id, return the game
    public Game getGameFromId(long game_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAME + " WHERE "
                + COLUMN_ID + " = " + game_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Game game = new Game();
        game.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        game.setName((c.getString(c.getColumnIndex(COLUMN_NAME))));

        return game;
    }

    //given a gamesession_id, return the game
    public GameSession getGameSessionFromId(long gamesession_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION + " WHERE "
                + COLUMN_ID + " = " + gamesession_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        GameSession gameSession = new GameSession();
        gameSession.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        gameSession.setStartTime((c.getString(c.getColumnIndex(COLUMN_START_TIME))));
        gameSession.setEndTime((c.getString(c.getColumnIndex(COLUMN_END_TIME))));

        return gameSession;
    }

    //given a gamesession_id and a player_id, get the player's color
    public Color getPlayerColor(long gamesession_id, long player_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION_PLAYER_SCORE_COLOR + " WHERE "
                + COLUMN_GAMESESSION_ID + " = " + gamesession_id + " AND "
                + COLUMN_PLAYER_ID + " = " + player_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        long color_id = c.getInt(c.getColumnIndex(COLUMN_COLOR_ID));

        Color color = getColorFromId(color_id);

        return color;
    }

    //given a gamesession_id and a player_id, get the player's score
    public Score getScoreFromGameSessionAndPlayerId(long gamesession_id, long player_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION_PLAYER_SCORE_COLOR + " WHERE "
                + COLUMN_GAMESESSION_ID + " = " + gamesession_id + " AND "
                + COLUMN_PLAYER_ID + " = " + player_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        long score_id = c.getInt(c.getColumnIndex(COLUMN_SCORE_ID));

        Score score = getScoreFromId(score_id);

        return score;
    }

    //given a gamesession_id, get the game being played
    public Game getGameFromGameSessionId(long gamesession_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION_GAME + " WHERE "
                + COLUMN_GAMESESSION_ID + " = " + gamesession_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        long game_id = c.getInt(c.getColumnIndex(COLUMN_GAME_ID));

        Game game = getGameFromId(game_id);

        return game;
    }


    //add a player to a gamesession
    public void addPlayerToGameSession(long player_id, long gamesession_id){
        SQLiteDatabase db = this.getWritableDatabase();

        Score playerScore = new Score(0);
        long score_id = createScore(playerScore);

        //NOT FINISHED -- color_id = random color id from list
        long color_id = 0;

        createGameSessionPlayerScoreColor(gamesession_id, player_id, score_id, color_id);
    }

    //update a player's score
    public int updatePlayerScore(long gamesession_id, long player_id, int amount){
        SQLiteDatabase db = this.getWritableDatabase();


        //NOT FINISHED
        //get player's score id

        //get player's score object

        //update score object

        //update in database

        //return updated score
        return 0;
    }

    //create/start a gamesession
    public long createGameSession(){
        //NOT FINISHED
        return 0;
    }

    //get arraylist of all games
    public ArrayList<Game> getAllGamess(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Game> games = new ArrayList<Game>();


        String selectQuery = "SELECT  * FROM " + TABLE_GAME;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        do{
            long game_id = c.getInt(c.getColumnIndex(COLUMN_ID));
            Game game = getGameFromId(game_id);
            games.add(game);
        } while (c.moveToNext());

        return games;
    }

    //get arraylist of all players
    public ArrayList<Player> getAllPlayers(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Player> players = new ArrayList<Player>();


        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
            do{
                long player_id = c.getInt(c.getColumnIndex(COLUMN_ID));
                Player player = getPlayerFromId(player_id);
                players.add(player);
            } while (c.moveToNext());

        return players;
    }

}
