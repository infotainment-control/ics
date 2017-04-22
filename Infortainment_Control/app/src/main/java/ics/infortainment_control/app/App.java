package ics.infortainment_control.app;


import android.app.Application;
import android.content.Context;

import ics.infortainment_control.premier.backend.Data.DBHelper;
import ics.infortainment_control.premier.backend.Data.DatabaseManager;


public class  App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }

    public static Context getContext(){
        return context;
    }

}