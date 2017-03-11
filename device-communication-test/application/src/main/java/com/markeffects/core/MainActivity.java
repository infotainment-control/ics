package com.markeffects.core;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

// TODO refactor to be general commanding interface hookup thing
// TODO also, make a few unit tests (those utility methods in IRBlasterManager, I'm looking at you)
public class MainActivity extends AppCompatActivity {

    private DeviceManager deviceManager;
    private IRBlasterManager irBlasterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize key fields
        deviceManager          = new MockDeviceManager();
        ConsumerIrManager mCIR = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
        irBlasterManager       = new IRBlasterManager(mCIR);

        // initialize the sole UI item
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            private int powerCommandsIssued  = 0;
            private int volumeCommandsIssued = 0;


            @Override
            public void onClick(View view) {
                powerCycleMarksTelevisions();
                //changeInsigniaVolume();
            }

            private void powerCycleMarksTelevisions() {
                switch (powerCommandsIssued % 4) {
                    case 0:  case 1:
                        toggleSamsungPower();
                        break;
                    case 2:  case 3:
                        toggleInsigniaPower();
                }
                powerCommandsIssued++;
            }

            private void changeInsigniaVolume() {
                if( volumeCommandsIssued % 10 < 5 ) {
                    lowerInsigniaVolume();
                } else {
                    raiseInsigniaVolume();
                }
                volumeCommandsIssued++;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_power_command, menu);
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

    // TODO these can certainly be refactored. The whole architecture will probably change

    private void toggleSamsungPower() {
        issuePowerCommand(DeviceID.SAMSUNG);
    }

    private void toggleInsigniaPower() {
        issuePowerCommand(DeviceID.INSIGNIA);
    }

    private void lowerInsigniaVolume() {
        issueVolumeDownCommand(DeviceID.INSIGNIA);
    }

    private void raiseInsigniaVolume() {
        issueVolumeUpCommand(DeviceID.INSIGNIA);
    }



    private void issuePowerCommand(DeviceID deviceID) {
        String rawCommand = deviceManager.getRawCommandCode(deviceID, TelevisionCommand.POWER);
        irBlasterManager.issueCommand(rawCommand);
    }

    private void issueVolumeDownCommand(DeviceID deviceID) {
        String rawCommand = deviceManager.getRawCommandCode(deviceID, TelevisionCommand.VOLUME_DOWN);
        irBlasterManager.issueCommand(rawCommand);
    }

    private void issueVolumeUpCommand(DeviceID deviceID) {
        String rawCommand = deviceManager.getRawCommandCode(deviceID, TelevisionCommand.VOLUME_UP);
        irBlasterManager.issueCommand(rawCommand);
    }

}
