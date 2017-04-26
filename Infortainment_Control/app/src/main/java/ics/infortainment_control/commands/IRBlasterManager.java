package ics.infortainment_control.commands;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ics.infortainment_control.InfortainmentControl;

/**
 *  Wraps the ConsumerIrManager class, converting the standard infrared
 *  hexadecimal pulse codes to meet the API's format.
 *
 *  Credit to Tobias Bielohlawek
 *  https://github.com/rngtng/IrDude/blob/master/src/com/rngtng/irdude/MainActivity.java
 *
 *  Credit to "Randy"
 *  http://stackoverflow.com/a/25518468
 */
public class IRBlasterManager {

    // TODO create a hardware assessment class that wraps the ConsumerIrManager's device-querying
    // functions. It would determine:
    //    A) if the device has an infrared blaster (if not, it disables the command-issuing module of the app)
    //    B) the device's IR Blaster frequency range and store it in the user table


    private static final IRBlasterManager INSTANCE = new IRBlasterManager();
    private static ConsumerIrManager irManager = null;

    private IRBlasterManager() {}

    // TODO refactor to lazily instantiate the IRBlasterManager
    public static IRBlasterManager getInstance() {
        if (irManager == null) {
            Context context = InfortainmentControl.getAppContext();
            if (context == null) {
                throw new RuntimeException("ERROR - attempting to invoke IRBlasterManager before application has initialized");
            }
            irManager = (ConsumerIrManager) context.getSystemService(Context.CONSUMER_IR_SERVICE);
        }

        return INSTANCE;
    }

    private static final String TAG = "[IRBlasterManager]";

    // TODO consider, perhaps not here, but at command registry, validating issuability of commands
    public void issueCommand(String hexCodeString) {

        Log.d(TAG, "Issuing Infrared Command...");

        // split the codes
        List<String> hexCodes = new ArrayList<>(Arrays.asList(hexCodeString.split(" ")));

        // the 1st, 2nd, 3rd and 4th words are not part of the pulse sequence
        //     1st is a code to indicate if the pulse sequence was learned or given from the manufacturer
        //     2nd is the freq code: the carrier frequency in Hertz
        //     3rd is the burst pair sequence 1 code
        //     4th is the burst pair sequence 2 code
        List<String> hexPulses = hexCodes.subList(4, hexCodes.size());

        // frequency is the 2nd word in the code string
        int frequency = Integer.parseInt(hexCodes.get(1), 16);
        frequency = (int) (1000000 / (frequency * 0.241246));

        Log.d(TAG, "Frequency: " + frequency);

        // retrieve the pulse durations in decimal value using the frequency
        int[] pattern = getDecimalDurations(frequency, hexPulses);

        String patternString = String.valueOf(pattern[0]);
        for(int i = 1; i < pattern.length; ++i) {
            patternString += "," + String.valueOf(pattern[i]);
        }
        Log.d(TAG, "Duration Pattern: " + patternString  );

        // execute the transmission
        irManager.transmit(frequency, pattern);
    }

    private int[] getDecimalDurations(int frequency, List<String> hexPulses) {
        int[] decimalDurations = new int[hexPulses.size()];

        int pulses = 1000000 / frequency;
        int count;
        int duration;

        for(int i = 0; i < hexPulses.size(); ++i) {
            count    = Integer.parseInt(hexPulses.get(i), 16);
            duration = count * pulses;

            decimalDurations[i] = duration;
        }

        return decimalDurations;
    }
}
