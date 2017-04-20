package ics.infortainment_control.devices.Data.Repo;



import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import ics.infortainment_control.devices.Data.Model.PremiereDB;
import ics.infortainment_control.devices.Data.DatabaseManager;


public class PremiereRepo {

    private PremiereDB premiereDB;

    public PremiereRepo(){

        premiereDB = new PremiereDB();

    }


    public static String createTable(){
        return " CREATE TABLE " + PremiereDB.TABLE  + " ( "
                + PremiereDB.KEY_PremID  + " TEXT PRIMARY KEY , "
                + PremiereDB.KEY_PremTitle + " TEXT , "
                + PremiereDB.KEY_PremGenre  + " TEXT , "
                + PremiereDB.KEY_PremCategory  + " TEXT , "
                + PremiereDB.KEY_Type + " TEXT , "
                + PremiereDB.KEY_PremInfo + " TEXT ) ";
    }



    public void insert(PremiereDB premiereDB) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(PremiereDB.KEY_PremID, premiereDB.getPremiereId());
        values.put(PremiereDB.KEY_PremTitle, premiereDB.getPremiereTitle());
        values.put(PremiereDB.KEY_PremGenre, premiereDB.getPremiereGenre());
        values.put(PremiereDB.KEY_PremCategory, premiereDB.getPremiereCategory());
        values.put(PremiereDB.KEY_Type, premiereDB.getPremiereType());
        values.put(PremiereDB.KEY_PremInfo, premiereDB.getPremiereInfo());


        // Inserting Row
        db.insert(PremiereDB.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(PremiereDB.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }





}