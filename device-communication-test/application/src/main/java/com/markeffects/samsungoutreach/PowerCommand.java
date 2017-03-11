package com.markeffects.samsungoutreach;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Map;

public class PowerCommand extends AppCompatActivity {

    private static final int   SAMSUNG_FREQUENCY = 38028;
    private static final int[] SAMSUNG_POWER_TOGGLE_DURATION = {4495,4368,546,1638,546,1638,546,1638,546,546,546,546,546,546,546,546,546,546,546,1638,546,1638,546,1638,546,546,546,546,546,546,546,546,546,546,546,546,546,1638,546,546,546,546,546,546,546,546,546,546,546,546,546,1664,546,546,546,1638,546,1638,546,1638,546,1638,546,1638,546,1638,546,46644,4394,4368,546,546,546,96044};

    private static final int INSIGNIA_FREQUENCY = -1;
    private static final int[] INSIGNIA_POWER_TOGGLE_DURATION = {-1,-1};

    private ConsumerIrManager mCIR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCIR = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCIR.hasIrEmitter()) {
                    System.out.println("we're cooking with gas, baby!");
                    for(int i = 1; i <= 100; ++i) {
                        System.out.println( "attempt " + i + " of 100 to toggle the tv power");
                        mCIR.transmit(SAMSUNG_FREQUENCY, SAMSUNG_POWER_TOGGLE_DURATION);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        System.out.println("checking utility class...");

        Log.d("POWER_COMMAND", "we're inside............");

        Map<String, List<Integer>> command_power_samsung = InfraredCodeUtility.translateStandardCommandFormat("0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0040 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e");

        List<Integer> durations = command_power_samsung.get("durations");
        String durationsString = String.valueOf(durations.remove(0));
        for(Integer duration : durations) {
            durationsString += "," + String.valueOf(duration);
        }

        System.out.println("expected frequency: " + String.valueOf(SAMSUNG_FREQUENCY));
        System.out.println("computed frequency: " + String.valueOf(command_power_samsung.get("frequency").get(0)));

        System.out.println("expected durations: " + "4495,4368,546,1638,546,1638,546,1638,546,546,546,546,546,546,546,546,546,546,546,1638,546,1638,546,1638,546,546,546,546,546,546,546,546,546,546,546,546,546,1638,546,546,546,546,546,546,546,546,546,546,546,546,546,1664,546,546,546,1638,546,1638,546,1638,546,1638,546,1638,546,1638,546,46644,4394,4368,546,546,546,96044");
        System.out.println("computed durations: " + durationsString);

        System.out.println("... checking complete. hope all went well!");
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
}
