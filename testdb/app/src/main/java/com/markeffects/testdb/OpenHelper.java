package com.markeffects.testdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class OpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    static final String MY_DATABASE_NAME   = "HeyThere";
    static final String MY_TABLE_NAME      = "Statuses";

    static final String MESSAGE_COL_NAME   = "message";
    static final String TIMESTAMP_COL_NAME = "timestamp";

    private static final String CREATE_DATABASE_COMMAND =
            "CREATE TABLE " + MY_TABLE_NAME +
            " ("
                 + MESSAGE_COL_NAME   + " TEXT, "
                 + TIMESTAMP_COL_NAME + " TEXT" +
            ");";

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    private static final String TAG = "[OPEN_HELPER]";

    OpenHelper(Context context) {
        super(context, MY_DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE_COMMAND);
        populateDatabase(db);
    }



    private void populateDatabase(SQLiteDatabase db) {
        // for optimizing when addressing the large insert of the device data, see blog post at
        // http://www.outofwhatbox.com/blog/2010/12/android-using-databaseutils-inserthelper-for-faster-insertions-into-sqlite-database/

        ContentValues contentValues = new ContentValues();

        List<String> messages = new ArrayList<>(5);
        messages.add("msg 1"); messages.add("msg 2"); messages.add("HI"); messages.add("msg 4"); messages.add("msg 5");

        for (String message : messages) {
            String timestamp = sdf.format(new Date());
            Log.d(TAG, "date trying to place in database" + timestamp);

            contentValues.put(MESSAGE_COL_NAME, message);
            contentValues.put(TIMESTAMP_COL_NAME, timestamp);

            db.insert(MY_TABLE_NAME, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "unsupported operation. we're not versioning our internal database!");
    }
}

/*
public class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "dictionary";
    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                KEY_WORD + " TEXT, " +
                KEY_DEFINITION + " TEXT);";

    DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }
}
 */
