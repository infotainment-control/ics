package ics.infortainment_control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import java.io.IOException;

import ics.infortainment_control.commands.IRBlasterManager;
import ics.infortainment_control.devices.database.DatabaseHelper;
import ics.infortainment_control.user_interface.dvd_fragment;
import ics.infortainment_control.user_interface.premieres_fragment;
import ics.infortainment_control.user_interface.settings_fragment;
import ics.infortainment_control.user_interface.tv_fragment;

public class InfortainmentControl extends ActionBarActivity {

    // exposed globally via getAppContext() for the IRBlasterManager to have access to the Context.CONSUMER_IR_SERVICE
    // note: this class must have called onCreate() before other classes attempt to make use of the IRBlasterManager
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    BottomBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // hook to aid development, especially determining device infrared codes
        if (true) devHook();

        // sets tv as default fragment
        tv_fragment f = new tv_fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();

        // event listener for tab clicks
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.navigation, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.navigation_tv) {
                    tv_fragment f = new tv_fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.navigation_dvd) {
                    dvd_fragment f = new dvd_fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.navigation_premieres) {
                    premieres_fragment f = new premieres_fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.navigation_settings) {
                    settings_fragment f = new settings_fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }

            }
        });

        // sets the color for each tab
        /*
        mBottomBar.mapColorForTab(0, "#000000");
        mBottomBar.mapColorForTab(1, "#FF8F00");
        mBottomBar.mapColorForTab(2, "#FFEB3B");
        mBottomBar.mapColorForTab(3, "#616161");*/


        mBottomBar.mapColorForTab(0, "#90A4AE");
        mBottomBar.mapColorForTab(1, "#607D8B");
        mBottomBar.mapColorForTab(2, "#455A64");
        mBottomBar.mapColorForTab(3, "#263238");
    }

    public static Context getAppContext() {
        return context;
    }

    private void devHook() {
        //issueHardCommand();
        //alternateHardCommands();
        issueDBInteractions();
    }

    private void issueDBInteractions() {

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db     = helper.getReadableDatabase();

        Log.d("[InfortainmentControl]", "how did your database opening go?");

    }

    private void issueHardCommand() {
        for(int i = 0; i < 3; ++i) {
            IRBlasterManager.getInstance().issueCommand("0000 006C 0022 0003 00AD 00AD 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 06A4 00AD 00AD 0016 0041 0016 0E6C");
            // ^open/close for an LG dvd player

            //IRBlasterManager.getInstance().issueCommand("0000 006C 0022 0003 00AD 00AD 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 06A4 00AD 00AD 0016 0041 0016 0E6C");
            // ^power for an LG dvd player
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void alternateHardCommands() {
        String brp_a = "0000 0069 0000 000d 0060 0018 0030 0018 0018 0018 0030 0018 0018 0018 0030 0018 0018 0018 0018 0018 0030 0018 0018 0018 0018 0018 0018 0018 0018 0411";
        for(int i = 0; i < 5; ++i) {

            //IRBlasterManager.getInstance().issueCommand("0000 0067 0000 0015 0060 0018 0030 0018 0018 0018 0030 0018 0018 0018 0030 0018 0018 0018 0018 0018 0018 0018 0030 0018 0018 0018 0030 0018 0030 0018 0030 0018 0018 0018 0018 0018 0030 0018 0018 0018 0018 0018 0030 0018 0018 0216");
            //IRBlasterManager.getInstance().issueCommand("0000 0069 0000 000d 0060 0018 0030 0018 0018 0018 0030 0018 0018 0018 0030 0018 0018 0018 0018 0018 0030 0018 0018 0018 0018 0018 0018 0018 0018 0411");
            IRBlasterManager.getInstance().issueCommand(brp_a);
            // ^power for a sony dvd player
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 5; ++i) {
            IRBlasterManager.getInstance().issueCommand("0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 06FB");
            // ^power ?

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        IRBlasterManager.getInstance().issueCommand("0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 06FB");
        // ^source

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IRBlasterManager.getInstance().issueCommand("\n" +
                "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 06FB");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IRBlasterManager.getInstance().issueCommand("\n" +
                "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 06FB");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IRBlasterManager.getInstance().issueCommand("\n" +
                "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 06FB");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ^ down

        IRBlasterManager.getInstance().issueCommand("0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 06FB");
        // ^ enter
    }
}

