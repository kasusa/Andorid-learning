package android.example.ADemos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Animi.db";
    String SQL_CREATE_ENTRIES = "CREATE TABLE \"AnimeCharacter\" (\n" +
            "\t\"ID\"\tINTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"name\"\tTEXT,\n" +
            "\t\"gender\"\tTEXT,\n" +
            "\t\"birthday\"\tTEXT,\n" +
            "\t\"Animation\"\tTEXT\n" +
            ")";
    String SQL_DELETE_ENTRIES = "";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
