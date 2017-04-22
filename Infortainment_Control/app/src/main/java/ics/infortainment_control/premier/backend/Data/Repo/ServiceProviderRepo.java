package ics.infortainment_control.premier.backend.Data.Repo;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ics.infortainment_control.premier.backend.Data.DatabaseManager;
import ics.infortainment_control.premier.backend.Data.Model.Channel;
import ics.infortainment_control.premier.backend.Data.Model.PremiereDB;
import ics.infortainment_control.premier.backend.Data.Model.ServiceProvider;
import ics.infortainment_control.premier.backend.Data.Model.TimeZone;
import ics.infortainment_control.premier.backend.Model.PremiereList;

import java.util.ArrayList;
import java.util.List;


public class ServiceProviderRepo {
    private final String TAG = ServiceProviderRepo.class.getSimpleName().toString();

    public ServiceProviderRepo() {

    }

    private ServiceProvider serviceProvider;



    public static String createTable(){
        return " CREATE TABLE " + ServiceProvider.TABLE  + " ( "
                + ServiceProvider.KEY_ServiceProviderID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + ServiceProvider.KEY_PremID + " TEXT , "
                + ServiceProvider.KEY_ChannelId + " TEXT,  "
                + ServiceProvider.KEY_ChannelNumber + " TEXT )";

    }



    public void insert(ServiceProvider serviceProvider) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(serviceProvider.KEY_PremID, serviceProvider.getPremiereId());
        values.put(serviceProvider.KEY_ChannelId, serviceProvider.getChannelId());
        values.put(serviceProvider.KEY_ChannelNumber, serviceProvider.getChannelNumber());

        // Inserting Row
        db.insert(ServiceProvider.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

    }


    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(ServiceProvider.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<PremiereList> getServiceProvider(){
        PremiereList premiereList = new PremiereList();
        List<PremiereList> premiereLists = new ArrayList<PremiereList>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT PremiereDB." + PremiereDB.KEY_PremID
                + ", PremiereDB." + PremiereDB.KEY_PremTitle
                + ", PremiereDB." + PremiereDB.KEY_PremGenre
                + ", PremiereDB." + PremiereDB.KEY_PremCategory
                + ", PremiereDB." + PremiereDB.KEY_Type
                + ", PremiereDB." + PremiereDB.KEY_PremInfo
                + ", Channel." + Channel.KEY_ChannelId
                + ", Channel." + Channel.KEY_ChannelName
                + ", TimeZone." + TimeZone.KEY_TimeZoneId
                + ", TimeZone. " + TimeZone.KEY_AirTime
                + ", ServiceProvider." + ServiceProvider.KEY_ChannelNumber
                + " FROM " + PremiereDB.TABLE + "  As PremiereDB "
                + " INNER JOIN " + ServiceProvider.TABLE + " ServiceProvider ON ServiceProvider." + ServiceProvider.KEY_PremID + " =  PremiereDB." + PremiereDB.KEY_PremID
                + " INNER JOIN " + Channel.TABLE + " Channel ON Channel." + Channel.KEY_ChannelId + "=  ServiceProvider." + ServiceProvider.KEY_ChannelId
                + " INNER JOIN " + TimeZone.TABLE + " TimeZone ON TimeZone." + TimeZone.KEY_TimeZoneId + "=  Channel." + Channel.KEY_ChannelId;
        ;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                premiereList = new PremiereList();
                premiereList.setPremiereId(cursor.getString(cursor.getColumnIndex(PremiereDB.KEY_PremID)));
                premiereList.setPremiereTitle(cursor.getString(cursor.getColumnIndex(PremiereDB.KEY_PremTitle)));
                premiereList.setPremiereGenre(cursor.getString(cursor.getColumnIndex(PremiereDB.KEY_PremGenre)));
                premiereList.setPremiereCategory(cursor.getString(cursor.getColumnIndex(PremiereDB.KEY_PremCategory)));
                premiereList.setPremiereType(cursor.getString(cursor.getColumnIndex(PremiereDB.KEY_Type)));
                premiereList.setPremiereInfo(cursor.getString(cursor.getColumnIndex(PremiereDB.KEY_PremInfo)));
                premiereList.setChannelId(cursor.getString(cursor.getColumnIndex(Channel.KEY_ChannelId)));
                premiereList.setChannelName(cursor.getString(cursor.getColumnIndex(Channel.KEY_ChannelName)));
                premiereList.setTimeZoneId(cursor.getString(cursor.getColumnIndex(TimeZone.KEY_TimeZoneId)));
                premiereList.setAirTime(cursor.getString(cursor.getColumnIndex(TimeZone.KEY_AirTime)));
                premiereList.setChannelNumber(cursor.getString(cursor.getColumnIndex(ServiceProvider.KEY_ChannelNumber)));


                premiereLists.add(premiereList);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return premiereLists;

    }

}