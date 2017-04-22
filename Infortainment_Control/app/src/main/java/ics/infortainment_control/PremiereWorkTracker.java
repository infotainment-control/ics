package ics.infortainment_control;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ics.infortainment_control.user_interface.premieres_fragment;

/**
 * Created by Hawkaloo on 4/19/2017.
 */

public class PremiereWorkTracker {

    private       int                runningTasks;
    private final premieres_fragment fragment;
    private final String             filename;
    private final Context            context;

    private ArrayList<List<Premiere>> premiereList;
    private final int taskMax;

    public PremiereWorkTracker(premieres_fragment fragment, int taskMax, String filename, Context context) {
        this.fragment = fragment;
        this.taskMax = taskMax;
        this.filename = filename;
        this.context = context;
        runningTasks = 0;
        premiereList = new ArrayList<>();
    }

    public void LaunchTasks(Elements shows) {
        int splitSize = (int)Math.ceil((double)shows.size() / taskMax);
        int i = 0, j;
        Log.i("INFO", "Show size: " + shows.size() + "\nSplit Size: " + splitSize);

        // Splits shows evenly and launches a new thread for each division
        while (i < shows.size()) {
            Elements splitShows = new Elements();

            // Since Elements is not a list, we have to do this the annoying way ;~;
            for (j = 0; j + i < shows.size() && j < splitSize; j++) {
                splitShows.add(shows.get(j + i));
            }

            PremiereGetter getter = new PremiereGetter(splitShows, this, runningTasks);
            getter.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            Log.i("TRACKER", "Task " + runningTasks + " now running");
            runningTasks++;

            i += j;
        }
    }

    public void TaskFinished(PremiereGetter getter) {
        // Handles special cases for unpredictable threads
        if (getter.Number < premiereList.size()) {
            // Thread spot has been placed, but not set
            Log.i("TRACKER", "Changed index " + getter.Number + " to list at task " + getter.Number);
            premiereList.set(getter.Number, getter.premieresList);
        } else if (getter.Number == premiereList.size()) {
            // Thread arriving at expected spot (add it to end of list)
            Log.i("TRACKER", "Added task " + getter.Number + " to end of list");
            premiereList.add(getter.Number, getter.premieresList);
        } else {
            // Thread arrived way too early, fill all spots ahead of it
            Log.i("TRACKER", "Filled list to " + getter.Number + " then added task " + getter.Number + " to end of list");
            while (getter.Number > premiereList.size()) {
                premiereList.add(new LinkedList<Premiere>());
            }

            premiereList.add(getter.Number, getter.premieresList);
        }

        runningTasks--;

        Log.i("TRACKER", runningTasks + " tasks left");

        if(runningTasks <=0)
        {
            Log.i("TRACKER", "All tasks have completed!");

            // Combine lists of all threads and send them to fragment
            List<Premiere> p = CombineLists();
            fragment.populateList(p);
            writePremieresToFile(p, filename, context);

            Log.i("TRACKER", "Premiere count: " + p.size());
        }
    }

    // Writes premieres to file
    private void writePremieresToFile(List<Premiere> premieres, String filename, Context context) {
        Log.d("INFO", "Attempting to write to file.");

        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            StringBuilder strBuilder = new StringBuilder();

            for (Premiere data : premieres) {
                strBuilder.append(data.name + ";" + data.channel + ";" + data.date + ";" + data.time + ";" + data.category + ";"
                        + data.genre + ";" + data.type + ";" + data.plot + "\n");

                String[] premiereData = {data.name, data.channel, data.date, data.time, data.category, data.genre, data.type, data.plot};
            }

            writer.write(strBuilder.toString());
            writer.close();

            Log.d("INFO", "Successfully written to " + filename);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    // Combines all the sub-lists in a 2D array (specifically for premieres)
    private List<Premiere> CombineLists() {
        List<Premiere> premieres = new LinkedList<>();

        for (int i = 0; i < premiereList.size(); i++) {
            premieres.addAll(premiereList.get(i));
        }

        return premieres;
    }
}
