package edu.auburn.csse.comp3710.group14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class GameSessionDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID,
        MySQLiteHelper.COLUMN_STARTTIME, MySQLiteHelper.COLUMN_ENDTIME};

    public GameSessionDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public GameSession createGameSession() {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_STARTTIME, getDateTime());
        long insertId = database.insert(MySQLiteHelper.TABLE_GAMESESSION,
                null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_GAMESESSION,
                allColumns, MySQLiteHelper.COLUMN_ID + " = "
                        + insertId, null, null, null, null
        );
        cursor.moveToFirst();
        GameSession newGameSession = cursorToGameSession(cursor);
        cursor.close();
        return newGameSession;
    }

    public void deleteGameSession(GameSession gameSession){
        long id = gameSession.getId();
        System.out.println("GameSession deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_GAMESESSION, MySQLiteHelper.COLUMN_ID
            + " = " + id, null);
    }

    public List<GameSession> getAllGameSessions() {
        List<GameSession> gameSessions = new ArrayList<GameSession>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_GAMESESSION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GameSession gameSession = cursorToGameSession(cursor);
            gameSessions.add(gameSession);
            cursor.moveToNext();
        }

        cursor.close();
        return gameSessions;
    }

    private GameSession cursorToGameSession(Cursor cursor){
        GameSession gameSession = new GameSession();
        gameSession.setId(cursor.getLong(0));
        gameSession.setStartTime(new Date());
        gameSession.setEndTime(new Date());
        return gameSession;


    }

}
