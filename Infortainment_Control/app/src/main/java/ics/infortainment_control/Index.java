package ics.infortainment_control;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;


public class Index extends ActionBarActivity {

    private TextView mTextMessage;

    BottomBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                else if(menuItemId == R.id.navigation_cable) {
                    cable_fragment f = new cable_fragment();
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
        mBottomBar.mapColorForTab(0, "#000000");
        mBottomBar.mapColorForTab(1, "#FF8F00");
        mBottomBar.mapColorForTab(2, "#FFEB3B");
        mBottomBar.mapColorForTab(3, "#616161");
    }

}
