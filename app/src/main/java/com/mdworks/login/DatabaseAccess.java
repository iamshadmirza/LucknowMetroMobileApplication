package com.mdworks.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all station from the database.
     *
     * @return a List of station
     */
    public List<String> getStation() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM station_data", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String getDistance(String station_name){
        String distance = null;
        Cursor cursor = database.rawQuery("SELECT distance from station_data WHERE name =? ", new String[] {station_name} );
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            distance = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        return distance;
    }

    public List<String> getRoute(String source, String destination){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM station_data WHERE distance>=? AND distance<=?",  new String[] {source, destination});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;

    }
}
