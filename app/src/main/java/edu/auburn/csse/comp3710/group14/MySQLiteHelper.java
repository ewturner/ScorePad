package edu.auburn.csse.comp3710.group14;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by BenKnowles on 4/14/14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "_id";

    public static final String TABLE_GAMESESSION = "gamesession";
    public static final String COLUMN_STARTTIME = "starttime";
    public static final String COLUMN_ENDTIME = "endtime";
    private static final String GAMESESSION_CREATE = "create table "
            + TABLE_GAMESESSION + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_STARTTIME
            + " datetime , " + COLUMN_ENDTIME + " datetime default null);";


    private static final String DATABASE_NAME = "scorepad.db";
    private static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(GAMESESSION_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESESSION);
        onCreate(db);
    }


}