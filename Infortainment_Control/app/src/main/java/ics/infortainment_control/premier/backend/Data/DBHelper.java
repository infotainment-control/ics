package ics.infortainment_control.premier.backend.Data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ics.infortainment_control.app.App;
import ics.infortainment_control.premier.backend.Data.Model.Channel;
import ics.infortainment_control.premier.backend.Data.Model.PremiereDB;
import ics.infortainment_control.premier.backend.Data.Model.ServiceProvider;
import ics.infortainment_control.premier.backend.Data.Model.TimeZone;
import ics.infortainment_control.premier.backend.Data.Repo.ChannelRepo;
import ics.infortainment_control.premier.backend.Data.Repo.PremiereRepo;
import ics.infortainment_control.premier.backend.Data.Repo.ServiceProviderRepo;
import ics.infortainment_control.premier.backend.Data.Repo.TimeZoneRepo;


public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =44;
    // Database Name
    private static final String DATABASE_NAME = "PremiereDB.db";
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(ChannelRepo.createTable());
        db.execSQL(PremiereRepo.createTable());
        db.execSQL(TimeZoneRepo.createTable());
        db.execSQL(ServiceProviderRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Channel.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PremiereDB.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TimeZone.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ServiceProvider.TABLE);
        onCreate(db);
    }

}