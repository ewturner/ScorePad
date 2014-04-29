package edu.auburn.csse.comp3710.group14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
    private static final String TABLE_GAMESESSION_PLAYER_SCORE = "gamesession_player_scores";

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

    //GAMESESSION_PLAYER_SCORE table column names
    private static final String COLUMN_PLAYER_ID = "player_id";
    private static final String COLUMN_SCORE_ID = "score_id";


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
            COLUMN_SCORE + " INTEGER)";

    //gamesession_player_score table create statement
    public static final String CREATE_TABLE_GAMESESSION_PLAYER_SCORE = "CREATE TABLE " +
            TABLE_GAMESESSION_PLAYER_SCORE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_GAMESESSION_ID + " INTEGER," + COLUMN_PLAYER_ID + " INTEGER," +
            COLUMN_SCORE_ID + " INTEGER)";


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
        db.execSQL(CREATE_TABLE_GAMESESSION_PLAYER_SCORE);

        //default data
        db.execSQL("INSERT INTO "+TABLE_GAME+" VALUES(null, 'Baseball')");
        db.execSQL("INSERT INTO "+TABLE_GAME+" VALUES(null, 'Basketball')");
        db.execSQL("INSERT INTO "+TABLE_GAME+" VALUES(null, 'Disc Golf')");
        db.execSQL("INSERT INTO "+TABLE_GAME+" VALUES(null, 'Dominoes')");
        db.execSQL("INSERT INTO "+TABLE_GAME+" VALUES(null, 'Football')");
        db.execSQL("INSERT INTO "+TABLE_GAME+" VALUES(null, 'Golf')");
        db.execSQL("INSERT INTO "+TABLE_GAME+" VALUES(null, 'Scrabble')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        //on upgrade, drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESESSION_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESESSION_PLAYER_SCORE);

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

    //create gamesession_player_scores table entry
    public long createGameSessionPlayerScore(long gamesession_id, long player_id,
                                             long score_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_GAMESESSION_ID, gamesession_id);
        values.put(COLUMN_PLAYER_ID, player_id);
        values.put(COLUMN_SCORE_ID, score_id);

        //insert row
        long gamesession_player_score_id = db.insert(TABLE_GAMESESSION_PLAYER_SCORE, null, values);

        //return record id
        return gamesession_player_score_id;
    }

    //given a player_id, return the player
    public Player getPlayerFromId(long player_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER + " WHERE "
                + COLUMN_ID + " = " + player_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {

            Player player = new Player();
            player.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
            player.setName((c.getString(c.getColumnIndex(COLUMN_NAME))));

            return player;
        }
        else
            return new Player();
    }

    //given a score_id, return the score
    public Score getScoreFromId(long score_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SCORE + " WHERE "
                + COLUMN_ID + " = " + score_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {

            Score score = new Score();
            score.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
            score.setScore((c.getInt(c.getColumnIndex(COLUMN_SCORE))));

            return score;
        }
        else
            return new Score();
    }

    //given a game_id, return the game
    public Game getGameFromId(long game_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAME + " WHERE "
                + COLUMN_ID + " = " + game_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {

            Game game = new Game();
            game.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
            game.setName((c.getString(c.getColumnIndex(COLUMN_NAME))));

            return game;
        }
        else
            return new Game();
    }

    //given a gamesession_id, return the game
    public GameSession getGameSessionFromId(long gamesession_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION + " WHERE "
                + COLUMN_ID + " = " + gamesession_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {

            GameSession gameSession = new GameSession();
            gameSession.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
            gameSession.setStartTime((c.getString(c.getColumnIndex(COLUMN_START_TIME))));
            gameSession.setEndTime((c.getString(c.getColumnIndex(COLUMN_END_TIME))));

            return gameSession;
        }
        else
            return new GameSession();
    }

    //given a gamesession_id and a player_id, get the player's score
    public Score getScoreFromGameSessionAndPlayerId(long gamesession_id, long player_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION_PLAYER_SCORE + " WHERE "
                + COLUMN_GAMESESSION_ID + " = " + gamesession_id + " AND "
                + COLUMN_PLAYER_ID + " = " + player_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {

            long score_id = c.getInt(c.getColumnIndex(COLUMN_SCORE_ID));

            Score score = getScoreFromId(score_id);

            return score;
        }
        else
            return new Score();
    }

    //given a gamesession_id, get the game being played
    public Game getGameFromGameSessionId(long gamesession_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION_GAME + " WHERE "
                + COLUMN_GAMESESSION_ID + " = " + gamesession_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {

            long game_id = c.getInt(c.getColumnIndex(COLUMN_GAME_ID));

            Game game = getGameFromId(game_id);

            return game;
        }
        else
            return new Game();
    }


    //add a player to a gamesession
    public void addPlayerToGameSession(long player_id, long gamesession_id){
        SQLiteDatabase db = this.getWritableDatabase();

        Score playerScore = new Score(0);
        long score_id = createScore(playerScore);

        createGameSessionPlayerScore(gamesession_id, player_id, score_id);
    }

    //update a player's score
    public int updatePlayerScore(long gamesession_id, long player_id, int amount){
        SQLiteDatabase db = this.getWritableDatabase();

        //get player's score object
        Score playerScore = getScoreFromPlayerAndGameSessionId(player_id, gamesession_id);

        //update score object
        int newScore = playerScore.updateScore(amount);

        //update in database
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, playerScore.getScore());

        // updating row
        return db.update(TABLE_SCORE, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(playerScore.getId()) });
    }

    //create/start a gamesession
    public long createGameSession(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_START_TIME, getDateTime());
        values.putNull(COLUMN_END_TIME);

        //insert row
        long gamesession_id = db.insert(TABLE_GAMESESSION, null, values);

        //return gamesession id
        return gamesession_id;
    }

    public void startGameSession(Game game, ArrayList<Player> players){
        long gamesession_id = createGameSession();
        long game_id = game.getId();

        createGameSessionGame(gamesession_id, game_id);

        for(Player player : players){
            addPlayerToGameSession(player.getId(), gamesession_id);
        }
    }

    //get arraylist of all games
    public ArrayList<Game> getAllGames(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Game> games = new ArrayList<Game>();


        String selectQuery = "SELECT  * FROM " + TABLE_GAME;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
                long game_id = c.getInt(c.getColumnIndex(COLUMN_ID));
                Game game = getGameFromId(game_id);
                games.add(game);
            } while (c.moveToNext());
        }

        c.close();

        return games;
    }

    //get arraylist of all players
    public ArrayList<Player> getAllPlayers(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Player> players = new ArrayList<Player>();

        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
                long player_id = c.getInt(c.getColumnIndex(COLUMN_ID));
                Player player = getPlayerFromId(player_id);
                players.add(player);
            } while (c.moveToNext());
        }

        c.close();

        return players;
    }

    public Score getScoreFromPlayerAndGameSessionId(long player_id, long gamesession_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION_PLAYER_SCORE + " WHERE " +
                COLUMN_GAMESESSION_ID + " = " + gamesession_id + " AND " + COLUMN_PLAYER_ID +
                " = " + player_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {

            Score playerScore = getScoreFromId(c.getInt(c.getColumnIndex(COLUMN_SCORE_ID)));
            return playerScore;
        }
        else
            return new Score();
    }

    public ArrayList<Player> getPlayersFromGameSessionId(long gamesession_id){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Player> players = new ArrayList<Player>();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION_PLAYER_SCORE + " WHERE " +
                COLUMN_GAMESESSION_ID + " = " + gamesession_id;

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
                long player_id = c.getInt(c.getColumnIndex(COLUMN_PLAYER_ID));
                Player player = getPlayerFromId(player_id);
                players.add(player);
            } while (c.moveToNext());
        }

        return players;
    }

    public int endGame(long gamesession_id){
        SQLiteDatabase db = this.getWritableDatabase();

        //get player's score object
        GameSession gameSession = getGameSessionFromId(gamesession_id);

        //update score object
        gameSession.setEndTime(getDateTime());

        //update in database
        ContentValues values = new ContentValues();
        values.put(COLUMN_END_TIME, gameSession.getEndTime());

        // updating row
        return db.update(TABLE_GAMESESSION, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(gameSession.getId()) });
    }

    public ArrayList<GameSession> getAllUnfinishedGameSessions(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<GameSession> unfinishedGameSessions = new ArrayList<GameSession>();

        String selectQuery = "SELECT  * FROM " + TABLE_GAMESESSION + " WHERE " +
                COLUMN_END_TIME + " IS NULL";

        Log.e(LOGCAT_TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
                long gamesession_id = c.getInt(c.getColumnIndex(COLUMN_ID));
                GameSession gameSession = getGameSessionFromId(gamesession_id);
                unfinishedGameSessions.add(gameSession);
            } while (c.moveToNext());
        }

        return unfinishedGameSessions;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
