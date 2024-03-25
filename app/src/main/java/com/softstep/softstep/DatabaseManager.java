package com.softstep.softstep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context _context) {
        context = _context;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert (String name, String description, String instructions, String videopath, String tags){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EXERCISE_NAME, name);
        contentValues.put(DatabaseHelper.EXERCISE_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.EXERCISE_INSTRUCTIONS, instructions);
        contentValues.put(DatabaseHelper.EXERCISE_VIDEOPATH, videopath);
        contentValues.put(DatabaseHelper.EXERCISE_TAGS, tags);
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch() {
        String [] columns = new String[] {DatabaseHelper.EXERCISE_ID, DatabaseHelper.EXERCISE_NAME, DatabaseHelper.EXERCISE_DESCRIPTION, DatabaseHelper.EXERCISE_INSTRUCTIONS, DatabaseHelper.EXERCISE_VIDEOPATH, DatabaseHelper.EXERCISE_TAGS };
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
