package ics.infortainment_control.devices.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
//    private static final String DB_NAME    = "northwind.db";
    private static final String DB_NAME    = "commands.db";
    private static final int    DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        setForcedUpgrade();
        //super.setForcedUpgrade();
    }
}
