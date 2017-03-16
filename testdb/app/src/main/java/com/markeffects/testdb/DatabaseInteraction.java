package com.markeffects.testdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;

public class DatabaseInteraction extends AppCompatActivity {

    private static final String TAG = "[MAIN_ACTIVITY]";

    String myStr = "hihihihihihi.";
    String other = "bye bye bye!!";

    boolean oneOrTheOther = false;

    OpenHelper dbHelper;// = new FeedReaderDbHelper(getContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_interaction);


        dbHelper = new OpenHelper(getApplicationContext());
        SQLiteDatabase sqlDB = dbHelper.getReadableDatabase();

        String[] projection = {
                OpenHelper.MESSAGE_COL_NAME,
                OpenHelper.TIMESTAMP_COL_NAME
        };

        Cursor resultCursor = sqlDB.query(OpenHelper.MY_TABLE_NAME, projection, null, null, null, null, null);

        int messageColIndex   = resultCursor.getColumnIndex(OpenHelper.MESSAGE_COL_NAME  );
        int timestampColIndex = resultCursor.getColumnIndex(OpenHelper.TIMESTAMP_COL_NAME);

        while(resultCursor.moveToNext()) {
            String message = resultCursor.getString(messageColIndex);
            Date timestamp = new Date(resultCursor.getInt(timestampColIndex) * 1000);

            Log.d(TAG, "message: " + message);
            Log.d(TAG, "created: " + timestamp.toString());
        }

//        List itemIds = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            long itemId = cursor.getLong(
//                    cursor.getColumnIndexOrThrow(FeedEntry._ID));
//            itemIds.add(itemId);
//        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //final TextView text = (TextView) findViewById(R.id.my_text_field);
        final TextView text = (TextView) findViewById(R.id.message);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                String next = (oneOrTheOther)
//                        ? myStr
//                        : other;
//
//                text.setText(next);

//                oneOrTheOther = ! oneOrTheOther;

                String inputMessage = text.getText().toString();
                if (! inputMessage.equals(getResources().getString(R.string.default_message))) {
                    Log.d(TAG, "trying to submit default entry -> no op");
                }

            }
        });

        resultCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database_interaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


/*
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.markeffects.testdb.DatabaseInteraction">

    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" />

    </android.support.design.widget.AppBarLayout>

    <include layout="@layout/content_database_interaction" />

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        app:srcCompat="@android:drawable/ic_dialog_email" />

</android.support.design.widget.CoordinatorLayout>

 */

/*
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context="com.markeffects.testdb.DatabaseInteraction"
    tools:showIn="@layout/activity_database_interaction">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>

 */