package com.markeffects.samsungoutreach;

import android.hardware.ConsumerIrManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

// TODO this class is 100% ass. Definitely separate frequency and pulse derivation, tidy up the collections... etc. etc.
public class IRBlasterManager {

    private static final String TAG = "[INFRARED_CODE_UTILITY]";

    // TODO investigate static..... the Manager classes ought to be singletons, right?
    private final ConsumerIrManager irManager;

    IRBlasterManager(ConsumerIrManager irManager) {
        this.irManager = irManager;
    }

    public void issueCommand(String rawHexCode) {
        Map<String, List<Integer>> commandData = translateStandardCommandFormat(rawHexCode);

        int frequency = commandData.get("frequency").get(0);

        List<Integer> decimalDurationsList = commandData.get("durations");
        int[] decimalDurations = new int[decimalDurationsList.size()];
        //for(Integer duration : decimalDurationsList ) {
        for(int i = 0; i < decimalDurationsList.size(); ++i) {
            decimalDurations[i] = decimalDurationsList.get(i);
        }

        irManager.transmit(frequency, decimalDurations);
    }


    /**
     * Method that does the thing, you dig? No? Fine, I'll comment better later.
     *
     * @param pulsesInHex
     * @return
     */
    public static Map<String, List<Integer>> translateStandardCommandFormat(String pulsesInHex) {

        List<Integer> frequency = new ArrayList<>(1);
        List<Integer> durations;

        String pulsesInDecimal = hexToDec(pulsesInHex);
        String durationData = countToDuration(pulsesInDecimal);

        // todo parse out the list of durations
        List<String> everything = new ArrayList<>(Arrays.asList(durationData.split(",")));

        //frequency.add(Integer.valueOf(everything.remove(0)));
        frequency.add(Integer.valueOf(pulsesInDecimal.substring(0, pulsesInDecimal.indexOf(","))));

        durations = new ArrayList<>(everything.size());
        for(String duration : everything) {
            durations.add(Integer.valueOf(duration));
        }

        // construct return object
        Map<String, List<Integer>> translatedCommand = new HashMap<>(2);
        translatedCommand.put("frequency", frequency);
        translatedCommand.put("durations", durations);

        return translatedCommand;
    }

    /**
     * Method for converting an infrared command from the standard hexadecimal IR code format
     * to decimal format
     *
     * @author Tobias Bielohlawek
     * https://github.com/rngtng/IrDude/blob/master/src/com/rngtng/irdude/MainActivity.java
     *
     * @param irData the standard
     * @return the pulses converted to decimal values
     */
    //private Map<String, Integer> hexToDec(String irData) {
    private static String hexToDec(String irData) {
        List<String> list = new ArrayList<>(Arrays.asList(irData.split(" ")));
        list.remove(0); // dummy
        int frequency = Integer.parseInt(list.remove(0), 16); // frequency
        list.remove(0); // seq1
        list.remove(0); // seq2

        for (int i = 0; i < list.size(); i++) {
            list.set(i, Integer.toString(Integer.parseInt(list.get(i), 16)));
        }

        frequency = (int) (1000000 / (frequency * 0.241246));
        list.add(0, Integer.toString(frequency));

        irData = "";
        for (String s : list) {
            irData += s + ",";
        }
        return irData;
    }

    /**
     * Method for converting...... you get the picture
     *
     * @author um... Randy
     * http://stackoverflow.com/a/25518468
     *
     * @param countPattern the standard
     * @return
     */
    //private Map<String, Integer> countToDuration(Map<String, Integer> command) {
    private static String countToDuration(String countPattern) {
        List<String> list = new ArrayList<>(Arrays.asList(countPattern.split(",")));
        int frequency = Integer.parseInt(list.get(0));
        int pulses = 1000000/frequency;
        int count;
        int duration;

        list.remove(0);

        for (int i = 0; i < list.size(); i++) {
            count = Integer.parseInt(list.get(i));
            duration = count * pulses;
            list.set(i, Integer.toString(duration));
        }

        String durationPattern = "";
        for (String s : list) {
            durationPattern += s + ",";
        }

        Log.d(TAG, "Frequency: " + frequency);
        Log.d(TAG, "Duration Pattern: " + durationPattern);

        return durationPattern;
    }
}
