package com.markeffects.core;

import android.hardware.ConsumerIrManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
class IRBlasterManager {

    private static final String TAG = "[IRBlasterManager]";

    private final ConsumerIrManager irManager;

    IRBlasterManager(ConsumerIrManager irManager) {
        this.irManager = irManager;
    }

    void issueCommand(String hexCodeString) {

        Log.d(TAG, "Issuing Infrared Command...");

        // split the codes
        List<String> hexCodes = new ArrayList<>(Arrays.asList(hexCodeString.split(" ")));

        // the 1st, 2nd, 3rd and 4th codes are not part of the pulse sequence
        //     1st is a dummy  code
        //     2nd is the freq code
        //     3rd is the seq1 code
        //     4th is the seq2 code
        List<String> hexPulses = hexCodes.subList(4, hexCodes.size());

        // frequency is the 2nd element in the code string
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
