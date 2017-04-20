package ics.infortainment_control.devices.Data.Repo;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import ics.infortainment_control.devices.Data.DatabaseManager;
import ics.infortainment_control.devices.Data.Model.Channel;

public class ChannelRepo {

    private Channel channel;

    public ChannelRepo(){

        channel = new Channel();

    }


    public static String createTable(){
        return "CREATE TABLE " + Channel.TABLE  + "("
                + Channel.KEY_ChannelId  + "   PRIMARY KEY    ,"
                + Channel.KEY_ChannelName + " TEXT )";
    }


    public int insert(Channel channel) {
        int courseId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Channel.KEY_ChannelId, channel.getChannelId());
        values.put(Channel.KEY_ChannelName, channel.getChannelName());

        // Inserting Row
        courseId=(int)db.insert(Channel.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return courseId;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Channel.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }






}