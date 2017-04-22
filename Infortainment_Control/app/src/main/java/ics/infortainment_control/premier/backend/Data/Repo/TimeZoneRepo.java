package ics.infortainment_control.premier.backend.Data.Repo;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import ics.infortainment_control.premier.backend.Data.DatabaseManager;
import ics.infortainment_control.premier.backend.Data.Model.TimeZone;


public class TimeZoneRepo {

    private TimeZone timeZone;

    public TimeZoneRepo(){

        timeZone = new TimeZone();

    }


    public static String createTable(){
        return "CREATE TABLE " + TimeZone.TABLE  + "("
                + TimeZone.KEY_TimeZoneId + " TEXT  PRIMARY KEY, "
                + TimeZone.KEY_AirTime + " TEXT, "
                + TimeZone.KEY_TimeStamp + " TEXT, "
                + TimeZone.KEY_TimeZone + " TEXT )";
    }



    public int insert(TimeZone timeZone) {
        int majorId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(TimeZone.KEY_TimeZoneId, timeZone.getTimeZoneId());
        values.put(TimeZone.KEY_TimeZone, timeZone.getTimeZone());
        values.put(TimeZone.KEY_AirTime, timeZone.getAirTime());
        values.put(TimeZone.KEY_TimeStamp, timeZone.getTimeStamp());

        // Inserting Row
        majorId=(int)db.insert(TimeZone.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return majorId;

    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TimeZone.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

}