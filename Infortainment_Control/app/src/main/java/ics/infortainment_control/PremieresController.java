package ics.infortainment_control;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kevin Maul on 3/17/2017.
 *
 * This class exists to kick start the
 */

public class PremieresController extends AsyncTask<Void, Void, List<Premiere>> {

    private Context context;
    private premieres_fragment fragment;

    private static final int NUMBER_OF_TASKS = 3;

    public PremieresController(premieres_fragment fragment, Context context) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    protected List<Premiere> doInBackground(Void... Params) {
        String filename = "testfile3"; /*"premieres-data-" +
                new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) +
                ".txt";*/

        return getPremieresData(filename);
    }

    @Override
    protected void onPostExecute(List<Premiere> result) {
        fragment.populateList(result);
    }

    // Retrieves list of premieres from file or web scraping if no updated file exists
    private List<Premiere> getPremieresData(String filename) {
        List<Premiere> premiereData = readPremieres(filename, context);

        if (premiereData.isEmpty()) {
            Log.d("INFO", "No data found in cache. Scraping new data");

            // Get new data
            try {
                String url = "http://www.tvtango.com/premieres";
                Document document = Jsoup.connect(url).get();

                Elements shows = document.select(".premier_show");

                PremiereWorkTracker tracker = new PremiereWorkTracker(fragment, NUMBER_OF_TASKS, filename, context);
                tracker.LaunchTasks(shows);
            }
            catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }
        else
            Log.d("INFO", "Data retrieved from file.");

        return premiereData;
    }

    // Returns list of premieres from text file
    private List<Premiere> readPremieres(String filename, Context context) {
        List<Premiere> ret = new LinkedList();

        Log.d("INFO", "Attempting to retrieve premieres from file");

        try {
            InputStream inputStream = context.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    List<String> premiereData = new LinkedList();
                    String[] splitString = receiveString.split(";");

//                    Log.d("READ", receiveString);

                    for (String str : splitString) {
                        premiereData.add(str);
                    }

                    ret.add(Premiere.createPremiere(premiereData));
                }

                inputStream.close();

//                Log.d("INFO", "Retrieved " + ret.size() + " premieres from " + filename);
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}