package ics.infortainment_control;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import ics.infortainment_control.commands.DeviceID;
import ics.infortainment_control.commands.DeviceManager;
import ics.infortainment_control.commands.IRBlasterManager;
import ics.infortainment_control.commands.MockDeviceManager;

public class InfortainmentControl extends ActionBarActivity {

    private TextView mTextMessage;

    private DeviceManager deviceManager;
    private IRBlasterManager irBlasterManager;

    private DeviceID currentDevice;

    BottomBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceManager          = new MockDeviceManager();
        ConsumerIrManager mCIR = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
        irBlasterManager       = new IRBlasterManager(mCIR);

        issueHardCommand();

        // sets tv as default fragment
        tv_fragment f = new tv_fragment();

        // todo delegate to lookups, registry service
        deviceManager.setActiveDevice(DeviceID.INSIGNIA);
        f.irBlasterManager = irBlasterManager;
        f.deviceManager    = deviceManager;

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

    public void issueHardCommand() {
        irBlasterManager.issueCommand("0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C");
    }

}
